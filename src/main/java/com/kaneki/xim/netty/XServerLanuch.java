package com.kaneki.xim.netty;

import com.kaneki.xim.netty.handler.XIMChannelInitHandler;
import com.kaneki.xim.concurrent.XIMThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.spi.SelectorProvider;

/**
 * @author yueqian
 * @Desctription
 * @date 2016/11/25
 * @email yueqian@mogujie.com
 */
public class XServerLanuch {
    private final Logger logger = LoggerFactory.getLogger(XServerLanuch.class);

    private static final int port = 9999;
    private static XServerLanuch instance;

    private ServerBootstrap serverBootstrap;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private boolean isServerStarted;

    private XServerLanuch() {
        serverBootstrap = new ServerBootstrap();
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup(Math.max(2, Runtime.getRuntime().availableProcessors()) * 2, new XIMThreadFactory("XIM ThreadFactory"), SelectorProvider.provider());
    }

    public static XServerLanuch getInstance() {
        if (instance == null) {
            synchronized (XServerLanuch.class) {
                if (instance == null) {
                    instance = new XServerLanuch();
                    return instance;
                } else
                    return instance;
            }
        } else
            return instance;
    }

    public boolean isServerStarted() {
        return isServerStarted;
    }

    public void startXServer() {
        try {
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new XIMChannelInitHandler());
            serverBootstrap.option(ChannelOption.TCP_NODELAY, true);
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            //绑定端口 同步等待成功
            ChannelFuture f = serverBootstrap.bind(port).sync();
            //等待服务端监听端口关闭
            isServerStarted = true;
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            isServerStarted = false;
        }
    }

    public void stop() {
        instance = null;
        isServerStarted = false;
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
