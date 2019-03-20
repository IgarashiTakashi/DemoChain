package com.liyuchain.p2ppbft;

import com.google.common.base.Strings;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于Spring Boot 2.0 的WebSocket服务端
 *
 * @Author: Igarashi
 * @Date: 2019-03-02 17:04
 */
public class P2pPointPbftServer {
    private Logger logger = LoggerFactory.getLogger(P2pPointPbftServer.class);

    //本机Server的WebSocket缓存器
    private int port = 7001;

    //所有连接到服务器的WebSocket缓存器
    private List<WebSocket> localSockets = new ArrayList<WebSocket>();

    public List<WebSocket> getLocalSockets() {
        return localSockets;
    }

    public void setLocalSockets(List<WebSocket> localSockets) {
        this.localSockets = localSockets;
    }

    /**
     * 初始化 p2p Server端
     */
    @PostConstruct
    @Order(1)
    public void initServer(){
        final WebSocketServer socketServer = new WebSocketServer(new InetSocketAddress(port)) {

            @Override
            public void onOpen(WebSocket conn, ClientHandshake handshake) {
                sendMessage(conn, "成都服务端成功创建连接");
                localSockets.add(conn);
            }

            @Override
            public void onClose(WebSocket conn, int code, String reason, boolean remote) {
                logger.info(conn.getRemoteSocketAddress() + "客户端与服务器断开连接！");
                localSockets.remove(conn);
            }

            @Override
            public void onMessage(WebSocket conn, String message) {
                logger.info("成都服务端接收到客户端消息："  + message);
                //收到入库的消息则不再发送
                if ("成都客户端开始区块入库啦".equals(message)) {
                    return;
                }

                //如果收到的不是JSON化数据，则说明扔处在双方建立连接的过程中。
                //目前连接已经建立完毕，发起投票
                if (!message.startsWith("{")) {
                    VoteInfo vi = createVoteInfo(VoteEnum.PREPREPARE)
                }
            }

            @Override
            public void onError(WebSocket conn, Exception ex) {

            }

            @Override
            public void onStart() {

            }
        }
    }

    private VoteInfo createVoteInfo(VoteEnum voteEnum) {
        VoteInfo vi = new VoteInfo();

    }

    public void sendMessage(WebSocket ws,String message) {
        logger.info("发送给" + ws.getRemoteSocketAddress().getPort() + "的p2p消息是：" + message);
        ws.send(message);
    }

    public void broadcast(String message){
        if (localSockets.size() == 0 || Strings.isNullOrEmpty(message)) {
            return;
        }

        logger.info("Glad to say the broadcast to client being startted!");
        for (WebSocket socket : localSockets) {
            this.sendMessage(socket,message);
        }
        logger.info("Glad to say broadcast to clients has overred!");
    }
}

