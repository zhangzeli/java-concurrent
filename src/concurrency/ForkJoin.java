package concurrency;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author: zhangzeli
 * @date 19:00 2018/4/9
 * <P></P>
 */
public class ForkJoin {
}

class CountTask extends RecursiveTask<Long>{
    private static  final int THRESHOLD =10000;
    Long start ;
    Long end;

    CountTask(Long start,Long end){
        this.start =start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum =0;
        boolean canCompute = (end-start)<THRESHOLD;
        if(canCompute){
            for(long i =start;i<end;i++){
                sum+=i;
            }
        }else{
            long step=(end-start)/100;
            ArrayList<CountTask> subTasks = new ArrayList<>();
            long pos = start;
            for (int i =0;i<100;i++){
                long lastone = pos+step;
                if(lastone>end){
                    lastone=end;
                }
                CountTask subTask = new CountTask(pos,lastone);
                pos+=step+1;
                subTasks.add(subTask);
                subTask.fork();
            }
            for(CountTask c: subTasks){
               sum +=c.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool fj = new ForkJoinPool();
        CountTask c = new CountTask(0L,200000L);
        ForkJoinTask<Long> submit = fj.submit(c);
        Long aLong = null;
        try {
            aLong = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(aLong);
        fj.shutdown();
    }
}
