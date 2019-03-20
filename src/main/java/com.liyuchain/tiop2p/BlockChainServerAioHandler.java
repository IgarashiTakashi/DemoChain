package com.liyuchain.tiop2p;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.nio.ByteBuffer;

public class BlockChainServerAioHandler implements ServerAioHandler {

    //日志记录
    private Logger logger = LoggerFactory.getLogger(BlockChainServerAioHandler.class);

    /**
     * 解码：把接收到的ByteBuffer解码成应用可以识别的业务消息包
     * 总的消息结构：消息头 + 消息体
     * 消息头结构：4个字节，存储消息体的长度
     * 消息体结构：对象的JSON串的byte[]
     */
    @Override
    public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
        //buffer的开始位置并不一定是0，应用需要从buffer.position()开始读取数据，
        //若收到的数据无法组成业务包BlockPacket，则返回Null以表示数据长度不够
        if (readableLength < BlockPacket.HEADER_LENGHT){
            return null;
        }

        //读取消息体的长度
        int bodyLength = buffer.getInt();

        //数据不正确，则抛出AioDecodeException异常
        if (bodyLength < 0) {
            throw new AioDecodeException("bodyLength [" + bodyLength + "]is not right , remote:" +
                    channelContext.getClientNode());
        }

        //计算本次需要的数据长度
        int needleLength = BlockPacket.HEADER_LENGHT + bodyLength;
        //收到的数据是否足够组包
        int isDataEnough = readableLength - needleLength;
        //不够消息体长度(剩下的buffer组不了消息体)
        if (isDataEnough < 0) {

        }
        return null;
    }

    @Override
    public ByteBuffer encode(Packet packet, GroupContext groupContext, ChannelContext channelContext) {
        return null;
    }

    @Override
    public void handler(Packet packet, ChannelContext channelContext) throws Exception {

    }
}
