package com.wjf.test;

public class KaoShi {
    private Student student;

    public void doTest(Student student){
        System.out.println("学号"+student.getId() +"姓名："+student.getName()+"  正在考试");
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
