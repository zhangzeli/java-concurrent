/*
 *reentrantLock 替代synchronized 
 *使用reentrantlock可以进行尝试锁定
 *
 *使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程interruptibly方法作出响应
 *在一个线程等待锁的过程中，可以被打断
 *
 *ReentrantLock还可以指定为公平锁
 */

package zzl.c._020;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock5 extends Thread{
	//参数为true表示为公平锁，请对比输出结果
	private static ReentrantLock lock = new ReentrantLock(true);
	
	public void run() {
		for(int i=0;i<100;i++) {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName()+"获得锁");
			} finally {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		ReentrantLock5 rl = new ReentrantLock5();
		Thread th1 = new Thread(rl);
		Thread th2 = new Thread(rl);
		th1.start();
		th2.start();
	}
}
