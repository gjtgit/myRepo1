package com.gjt.jdk8.funcinterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TestFuncInterface {
    public static void main(String[] args) {
        TestFuncInterface tester = new TestFuncInterface();
        MyClass1 class1 = new MyClass1();
        tester.func(class1);
        MyClass2 class2 = new MyClass2();
        tester.func(class2);
        
        //------------------------------------------
        //Function 接口, apply 类似于y=f(x)
        Function<Integer,Integer> f1 = x -> x+1;
        Integer r = f1.apply(5);
        System.out.println(r);
        
        //把function作为参数传入
        System.out.println(testFunc(f1,5));
        
        //------------------------------------------
        Function<Integer,Integer> f2 = x -> x*2;
        System.out.println("A:"+f2.apply(f1.apply(5)));
        //compose先执行传入的参数方法再执行本身的方法
        System.out.println("A:"+f2.compose(f1).apply(5));

        System.out.println("B:"+f1.apply(f2.apply(5)));
        //andThen先执行本身的方法再执行传入的参数方法
        System.out.println("B:"+f2.andThen(f1).apply(5));
        
        //------------------------------
        IntFunction<int[]> intFunc1 = (x) -> new int[x];
        IntFunction<int[]> intFunc2 = int[]::new;
        intFunc1.apply(5);
        intFunc2.apply(5);
        
        //------------------------------------------
        //supplier consumer接口
        Supplier<String> supplier = () -> "test supplier";
        System.out.println(supplier.get());
        
        Consumer<String> consumer = (str) -> System.out.println(str);
        consumer.accept("test consumer");
        Consumer<String> consumerAfter = (str) -> System.out.println("---and then "+str) ;
        consumer.andThen(consumerAfter).accept("test111");
        consumer.accept("test222");consumerAfter.accept("test222");
        
        // -----------------------------
        // Predicate接口
        List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Predicate<Integer> pd1 = x -> x > 3;
        Predicate<Integer> pd2 = x -> x < 9;
        Predicate<Integer> pd3 = x -> x %2 == 0 ;
        Predicate<Integer> pd4 = x -> x %2 == 0 ;
        System.out.println("stream的filter使用predicate进行判断");
        intList.stream().filter(pd1.and(pd2).and(pd3))
            .collect(Collectors.toList())
            .forEach(System.out::print);
        System.out.println("\n使用negate返回predicate的否定");
        intList.stream().filter(pd1.and(pd2).and(pd3.negate()))
            .collect(Collectors.toList())
            .forEach(System.out::print);
        System.out.println("\n使用isEqual返回类型也是Predicate,可以看作==");
        intList.stream().filter(pd1.and(pd2).and(pd3.negate()).and(Predicate.isEqual(7)))
        .collect(Collectors.toList())
        .forEach(System.out::print);
        
        
    }
    
    public void func(MyInterface myIn) {
        Function f = Function.identity();
        myIn = (MyInterface) f.apply(myIn); 
        myIn.method();
    }
    
    public static Integer testFunc(Function<Integer,Integer> func, Integer number) {
        return func.apply(number);
    }
}
