package com.hpn.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
public class CustomerPositionsHandle {
	 /**
     * 新的WebSocket请求开启
     */
    @OnOpen
    public void onOpen(Session session) {
    	// 一定要启动新的线程，防止InputStream阻塞处理WebSocket的线程
    	CustomerPositionThread thread = new CustomerPositionThread(session);
        thread.start();
    }

    /**
     * WebSocket请求关闭
     */
    @OnClose
    public void onClose() {
    }

    @OnError
    public void onError(Throwable thr) {
        thr.printStackTrace();
    }
}
