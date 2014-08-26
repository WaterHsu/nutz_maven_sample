package com.my.maven.nutz.pro.my_maven_nutz_sample.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月26日
 */
public class SecondJob implements Job{
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException{
		System.out.println("second Job!");
	}
}
