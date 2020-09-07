package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient2 {
	public static void main(String[] args) {
		new TcpMultiChatClient2().clientStart();
	}
	
	// 클라이언트가 시작하는 메서드
	private void clientStart() {
		Socket socket = null;
		
		try {
			String serverIp = "localhost";
			socket = new Socket(serverIp, 7777);
			
			System.out.println("서버에 연결되었습니다.");
			System.out.println();
			
			ClientSender sender = new ClientSender(socket);
			ClienReceiver recevier = new ClienReceiver(socket);
			
			sender.start();
			recevier.start();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	} // 클라이언트 메서드 끝
	
	// 메시지 전송용 쓰레드
	class ClientSender extends Thread {
		private Socket socket;
		private DataOutputStream dos;
		private DataInputStream dis;
		private String name;
		private Scanner scan;
		
		// 생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);
			
			try {
				// 전송용 스트림 객체 생성
				dos = new DataOutputStream(socket.getOutputStream());
				// feedback수신용 스트림 객체 생성
				dis = new DataInputStream(socket.getInputStream());
				
				if (dos != null) {
					// 클라이언트 프로그램이 처음 실행되면 자신의 대화명을 입력받아 서버로 전송하고 대화명의 중복 여부를 feedback으로 받아서 확인한다.
					System.out.print("대화명 : ");
					String irum = scan.nextLine();
					
					while(true) {
						dos.writeUTF(irum);	// 대화명을 서버로 전송
						
						String feedback = dis.readUTF();	// 대화명의 중복여부를 서버로부터 받는다.
						if ("이름중복".equals(feedback)) {	// 대화명이 중복될 때
							System.out.println(irum + "은 중복됩니다.");
							System.out.println("다른 대화명을 입력하세요.");
							System.out.print("대화명 : ");
							irum = scan.nextLine();
						} else {	// 대화명이 중복되지 않을 때
							name = irum;
							System.out.println(name + " 이름으로 대화방에 입장했습니다.");
							break;	// 반복문 탈출
						}
					}
					
				}
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
		
		@Override
		public void run() {
			try {
				while(dos != null) {
					// 대화 내용을 입력해서 서버로 전송한다.
					dos.writeUTF("[" + name + "] " + scan.nextLine());
				}
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
		
	} // 메시지 전송용 쓰레드 끝
	
	// 메시지 수신용 쓰레드
	class ClienReceiver extends Thread {
		private Socket socket;
		private DataInputStream dis;
		
		// 생성자
		public ClienReceiver(Socket socket) {
			this.socket = socket;
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
		
		@Override
		public void run() {
			try {
				while(dis != null) {
					// 서버에서 받은 메시지를 화면에 출력한다.
					System.out.println(dis.readUTF());
				}
			} catch (IOException  e) {
				// TODO: handle exception
			}
		}
	}
}
