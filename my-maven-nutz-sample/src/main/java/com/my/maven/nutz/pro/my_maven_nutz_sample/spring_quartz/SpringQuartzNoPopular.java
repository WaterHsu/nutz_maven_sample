package com.my.maven.nutz.pro.my_maven_nutz_sample.spring_quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月26日
 */
public class SpringQuartzNoPopular extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Spring Quartz调度， 采用继承spring中实现的任务类");
		String s = (String)arg0.getMergedJobDataMap().get("service");
		System.out.println(s);
	}
	
}
