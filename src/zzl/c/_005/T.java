/**
 * 分析一下这个程序的输出
 * @author zhangzeli
 */
package zzl.c._005;

public class T implements Runnable{
	
	private volatile int count =10;
	
	public synchronized  void run() {
			count--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" count = "+count);
	}
	
	public static void main(String[] args) {
		 T t = new T();
		 for (int i = 0; i < 5; i++) {
			new Thread(t,"THREAD"+i).start();
		}
	}

}
