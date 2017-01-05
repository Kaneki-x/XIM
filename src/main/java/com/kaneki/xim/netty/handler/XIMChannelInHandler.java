package com.kaneki.xim.netty.handler;

import com.kaneki.xim.netty.ServerChannelMap;
import com.kaneki.xim.protoc.XProtocol;
import com.kaneki.xim.protoc.request.XLoginRequest;
import com.kaneki.xim.protoc.request.XRequest;
import com.kaneki.xim.protoc.response.XLoginResponse;
import com.kaneki.xim.protoc.response.XResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @author yueqian
 * @Desctription
 * @date 2016/12/13
 * @email yueqian@mogujie.com
 */
public class XIMChannelInHandler extends ChannelInboundHandlerAdapter {
    private final Logger logger = LoggerFactory.getLogger(XIMChannelInHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        XProtocol.Protocol protocol = (XProtocol.Protocol) msg;
        long fromId = protocol.getFromId();
        long toId = protocol.getToId();

        if (protocol.getType() == XProtocol.Type.BASE_TYPE_REQUEST) {
            XRequest.Request request = protocol.getExtension(XProtocol.requset);
            XLoginRequest.LoginRequest loginRequest = request.getExtension(XRequest.loginRequest);

            logger.info(loginRequest.getUser()+ " is login");

            ServerChannelMap.add(fromId, ctx);

            protocol = XProtocol.Protocol.newBuilder()
                    .setCreated(0)
                    .setType(XProtocol.Type.BASE_TYPE_RESPONSE)
                    .setFromId(0)
                    .setToId(0)
                    .setPacketId(UUID.randomUUID().toString())
                    .setPacketSize(0)
                    .setExtension(XProtocol.respone, XResponse.Response.newBuilder()
                            .setType(XResponse.ResponseType.RESPONSE_TYPE_LOGIN)
                            .setExtension(XResponse.loginResponse, XLoginResponse.LoginResponse.newBuilder()
                                    .setCode(200)
                                    .setMsg("success")
                                    .build())
                            .build())
                    .build();
            ctx.writeAndFlush(protocol);
        } else {
            ServerChannelMap.get(toId).writeAndFlush(msg);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ServerChannelMap.remove(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ServerChannelMap.remove(ctx);
        cause.printStackTrace();
        ctx.close();
    }
}
