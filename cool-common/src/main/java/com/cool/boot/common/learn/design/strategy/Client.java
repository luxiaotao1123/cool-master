package com.cool.boot.common.learn.design.strategy;


import com.cool.boot.common.learn.design.strategy.member.AdvancedMemberStrategy;

/**
 * 用户
 */
public class Client {

    public static void main(String[] args) {

        System.out.println(new Price(new AdvancedMemberStrategy()).quote(300));

    }
}
