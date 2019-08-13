package com.gjt.jdk8.funcinterface;

public class MyClass2 implements MyInterface {

    public MyClass2() {
        System.out.println("---MyClass1 constractor---");
    }
    
    @Override
    public void method() {
        System.out.println("---MyClass2 method---");
    }

}
