package com.hpn.websocket;

import java.io.IOException;

import javax.websocket.Session;

import com.hpn.action.cr.CustomerPositionAction;

public class CustomerPositionThread extends Thread {
	private Session session;

	public CustomerPositionThread(Session session) {
		this.session = session;

	}

	@Override
	public void run() {
		try {
			CustomerPositionAction action = new CustomerPositionAction();
			while (true) {
				try {
					 //1分=60000毫秒，这是10秒触发一次
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 将实时日志通过WebSocket发送给客户端，给每一行添加一个HTML换行
				session.getBasicRemote().sendText(action.findCurrentCustomer());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
