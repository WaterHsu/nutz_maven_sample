package com.my.maven.nutz.pro.my_maven_nutz_sample.enumtype;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年9月23日
 */
public enum EnumMore {
	MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6){
		
		public boolean isRest(){
			return true;
		}
	},
	SUN(0){
		public boolean isRest(){
			return true;
		}
	};
	
	private int value;
	
	private EnumMore(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setValue(int value){
		this.value = value;
	}
}
