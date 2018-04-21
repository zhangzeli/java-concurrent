package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: zhangzeli
 * @date 11:28 2018/4/9
 * <P></P>
 */
public class SemapDemo implements Runnable {
    Semaphore s = new Semaphore(5);

    @Override
    public void run() {
        try {
            s.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+":done!");
            s.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        SemapDemo sd = new SemapDemo();
        for (int i=0;i<20;i++){
            executorService.execute(sd);
        }
    }
}
