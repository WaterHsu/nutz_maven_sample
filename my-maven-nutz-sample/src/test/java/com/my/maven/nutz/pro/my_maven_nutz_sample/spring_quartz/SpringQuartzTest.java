package com.my.maven.nutz.pro.my_maven_nutz_sample.spring_quartz;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月26日
 */
public class SpringQuartzTest {
	
	@Test
	public void testSpringQuartzPopular() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Thread.sleep(120000);
		System.out.println("Test end ..fff.");
	}
	
	@Test
	public void testSpringQuartzNoPopular() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
		Thread.sleep(120000);
		System.out.println("Test end ...");
	}
	
	@Test
	public void testSpringQuartzNoPopularWithSimpleTrigger() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext3.xml");
		Thread.sleep(240000);
		System.out.println("Test end ...");
	}
}
