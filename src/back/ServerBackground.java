package back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import frame.ServerGui;
 
public class ServerBackground {
 
  
    private ServerSocket serverSocket;
    private Socket socket;
    private ServerGui gui;
    private String msg;
 
    
    private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();
 
    public final void setGui(ServerGui gui) {
        this.gui = gui;
    }
 
    public void setting() throws IOException {
            Collections.synchronizedMap(clientsMap); 
            serverSocket = new ServerSocket(7777);
            System.out.println(serverSocket.getLocalPort() + "포트로 연결 완료했습니다.");
            while (true) {
              
                System.out.println("서버 대기중...");
                socket = serverSocket.accept(); 
                System.out.println(socket.getInetAddress() + "에서 접속했습니다.");
                
                Receiver receiver = new Receiver(socket);
                receiver.start();
            }
    }
 
    public static void main(String[] args) throws IOException {
        ServerBackground serverBackground = new ServerBackground();
        serverBackground.setting();
    }
 
    public void addClient(String nick, DataOutputStream out) throws IOException {
        sendToAll(nick + "님이 입장하셨습니다.\n");
        //System.out.println(nick + "님을 map에 추가");
        clientsMap.put(nick, out);
    }
 
    public void removeClient(String nick) {
        sendToAll(nick + "님이 나가셨습니다.\n");
        clientsMap.remove(nick);
    }
 
    public void sendToAll(String msg) {
        Iterator<String> it = clientsMap.keySet().iterator();
        String key = "";
        while (it.hasNext()) {
            key = it.next();
            try {
                clientsMap.get(key).writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    // -----------------------------------------------------------------------------
    class Receiver extends Thread {
        private DataInputStream in;
        private DataOutputStream out;
        private String nick;
 
        public Receiver(Socket socket) throws IOException {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            nick = in.readUTF();
            gui.appendMsg(nick + "접속했습니다.\n");
            addClient(nick, out);
        }
 
        public void run() {
            try {
                while (in != null) {
                    msg = in.readUTF();
                    gui.appendMsg("["+nick + "]"+msg + "\n");
                    System.out.println("3. 서버에서 Clientback에서 보낸 id 받고 자기 자신의 textarea에 append 완료." + msg);
                    sendToAll("[" + nick + "]" + msg + "\n");
                    System.out.println("4. 서버에서 받은 id를 nick과 함께 clientback으로 전송");
                   // gui.appendMsg(msg);
                }
            } catch (IOException e) {
                
                removeClient(nick);
            }
        }
    }
	
}