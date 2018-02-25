/**
 * 线程在执行的过程中，如果出现异常，默认情况会释放锁
 * 所以,在并发处理的过程中，有异常要多加小心不然可能发生不一致的情况
 * 比如，在一个web app处理过程中，多个servlet线程共同访问同一个资源，这时如果异常处理不合适
 * 在第一个线程中抛出的异常，其他线程就会进入同步代码区，有可能会访问到异常产生时的数据
 * 
 * @author zhangzeli
 */
package zzl.c._011;

public class T {
	int count = 0;

	public synchronized void m() {
		System.out.println(Thread.currentThread().getName() + " start");
		while (true) {
			count++;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (count == 5) {
				int i = 1 / 0;// 此时将抛出异常，要想不被释放，可以在这里进行异常处理
			}
		}
	}

	public static void main(String[] args) {
		T t = new T();
		new Thread(() -> t.m(), "t1").start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(() -> t.m(), "t2").start();
	}

}
