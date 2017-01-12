package web;

import java.io.IOException;

import javax.websocket.Session;

public class SendMessage {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("ddddddddddddddddddddddddddd"+WebSocketTest.sessionUserIdMap.size());

for(Session session :WebSocketTest.sessionUserIdMap.keySet()){
	System.out.println(WebSocketTest.sessionUserIdMap.get(session));
	if(WebSocketTest.sessionUserIdMap.get(session) == 101){
		session.getBasicRemote().sendText("Test successfull "+WebSocketTest.sessionUserIdMap.get(session));
	}
	
}
System.out.println("dddddddddddwwwwwwwwwwwwwwwwwwww");

	}

}
