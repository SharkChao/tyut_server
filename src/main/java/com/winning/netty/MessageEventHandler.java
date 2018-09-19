package com.winning.netty;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MessageEventHandler {
	private static SocketIOServer socketIoServer;
	static ArrayList<UUID> listClient = new ArrayList<>();
	static ConcurrentHashMap<String,UUID> clientMap = new ConcurrentHashMap<>();
	static final int limitSeconds = 60;


	@Autowired
	public MessageEventHandler(SocketIOServer server) {
		this.socketIoServer = server;
	}

	@OnConnect
	public void onConnect(SocketIOClient client) {
		listClient.add(client.getSessionId());
		System.out.println("客户端:" + client.getSessionId() + "已连接");
	}
	
	@OnDisconnect
	public void onDisconnect(SocketIOClient client) {
		System.out.println("客户端:" + client.getSessionId() + "断开连接");
	}

}