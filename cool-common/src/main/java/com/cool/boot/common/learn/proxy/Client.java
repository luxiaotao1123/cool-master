package com.cool.boot.common.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {


    public static void main(String[] args) {
        //    我们要代理的真实对象
        Subject coolSubject = new CoolSubject();

        //    我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new CoolProxy(coolSubject);


        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                coolSubject.getClass().getInterfaces(),
                handler);


        //result

        System.out.println(coolSubject.getClass().getName());
        System.out.println(subject.getClass().getName());
        System.out.println(subject.run("success"));

    }
}
