package com.kaneki.xim.parse;

import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.MessageLite;
import com.kaneki.xim.model.packet.Packet;
import com.kaneki.xim.model.packet.PacketFactory;
import com.kaneki.xim.protoc.XProtocol;
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
            XProtocol.Protocol protocol = (XProtocol.Protocol) out.get(0);
            Packet packet = PacketFactory.getPacket(protocol);

            if (packet != null) {
                packet.decodeHeader(protocol);
                packet.decodeBody(protocol);

                out.add(0, packet);
            } else
                out.remove(0);
        }
    }
}
