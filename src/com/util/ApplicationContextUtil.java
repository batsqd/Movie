package com.util;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

//ApplicationContext是一个重量级的对象，故保证只能有一个，要做成单例的
public final class ApplicationContextUtil {
     private static ApplicationContext ac=null;
     private ApplicationContextUtil(){
    	 
     }
     static{
    	 ac=new ClassPathXmlApplicationContext("applicationContext.xml");
     }
     public static ApplicationContext getApplicationContext(){
    	 return ac;
     }
     
}
