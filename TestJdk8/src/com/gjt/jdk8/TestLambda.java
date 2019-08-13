package com.gjt.jdk8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class TestLambda {
    interface MathOperation {
        int operation(int a, int b);
        default void defaultMethod() {
            System.out.println("java8 interface can has a defaultMethod");
        }
        static void staticMethod() {
            System.out.println("java8 interface  a staticMethod");
        }
    }
    
    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }
    
    interface GreetingService {
        void sayMessage(String message);
    }
    
    private static void printVal(Object val){
        System.out.print(val);
    }
    
    public static void main(String args[]){
        TestLambda tester = new TestLambda();
        
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        
        //1.8后接口可以有默认方法和静态方法
        addition.defaultMethod();
        MathOperation.staticMethod();
        
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        
        // 大括号中的返回语
        MathOperation multiplication = (int a, int b) -> { return a * b; };
        System.out.println("10 * 5 = " + tester.operate(10, 5, multiplication));
        
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));
        
        GreetingService greet = (msg) ->  System.out.println("hello"+msg); 
        greet.sayMessage("xxx");
        
        //静态方法的引用
        String[] strArray = new String[]{"7","5","6","4","2","1"};
        List<String> list = new ArrayList<String>();
        list.add("4");
        list.add("2");
        list.add("3");
        list.add("1");
        list.forEach( e -> System.out.print(e+";") );
        
        System.out.println("---------");
        list.forEach(TestLambda::printVal);
                
        //Consumer<String> methodParam = TestLambda::printVal; //方法参数
        //list.forEach(x -> methodParam.accept(x.toString()));//方法执行accept 
        
        System.out.println("---------");
        list.sort(new Comparator<String>() {  
            @Override  
            public int compare(String s1, String s2) {  
                return (s1.compareTo(s2));  
            }  
        });
        list.forEach(System.out::println);
        
        System.out.println("---------");
        //Comparator<String> sortByVal = (String s1, String s2) -> (s1.compareTo(s2));
        //list.sort(sortByVal);
        list.sort((String s1, String s2) -> (s1.compareTo(s2)));
        list.forEach(System.out::println);
    }
}
