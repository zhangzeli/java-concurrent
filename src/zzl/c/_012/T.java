/**
 * volatile关键字
 * @author zhangzeli
 */
package zzl.c._012;

public class T {
	
	/*volatile*/ boolean running =true;//对比一有无volatile的运行情况
	void m() {
		System.out.println("m start");
		while(running){
			
		}
		System.out.println("m end");
	}

	public static void main(String[] args) {
		T t = new T();
		new Thread(() -> t.m(), "t1").start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.running=false;
	}

}
