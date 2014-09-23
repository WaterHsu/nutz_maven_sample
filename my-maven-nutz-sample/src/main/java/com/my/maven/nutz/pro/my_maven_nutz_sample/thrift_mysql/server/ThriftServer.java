package com.my.maven.nutz.pro.my_maven_nutz_sample.thrift_mysql.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TNonblockingServer.Args;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;

import com.my.maven.nutz.pro.my_maven_nutz_sample.thrift_mysql.impl.ThriftMysqlServiceImpl;
import com.my.maven.nutz.pro.my_maven_nutz_sample.thrift_mysql.rpc.ThriftMysqlService;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月29日
 */
public class ThriftServer {
	
		public static final int SERVER_PORT = 8090;
		
		/**
		 * TSimpleServer 方式
		 * 简单的单线程服务模型，一般用于测试
		 */
		public void startTSimpleServer(){
			try{
				System.out.println("ThriftMysqlService Tserver TSimpleServer start .... ");
				TProcessor tprocessor = new ThriftMysqlService.Processor<ThriftMysqlService.Iface>(new ThriftMysqlServiceImpl());
				/**
				 * 简单的单线程服务模型，一般用于测试
				 */
				TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
				TServer.Args tArgs = new TServer.Args(serverTransport);
				tArgs.processor(tprocessor);
				tArgs.protocolFactory(new TBinaryProtocol.Factory());
				TServer server = new TSimpleServer(tArgs);
				server.serve();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		/**
		 * 线程池服务模型，使用标准的阻塞式IO，预先创建一组县城处理请求
		 */
		public void startTThreadPoolServer(){
			try{
				System.out.println("ThriftMysqlService Tserver TThreadPoolServer start .... ");
				
				TProcessor tprocessor = new ThriftMysqlService.Processor<ThriftMysqlService.Iface>(new ThriftMysqlServiceImpl());
				TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
				TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(serverTransport);
				ttpsArgs.processor(tprocessor);
				ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());
				
				/**
				 * 线程池服务模型，使用标准的阻塞IO，预先创建一组县城处理请求
				 */
				TServer server = new TThreadPoolServer(ttpsArgs);
				server.serve();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		/**
		 * 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
		 */
		public void startTNonblockingServer(){
			try{
				System.out.println("ThriftMysqlService Tserver TNonblockingServer start .... ");
				
				TProcessor tprocessor = new ThriftMysqlService.Processor<ThriftMysqlService.Iface>(new ThriftMysqlServiceImpl());
				TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(SERVER_PORT);
				TNonblockingServer.Args tnArgs = new TNonblockingServer.Args(tnbSocketTransport);
				tnArgs.processor(tprocessor);
				tnArgs.transportFactory(new TFramedTransport.Factory());
				tnArgs.protocolFactory(new TCompactProtocol.Factory());
				
				/**
				 * 使用非阻塞式IO， 服务端和客户端需要指定TFramedTransport数据传输的方式
				 */
				TServer server = new TNonblockingServer(tnArgs);
				server.serve();
			}catch(Exception e){
				
			}
		}
		
		/**
		 * 半同步半异步的服务端模型， 需要制定为： TFramedTransport数据传输的方式
		 */
		public void startTHsHaServer(){
			try{
				System.out.println("ThriftMysqlService Tserver THsHaServer start .... ");
				
				TProcessor tprocessor = new ThriftMysqlService.Processor<ThriftMysqlService.Iface>(new ThriftMysqlServiceImpl());
				TNonblockingServerSocket tnSocketTransport = new TNonblockingServerSocket(SERVER_PORT);
				THsHaServer.Args thhsArgs = new THsHaServer.Args(tnSocketTransport);
				thhsArgs.processor(tprocessor);
				thhsArgs.transportFactory(new TFramedTransport.Factory());
				thhsArgs.protocolFactory(new TBinaryProtocol.Factory());
				/**
				 * 半同步半异步的服务模型
				 */
				TServer server = new THsHaServer(thhsArgs);
				server.serve();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		/**
		 * 使用客户端方式通信
		 */
		public void startAsynClientServer(){
			try{
				System.out.println("ThriftMysqlService Tserver AsynClient start ....");
				
				TProcessor tprocessor = new ThriftMysqlService.Processor<ThriftMysqlService.Iface>(new ThriftMysqlServiceImpl());
				TNonblockingServerSocket tnSocketTransport = new TNonblockingServerSocket(SERVER_PORT);
				TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(tnSocketTransport);
				tnbArgs.processor(tprocessor);
				tnbArgs.transportFactory(new TFramedTransport.Factory());
				tnbArgs.protocolFactory(new TCompactProtocol.Factory());
				
				/**
				 * 使用非阻塞式IO， 服务端和客户端需要制定TFramedTransport数据传输的方式
				 */
				TServer server = new TNonblockingServer(tnbArgs);
				server.serve();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
}
