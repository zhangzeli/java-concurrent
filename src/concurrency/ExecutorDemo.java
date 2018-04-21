package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhangzeli
 * @date 14:19 2018/4/9
 * <P></P>
 */
public class ExecutorDemo {
    //Executors
    public static void main(String[] args) {
        //test2();
        //test3();
        test4();
    }

    /**
     * 测试newCachedThreadPool
     */
    public static void test1() {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                System.out.println(System.currentTimeMillis() + ":Thread Id=" + Thread.currentThread().getId());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }

    /**
     * 测试schedule
     */
    public static void test2() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < 10; i++) {
            scheduledExecutorService.schedule(() -> {
                while (true) {
                    try {
                        System.out.println(System.currentTimeMillis() + ":Thread Id=" + Thread.currentThread().getId());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, 3L, TimeUnit.SECONDS);
        }
    }

    /**
     * 测试scheduleWithFixedDelay
     */
    public static void test3() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        //for (int i = 0; i < 10; i++) {
            scheduledExecutorService.scheduleWithFixedDelay(() -> {
                try {
                    System.out.println(System.currentTimeMillis() + ":Thread Id=" + Thread.currentThread().getId());
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 0L,2L, TimeUnit.SECONDS);
        //}
    }

    /**
     * 测试scheduleWithFixedDelay
     */
    public static void test4() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        //for (int i = 0; i < 10; i++) {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                System.out.println(System.currentTimeMillis() + ":Thread Id=" + Thread.currentThread().getId());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0L,1L, TimeUnit.SECONDS);
        //}
    }
}
