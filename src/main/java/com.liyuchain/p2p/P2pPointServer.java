package com.liyuchain.p2p;

import com.google.common.base.Strings;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于Spring Boot 2.0的WebSocket服务器
 *
 * @Author: Igarashi
 * @Date: 2019-02-28 11:51
 */
@Component
public class P2pPointServer {
    //日志记录
    private Logger logger = LoggerFactory.getLogger(P2pPointServer.class);

    //本机Server的WebSocket端口
    //多机测试时可以改变该值
    private int port = 7001;

    //所有连接到服务端的WebSocket缓存器
    private List<WebSocket> localSockets = new ArrayList<WebSocket>();

    public List<WebSocket> getLocalSockets() {
        return localSockets;
    }

    public void setLocalSockets(List<WebSocket> localSockets) {
        this.localSockets = localSockets;
    }

    /**
     * 初始化P2P Server端
     */
    @PostConstruct
    @Order(1)
    public void initServer(){
        /**
         * 初始化WebSocket的服务端定义内部类对象socketServer,源于WebSocketServer;new InetSocketAddress(port)
         * 是WebSocketServer构造器的参数InetSocketAddress是（IP地址+端口号）类型，即端口地址类型
         */
        final WebSocketServer socketServer = new WebSocketServer(new InetSocketAddress(port)) {
            /**
             * 重写5个事件方法，事件发生时触发对应的方法
             */
            @Override
            //创建连接成功时触发
            public void onOpen(WebSocket conn, ClientHandshake handshake) {
                sendMessage(conn, "成都服务端成功创建连接");

                //当成功创建一个WebSocket连接时，将该连接加入连接池
                localSockets.add(conn);
            }

            @Override
            //断开连接时触发
            public void onClose(WebSocket conn, int code, String reason, boolean remote) {
                logger.info(conn.getRemoteSocketAddress() + "客户端与服务器断开连接！");

                //当客户端断开连接时，WebSocket连接池删除该连接
                localSockets.remove(conn);
            }

            @Override
            //收到客户端发来的消息时触发
            public void onMessage(WebSocket conn, String message) {
                logger.info("成都服务端接受到客户端消息" + message);
                sendMessage(conn,"收到消息");
            }

            @Override
            //连接发生错误时调用，紧接着触发onClose方法
            public void onError(WebSocket conn, Exception ex) {
                logger.info(conn.getRemoteSocketAddress() + "客户端连接错误!");
                localSockets.remove(conn);
            }

            @Override
            public void onStart() {
                logger.info("成都的WebSocket Server端启动...");
            }
        };

        socketServer.start();
        logger.info("成都服务端监听socketServer端口：" + port);
    }

    /**
     * 向连接到本机的某客户端发送消息
     *
     * @param conn
     * @param message
     */
    private void sendMessage(WebSocket conn, String message) {
        logger.info("发送给" + conn.getRemoteSocketAddress().getPort() + "p2p消息是：" + message);
        conn.send(message);
    }

    /**
     * 向所有连接到本机的客户端广播消息
     *
     * @param message
     */
    public void broatcast(String message){
        if (localSockets.size() == 0 || Strings.isNullOrEmpty(message)) {
            return;
        }

        logger.info("Glad to say broatcast to clients being startted!");
        for (WebSocket socket : localSockets) {
            this.sendMessage(socket,message);
        }
        logger.info("Glad to say broadcast to clients has overred!");
    }
}
