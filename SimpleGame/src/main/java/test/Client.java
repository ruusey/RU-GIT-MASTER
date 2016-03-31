package test;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.UUID;

import com.owlike.genson.Genson;

import packets.HELLO;
import packets.UPDATE;
import processing.core.PApplet;

public final class Client extends PApplet{

    static final int PORT = Integer.parseInt(System.getProperty("port", "5000"));
    static Genson gen = new Genson();
    static boolean handShake = false;
    public static Player p;
    public static void main(String[] args) {
    	PApplet.main(Client.class.getName());
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioDatagramChannel.class)
             .handler(new ClientHandler());

            Channel ch = b.bind(0).sync().channel();

            // Broadcast the QOTM request to port 8080.
            if(!handShake){
            	UUID id = UUID.randomUUID();
            	  ch.writeAndFlush(serialize(new HELLO(id.toString()), new InetSocketAddress("localhost", PORT))).sync();
            }
          
                    

            
            // QuoteOfTheMomentClientHandler will close the DatagramChannel when a
            // response is received.  If the channel is not closed within 5 seconds,
            // print an error message and quit.
            if (!ch.closeFuture().await(5000)) {
                System.err.println("Quote request timed out.");
            }
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            group.shutdownGracefully();
        }
    }
    public static DatagramPacket serialize(Object o , InetSocketAddress sender){
		return new DatagramPacket(Unpooled.copiedBuffer(gen.serialize(o), CharsetUtil.UTF_8), sender);
	}
    public void draw(){
    	if(p!=null){
    		background(0);
        	fill(255);
        	ellipse(p.pos.x,p.pos.y,50,50);
        	ClientHandler.packetQ.add(new UPDATE(p.pos.add(p.vel),p.UUID));
    	}
    	
    }
    public void setup(){
    	
    }
    public void settings(){
    	size(640,480);
    }
    public void keyPressed(){
    	if(key=='w'){
    		p.vel.y=-5;
    		
    	}else if(key=='s'){
    		p.vel.y=5;
    	}
    	else if(key=='a'){
    		p.vel.x=-5;
    	}
    	else if(key=='d'){
    		p.vel.x=5;
    	}
    }
    public void keyReleased(){
    	if(key=='w'){
    		p.vel.y=0;
    		
    	}else if(key=='s'){
    		p.vel.y=0;
    	}
    	else if(key=='a'){
    		p.vel.x=0;
    	}
    	else if(key=='d'){
    		p.vel.x=0;
    	}
    }
}