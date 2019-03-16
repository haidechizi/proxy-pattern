package com.gupaoedu.pattern.strategy;

public class PayTest {

    public static void main(String[] args) {
        String payType = "AliPay";
        PayMent payMent = PayStrategy.getPayment(payType);
        payMent.pay();
    }
}
