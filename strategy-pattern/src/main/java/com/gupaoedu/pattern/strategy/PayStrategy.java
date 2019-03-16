package com.gupaoedu.pattern.strategy;

public class PayStrategy {

    public static PayMent getPayment(String payType) {
        if ("AliPay".equals(payType)) {
            return new AliPay();
        } else if ("Apple".equals(payType)) {
            return new ApplePay();
        } else if ("Wechat".equals(payType)) {
            return new WechatPay();
        } else if ("UnionPay".equals(payType)) {
            return new UnionPay();
        } else {
            return new AliPay();

        }
    }
}
