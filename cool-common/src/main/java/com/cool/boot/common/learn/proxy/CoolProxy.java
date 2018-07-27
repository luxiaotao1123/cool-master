package com.cool.boot.common.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CoolProxy implements InvocationHandler {

    private Subject subject;

    public CoolProxy(Subject subject){
        this.subject = subject;
    }

    /**
     * java动态代理
     * @param proxy 代理对象
     * @param method    代理对象方法
     * @param args  代理对象方法参数
     * @return  result
     * @throws Throwable null
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        return method.invoke(subject, args);
    }


}
