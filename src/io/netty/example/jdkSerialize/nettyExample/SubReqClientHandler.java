package io.netty.example.jdkSerialize.nettyExample;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqClientHandler extends ChannelHandlerAdapter {
	public SubReqClientHandler() {
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 10; i++) {
			ctx.write(subReq(i));
		}
		ctx.flush();
	}
	private SubscribeReq subReq(int i){
		SubscribeReq req = new SubscribeReq();
		req.subReqId = i;
		req.userName = "lanker";
		req.productName = "netty";
		req.phoneNumber = "18632165487";
		req.address = "深圳市香港村";
		return req;
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("Receive server response :["+msg+"]");
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
