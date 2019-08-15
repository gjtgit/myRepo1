package com.gjt.jdk8.funcinterface;

import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

public class TestFuncInterface {
    public void func(MyInterface myIn) {
        Function<MyInterface, MyInterface> f = Function.identity();
        myIn = (MyInterface) f.apply(myIn); 
        myIn.method();
    }
    
    public static Integer testFunc(Function<Integer,Integer> func, Integer number) {
        return func.apply(number);
    }
    
    public static void main(String[] args) {
        TestFuncInterface tester = new TestFuncInterface();
        MyClass1 class1 = new MyClass1();
        tester.func(class1);
        MyClass2 class2 = new MyClass2();
        tester.func(class2);
        
        //Function 接口, apply 类似于y=f(x)
        Function<Integer,Integer> f1 = x -> x+1;
        Integer r = f1.apply(5);
        System.out.println(r);
        
        //把function作为参数传入
        System.out.println(testFunc(f1,5));
        
        //------------------------------------------
        Function<Integer,Integer> f2 = x -> x*2;
        System.out.println("A=(5+1)*2:"+f2.apply(f1.apply(5)));
        //compose先执行传入的参数方法再执行本身的方法
        System.out.println("A=(5+1)*2:"+f2.compose(f1).apply(5));

        System.out.println("B=(5*2)+1:"+f1.apply(f2.apply(5)));
        //andThen先执行本身的方法再执行传入的参数方法
        System.out.println("B=(5*2)+1:"+f2.andThen(f1).apply(5));
        
        //------------------------------
        IntFunction<int[]> intFunc1 = (x) -> new int[x];
        IntFunction<int[]> intFunc2 = int[]::new;
        int[] intArray1 = intFunc1.apply(5);
        int[] intArray2 = intFunc2.apply(5);
        
        DoubleFunction<Double> dbFunc1 = (x) -> x/2;
        double dbRes = dbFunc1.apply(4.7);
        System.out.println(dbRes);
    }
    
}
