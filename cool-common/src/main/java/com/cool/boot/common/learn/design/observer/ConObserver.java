package com.cool.boot.common.learn.design.observer;

public class ConObserver implements Observer {


    @Override
    public void update(String msg) {

        System.out.println(msg);
    }
}
