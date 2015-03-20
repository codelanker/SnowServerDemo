package io.netty.example.time.aio;

public class TimeClient {
public static void main(String[] args) {
	new Thread(new AsyncTimeClientHandle("127.0.0.1", 8080)).start();
}
}
