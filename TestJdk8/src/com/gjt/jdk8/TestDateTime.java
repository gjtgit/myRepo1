package com.gjt.jdk8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestDateTime {
    public static void main(String[] args) {
        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);
        
        LocalDate date1 = currentTime.toLocalDate();
        LocalTime time1 = currentTime.toLocalTime();
        System.out.println("date1=" + date1+", time1="+time1);
        
        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();
        System.out.println("月: " + month +", 日: " + day +", 秒: " + seconds);
        
        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);
      
        // 12 december 2014
        LocalDate date3 = LocalDate.of(2019, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);
        
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);
        
        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
     
        //时区
        // 获取当前时间日期
        ZonedDateTime zoneDate1 = ZonedDateTime.parse("2019-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("zoneDate1: " + zoneDate1);
        
        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);
        ZonedDateTime zoneDate2 = ZonedDateTime.now(id);
        System.out.println("zoneDate2: " + zoneDate2);
    
        Period p = Period.between(date1, date3);
        System.out.println(p.getYears()+"  "+p.getMonths()+"  "+p.getDays());
        
        System.out.println(date1.plusDays(10));
        System.out.println(zoneDate2.plusDays(10));
        
    }
}
