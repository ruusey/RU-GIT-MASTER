
package GameServer.SimpleGame;

import java.awt.Image;
import java.awt.image.BufferedImage;

import processing.core.PApplet;
import processing.core.PImage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * Modification of {@link EchoClient} which utilizes Java object serialization.
 */
public final class ObjectEchoClient extends PApplet{

    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));
    static PImage bg;
    public static void main(String[] args) {
    	constructWindow();
        try {
			startClient();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static void constructWindow(){
    	PApplet.main(ObjectEchoClient.class.getName());
		
    }
    public static void setImage(BufferedImage img) {
		if (img != null) {
			bg = new PImage((Image)img);
		}
		
		
	}
   
    public static void startClient() throws Exception{
    	final SslContext sslCtx;
        if (SSL) {
            sslCtx = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslCtx = null;
        }

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.option(ChannelOption.SO_SNDBUF, 20000000);
            b.group(group)
             .channel(NioSocketChannel.class)
             .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    if (sslCtx != null) {
                        p.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
                    }
                    p.addLast(
                    		 new ObjectEncoder(),
	                            new ObjectDecoder(ClassResolvers.softCachingResolver(ClassLoader.getSystemClassLoader())),
                            new ObjectEchoClientHandler());
                   
                    
                }
             });

            // Start the connection attempt.
            b.connect(HOST, PORT).sync().channel().closeFuture().sync();
            
        } finally {
            group.shutdownGracefully();
        }
    }
    public void setup(){
    	background(0);
    }
    public void settings(){
    	size(640,480);
    }
    public void draw(){
    	if(bg!=null){
    		image(bg,0,0,640,480);
    	}
    	
    	
    }
}