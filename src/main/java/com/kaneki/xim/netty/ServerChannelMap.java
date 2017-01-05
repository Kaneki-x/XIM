package com.kaneki.xim.netty;

import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yueqian
 * @Desctription
 * @date 2016/12/22
 * @email yueqian@mogujie.com
 */
public class ServerChannelMap {
    private static ConcurrentHashMap<Long, ChannelHandlerContext> channelHandlerContextHashMap = new ConcurrentHashMap<Long, ChannelHandlerContext>();

    public static void add(long id, ChannelHandlerContext channelHandlerContext) {
        channelHandlerContextHashMap.put(id, channelHandlerContext);
    }

    public static ChannelHandlerContext get(long id){
        return channelHandlerContextHashMap.get(id);
    }

    public static void remove(ChannelHandlerContext channelHandlerContext) {
        for (ConcurrentHashMap.Entry entry : channelHandlerContextHashMap.entrySet()){
            if (entry.getValue() == channelHandlerContext){
                channelHandlerContextHashMap.remove(entry.getKey());
            }
        }
    }
}
