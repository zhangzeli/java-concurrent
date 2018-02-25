/**
 * synchronized优化
 * 同步代码块的语句越少越好
 * 比较m1和m2
 * @author zhangzeli
 */
package zzl.c._016;

import java.util.concurrent.TimeUnit;

public class T {
	
    int count =0;
	synchronized void m1() {
		//do sth need not syn
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//业务逻辑中只有下面这句需要sync,这是不应该给整个方法上锁
		count ++;
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	 void m2() {
		//do sth need not syn
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//业务逻辑中只有下面这句需要sync,这是不应该给整个方法上锁
		//采用细腻度的锁，可以使线程征用的时间变短，从而提高概率
		synchronized(this){
			count ++;
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		T t = new T();
	
	}

}
