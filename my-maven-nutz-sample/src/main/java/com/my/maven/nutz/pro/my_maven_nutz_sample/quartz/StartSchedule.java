package com.my.maven.nutz.pro.my_maven_nutz_sample.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月26日
 */
public class StartSchedule {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		JobDetail firstJob = new JobDetail("job1", "group1", FirstJob.class);
		JobDetail secondJob = new JobDetail("job2", "group1", SecondJob.class);
		CronTrigger t1 = new CronTrigger("trigger1", "group1", "job1", "group1", "0/2 * * * * ?");
		CronTrigger t2 = new CronTrigger("trigger2", "group1", "job2", "group1", "0/5 * * * * ?");
		sched.scheduleJob(firstJob, t1);
		sched.scheduleJob(secondJob, t2);
		sched.start();
		Thread.sleep(500000);
		sched.shutdown(true);
	}

}
