/*
 *reentrantLock 替代synchronized 
 *使用reentrantlock可以进行尝试锁定
 */

package zzl.c._020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock3 {
	Lock lock = new ReentrantLock();
	void m1() {
		try {
			lock.lock();
			for(int i = 0;i<10;i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	/**
	 * 使用tyrlock进行尝试锁定，不管锁定与否，方法都将继续执行
	 * 可以根据tryLock的放回值来判定是否锁定
	 * 也可以指定tryLock,由于tryLock(time)抛出异常，所以要注意unlock的处理
	 */
	void m2() {
		/*lock.lock();
		System.out.println("m2...");
		lock.unlock();*/
		boolean locked =false;
		try {
			locked = lock.tryLock(5,TimeUnit.SECONDS);
			System.out.println("m2...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			if(locked) {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		ReentrantLock3 r1 = new ReentrantLock3();
		new Thread(r1::m1).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r1::m2).start();
	}
}
