package com.gjt.jdk8.funcinterface;

public class MyClass1 implements MyInterface {

    public MyClass1() {
        System.out.println("---MyClass1 constractor---");
    }
    
    @Override
    public void method() {
        System.out.println("---MyClass1 method---");
    }

}
