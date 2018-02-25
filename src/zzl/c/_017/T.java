/**
 * 锁定莫对象o，如果o的属性发生改变，不影响锁的使用
 * 但是如果o变成另外一个对象，则锁定的对象发生改变
 *应该避免锁定对象的应用变成另外的对象 
 * @author zhangzeli
 */
package zzl.c._017;

import java.util.concurrent.TimeUnit;

public class T {
	
   Object o = new Object();
	void m () {
		synchronized (o) {
			while(true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}
	}
	 
	public static void main(String[] args) {
		T t = new T();
		//启动第一个线程
		new Thread(t::m,"t1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//创建第二个线程
		Thread t2 = new Thread(t::m,"t2");
		t.o=new Object();//锁的对象发生改变，如果注释这句话线程2永远得不到执行
		t2.start();
	}

}
