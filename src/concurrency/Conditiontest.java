package concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: zhangzeli
 * @date 11:13 2018/4/9
 * <P></P>
 */
public class Conditiontest  implements  Runnable{
    public static ReentrantLock re = new ReentrantLock();
    public static Condition condition = re.newCondition();
    @Override
    public void run() {
        try{
            re.lock();
            condition.await();
            System.out.println("1 am gong on");

        }catch (InterruptedException e ){
            e.printStackTrace();
        }finally {
            re.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Conditiontest c = new Conditiontest();
        Thread t1 = new Thread(c);
        t1.start();
        Thread.sleep(2000);

        re.lock();
        condition.signal();
        re.unlock();
    }
}
