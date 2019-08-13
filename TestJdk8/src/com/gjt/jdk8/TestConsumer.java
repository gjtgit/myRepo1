package com.gjt.jdk8;

import java.util.function.Consumer;

public class TestConsumer {
    public static void main(String[] args) {
        Consumer<Integer> consumer = (x) -> {
            int num = x * 2;
            System.out.println(num);
        };
        Consumer<Integer> consumer1 = (x) -> {
            int num = x * 3;
            System.out.println(num);
        };
        
        //该函数式接口的唯一的抽象方法,接收一个参数,没有返回值
        consumer.accept(1);
        //在执行完调用者方法后再执行传入参数的方法
        consumer.andThen(consumer1).accept(10);
    }
}
