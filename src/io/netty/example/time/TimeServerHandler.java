package io.netty.example.time;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
public class TimeServerHandler extends ChannelHandlerAdapter {
	private int counter;
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		/*final	ByteBuf time = ctx.alloc().buffer(4);
		//int rs = (int)(System.currentTimeMillis()/1000L+2208988800L);
		time.writeInt(new UnixTime().value());
		
		final ChannelFuture f = ctx.writeAndFlush(time);
		f.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture paramF) throws Exception {
				assert f == paramF;
				ctx.close();
			}
		});*/
		ChannelFuture f = ctx.writeAndFlush(new UnixTime());
		//f.addListener(ChannelFutureListener.CLOSE);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		String body = new String(bytes,"UTF-8")
				.substring(0, bytes.length-System.getProperty("line.separator").length());
		System.out.println("The time server receive order :"+body+
				"; the counter is :"+(++counter));
		String currentTime  = "QUERY TIME ORDER".equals(body)?
				new Date(System.currentTimeMillis()).toString():
				"BAD ORDER";
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.writeAndFlush(resp);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
