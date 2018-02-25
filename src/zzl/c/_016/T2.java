/**
 * 当使用多个方法时也不能保证原子性
 * @author zhangzeli
 */
package zzl.c._016;

import java.util.concurrent.atomic.AtomicInteger;

public class T2 {
	
	int count =0;
    public synchronized int increase(){  
        return count++;  
    }  
      
    public static void main(String args[]){  
        long start = System.currentTimeMillis();  
          
       T2 test = new T2();
        for( int i=0;i< 1000000000;i++){  
            test.increase();  
        }  
        long end = System.currentTimeMillis();  
        System.out.println("time elapse:"+(end -start));  
        System.out.println(test.count);
          
        long start1 = System.currentTimeMillis();  
          
        AtomicInteger atomic = new AtomicInteger(0);  
          
        for( int i=0;i< 1000000000;i++){  
            atomic.incrementAndGet();  
        }  
        long end1 = System.currentTimeMillis();  
        System.out.println("time elapse:"+(end1 -start1) );  
        System.out.println(test.count);
    }  

}
