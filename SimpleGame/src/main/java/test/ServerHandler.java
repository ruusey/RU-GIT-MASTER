package test;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Random;

import packets.ACTIVE;
import packets.HELLO;
import packets.UPDATE;

import com.owlike.genson.Genson;

public class ServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    Genson gen = new Genson();
    boolean handShake = false;
    ArrayList<Player> playerStates = new ArrayList<Player>();
   

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet)
			throws Exception {
		Object o = deserialize(packet);
		System.out.println("Received Handshake From Client: "+gen.serialize(o));
		
		if(o instanceof HELLO){
			HELLO h = (HELLO)o;
			ctx.writeAndFlush(serialize(o,packet.sender()));
			playerStates.add(new Player(200,200,h.UUID));
		}
		if(o instanceof UPDATE){
			UPDATE u = (UPDATE)o;
			Player p = getPlayerById(u.playerID);
			p.pos=u.pos;
			ctx.writeAndFlush(serialize(new UPDATE(p.pos,p.UUID),packet.sender()));
		}
		
		
//		 System.err.println(packet.content().toString(CharsetUtil.UTF_8));
//	        if ("Quote".equals(packet.content().toString(CharsetUtil.UTF_8))) {
//	            ctx.write(serialize(new HELLO(1),packet.sender()));
//	        
//	        }
		
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
	public Player getPlayerById(String UUID){
		for(Player p : playerStates){
			if(p.UUID.equals(UUID)){
				return p;
			}
		}
		return null;
	}
}