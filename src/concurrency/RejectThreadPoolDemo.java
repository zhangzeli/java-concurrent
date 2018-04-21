package concurrency;


import java.util.concurrent.*;

/**
 * @author: zhangzeli
 * @date 15:32 2018/4/9
 * <P>队列满了的处理策略</P>
 */
public class RejectThreadPoolDemo {

    static Runnable ar = () -> {
        System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    public static void main(String[] args) throws InterruptedException {
        int i = Runtime.getRuntime().availableProcessors();
        //test2();
       //System.out.println(i);
        test3();
    }

    /**
     * 自定义策略
     */
    public static  void  test1(){
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L
                , TimeUnit.SECONDS
                , new LinkedBlockingDeque<Runnable>(10)
                , Executors.defaultThreadFactory()
                , (ra, ee) -> {
            System.out.println(ra.toString() + " is discard");
        });
        while (true){
            es.submit(ar);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试执行策略
     */
    public static  void  test2(){
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0l, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行:"+r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成:"+r);
            }

            @Override
            protected void terminated() {
                System.out.println("退出");
            }
        };
        for(int i=0;i<5;i++){
            Runnable r1 = ()->{
                System.out.println("aaa");

            };
            threadPoolExecutor.execute(r1);
        }
    }

    /**
     * 测试堆栈情况
     */
    public static  void  test3(){
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L
                , TimeUnit.SECONDS
                , new LinkedBlockingDeque<Runnable>(10)
                , Executors.defaultThreadFactory()
                , (ra, ee) -> {
            System.out.println(ra.toString() + " is discard");
        });
        for (int i=0;i<5;i++){
            Future submit = es.submit(new DivTask(100, i));
            try {
                Object o = submit.get();
                System.out.println(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        es.shutdown();
    }



}

class DivTask implements Runnable {

    int a,b;

    DivTask(int a ,int b){
        this.a=a;
        this.b=b;
    }
    @Override
    public void run() {
        double re = a/b;
        System.out.println(re);
    }
}
