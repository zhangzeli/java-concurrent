package concurrency;

/**
 * @author: zhangzeli
 * @date 9:16 2018/4/9
 * <P></P>
 */
public class ThreadGroupName implements Runnable{

    @Override
    public void run() {
        String groupName = Thread.currentThread().getThreadGroup().getName()+"-"+Thread.currentThread().getName();
        while (true){
            System.out.println("I am" +groupName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("pront group");
        Thread t1 = new Thread(tg,new ThreadGroupName(),"t1");
        Thread t2 = new Thread(tg,new ThreadGroupName(),"t2");
        t1.start();
        t2.start();
        System.out.println(tg.activeCount());
        tg.list();
    }
}
