package com.test.version2asynchronous;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhangzeli
 * @date 19:17 2018/4/20
 * <P></P>
 */
public class Mike implements Student {
    @Override
    public void calculation(String question, Callable callable) {
        System.out.println("老师的问题是:"+question);
        try {
            System.out.println("Mike在思考准备回答");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Mike思考完毕,回答老师结果:2");
        callable.tallAnswer("Mike2");
    }
}
