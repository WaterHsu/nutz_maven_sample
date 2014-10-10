/**
 * 秀吧网络科技有限公司版权所有
 * Copyright (C) xiu8 Corporation. All Rights Reserved
 */
package com.my.maven.nutz.pro.my_maven_nutz_sample.listsort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年9月23日
 */
public class EdgeInfo {
	public int currentConnection;
	
	public int currentBandWidth;
	
	public int connectionLimit;
	
	public int bandWidthLimit;
	
	public EdgeInfo(int currentConnection, int connectionLimit, int currentBandWidth, int bandWidthLimit){
		this.currentConnection = currentConnection;
		this.currentBandWidth = currentBandWidth;
		this.connectionLimit = connectionLimit;
		this.bandWidthLimit = bandWidthLimit;
	}
	
	public static void sort(List<EdgeInfo> list){
		Collections.sort(list, new Comparator<EdgeInfo>(){

			@Override
			public int compare(EdgeInfo o1, EdgeInfo o2) {
			//	return Double.compare((e1.currentConnection / e1.connectionLimit), (e2.currentConnection / e2.connectionLimit));
				return Double.compare((double)o1.currentConnection / (double)o1.connectionLimit, (double)o2.currentConnection / (double)o2.connectionLimit);
			}
			
		});
	}
	
	public static void main(String args[]){
		List<EdgeInfo> list = new ArrayList<EdgeInfo>();
		EdgeInfo e1 = new EdgeInfo(10, 100, 20, 500);
		EdgeInfo e2 = new EdgeInfo(30, 120, 300, 1400);
		EdgeInfo e3 = new EdgeInfo(50, 2000, 20, 1000);
		EdgeInfo e4 = new EdgeInfo(540, 900, 550, 3800);
		EdgeInfo e5 = new EdgeInfo(50, 1000, 20, 1000);
		list.add(e1);
		list.add(e2);
		list.add(e3);
		list.add(e4);
		list.add(e5);
		
		sort(list);
		
		for(EdgeInfo e : list){
			System.out.println(e + "   " + e.currentConnection + "    " + e.connectionLimit);
		}
	}
}
