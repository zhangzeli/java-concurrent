/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程已经10个消费者线程的阻塞调用
 * 
 * 使用 wait和notify/notifyAll来实现
 */
package zzl.c._021;

import java.util.LinkedList;

public class MyContainer1<T> {
	final private LinkedList<T> lists = new LinkedList<>();
	final private int MAX=10;
	private  int count =0;
	
	public synchronized void put(T t) {
		while(lists.size()==MAX) {//为什么用while而不用if
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lists.add(t);
		//System.out.println(Thread.currentThread().getName()+"生产");
		++count;
		this.notifyAll();//通知消费者进行消费
	}
	
	public  synchronized T get() {
		T t = null;
		while(lists.size()==0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count--;
		t = lists.removeFirst();
		//System.out.println(Thread.currentThread().getName()+"消费");
		this.notifyAll(); //通知生产者
		return t;
	}
	
	public int getCount() {
		return count;
	}
	
	public static void main(String[] args) {
		MyContainer1<Integer> c = new MyContainer1<>();
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
					System.out.println(Thread.currentThread().getName()+"  "+c.get());
				}
			},"消费者"+i).start();
		}
	}
}
