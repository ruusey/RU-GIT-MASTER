package test;

import java.net.InetSocketAddress;
import java.util.ArrayList;

import packets.ACTIVE;
import packets.HELLO;
import packets.UPDATE;

import com.owlike.genson.Genson;

import finalproj.Client;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class ClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {
	Genson gen = new Genson();
	public static ArrayList<Object> packetQ = new ArrayList<Object>();
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet)
			throws Exception {
		Object o = deserialize(packet);
		if(o instanceof HELLO){
			String UUID = ((HELLO)o).UUID;
			System.out.println("HELLO responded to, creating new Player");
			//Client.p=new Player(200,200,UUID);
			Client.handShake=true;
		}
		if(o instanceof UPDATE){
			UPDATE u = (UPDATE)o;
			Client.p.pos=u.pos;
		}
		if(packetQ.size()>0){
			ctx.writeAndFlush(serialize(packetQ.get(0),packet.sender()));
			packetQ.remove(0);
		}
		
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx)
			throws Exception {
		 
		
	}
	public DatagramPacket serialize(Object o , InetSocketAddress sender){
		return new DatagramPacket(Unpooled.copiedBuffer(gen.serialize(o), CharsetUtil.UTF_8), sender);
	}
	public Object deserialize(DatagramPacket packet){
		Object o = null;
		try{o=gen.deserialize(packet.content().toString(CharsetUtil.UTF_8), HELLO.class);} catch (Exception e){}
		try{o=gen.deserialize(packet.content().toString(CharsetUtil.UTF_8), UPDATE.class);} catch (Exception e){}
		return o;
	}
}