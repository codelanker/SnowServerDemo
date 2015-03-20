package io.netty.example.time.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable {
	private Selector selector;
	private ServerSocketChannel servChannel;
	private volatile boolean stop;

	@Override
	public void run() {
		while(!stop){
			try {
				//有通道被选中，或时间到了，通道调用wakeup方法，选择器线程中断
				selector.select(1000);
				//遍历选中的通道key
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectedKeys.iterator();
				SelectionKey key = null;
				while(it.hasNext()){
					key = it.next();
					it.remove();
					try{
						//通道处理handle（filter）
						handleInput(key);
					}catch(Exception e){
						if(key!=null){
							//通道key取消
							key.cancel();
							if(key.channel()!=null){
								//通道key对应的通道关闭
								key.channel().close();
							}
						}
					}
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	private void handleInput(SelectionKey key) throws IOException{
		if(key.isValid()){
			if(key.isAcceptable()){
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}
			if(key.isReadable()){
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);
				if(readBytes>0){
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String body = new String(bytes,"UTF-8");
					System.out.println("The time server receive order :" +body);
					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String currentTime = "QUERY TIME ORDER".equals(body)?
							f.format(new Date(System.currentTimeMillis())):"BAD ORDER";
					doWrite(sc, currentTime);
				}else if(readBytes<0){
					key.cancel();
					sc.close();
				}else{
					//读到0字节，忽略
				}
			}
		}
	}
	private void doWrite(SocketChannel channel,String response) throws IOException{
		if(response!=null&&response.trim().length()>0){
			byte[] bytes = response.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			channel.write(writeBuffer);
		}
	}
	public MultiplexerTimeServer(int port) {
		try {
			//打开多路复用选择器
			selector = Selector.open();
			//打开服务器通道
			servChannel = ServerSocketChannel.open();
			//服务器通道设置成非阻塞(异步)模式
			servChannel.configureBlocking(false);
			//通道套接字绑定端口
			servChannel.socket().bind(new InetSocketAddress(port), 1024);
			servChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("The time server is start in port : "+port);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	public void stop(){
		this.stop = true;
	}
	
}
