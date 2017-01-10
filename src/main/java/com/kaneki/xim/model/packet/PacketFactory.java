package com.kaneki.xim.model.packet;

import com.kaneki.xim.model.packet.request.LoginRequestPacket;
import com.kaneki.xim.protoc.XProtocol;
import com.kaneki.xim.protoc.message.XMessage;
import com.kaneki.xim.protoc.push.XPush;
import com.kaneki.xim.protoc.request.XRequest;
import com.kaneki.xim.protoc.response.XResponse;

/**
 * @author yueqian
 * @Desctription
 * @date 2017/1/5
 * @email yueqian@mogujie.com
 */
public class PacketFactory {

    public static Packet getPacket(XProtocol.Protocol protocol) {
        switch (protocol.getType().getNumber()) {
            case XProtocol.Type.BASE_TYPE_REQUEST_VALUE:
                switch (protocol.getExtension(XProtocol.requset).getType().getNumber()) {
                    case XRequest.RequestType.REQUEST_TYPE_LOGIN_VALUE:
                        return new LoginRequestPacket();
                    default:
                        return null;
                }
            case XProtocol.Type.BASE_TYPE_RESPONSE_VALUE:
                switch (protocol.getExtension(XProtocol.respone).getType().getNumber()) {
                    case XResponse.ResponseType.RESPONSE_TYPE_LOGIN_VALUE:
                        return new LoginRequestPacket();
                    default:
                        return null;
                }
            case XProtocol.Type.BASE_TYPE_MESSAGE_VALUE:
                switch (protocol.getExtension(XProtocol.message).getType().getNumber()) {
                    case XMessage.MessageType.MESSAGE_TYPE_TEXT_VALUE:
                        return new LoginRequestPacket();
                    default:
                        return null;
                }
            case XProtocol.Type.BASE_TYPE_PUSH_VALUE:
                switch (protocol.getExtension(XProtocol.push).getType().getNumber()) {
                    case XPush.PushType.PUSH_TYPE_KICK_OUT_VALUE:
                        return new LoginRequestPacket();
                    default:
                        return null;
                }
            default:
                return null;
        }
    }
}
