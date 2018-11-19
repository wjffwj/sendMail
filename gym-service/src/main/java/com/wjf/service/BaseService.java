package com.wjf.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public abstract class BaseService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public BaseService() {
    }

    public static void info(String str){
        System.out.println(str);
    }
}