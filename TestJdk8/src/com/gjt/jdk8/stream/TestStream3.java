package com.gjt.jdk8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStream3 {
    public static void main(String[] args) {
        
        // ------------------------------------------
        //构造流的几种方法 
        //------------------------------
        //通过实现 Supplier 接口，你可以自己来控制流的生成。这种情形通常用于随机数、常量的 Stream，或者需要前后元素间维持着某种状态信息的 Stream
        System.out.println("使用generate ---------------------");
        Random seed = new Random();
        Supplier<Integer> random = () -> seed.nextInt(10);
        Stream.generate(random).limit(4).forEach(System.out::println);
        
        //-----------------------------
        //Stream.iterate 和reduce很像,接受一个种子值，和一个1元运算符（例如 f）。然后种子值成为 Stream 的第一个元素
        System.out.println("使用iterate ---------------------");
        Stream.iterate(1, n->n+2).limit(6).forEach(x->System.out.println(x));
        
        // 1 Stream接口的静态方法of
        Stream<String> stream = Stream.of("a", "b", "c");
        // Arrays
        String [] strArray = new String[] {"a", "b", "c", "a"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        
        // 2. Collection接口的stream方法
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();
        
        // ------------------------------------------
        // 流转换为其它数据结构
        // ------------------------------------------
        // 1. Array
        String[] strArray1 = list.stream().toArray(String[]::new);
        // 2. Collection
        List<String> list1 = list.stream().collect(Collectors.toList());
        List<String> list2 = list.stream().collect(Collectors.toCollection(ArrayList::new));
        Set<String> set1 = list.stream().collect(Collectors.toSet());
        Stack<String> stack1 = list.stream().collect(Collectors.toCollection(Stack::new));
        // 3. String
        String str = list.stream().collect(Collectors.joining()).toString();
        
        /*
        //数值流的构造,IntStream/LongStream/DoubleStream
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::print);
        System.out.println("\n使用range打印1-3,但不包括3-----");
        IntStream.range(1, 3).forEach(System.out::print);
        System.out.println("\n使用rangeClosed打印1-3,包括3-----");
        IntStream.rangeClosed(1, 3).forEach(System.out::print);
        System.out.println("\n使用Stream<Integer>代替IntStream-----");
        Stream<Integer> intStream = Stream.of(1,2,3);
        intStream.forEach(System.out::print);
        */
        
        // ------------------------------------------
        //当把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作
        // ------------------------------------------
        //intermediate/terminal, 只能有1个terminal
        //map 生成的是个 1:1 映射,作用就是把 input Stream 的每一个元素按规则映射成 output Stream 的另外一个元素
        List<String> upperList = list.stream().map(String::toUpperCase)
                .collect(Collectors.toList());
        
        //-----------------------------
        System.out.println("使用mapToInt/Long/Double把原始Stream转换成一个新的Stream,其中的元素都是int/long/double类型");
        List<String> strList = Arrays.asList(new String[]{"1","22","333","4444"});
        IntStream intStreamFromStr = strList.stream().mapToInt(String::length);
        intStreamFromStr.forEach(System.out::println);
        
        //flatMap 是一对多映射关系的, 不同的是其每个元素转换得到的是Stream对象，会把子Stream中的元素压缩到父集合中
        //flatMap 把input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，最终 output 的新 Stream 里面
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
                );
//        inputStream.forEach(System.out::println); //会打印出3个list
        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
//        outputStream.forEach(System.out::println); //没有list了会打印出全部数字
     
        //可以使用peek可替代forEach
//        inputStream.peek(System.out::println)
//            .flatMap(c->c.stream())
//            .peek(System.out::println)
//            .collect(Collectors.toList());
        
        System.out.println("使用 termimal 兼 short-circuiting--------------------");
        //before jdk1.8
        //if(list.get(0)!=null) System.out.println(list.get(0));
        list.stream().findFirst().ifPresent(System.out::println);
        
        //----------------------------------------
        //reduce是把 Stream 元素组合起来, reduce方法接受一个函数，这个函数有两个参数，第一个参数是上次函数执行的返回值（也称为中间结果），第二个参数是stream中的元素，这个函数把这两个值相加，得到的和会被赋值给下次执行这个函数的第一个参数
        String joinStr1 = list.stream().reduce("", String::concat);
        System.out.println("使用reduce连接字符,3中写法--------------------");
        System.out.println("joinStr1="+joinStr1);
        Optional<String> joinStr2 = list.stream().reduce(String::concat); //没初始值的结果可能是null所以返回optional
        Optional<String> joinStr3 = list.stream().reduce((a,b) -> a+b);
        System.out.println("joinStr2="+joinStr2.get());
        System.out.println("joinStr3="+joinStr3.get());
        
        System.out.println("使用reduce计算总和--------------------");
        int sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();  
        int sumValue2 = Stream.of(1, 2, 3, 4).reduce((a,b)->a+b).get(); //2元运算符
        System.out.println(sumValue+","+ sumValue2);
        
        
    }
}
