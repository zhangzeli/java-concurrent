package concurrency;

/**
 * @author: zhangzeli
 * @date 20:03 2018/4/8
 * <P>等待线程结束(join)和谦让(yield)</P>
 */
public class JoinYield {
    volatile static int i = 0;

   static class C implements Runnable {

        @Override
        public void run() {
            for (i = 0; i < 10000000; i++) ;
        }
    }

    public static void main(String[] args) {

        Thread t = new Thread(new C());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
}
