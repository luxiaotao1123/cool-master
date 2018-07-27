package com.cool.boot.common.learn.design.strategy;

/**
 * @author Vincent
 * 折扣策略接口
 */
public interface MemberStrategy {

    double calcPrice(double price);

}
