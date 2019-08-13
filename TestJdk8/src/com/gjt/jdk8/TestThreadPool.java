package com.gjt.jdk8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();//线程池里面的线程数会动态变化，并可在线程线被移除前重用
           for (int i = 1; i <= 3; i ++) {
               final  int task = i;   //10个任务
               TimeUnit.SECONDS.sleep(1); //newCachedThreadPool能重用可用线程,启用改行会重用线程
               threadPool.execute(new Runnable() {    //接受1个Runnable实例
                   public void run() {
                       System.out.println("线程名字= " + Thread.currentThread().getName() +  "  任务名为= "+task);
                   }
               });
           }
    }
}
