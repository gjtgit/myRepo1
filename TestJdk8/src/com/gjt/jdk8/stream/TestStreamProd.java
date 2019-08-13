package com.gjt.jdk8.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestStreamProd {
    public static void main(String[] args) {
        List<Prod> pl = new ArrayList<Prod>();
        Prod p1 = new Prod("prod1","desc1","Food",1,5.1);
        Prod p2 = new Prod("prod2","desc2","Food",1,3.2);
        Prod p3 = new Prod("prod3","desc3","Food",1,2.3);
        Prod p4 = new Prod("prod4","desc4","Cloth",1,50.4);
        Prod p5 = new Prod("prod5","desc5","Cloth",1,20.5);
        Prod p6 = new Prod("prod6","desc5","Cloth",1,80.6);
        pl.add(p1);
        pl.add(p2);
        pl.add(p3);
        pl.add(p4);
        pl.add(p5);
        pl.add(p6);
        
        List<Prod> foodList = new ArrayList<Prod>();
        for(Prod p:pl){
            if("Food".equals(p.getType())){
                foodList.add(p);
            }
        }
        
        Collections.sort(foodList, new Comparator<Prod>(){
            @Override
            public int compare(Prod o1, Prod o2) {
                if(o1.getPrice() > o2.getPrice()) {
                    return 1;
                }else if(o1.getPrice() < o2.getPrice()) {
                    return -1;
                }
                return 0;
            }
        }             
        );
        
        List<String> foodNameList = new ArrayList<String>();
        for(Prod p:foodList) {
            foodNameList.add(p.getName());
        }
        
        for(String n:foodNameList) {
            System.out.println(n);
        }
        System.out.println("使用stream过滤排序取值-----------");
        List<String> foodNameList2 = pl.parallelStream().filter(p->"Food".equals(p.getType()))
                .sorted((o1,o2) -> {
                    if(o1.getPrice() > o2.getPrice()) {
                        return 1;
                    }else if(o1.getPrice() < o2.getPrice()) {
                        return -1;
                    }
                    return 0;
                })
                .map(Prod::getName)
                .collect(Collectors.toList());
        for(String n:foodNameList2) {
            System.out.println(n);
        }
        
        
        //--------------------------------
        System.out.println("使用limit---------");
        List<String> limitList = pl.parallelStream().map(Prod::getName)
            .limit(4).skip(2).collect(Collectors.toList());
        System.out.println(limitList);
        List<Prod> personList2 = pl.stream().sorted(
                (o1, o2) -> o2.getName().compareTo(o1.getName())
                ).limit(2).collect(Collectors.toList());
        System.out.println(personList2);
        
        boolean noneMatch = pl.stream().noneMatch( p -> "prod5".equalsIgnoreCase(p.getName()));
        System.out.println(  noneMatch);
    }
}
