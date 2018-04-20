package com.test.version2asynchronous;

import java.util.ArrayList;
import java.util.List;
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
            System.out.println("TOM在思考准备回答");
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TOM思考完毕,回答老师结果:2");
        callable.tallAnswer("TOM2");
    }

    public static void main(String[] args) {
        Student tom = new Tom();
        Student mike = new Mike();
        Student lucy = new Lucy();
        List<Student> p = new ArrayList<>();
        p.add(tom);
        p.add(mike);
        p.add(lucy);
        Teacher t = new Teacher(p);
        t.ask();
    }
}
