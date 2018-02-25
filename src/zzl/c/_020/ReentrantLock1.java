/**
 * 线程一执行玩在执行线程二,这里是复习synchronized最原始的语义
 * 使用reentrantlock可以完成同样的功能，需要注意的是必须要手动释放锁
 * 使用synchronized遇到异常jvm会自动释放锁，但是lock必须手动释放锁，
 * 因此常在finally中进行锁定
 */
package zzl.c._020;

import java.util.concurrent.TimeUnit;

public class ReentrantLock1 {

	synchronized void m1() {
		for(int i = 0;i<10;i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}
	synchronized void m2() {
		System.out.println("m2...");
	}
	
	public static void main(String[] args) {
		ReentrantLock1 r1 = new ReentrantLock1();
		new Thread(r1::m1).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r1::m2).start();
	}
}
