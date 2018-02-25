/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程已经10个消费者线程的阻塞调用
 * 
 * 使用 wait和notify/notifyAll来实现
 * 
 * 使用reentrantlock的 condition
 */
package zzl.c._021;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer2<T> {
	final private LinkedList<T> lists = new LinkedList<>();
	final private int MAX=10;
	private  int count =0;
	ReentrantLock lock = new ReentrantLock();
	Condition producer = lock.newCondition();
	Condition consumer = lock.newCondition();
	public  void put(T t) {
		lock.lock();
		try {
			while(lists.size()==MAX) {//为什么用while而不用if
				producer.await();
			}
			lists.add(t);
			//System.out.println(Thread.currentThread().getName()+"生产");
			++count;
			consumer.signalAll();//通知消费者进行消费
		}catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public   T get() {
		T t = null;
		lock.lock();
		try {
			while(lists.size()==0) {
				consumer.await();
			}
			count--;
			t = lists.removeFirst();
			producer.signalAll(); //通知生产者
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return t;
	}
	
	public int getCount() {
		return count;
	}
	
	public static void main(String[] args) {
		MyContainer2<Integer> c = new MyContainer2<>();
		for(int i = 0;i<2;i++) {
			new Thread(()->{
				for(int j =0;j<25;j++) {
					c.put(j);
				}
			},"生产者"+i).start();
		}
		for(int i = 0;i<10;i++) {
			new Thread(()->{
				for(int j=0;j<5;j++) {
					System.out.println(c.get());
				}
			},"消费者"+i).start();
		}
	}
}
