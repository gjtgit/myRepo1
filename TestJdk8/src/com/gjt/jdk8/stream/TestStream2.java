package com.gjt.jdk8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestStream2{
    
    static class Person{
        private String name="";
        private int age;
        
        public Person(String name,int age) {
            this.name=name;
            this.age=age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }

    public static void main(String[] args) {
        List<Person> l = Arrays.asList(
            new Person[]{
                new Person("apple", 5), new Person("apple", 5),
                new Person("banana", 7), new Person("banana", 7),
                new Person("banana", 7), new Person("grape",8)
            }
        );
        
        //groupby name,count 
        System.out.println("groupby name,输出每个group的总数-----------------------");
        Map<String, Long> map = l.stream().
                collect(Collectors.groupingBy(Person::getName,Collectors.counting()));
        map.forEach((k,v) -> System.out.println("k="+k+",v="+v));
        
        System.out.println("根据map值输出-------------------------");
        map.entrySet().stream().sorted(Map.Entry.comparingByValue())
        .forEachOrdered(System.out::println);
        
        System.out.println("根据map值倒序输出-------------------------");
        map.entrySet().stream().sorted(Map.Entry.<String,Long> comparingByValue().reversed())
        .forEachOrdered(System.out::println);
     
        System.out.println("groupby name,输出每个group的age总计-------------------------");
        Map<String, Integer> sumMap = l.stream().collect(
                (Collectors.groupingBy(Person::getName, Collectors.summingInt(Person::getAge))
                ));
        sumMap.forEach((k,v)->System.out.println("k="+k+",sum="+v));
        
        System.out.println("groupby name,总计每个group的元素-------------------------");
        Map<String, List<Person>> groupMap = 
                l.stream().collect(Collectors.groupingBy(Person::getName));
        groupMap.forEach((k,v)->System.out.println("k="+k+",v="+v));
        
        System.out.println("使用reduce累计全部的age值-------------------------");
        //T result = identity;
        //for (T element : this stream)
        //    result = accumulator.apply(result, element)
        //return result;
        //result = a = 0, element = b
        Integer totalScore1 = l.stream().map(Person::getAge)
                .reduce(0,(a,b) -> a + b);
        System.out.println(totalScore1);
        
        System.out.println("使用reduce累计全部的age值，没初始值时返回optinoal类型-------------------------");
        Optional<Integer> totalScore2 = l.stream().map(Person::getAge)
                .reduce((a,b) -> a + b);
        System.out.println(totalScore2.get());
        
        System.out.println("使用reduce找出最大的age值，没初始值时返回optinoal类型-------------------------");
        Optional<Integer> max = l.stream().map(Person::getAge).reduce(Integer::max);
        System.out.println("max="+max);
    }

}

