package screenController;

import back.ClientBackground;
import frame.ClientGui;
import frame.ClientGui2;
import frame.ClientGui3;
import frame.ClientGui4;
import frame.YowonGui1;
import frame.YowonGui2;
import frame.YowonGui3;

public class ScreenController {
	private ClientGui clientGui;
	private ClientGui2 clientGui2;
	private ClientGui3 clientGui3;
	private ClientGui4 clientGui4;
	
	private YowonGui1 yowonGui1;
	private YowonGui2 yowonGui2;
	private YowonGui3 yowonGui3;
	
	private ClientBackground clientBackground;
	

	public static void main(String[] args) {
		ScreenController screenController = new ScreenController();

	}

	public void showClientGui2(ClientGui clientGui, ClientBackground client) {
		clientGui.dispose();
		this.clientBackground = client;
		ClientGui2 clientGui2 = new ClientGui2(clientBackground);
		System.out.println("1-3 ���� gui�� ���� ���� clientBackground�� ���� ����� ��� clientgUI2���� �����ڷ� ������.");
		clientBackground.setClientGui2(clientGui2);
		System.out.println(
				"1-3 ScreenController.ShowClientGui2 : clientgui2�� ��ȯ�ϸ鼭 client�� back�� �Բ� �����ְ�, clientBack�� gui2�� ��������. ");
	}

	public void showYowonGui1(ClientGui clientGui, ClientBackground client) {
		clientGui.dispose();
		this.clientBackground = client;
		YowonGui1 yowonGui1 = new YowonGui1(clientBackground);
		this.yowonGui1 = yowonGui1;
	}

	public void showYowonGui2(YowonGui1 yowonGui1, ClientBackground client) {
		yowonGui1.dispose();
		this.clientBackground = client;
		YowonGui2 yowonGui2 = new YowonGui2(clientBackground);
		this.yowonGui2 = yowonGui2;
	}

	public void showYowonGui3(YowonGui1 yowonGui1, ClientBackground client) {
		yowonGui1.dispose();
		this.clientBackground = client;
		YowonGui3 yowonGui3 = new YowonGui3(clientBackground);
		this.yowonGui3 = yowonGui3;
		
	}

	//��� �� �������� �������� �� ���
	public void showYowonGui3(YowonGui2 yowonGui2, ClientBackground client) {
		yowonGui2.dispose();
		this.clientBackground = client;
		YowonGui3 yowonGui3 = new YowonGui3(clientBackground);
	}

	public void showClientGui3(ClientGui2 clientGui2, ClientBackground clientBackground) {
		clientGui2.dispose();
		this.clientBackground = clientBackground;
		ClientGui3 clientGui3 = new ClientGui3(clientBackground);
	}

	public void showClientGui4(ClientGui2 clientGui2, ClientBackground clientBackground) {
		clientGui2.dispose();
		this.clientBackground = clientBackground;
		ClientGui4 clientGui4 = new ClientGui4(clientBackground);
	}

	
	
	///clientGui4���� ����ϴ�  �޼��� 
	public void showClientGui2(ClientGui4 clientGui4, ClientBackground clientBackground) {
		clientGui4.dispose();
		this.clientBackground = clientBackground;
		ClientGui2 clientGui2 = new ClientGui2(clientBackground);
	
	}

	public void showClientGui3(ClientGui4 clientGui4, ClientBackground clientBackground) {
		clientGui4.dispose();
		this.clientBackground = clientBackground;
		ClientGui3 clientGui3 = new ClientGui3(clientBackground);
		
	}

	public void showClientGui4(ClientGui3 clientGui3, ClientBackground clientBackground2) {
		clientGui3.dispose();
		this.clientBackground = clientBackground;
		ClientGui4 clientGui4 = new ClientGui4(clientBackground);
	}


}
