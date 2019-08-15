package com.gjt.jdk8;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCompletionService {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(10);        //创建10条线程的线程池
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
        for (int i =1; i <=10; i ++) {
            final  int result = i;
            completionService.submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));   //让当前线程随机休眠一段时间
                    return result;
                }
            });
        }
        System.out.println(completionService.take().get());   //获取执行结果
        
        //Test callable and future
        ExecutorService singleExecutor= Executors.newSingleThreadExecutor();
        Future<String> future = singleExecutor.submit(new Callable<String>() {   //接受callable实例
            public String call() throws Exception {
                return "result from callable";
            }
        });
        System.out.println("任务的执行结果："+future.get());
    }
}
