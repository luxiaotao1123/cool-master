package com.cool.boot.common.learn.design.strategy.member;


import com.cool.boot.common.learn.design.strategy.MemberStrategy;

/**
 * @author Vincent
 * 中级会员折扣
 */
public class IntermediateMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double price) {

        return price * 0.8;
    }
}
