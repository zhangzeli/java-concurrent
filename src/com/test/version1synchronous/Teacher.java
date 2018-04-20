package com.test.version1synchronous;

/**
 * @author: zhangzeli
 * @date 17:56 2018/4/20
 * <P></P>
 */
public class Teacher implements Callable {
    private Student student;

    public Teacher(Student student){
        this.student = student;
    }

    public void ask(){
        System.out.println("老师问学生:1+1");
        student.calculation("1+1",this);
        System.out.println("问题问完");
    }

    @Override
    public void tallAnswer(String msg) {
        System.out.println("老师得到答案:"+msg);
    }
}
