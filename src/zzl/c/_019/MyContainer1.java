/**
 * 曾经的面试题
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数小于5的时候，线程2给出提示并结束
 * 
 * 下面的程序能实现这个功能吗？
 * @author zhangzeli
 */
package zzl.c._019;

import java.util.ArrayList;
import java.util.List;


public class MyContainer1 {
	
    List lists = new ArrayList();
    
    public void add(Object o) {
    	lists.add(o);
    }
    
    public int size() {
    	return lists.size();
    }
    
    public static void main(String[] args) {
		MyContainer1 c = new MyContainer1();
		new Thread(()-> {
			for(int i =0;i<10;i++) {
				c.add(new Object());
				System.out.println("add"+i);
			}
		},"t1").start();
		new Thread(()-> {
			while(true) {
				if(c.size()==5) {
					
					break;
				}
			}
			System.out.println("结束");
		},"t2").start();
	}
}
