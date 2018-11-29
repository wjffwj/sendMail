package com.wjf.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
        KaoShi k= (KaoShi) ac.getBean("kaoShi");
        k.doTest(k.getStudent());

    }
}
