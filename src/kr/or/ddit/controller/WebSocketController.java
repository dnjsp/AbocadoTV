package kr.or.ddit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import kr.or.ddit.util.GsonUtil;
import kr.or.ddit.vo.ChatMessageVO;

@ServerEndpoint("/webSocketControl")
public class WebSocketController {
	static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<Session>());
	
	public WebSocketController(){
	}	
	
	@OnOpen
	public void handleOpen(Session userSession) {
		sessionUsers.add(userSession);
	}
	
	@OnMessage
	public void handleMessage(String message,Session userSession) throws IOException {
		String username = (String)userSession.getUserProperties().get("username");
		System.out.println(message);
		Gson gson = GsonUtil.getGson();
		ChatMessageVO chatMessageVO = gson.fromJson(message, ChatMessageVO.class);
		Iterator<Session> iterator = sessionUsers.iterator();
		if(chatMessageVO.getCommand().equals("create")) {
		}else if(chatMessageVO.getCommand().equals("donate")) {
			while(iterator.hasNext()){
				iterator.next().getBasicRemote().sendText(message);
			}
		}else if(chatMessageVO.getCommand().equals("send")) {
			while(iterator.hasNext()){
				iterator.next().getBasicRemote().sendText(message);
			}
		}else if(chatMessageVO.getCommand().equals("join")) {
			
		}
	}
	
	@OnClose
	public void handleClose(Session userSession) {
		sessionUsers.remove(userSession);
	}
	
	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
}
