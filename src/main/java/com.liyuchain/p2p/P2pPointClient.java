package com.liyuchain.p2p;

import com.google.common.base.Strings;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Igarashi
 * @Date: 2019-02-28 14:24
 */
public class P2pPointClient {
    //日志记录
    private Logger logger = LoggerFactory.getLogger(P2pPointClient.class);

    //P2P网络中的节点既是服务端又是客户端。作为服务端运行在7001端口，同时作为客户通过ws://localhost:7001连接到服务端
    private String wsUrl = "ws://localhost:7001/";

    //所有客户端WebSocket的连接池缓存
    private List<WebSocket> localSockets = new ArrayList<WebSocket>();

    public List<WebSocket> getLocalSockets() {
        return localSockets;
    }

    public void setLocalSockets(List<WebSocket> localSockets) {
        this.localSockets = localSockets;
    }

    /**
     * 连接到服务器
     */
    @PostConstruct
    @Order(2)
    public void connectPeer() throws URISyntaxException {
        try {
            //创建WebSocket的客户端
            final WebSocketClient socketClient = new WebSocketClient(new URI(wsUrl)) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    sendMessage(this,"成都客户端成功创建客户端");

                    localSockets.add(this);
                }

                @Override
                public void onMessage(String message) {
                    logger.info("成都客户端收到成都服务端发来的消息：" + message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    logger.info("成都客户端关闭");
                    localSockets.remove(this);
                }

                @Override
                public void onError(Exception ex) {
                    logger.info("成都客户端报错");
                    localSockets.remove(this);
                }
            };
        } catch (URISyntaxException e) {
            logger.info("成都连接错误：" + e.getMessage());
        }
    }

    /**
     * 向服务端发送消息，当前WebSocket的远程Socket地址就是服务端
     *  @param ws
     * @param message
     */
    public void sendMessage(WebSocket ws, String message) {
        logger.info("发送给" + ws.getRemoteSocketAddress().getPort() + "的p2p消息：" + message);
        ws.send(message);
    }

    /**
     * 向所有连接过的服务端广播消息
     * @param message
     */
    public void broadcast(String message) {
        if (localSockets.size() == 0 || Strings.isNullOrEmpty(message)) {
            return;
        }
        logger.info("Glad to say broadcast to servers being startted!");
        for (WebSocket socket : localSockets){
            this.sendMessage(socket,message);
        }
        logger.info("Glad to say broadcast to servers has overred!");
    }
}
