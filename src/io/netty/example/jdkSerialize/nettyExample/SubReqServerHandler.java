package io.netty.example.jdkSerialize.nettyExample;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		SubscribeReq req = (SubscribeReq) msg;
		if("lanker".equalsIgnoreCase(req.userName)){
			System.out.println("Service accept client subscribe req:["+req.toString()+"]");
			ctx.writeAndFlush(new SubscribeResp(req.subReqId, 0, 
					"netty book order success,3 days later,sent to the designated address"));
		}
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
