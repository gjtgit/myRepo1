package com.gjt.jdk8.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestStream {
    public static void main(String[] arg) {
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        
        List<String> filterEmpty = list.stream()
                .filter(e -> !e.isEmpty())
                .collect(Collectors.toList());
        System.out.println("去除空字符串--------------------");
        System.out.println(filterEmpty);
        
        //filter,collect,limit,forEach
        List<String> filtered = list.stream()
                .filter(e -> !e.isEmpty())
                .collect(Collectors.toList());
        filtered.forEach(System.out::print);
        System.out.println(filtered);
        System.out.println(filtered.stream().limit(3).collect(Collectors.toList()));

        System.out.println("collectors.joining 拼接--------------------");
        String filteredNew = list.stream()
                .filter(e -> !e.isEmpty())
                .collect(Collectors.joining(","));
        System.out.println(filteredNew);
        
        System.out.println("map 转成1对1映射,每个求平方--------------------");
        // map,sorted
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().map(i -> i*i).distinct().collect(Collectors.toList());
        squaresList.stream().sorted().forEach(System.out::println);
        
        System.out.println("parllelStream 不按顺序处理---------------");
        //parallelStream 会导致不按原来的顺序处理,可能任何顺序,可以用forEachOrdered保持原来顺序
        numbers.parallelStream().sorted().forEach(System.out::println);  
        //numbers.parallelStream().sorted().forEachOrdered(System.out::println);  
        
        System.out.println("使用reduce连接字符-------------------");
        //reduce是把 Stream 元素组合起来,它提供一个起始值,然后依照运算规则 BinaryOperator和前面 Stream 的第一个、第二个、第 n 个元素组合
        String joinStr = list.stream().reduce("", String::concat);
        System.out.println(joinStr);
        Optional<String> joinStr2 = list.stream().reduce(String::concat); //结果可能是null所以返回optional
        
        
        //statistic
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的是: " + stats.getMax());
        System.out.println("列表中最小的是 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        
    }

}
