package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

// 이 클래스는 소켓을 통해서 메시지를 받아 화면에 출력하는 역할을 담당한다.
public class Receiver extends Thread{
	private Socket socket;
	private DataInputStream dis;
	
	public Receiver(Socket socket) {
		this.socket = socket;
		
		try {
			// 수신용 스트림 객체 생성
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	@Override
	public void run() {
		while(dis != null){
			try {
				System.out.println(dis.readUTF());
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
	}
}
