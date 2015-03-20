package io.netty.example.time.megerPackage;

import com.sun.istack.internal.logging.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandle extends ChannelHandlerAdapter {
	private static final Logger logger = Logger.getLogger(TimeClientHandle.class);
	private int counter;
	private byte[] req;
	public TimeClientHandle() {
		req = ("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf message = null;
		for (int i = 0; i < 100; i++) {
			message = Unpooled.compositeBuffer(req.length);
			message.writeBytes(req);
			ctx.writeAndFlush(message);
		}
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String body = (String)msg;
		System.out.println("Now is :"+body+";the counter is :"+(++counter));
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		logger.warning("Unexpected exception from dowmstream:"+cause.getMessage());
		ctx.close();
	}
}
