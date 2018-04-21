package concurrency;

/**
 * @author: zhangzeli
 * @date 9:33 2018/4/9
 * <P>测试线程优先级</P>
 */
@SuppressWarnings("all")
public class PriorityDemo {

    static int count = 0;
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (PriorityDemo.class){
                while (true){
                    count++;
                    if(count>10000000){
                        System.out.println("t1 complete");
                        break;
                    }
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (PriorityDemo.class){
                while (true){
                    count++;
                    if(count>10000000){
                        System.out.println("t2 complete");
                        break;
                    }
                }
            }
        });
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
    }
}
