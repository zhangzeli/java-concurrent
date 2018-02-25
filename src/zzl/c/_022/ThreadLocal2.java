/**
 * 有时候不想让改变让其他线程知道ThreadLocal
 * ThreadLocal是用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在于ThreadLocal，避免使用synchronized
 */
package zzl.c._022;

import java.util.concurrent.TimeUnit;

public class ThreadLocal2 {

	//volatile static Person person = new Person();
	static  ThreadLocal<Person1> tl = new ThreadLocal<>();
	public static void main(String[] args) {
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(tl.get());
		}).start();
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tl.set(new Person1());
		}).start();;
	}
}
class Person1{
	String name="zhangsan";
}
