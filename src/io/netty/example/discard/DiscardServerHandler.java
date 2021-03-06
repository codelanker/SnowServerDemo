/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.example.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

import sun.misc.Cache;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger logger = Logger.getLogger(
            DiscardServerHandler.class.getName());

    @Override
    public void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        // discard
    	System.out.println("meg receive");
    	ByteBuf in = (ByteBuf) msg;
    	System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
    	/*try {
    		while(in.isReadable()){
    			System.out.print((char)in.readByte());
    			System.out.flush();
    		}
    		//System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
    	}
    	catch(Exception e){
    		System.out.println(e.toString());
    	} 
    	finally{
			//ReferenceCountUtil.release(msg);
    		in.release();
		}*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
            Throwable cause) throws Exception {
        // Close the connection when an exception is raised.
        logger.log(
                Level.WARNING,
                "Unexpected exception from downstream.",
                cause);
        ctx.close();
    }
}
