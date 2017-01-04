package com.kaneki.xim.parse;

import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

import java.util.List;

/**
 * @author yueqian
 * @Desctription
 * @date 2017/1/4
 * @email yueqian@mogujie.com
 */
public class XProtobufDecoder extends ProtobufDecoder {

    public XProtobufDecoder(MessageLite prototype, ExtensionRegistry extensionRegistry) {
        super(prototype, extensionRegistry);
    }


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        super.decode(ctx, msg, out);

        if (!out.isEmpty()) {

        }
    }
}
