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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import com.owlike.genson.Genson;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class ObjectEchoServerHandler extends
		SimpleChannelInboundHandler<BufferedImage> {
	boolean response = false;
	Genson gen = new Genson();
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws IOException {

		System.out.println("Msg Received!");
		if (!response) {
			Packet rec = (Packet)msg;
			if (rec.PID == 2) {
				// CONNECTED
				System.out.println("Heartbeat From Client!");
				response = true;
				ctx.write(new Packet(2));
			}
		} else if(response){
			System.out.println("Heartbeat From Client!");
			if (MotionDetection.detectionFrames.size() == 0) {
				try {
					Thread.sleep(100);
				} catch (Exception e) {

				}
				ctx.writeAndFlush(new Packet(3));
				return;
			}
			BufferedImage b = MotionDetection.detectionFrames
					.get(MotionDetection.detectionFrames.size() - 1);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(b, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();

			Packet p = new Packet(5,imageInByte);
			
			
			
			ctx.writeAndFlush(p);
			
			//ctx.writeAndFlush(p);
			System.out.println("Sent Image! ");
		}

	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws IOException {
		System.out.println("Handshake with client, sending Packet 1");
		ctx.writeAndFlush(new Packet(1));

	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, BufferedImage msg)
			throws Exception {
		// TODO Auto-generated method stub

	}

}