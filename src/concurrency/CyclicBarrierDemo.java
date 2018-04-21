package concurrency;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: zhangzeli
 * @date 11:49 2018/4/9
 * <P></P>
 */
public class CyclicBarrierDemo {

    public static  class Soldier implements Runnable{

        public String soldier;

        public final CyclicBarrier cyl ;

        Soldier(String sr,CyclicBarrier cy){
            soldier=sr;
            cyl=cy;
        }

        @Override
        public void run() {
            try {
                cyl.await();
                dowork();
                cyl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void dowork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier+"任务完成");
        }
    }

    public static class BarrierRun implements Runnable{
        boolean flag;
        int N;
        BarrierRun(boolean flag,int N){
            this.flag=flag;
            this.N = N;
        }

        @Override
        public void run() {
            if(flag){
                System.out.println("司令[士兵"+N+"个任务完成]");
            }else{
                System.out.println("司令[士兵"+N+"个集合完毕]");
                flag=true;
            }
        }
    }

    public static void main(String[] args) {
        final int N =10;
        Thread [] soldier =new Thread[N];
        boolean flag =false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N,new BarrierRun(flag,N));
        System.out.println("集合队伍");
        for(int i =0;i<N;i++){
            soldier[i] = new Thread( new Soldier("士兵"+i,cyclicBarrier));
            soldier[i].start();
        }
    }
}
