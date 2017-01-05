package com.kaneki.xim.model.packet;

import com.kaneki.xim.protoc.XProtocol;

/**
 * @author yueqian
 * @Desctription
 * @date 2016/12/23
 * @email yueqian@mogujie.com
 */
public abstract class Packet {
    private String packetId;
    private long toId;
    private long formId;
    private long create;
    private long packetSize;
    private int headerType;
    private int bodyType;

    public String getPacketId() {
        return packetId;
    }

    public void setPacketId(String packetId) {
        this.packetId = packetId;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    public long getFormId() {
        return formId;
    }

    public void setFormId(long formId) {
        this.formId = formId;
    }

    public long getCreate() {
        return create;
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public long getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(long packetSize) {
        this.packetSize = packetSize;
    }

    public int getHeaderType() {
        return headerType;
    }

    public void setHeaderType(int headerType) {
        this.headerType = headerType;
    }

    public void setBodyType(int bodyType) {
        this.bodyType = bodyType;
    }

    public int getBodyType() {
        return bodyType;
    }

    public void decodeHeader(Object msg) throws Exception {
        XProtocol.Protocol protocol = (XProtocol.Protocol) msg;
        setFormId(protocol.getFromId());
        setPacketId(protocol.getPacketId());
        setToId(protocol.getToId());
        setPacketSize(protocol.getPacketSize());
        setHeaderType(protocol.getType().getNumber());
        setCreate(protocol.getCreated());
        setBodyType(decodeBodyType());

    }

    public abstract int decodeBodyType();

    public abstract void decodeBody(XProtocol.Protocol protocol);

    public abstract XProtocol.Protocol encode();
}
