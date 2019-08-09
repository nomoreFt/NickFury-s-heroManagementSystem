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
            System.out.println(serverSocket.getLocalPort() + "��Ʈ�� ���� �Ϸ��߽��ϴ�.");
            while (true) {
              
                System.out.println("���� �����...");
                socket = serverSocket.accept(); 
                System.out.println(socket.getInetAddress() + "���� �����߽��ϴ�.");
                
                Receiver receiver = new Receiver(socket);
                receiver.start();
            }
    }
 
    public static void main(String[] args) throws IOException {
        ServerBackground serverBackground = new ServerBackground();
        serverBackground.setting();
    }
 
    public void addClient(String nick, DataOutputStream out) throws IOException {
        sendToAll(nick + "���� �����ϼ̽��ϴ�.\n");
        //System.out.println(nick + "���� map�� �߰�");
        clientsMap.put(nick, out);
    }
 
    public void removeClient(String nick) {
        sendToAll(nick + "���� �����̽��ϴ�.\n");
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
            gui.appendMsg(nick + "�����߽��ϴ�.\n");
            addClient(nick, out);
        }
 
        public void run() {
            try {
                while (in != null) {
                    msg = in.readUTF();
                    gui.appendMsg("["+nick + "]"+msg + "\n");
                    System.out.println("3. �������� Clientback���� ���� id �ް� �ڱ� �ڽ��� textarea�� append �Ϸ�." + msg);
                    sendToAll("[" + nick + "]" + msg + "\n");
                    System.out.println("4. �������� ���� id�� nick�� �Բ� clientback���� ����");
                   // gui.appendMsg(msg);
                }
            } catch (IOException e) {
                
                removeClient(nick);
            }
        }
    }
	
}