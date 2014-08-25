package com.my.maven.nutz.pro.my_maven_nutz_sample.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月22日
 */
public class Client {
	public void startClient(){
		TTransport transport;
		try{
			System.out.println("thrift client connect server at 1234 port");
			transport = new TSocket("localhost", 1234);
			TProtocol protocol = new TBinaryProtocol(transport);
			Hello.Client client = new Hello.Client(protocol);
			transport.open();
			System.out.println(client.helloString("Hello World in Thrift!"));
			transport.close();
			System.out.println("thrift client close connection!");
		}catch(TTransportException e){
			e.printStackTrace();
		}catch(TException e){
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		System.out.println("thrift client init");
		Client client = new Client();
		System.out.println("thrift client start");
		client.startClient();
		System.out.println("thrift client end");
	}
}
