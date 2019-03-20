package com.liyuchain.tiop2p;

import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.nio.ByteBuffer;

/**
 * 区块链底层定制的Packet
 *
 * @Author: Igarashi
 * @Date: 2019-02-28 16:06
 */
public class BlockPacket extends Packet {
    //网络传输需序列化，这里采用Java自带序列化方式
    private static final long serialVersionUID = 6211440813865691626L;
    //消息头的长度
    public static final int HEADER_LENGHT = 4;
    //字符编码类型
    public static final String CHARSET = "utf-8";
    //传输内容的字节
    private byte[] body;

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}

