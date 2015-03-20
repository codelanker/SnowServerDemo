package io.netty.example.time.aio;

public class TimeServer {
	public static void main(String[] args) {
		AsyncTimeServerHandler timeServerHandler = new AsyncTimeServerHandler(8080);
		new Thread(timeServerHandler,"AIO-AsyncTimeServerHandler-001").start();
	}
}
