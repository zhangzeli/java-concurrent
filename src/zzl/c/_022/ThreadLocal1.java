/**
 * 写volatile不会发生问题
 * 不写可能发生问题也可能不发生问题
 * 
 * 有时候不想让改变让其他线程知道ThreadLocal
 */
package zzl.c._022;

import java.util.concurrent.TimeUnit;

public class ThreadLocal1 {

	volatile static Person person = new Person();
	
	public static void main(String[] args) {
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(person.name);
		}).start();
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			person.name="lisi";
		}).start();;
	}
}

class Person{
	String name="zhangsan";
}
