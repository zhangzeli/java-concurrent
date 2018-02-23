/**
 * synchronized关键字
 * 对某个对象加锁
 */
package zzl.c._002;

public class T {
	
	private int count =10;
	
	public void m() {
		synchronized (this) {//任务线程要执行下面的代码，必须先拿到（this）的锁
			count--;
			System.out.println(Thread.currentThread().getName()+"count = "+count);
		}
	}

}
