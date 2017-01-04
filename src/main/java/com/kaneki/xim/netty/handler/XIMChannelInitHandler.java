package com.kaneki.xim.netty.handler;

import com.google.protobuf.ExtensionRegistry;
import com.kaneki.xim.protoc.XProtocol;
import com.kaneki.xim.protoc.message.XMessage;
import com.kaneki.xim.protoc.push.XPush;
import com.kaneki.xim.protoc.request.XRequest;
import com.kaneki.xim.protoc.response.XResponse;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @author yueqian
 * @Desctription
 * @date 2016/12/13
 * @email yueqian@mogujie.com
 */
public class XIMChannelInitHandler extends ChannelInitializer<SocketChannel> {
    private ExtensionRegistry registry;

    public XIMChannelInitHandler() {
        registry = ExtensionRegistry.newInstance();
        XProtocol.registerAllExtensions(registry);
        XRequest.registerAllExtensions(registry);
        XResponse.registerAllExtensions(registry);
        XPush.registerAllExtensions(registry);
        XMessage.registerAllExtensions(registry);
    }

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
        channelPipeline.addLast("protobufDecoder", new ProtobufDecoder(XProtocol.Protocol.getDefaultInstance(), registry));
        channelPipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
        channelPipeline.addLast("protobufEncoder", new ProtobufEncoder());
        channelPipeline.addLast("handler", new XIMChannelInHandler());
    }
}
