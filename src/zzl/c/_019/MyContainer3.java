/**
 * 曾经的面试题
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数小于5的时候，线程2给出提示并结束
 * 
 * 下面的程序能实现这个功能吗？
 * 
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu。而且当size等于6的时候才打印
 * @author zhangzeli
 */
package zzl.c._019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MyContainer3 {
	
   volatile List lists = new ArrayList();
    
    public  void add(Object o) {
    	lists.add(o);
    }
    
    public int size() {
    	return lists.size();
    }
    
    public static void main(String[] args) {
		MyContainer3 c = new MyContainer3();
		final Object o = new Object();
		new Thread(()-> {
			for(int i =0;i<10;i++) {
				c.add(new Object());
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o) {
					if(c.size()==5) {
						o.notify();
					}
				}
				System.out.println("add"+i);
			}
		},"t1").start();
		new Thread(()-> {
			synchronized (o) {
				System.out.println("t2启动");
				if(c.size()!=5) {
					try {
						o.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("t2结束");
				//o.notify();
			}
		},"t2").start();
	}
}
