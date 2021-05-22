package edu.xihua.project.pms.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.log4j.Log4j2;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/22 7:32 下午
 * @since 1.0
 */
@Log4j2
public class ChatServer implements Server {

    public static void run() {
        new ChatServer().run(ChatConst.CHAT_PORT);
    }

    @Override
    public void run(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new WebSocketServerCompressionHandler());
                            pipeline.addLast(new WebSocketServerProtocolHandler(ChatConst.WEBSOCKET_PATH, null, true));
                            pipeline.addLast(new ChatServerHandler());
                        }
                    });
            Channel channel = bootstrap.bind(port).sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
