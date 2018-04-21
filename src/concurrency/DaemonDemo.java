package concurrency;

/**
 * @author: zhangzeli
 * @date 9:27 2018/4/9
 * <P>守护线程</P>
 */
public class DaemonDemo implements Runnable {

    @Override
    public void run() {
        while (true){
            System.out.println("i am alive");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new DaemonDemo());
        t.setDaemon(true);
        t.start();
        Thread.sleep(3000);
    }
}
