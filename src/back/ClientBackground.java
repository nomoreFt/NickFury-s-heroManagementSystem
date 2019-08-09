package back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import frame.ClientGui;
import frame.ClientGui2;
import frame.ClientGui3;
import frame.ClientGui4;
import frame.YowonGui1;
import frame.YowonGui2;
import frame.YowonGui3;
 
public class ClientBackground {
 
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    //
    private ClientGui clientGui1;
    private ClientGui2 clientGui2;
    private ClientGui3 clientGui3;
    private ClientGui4 clientGui4;
    //국장 gui
    
    //요원 gui
    private YowonGui1 yowonGui1;
    private	 YowonGui2 yowonGui2;
    private YowonGui3 yowonGui3;
    //
    private String msg;
    private String nickName;
	
 
    public final void setClientGui1(ClientGui clientGui1) {
        this.clientGui1 = clientGui1;
    }
    
    public final void setClientGui2(ClientGui2 clientGui2) {
        this.clientGui2 = clientGui2;
    }
    
    public final void setClientGui3(ClientGui3 clientGui3) {
        this.clientGui3 = clientGui3;
    }
    public final void setClientGui4(ClientGui4 clientGui4) {
        this.clientGui4 = clientGui4;
    }
    
    public final void setYowonGui1(YowonGui1 yowonGui1) {
        this.yowonGui1 = yowonGui1;
    }
    
    public final void setYowonGui2(YowonGui2 yowonGui2) {
    	this.yowonGui2 = yowonGui2;
    }
    
    public final void setYowonGui3(YowonGui3 yowonGui3) {
    	this.yowonGui3 = yowonGui3;
    }
    



    public void connet() {
        try {
            socket = new Socket("127.0.0.1", 7777);
            System.out.println("서버와 연결됨.");
             
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
             
            //시작부터 닉네임을 받는다.
            out.writeUTF(nickName); 
            System.out.println("Clientback : 서버로 nickname 보냈습니다." + nickName);
            while(in!=null){
                msg=in.readUTF();
                System.out.println("5. serverserver에서 받은 nick+id를  clientserver에서 읽음");
                System.out.println(msg);
                if(clientGui2 != null)
                clientGui2.appendMsg(msg+"");
                if(yowonGui1 != null)
                yowonGui1.appendMsg(msg+"");
                System.out.println("6.clientserver에서 server에서 받아 msg변수에 저장한 내용을 gui2 textarea에 append");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 //gui2의 채팅창에 append가 안됨.
    public static void main(String[] args) {
        ClientBackground clientBackground = new ClientBackground();
        clientBackground.connet();
    }
 
    public void sendToServer(String msg) {
        try {
            out.writeUTF(msg);
            System.out.println("2.클라이언트서버에서 서버서버로 gui1 textfield에서 받은 id전송 완료.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public void setNickname(String nickName) {
        this.nickName = nickName;
    }
    public String getNickname() {
    	return this.nickName;
    }




}