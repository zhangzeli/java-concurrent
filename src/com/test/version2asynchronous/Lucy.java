package com.test.version2asynchronous;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhangzeli
 * @date 19:19 2018/4/20
 * <P></P>
 */
public class Lucy implements Student {
    @Override
    public void calculation(String question, Callable callable) {
        System.out.println("老师的问题是:"+question);
        try {
            System.out.println("Lucy在思考准备回答");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Lucy思考完毕,回答老师结果:2");
        callable.tallAnswer("Lucy2");
    }
}
