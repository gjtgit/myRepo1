package com.gjt.jdk8.funcinterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TestSupplierConsumer {
    
    public static void main(String[] args) {
        //------------------------------------------
        //supplier consumer接口
        Supplier<String> supplier = () -> "test supplier";
        System.out.println(supplier.get());
        
        Consumer<String> consumer = (str) -> System.out.println(str);
        consumer.accept("test consumer");
        Consumer<String> consumerAfter = (str) -> System.out.println("---and then "+str) ;
        System.out.println("andThen");
        consumer.andThen(consumerAfter).accept("test111");
        
        consumerAfter.accept("test222");
        
    }
}
