package com.game.core;

import com.game.core.handler.BaseServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by 文江 on 2017/7/26.
 */
public class BaseNettyServer {
    public void bind(int port) {

        EventLoopGroup common = new NioEventLoopGroup();
        EventLoopGroup exector = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(common, exector);
        //初始化通道程序
        bootstrap.childHandler(new LoggingHandler(LogLevel.INFO));
        //通道类型
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.localAddress("127.0.0.1", port);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
                channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
                channel.pipeline().addLast(new StringDecoder());
                channel.pipeline().addLast(new BaseServerHandler());
            }
        });
//
        bootstrap.option(ChannelOption.SO_BACKLOG, 100);
        try {
            ChannelFuture future = bootstrap.bind(port).sync();//同步绑定等待端口连接
            //等待服务监听关闭端口
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            common.shutdownGracefully();
            exector.shutdownGracefully();
        }
    }
}
