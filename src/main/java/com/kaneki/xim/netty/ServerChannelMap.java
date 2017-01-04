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
    private static ConcurrentHashMap<String, ChannelHandlerContext> channelHandlerContextHashMap = new ConcurrentHashMap<String, ChannelHandlerContext>();

    public static void add(String channelId, ChannelHandlerContext channelHandlerContext) {
        channelHandlerContextHashMap.put(channelId, channelHandlerContext);
    }

    public static ChannelHandlerContext get(String channelId){
        return channelHandlerContextHashMap.get(channelId);
    }

    public static void remove(ChannelHandlerContext channelHandlerContext) {
        for (ConcurrentHashMap.Entry entry : channelHandlerContextHashMap.entrySet()){
            if (entry.getValue() == channelHandlerContext){
                channelHandlerContextHashMap.remove(entry.getKey());
            }
        }
    }
}
