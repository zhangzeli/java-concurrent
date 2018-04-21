package concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: zhangzeli
 * @date 15:35 2018/4/11
 * <P></P>
 */
public class AskThread implements Runnable {
    CompletableFuture<Integer> re =null;
    public AskThread(CompletableFuture<Integer> re ){
        this.re =re;
    }
    @Override
    public void run() {
        int myre =0;
        try {
            myre =re.get() * re.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("====="+myre);
    }

    public static void main(String[] args) {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        future.complete(60);
        System.out.println("111");
    }
}
