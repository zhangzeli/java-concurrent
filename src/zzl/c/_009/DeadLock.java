package zzl.c._009;

public class DeadLock implements Runnable{
	final static Object o1=new Object();
	final static Object o2 = new Object();
    boolean flag;
    
    public DeadLock(Boolean flag) {
    	this.flag=flag;
    }
	public void run() {
		if(flag) {
			synchronized (o1) {
				System.out.println("if o1 lock");
				synchronized (o2) {
					System.out.println("if o2 lock");
				}
			}
		}else {
			synchronized (o2) {
				System.out.println("if o2 lock");
				synchronized (o1) {
					System.out.println("if o1 lock");
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		DeadLock lock1 = new DeadLock(false);
		DeadLock lock2 = new DeadLock(true);
		new Thread(lock1).start();
		new Thread(lock2).start();
	}
	
	/**
	 * 分析程序执行过程
	 * 两个线程同步开启，进而执行run()根据条件判断。线程1获得o2的锁，继续执行，此时如果能继续拿到对象o1的锁
	 * rub()执行完毕，线程1就可以顺利结束，然后释放对两个对象的锁。现在的问题是如果线程一在获得o1的锁之前，
	 * 线程2就已经获得对象o1的锁，那么线程2就会寻求对象o2的锁能，显然无法得到，这就导致两个线程都无法释放自己的锁
	 */
	 

}
