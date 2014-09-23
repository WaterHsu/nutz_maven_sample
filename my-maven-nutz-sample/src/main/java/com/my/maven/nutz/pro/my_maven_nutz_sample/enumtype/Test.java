package com.my.maven.nutz.pro.my_maven_nutz_sample.enumtype;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年9月23日
 */
public class Test {
	public static void main(String args[]){
		for(EnumTest e : EnumTest.values()){
			System.out.println(e.toString());
		}
		System.out.println("---------------------------------");
		System.out.println(EnumLike.FRI);
		System.out.println("---------------------------------");
		EnumTest test = EnumTest.TUE;
		switch(test){
			case MON:
				System.out.println("Mon");
				break;
			case TUE:
				System.out.println("Tue");
				break;
			case WED:
				System.out.println("Wed");
				break;
			case THU:
				System.out.println("Thu");
				break;
			case FRI:
				System.out.println("Fri");
				break;
			default:
				System.out.println(test);
				break;
		}
		System.out.println("---------------------------------");
		switch(test.compareTo(EnumTest.MON)){
			case -1:
				System.out.println("Tue 在 Mon前面");
				break;
			case 1:
				System.out.println("Tue 在 Mon后面");
				break;
			default:
				System.out.println("Tue 和 Mon在同一位置");
				break;
		}
		System.out.println("---------------------------------");
		System.out.println("getDeclaringClass(): " + test.getDeclaringClass().getName());
		System.out.println("name(): " + test.name());
		System.out.println("toString(): " + test.toString());
		System.out.println("ordinal(): " + test.ordinal());
	}
}
