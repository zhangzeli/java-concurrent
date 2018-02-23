/**
 * synchronized关键字
 * 对某个对象加锁
 * @author zhangzeli
 */
package zzl.c._003;

public class T {
	
	private int count =10;
	
	public synchronized void m() {//等同于在方法的代码执行时要synchronized（this）
		synchronized (this) {//任务线程要执行下面的代码，必须先拿到（this）的锁
			count--;
			System.out.println(Thread.currentThread().getName()+"count = "+count);
		}
	}

}
