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
package GameServer.SimpleGame;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.owlike.genson.Genson;

/**
 * Handler implementation for the object echo client. It initiates the ping-pong
 * traffic between the object echo client and server by sending the first
 * message to the server.
 */
public class ObjectEchoClientHandler extends ChannelInboundHandlerAdapter {
	public byte[] img;
	public boolean received = false;
	Genson gen = new Genson();

	@Override
	public void channelActive(ChannelHandlerContext ctx) {

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws IOException {
		if (!received) {
			System.out.println(msg);
			
			Packet rec = (Packet)msg;
			if (rec.PID == 1) {
				System.out.println("Received Handshake from server");
				System.out.println("Writing Empty response Packet 2");
				received = true;
				ctx.write(new Packet(2));
			}
		} else {
			Packet p = (Packet)msg;
			

			if (p.data == null) {
				System.out.println("No Image");
				ctx.write(new Packet(2));
			} else {
				
				InputStream in = new ByteArrayInputStream(p.data);
				BufferedImage bufferedImage = ImageIO.read(in);
				ObjectEchoClient.setImage(bufferedImage);

				System.out.println("Read " + p.data.length
						+ " Bytes From The Server");

			}
			ctx.write(new Packet(3));
		}

	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		// ctx.close();
	}

}