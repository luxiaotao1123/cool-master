package com.cool.boot.common.learn.design.strategy.member;


import com.cool.boot.common.learn.design.strategy.MemberStrategy;

/**
 * @author Vincent
 * 高级会员折扣
 */
public class AdvancedMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double price) {

        return price * 0.5;
    }
}
