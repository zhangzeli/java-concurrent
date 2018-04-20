package com.test.version1synchronous;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhangzeli
 * @date 18:03 2018/4/20
 * <P></P>
 */
public class Tom implements Student {

    @Override
    public void calculation(String question, Callable callable) {
        System.out.println("老师的问题是:"+question);
        try {
            System.out.println("我在思考准备回答");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("思考完毕,回答老师结果:2");
        callable.tallAnswer("2");
    }

    public static void main(String[] args) {
        Student tom = new Tom();
        Teacher t = new Teacher(tom);
        t.ask();
    }
}
