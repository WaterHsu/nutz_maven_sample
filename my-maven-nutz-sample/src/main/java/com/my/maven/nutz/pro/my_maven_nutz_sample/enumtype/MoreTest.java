package com.my.maven.nutz.pro.my_maven_nutz_sample.enumtype;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年9月23日
 */
public class MoreTest {
	public static void main(String args[]){
		System.out.println("EnumMore.FRI 的value = " + EnumMore.FRI.getValue());
		System.out.println("---------------------------------");
		EnumSet<EnumMore> weekSet = EnumSet.allOf(EnumMore.class);
		for(EnumMore e : weekSet){
			System.out.println(e);
		}
		
		System.out.println("---------------------------------");
		EnumMap<EnumMore, String> weekMap = new EnumMap(EnumMore.class);
		weekMap.put(EnumMore.MON, "星期一");
		weekMap.put(EnumMore.TUE, "星期二");
		weekMap.put(EnumMore.WED, "星期三");
		weekMap.put(EnumMore.THU, "星期四");
		weekMap.put(EnumMore.FRI, "星期五");
		weekMap.put(EnumMore.SAT, "星期六");
		weekMap.put(EnumMore.SUN, "星期七");
		
		for(Iterator<Entry<EnumMore, String>> iter = weekMap.entrySet().iterator(); iter.hasNext(); ){
			Entry<EnumMore, String> entry = iter.next();
			System.out.println(entry.getKey().name() + ": " + entry.getValue());
		}
	}
}
