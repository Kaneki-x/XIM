package com.kaneki.xim.model.packet.request;

import com.kaneki.xim.model.packet.Packet;
import com.kaneki.xim.protoc.XProtocol;
import com.kaneki.xim.protoc.request.XLoginRequest;
import com.kaneki.xim.protoc.request.XRequest;

/**
 * @author yueqian
 * @Desctription
 * @date 2016/12/23
 * @email yueqian@mogujie.com
 */
public class LoginRequestPacket extends Packet {

    private String user;
    private String password;

    public int decodeBodyType() {
        return XRequest.RequestType.REQUEST_TYPE_LOGIN_VALUE;
    }

    public void decodeBody(XProtocol.Protocol protocol) {
        XRequest.Request request = protocol.getExtension(XProtocol.requset);
        XLoginRequest.LoginRequest loginRequest = request.getExtension(XRequest.loginRequest);
        setUser(loginRequest.getUser());
        setPassword(loginRequest.getPswd());
    }

    public XProtocol.Protocol encode() {
        return null;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
