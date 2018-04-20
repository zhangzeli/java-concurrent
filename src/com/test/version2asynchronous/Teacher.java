package com.test.version2asynchronous;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: zhangzeli
 * @date 17:56 2018/4/20
 * <P></P>
 */
public class Teacher implements Callable {
    ExecutorService executorService;
    private List<Student> student;

    public Teacher(List<Student> student){
        executorService = Executors.newFixedThreadPool(3);
        this.student = student;
    }

    public void ask(){

        System.out.println("老师问学生:1+1");
        student.forEach(
            c->
            executorService.execute(()->{
                c.calculation("1+1",this);
            })
        );
        executorService.shutdown();
        System.out.println("问题问完,等待结果,可以做其他事情");
    }

    @Override
    public void tallAnswer(String msg) {
        System.out.println("老师得到答案:"+msg);
    }
}
