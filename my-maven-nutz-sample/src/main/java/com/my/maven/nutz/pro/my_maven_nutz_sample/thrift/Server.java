package com.my.maven.nutz.pro.my_maven_nutz_sample.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.my.maven.nutz.pro.my_maven_nutz_sample.thrift.Hello.Processor;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月22日
 */
public class Server {
	
	public void startServer(){
		try{
			System.out.println("thrift server open port 1234");
			TServerSocket serverTransport = new TServerSocket(1234);
			Hello.Processor processor = new Processor(new HelloImpl());
			Factory portFactory = new TBinaryProtocol.Factory(true, true);
			Args args = new Args(serverTransport);
			args.processor(processor);
			args.protocolFactory(portFactory);
			TServer server = new TThreadPoolServer(args);
			server.serve();
		}catch(TTransportException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		System.out.println("thrift server init");
		Server server =  new Server();
		System.out.println("thrift server start");
		server.startServer();
		System.out.println("thrift server end");
	}
}
