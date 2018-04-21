package concurrency;

/**
 * @author: zhangzeli
 * @date 19:10 2018/4/8
 * <P>测试Interrupt</P>
 */
public class Interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("interrupted");
                    break;
                }
                Thread.yield();
            }
        },"t1");
        t1.start();
        t1.interrupt();

        Thread t2 = new Thread(()->{
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    System.out.print("interrupted!!!");
                    break;
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("err");
                    Thread.currentThread().interrupt();
                }
                Thread.yield();
            }
        });

        t2.start();
        Thread.sleep(2000);
        t2.interrupt();
    }

}
