package io.netty.example.time;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {
	private ByteBuf buf;
	private byte[] req;
	public TimeClientHandler() {
		req = ("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
	}
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		buf = ctx.alloc().buffer(4);
	}
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		buf.release();
		buf = null;
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf message = null;
		for (int i = 0; i < 100; i++) {
			message = Unpooled.buffer(req.length);
			message.writeBytes(req);
			ctx.writeAndFlush(message);
		}
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		/*ByteBuf m = (ByteBuf)msg;
		buf.writeBytes(m);
		m.release();
		if(buf.readableBytes()>=4){*/
			/*int in = buf.readInt();
			System.out.println(in);
			long currMillis = (in)*1000L;
			System.out.println(currMillis);
			System.out.println(f.format(new Date(currMillis)));
			ctx.close();*/
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			long currentTimeMillis = (((ByteBuf)msg).readUnsignedInt() - 2208988800L) * 1000L;
			System.out.println(f.format(new Date(currentTimeMillis)));
			//ctx.close();
		//}
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
