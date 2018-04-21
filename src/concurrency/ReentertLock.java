package concurrency;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: zhangzeli
 * @date 10:37 2018/4/9
 * <P></P>
 */
public class ReentertLock implements Runnable {
    static int j =0;
    ReentrantLock lock = new ReentrantLock(true);
    @Override
    public void run() {

        for (int i=0;i<1000000;i++){
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"获得锁");
                j++;
            }finally {
                lock.unlock();
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        ReentertLock t = new ReentertLock();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(j);
    }
}
