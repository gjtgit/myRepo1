package com.gjt.jdk8.funcinterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TestPredicate {
    
    public static void main(String[] args) {
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
}
