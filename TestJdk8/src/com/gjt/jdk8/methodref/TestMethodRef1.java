package com.gjt.jdk8.methodref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// 静态方法引用
public class TestMethodRef1 {
    
    public static int compareMC(MyStringOps a, MyStringOps b) {
        return (a.getVal() - b.getVal());
    } 
    
    public static String stringOp(StringFunc sf, String s) {
        return sf.func(s);
    }
    
    public static void main(String[] args) {
        String inStr = "1234567";
        
        //1.静态方法引用, CLass::static_method,要求接受一个class类型参数
        //MyStringOps::strReverse 相当于实现了接口方法func() 
        //并在func()中做了MyStringOps.strReverse()操作
        String outStr = stringOp(MyStringOps::staticStrReverse, inStr);
        System.out.println("Original string: " + inStr);
        System.out.println("String reserved: " + outStr);
        
        //静态方法引用,CLass::static_method,class参数TestMethodRef1
        ArrayList<MyStringOps> a1 = new ArrayList<MyStringOps>();
        a1.add(new MyStringOps(1));
        a1.add(new MyStringOps(4));
        a1.add(new MyStringOps(2));
        MyStringOps maxValObj = Collections.max(a1, TestMethodRef1::compareMC);
        System.out.println("Maximum value is: " + maxValObj.getVal());
        
        //2.特定类的任意对象的方法引用,Class::method
        a1.forEach(MyStringOps::printVal);
        
        //2.特定类的任意对象方法引用,Class::method
        String[] stringArray = { "3", "4", "1", "2", "5"};
        Arrays.sort(stringArray, String::compareToIgnoreCase);
        for (int s = 0; s < stringArray.length; s++) {
            System.out.print(s);
        }

        //3.特定对象的方法引用,instance::method,要求方法接受一个参数
        MyStringOps strOps = new MyStringOps(1);//实例对象
        //strOps::strReverse  相当于实现了接口方法func() 
        String outStr2 = stringOp(strOps::strReverse, inStr);
        
        //4.构造方法引用,Class::new
        MyStringFunc myStringFunc = MyStringOps::new;
        MyStringOps strOps2 = myStringFunc.func(4);
        strOps2.printVal();
    }
    
}
