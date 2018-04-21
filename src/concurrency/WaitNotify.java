package concurrency;

/**
 * @author: zhangzeli
 * @date 19:48 2018/4/8
 * <P>
 *     wait 和 sleep的区别
 *     都可以让线程等待,但是wait可以被唤醒,而且会释放锁
 * </P>
 */
public class WaitNotify {
    final static Object o = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (o){
                System.out.println("t1 start");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 end");
            }
        },"t1").start();

        new Thread(()->{
            synchronized (o){
                System.out.println("t2 start");
                try {
                    o.notify();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 sleep  end");
            }
        },"t2").start();
    }
}
