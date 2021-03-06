package io.netty.example.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TimeEncoder extends MessageToByteEncoder<UnixTime> {

	@Override
	protected void encode(ChannelHandlerContext ctx,
			UnixTime msg, ByteBuf out) throws Exception {
		System.out.println(msg.value());
		out.writeInt(msg.value());
	}

}
