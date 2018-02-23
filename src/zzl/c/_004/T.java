/**
 * synchronized关键字
 * 对某个对象加锁
 * @author zhangzeli
 */
package zzl.c._004;

public class T {
	
	private static int count =10;
	
	public synchronized static void m() {//等同于synchronized (xx.class) 
			count--;
			System.out.println(Thread.currentThread().getName()+"count = "+count);
	}
	
	public static void mm() {
		synchronized (T.class) {//静态的方法没有this
			count--;
		}
	}

}
