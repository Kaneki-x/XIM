package com.kaneki.xim.model.packet.request;

import com.kaneki.xim.model.packet.Packet;
import com.kaneki.xim.protoc.XProtocol;
import com.kaneki.xim.protoc.request.XRequest;

/**
 * @author yueqian
 * @Desctription
 * @date 2016/12/23
 * @email yueqian@mogujie.com
 */
public class LoginRequestPacket extends Packet {

    public int getBodyType() {
        return XRequest.RequestType.REQUEST_TYPE_LOGIN_VALUE;
    }

    public void decodeBody(XProtocol.Protocol protocol) {

    }

    public XProtocol.Protocol encode() {
        return null;
    }
}
