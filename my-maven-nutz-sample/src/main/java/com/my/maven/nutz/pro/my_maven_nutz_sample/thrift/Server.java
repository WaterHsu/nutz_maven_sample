/**
 * 秀吧网络科技有限公司版权所有
 * Copyright (C) xiu8 Corporation. All Rights Reserved
 */
package com.my.maven.nutz.pro.my_maven_nutz_sample.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.my.maven.nutz.pro.my_maven_nutz_sample.thrift.Hello.Processor;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月22日
 */
public class Server {
	@SuppressWarnings("")
	public void startServer(){
		try{
			System.out.println("thrift server open port 1234");
			TServerSocket serverTransport = new TServerSocket(1234);
			Hello.Processor processor = new Processor(new HelloImpl());
//			Factory portFactory = new TBinaryProtocol().Factory(true, true);
		}catch(TTransportException e){
			e.printStackTrace();
		}
	}
}
