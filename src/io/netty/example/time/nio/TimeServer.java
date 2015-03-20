package io.netty.example.time.nio;

public class TimeServer {
public static void main(String[] args) {
	//初始化服务器，监听帮点端口
	MultiplexerTimeServer timeServer = new MultiplexerTimeServer(8080);
	//启动服务器线程，执行run()
	new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();
}
}
