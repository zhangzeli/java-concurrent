/**
 * 当使用多个方法时也不能保证原子性
 * @author zhangzeli
 */
package zzl.c._015;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T2 {
	
	/*volatile int count =0;*/
	AtomicInteger count = new AtomicInteger(0);
	/*synchronized*/ void m() {
		for(int i =0;i<10000;i++) {
			if(count.get()<1000) {
				count.incrementAndGet();
			}
		}
	}
	public static void main(String[] args) {
		T2 t = new T2();
		List<Thread> threads = new ArrayList<>();
		for(int i =0;i<10;i++) {
			threads.add(new Thread(t::m,"thread"+i));
		}
		threads.forEach((o)->o.start());
		threads.forEach((o)->{
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println(t.count);
		}

}
