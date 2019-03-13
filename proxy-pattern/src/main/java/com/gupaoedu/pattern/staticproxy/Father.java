package com.gupaoedu.pattern.staticproxy;


/**
 * 静态代理，被代理类发生改变时，代理类也必须发送改变
 *
 * 不符合开闭原则
 */
public class Father implements Person {

    private Person person;

    public Father(Person person) {
        this.person = person;
    }

    /**
     * 找女朋友
     */
    @Override
    public void findLove() {

        System.out.println("找对象");

        person.findLove();

        System.out.println("找到对象");


    }
}
