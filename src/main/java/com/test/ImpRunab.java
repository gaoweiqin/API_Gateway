package com.test;

public class ImpRunab implements Runnable {

    @Override
    public void run() {
        System.out.println("实现Runnable接口");
    }

    public static void main(String[] args) {
//        ImpRunab impRunab = new ImpRunab(); 错误写法
        Thread thread = new Thread(new ImpRunab());
        thread.start();
        //也可以实现Runnable创建匿名类
        Runnable runn = new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名类接口");
            }
        };
        Thread thr = new Thread(runn);
        thr.start();
    }

}
