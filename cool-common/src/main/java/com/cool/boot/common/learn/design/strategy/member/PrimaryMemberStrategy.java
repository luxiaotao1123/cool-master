package com.cool.boot.common.learn.design.strategy.member;


import com.cool.boot.common.learn.design.strategy.MemberStrategy;

/**
 * @author Vincent
 * 初级会员折扣
 */
public class PrimaryMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double price) {

        return price;
    }
}
