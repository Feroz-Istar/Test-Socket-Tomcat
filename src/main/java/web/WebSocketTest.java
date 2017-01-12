package web;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
 
@ServerEndpoint("/websocket")
public class WebSocketTest {
	
	   public  static Map<Session,Integer> sessionUserIdMap = new ConcurrentHashMap<>();

	
    @OnMessage
    public void onMessage(String message, Session session) throws IOException,
            InterruptedException {
        System.out.println("User input: " + message);
        session.getBasicRemote().sendText("Hello world Mr. " + message);
        // Sending message to client each 1 second
        for (int i = 0; i <= 25; i++) {
            session.getBasicRemote().sendText(i + " Message from server");
            Thread.sleep(1000);
 
        }
    }
 
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Client connected");
        int user_id = 0;
        try{
        	String url = session.getRequestURI().toString().replaceAll("&encoding=text","");
        	System.out.println(" url --> "+url);
        	int index = url.lastIndexOf("abc=");
        	user_id= Integer.parseInt(url.substring(index+4,url.length()));
            System.out.println("user_id connected"+user_id);

        	}catch(Exception e){
        		e.printStackTrace();
        	
        }
        
        if(user_id !=0)
        sessionUserIdMap.put(session,user_id);
        //clients.add(session);

        try {
        	session.getBasicRemote().sendText("Hello world Mr. " + "Ram");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
 
    @OnClose
    public void onClose(Session session) {
        System.out.println("Connection closed");
        try {
        	session.getBasicRemote().sendText("Hello world Mr. " + "Ram closed");
        	sessionUserIdMap.remove(session);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
   public static void sendMessage(int user_id) throws IOException{
	   for(Session session:sessionUserIdMap.keySet()){
		   System.out.println("sessionUserIdMap.get(session) "+sessionUserIdMap.get(session));
		   if(sessionUserIdMap.get(session) == user_id){
			   System.out.println("Match Found");
	        	session.getBasicRemote().sendText("this message is specific to "+user_id);

		   }else{
			   System.out.println("Match not found");
		   }
	   }
   }
    
}
