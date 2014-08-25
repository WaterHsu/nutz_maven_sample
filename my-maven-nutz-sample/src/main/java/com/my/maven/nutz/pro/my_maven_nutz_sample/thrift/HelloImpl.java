package com.my.maven.nutz.pro.my_maven_nutz_sample.thrift;

import org.apache.thrift.TException;

import com.my.maven.nutz.pro.my_maven_nutz_sample.thrift.Hello.Iface;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月22日
 */
public class HelloImpl implements Iface {
	private static int count = 0;
	@Override
	public String helloString(String word) throws TException {
		count += 1;
		System.out.println("get " + word + " " + count);
		return "hello " + word + " " + count;
	}

}
