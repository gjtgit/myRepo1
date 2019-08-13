package com.gjt.jdk8;

import java.util.Optional;

public class TestOptional {

    public Integer sum(Optional<Integer> a, Optional<Integer> b){
        
        // Optional.orElse - 如果值存在，返回它，否则返回预设值
        Integer value1 = a.orElse(new Integer(1));  
        
        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return (value1 + value2);
    }
    
    public Integer get() {
        System.out.println("执行Get");
        return 111;
    }
    
    public static void main(String[] args) {
        TestOptional java8Tester = new TestOptional();
        Integer value1 = null;
        Integer value2 = new Integer(10);
          
        //1. Optional.ofNullable - 允许传值为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);
        
        //2. Optional.of - 如果传值的参数为 null，抛出异常 NullPointerException
        try {
            Optional<String> nullOpt = Optional.of(null);
            System.out.println(nullOpt);
        }catch(Exception e) {
            e.printStackTrace();
        }
          
        Optional<Integer> b = Optional.of(value2);
        
        // Optional.isPresent - 判断值是否存在
        System.out.println("第一个参数值存在: " + a.isPresent()+"  "+a+"  "+a.hashCode());
        System.out.println("第二个参数值存在: " + b.isPresent()+"  "+b+"  "+b.hashCode());
        
        System.out.println(java8Tester.sum(a,b));
        
        //3.Optional.empty创建一个空对象,等同 Optional.ofNullable(null);
        Optional<String> emp = Optional.empty();
        //如果值不存在,返回hashcode=0
        System.out.println(emp+"  "+emp.hashCode()+"   "+emp.equals(a));
        
        //a=null, orElse和orElseGet相同; a!=null, orElse仍会执行get
        Integer x1 = a.orElse(java8Tester.get());
        Integer x2 = a.orElseGet(()-> java8Tester.get());
        x1 = b.orElse(java8Tester.get()); //a!=null, orElse仍会执行get
        x2 = b.orElseGet(()-> java8Tester.get()); //a!=null, orElseGet 不会执行get
        
    }
    
}
