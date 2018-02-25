/*
 * 门闩
 * 使用await和countdown方法替代wati和notify
 * CountDownLatch不涉及锁定，当count的值为0时程序继续运行
 * 当不涉及同步，只是实际线程通信的时候用synchronize+wait/notify显得太重了
 * 这时可以考虑countdownlatch/cyslicbarrier/semaphore
 * @author zhangzeli
 */
package zzl.c._019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class MyContainer4 {
	
	//添加volatile，使t2能得到通知
   volatile List lists = new ArrayList();
    
    public  void add(Object o) {
    	lists.add(o);
    }
    
    public int size() {
    	return lists.size();
    }
    
    public static void main(String[] args) {
		MyContainer4 c = new MyContainer4();
		CountDownLatch latch = new CountDownLatch(1);
		
		new Thread(()-> {
			System.out.println("t2启动");
			if(c.size()!=5) {
				try {
					latch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("t2结束");
		},"t2").start();
		new Thread(()-> {
			for(int i =0;i<10;i++) {
				c.add(new Object());
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(c.size()==5) {
					latch.countDown();
				}
				System.out.println("add"+i);
			}
		},"t1").start();
	}
}
