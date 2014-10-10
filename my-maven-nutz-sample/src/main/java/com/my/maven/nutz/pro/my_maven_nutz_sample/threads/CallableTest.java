package com.my.maven.nutz.pro.my_maven_nutz_sample.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年10月10日
 */
public class CallableTest {
	
	public static void testFuture(){
		ExecutorService exec = Executors.newCachedThreadPool();
		Task task = new Task();
		Future<Integer> result = exec.submit(task);
		exec.shutdown();
		
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e1){
			e1.printStackTrace();
		}
		
		System.out.println("主线程在执行任务");
		
		try{
			System.out.println("task 运行结果" + result.get());
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("所有任务执行完毕");
	}
	
	public static void testFutureTask1(){
		ExecutorService exec = Executors.newCachedThreadPool();
		Task task = new Task();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
		exec.submit(futureTask);
		exec.shutdown();
		

		try{
			Thread.sleep(1000);
		}catch(InterruptedException e1){
			e1.printStackTrace();
		}
		
		System.out.println("主线程在执行任务");
		
		try{
			System.out.println("task 运行结果" + futureTask.get());
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("所有任务执行完毕");
	}
	
	public static void testFuturetask2(){
		Task task = new Task();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
		Thread thread = new Thread(futureTask);
		thread.start();
		
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e1){
			e1.printStackTrace();
		}
		
		System.out.println("主线程在执行任务");
		
		try{
			System.out.println("task 运行结果" + futureTask.get());
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("所有任务执行完毕");
	}
	
	public static void main(String[] args){
		testFuture();
		testFuturetask2();
		testFutureTask1();
		
	}
}

class Task implements Callable<Integer>{
	
	public Integer call() throws Exception{
		System.out.println("子线程在进行计算");
		Thread.sleep(3000);
		int sum = 0;
		for(int i = 0; i < 100; i++){
			sum += i;
		}
		
		return sum;
	}
}
