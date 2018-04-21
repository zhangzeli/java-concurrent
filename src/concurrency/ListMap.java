package concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @author: zhangzeli
 * @date 10:00 2018/4/9
 * <P>并发下的容器</P>
 */
public class ListMap {
    static ArrayList<Integer> ai = new ArrayList<>(10);
    static Vector<Integer> vector = new Vector<>(10);
    static Map<String,String> map = new HashMap<>();

    static class AddThread implements Runnable{
        @Override
        public void run() {
            for (int i=0;i<10000000;i++){
                vector.add(i);
            }
        }
    }
    static class AddThread2 implements Runnable{
        int start =0;
        public AddThread2(int start){
            this.start = start;
        }
        @Override
        public void run() {
            for (int i=start;i<10000000;i+=2){
                map.put(Integer.toString(i),Integer.toBinaryString(i));
            }
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(new AddThread());
//        Thread t2 = new Thread(new AddThread());
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println(vector.size());
//    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread2(0));
        Thread t2 = new Thread(new AddThread2(1));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
    }
}
