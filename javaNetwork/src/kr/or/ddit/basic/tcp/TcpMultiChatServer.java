package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {
	/*
		접속한 클라이언트 정보를 저장할 Map객체 선언
			Key : 접속한 사람의 이름
			Value : 접속한 클라이언트와 연결한 Socket객체
	*/
	private Map<String, Socket> clientMap;
	
	// 생성자
	public TcpMultiChatServer() {
		// clientMap을 동기화 처리가 되도록 생성한다.
		clientMap = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	public static void main(String[] args) {
		new TcpMultiChatServer().serverStart();
	}
	
	// 서버가 시작하는 메서드
	private void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			while(true) {
				socket = server.accept();	// 클라이언트의 접속을 기다린다.
				
				// 접속이 완료되면 ..
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "] 에서 접속했습니다.");
				
				// 데이터를 받아서 전체에게 전송하는 쓰레드 객체 생성 및 실행
				ServerReceiver serverThread = new ServerReceiver(socket);
				serverThread.start();
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}// 서버 시작 메서드 끝
	
	// clientMap에 저장된 전체 사용자에게 메시지를 전송하는 메서드
	private void sendToAll(String msg){
		// clientMap에 저장된 데이터 개수만큼 반복
		for(String name : clientMap.keySet()) {
			try {
				// 각각의 클라이언트와 연결된 소켓에서 OutputStream객체를 구한다.
				DataOutputStream dos = new DataOutputStream(clientMap.get(name).getOutputStream());
				dos.writeUTF(msg);
				
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
	}// 전체사용자 메시지 전송 메서드 종료
	
	// Inner Class로 각각의 클라이언트가 보내온 메시지를 클라이언트로 전송하는 Thread를 만든다.
	class ServerReceiver extends Thread {
		private Socket socket;
		private DataOutputStream dos;
		private DataInputStream dis;
		
		// 생성자
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				// 송신용 객체 생성
				dos = new DataOutputStream(socket.getOutputStream());
				// 수신용 개체 생성
				dis = new DataInputStream(socket.getInputStream());
				
			} catch (IOException e) {
				// TODO: handle exception
			}
		} // 생성자 종료
		
		@Override
		public void run() {
			String name = "";	// 클라이언트가 맨 처음 보내온 이름 데이터를 저장할 변수 선언
			try {
				/*
					클라이언트가 이름 데이터를 보내오면 이름이 중복되는지 검사해서 결과를 클라이언트에게 feedback으로 보낸다.
						이름이 중복되면 '이름중복', 중복되지 않으면 'OK'를 feedback으로 보낸다.
				*/
				while(true) {
					name = dis.readUTF();	// 이름 받기
					if (clientMap.containsKey(name)) {	// 이름이 중복되면..
						dos.writeUTF("이름중복");
					} else {
						dos.writeUTF("OK");
						break;
					}
				}
				// 현재 접속자가 보내온 이름으로 이전에 접속되어 있는 다른 클라이언트들에게 대화방 참여 메시지를 보낸다.
				sendToAll("[" + name + "]님이 입장하셨습니다.");
				
				// 대화명(이름)과 Socket객체를 Map에 추가된다.
				clientMap.put(name, socket);
				
				System.out.println("현재 서버 접속한 인원 : " + clientMap.size() + "명");
				
				// 한 클라이언트가 보내온 메시지를 전체 클라이언트에게 보낸다.
				while(dis != null) {
					sendToAll(dis.readUTF());
				}
				
			} catch (IOException e) {
				// TODO: handle exception
			} finally {
				//이 finally 영역이 실행된다는 것은 클라이언트가 접속을 종료했다는 의미
				sendToAll("[" + name + "]님이 대화방을 나갔습니다.");
				
				// 사용자 목록에서 삭제한다.
				clientMap.remove(name);
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속을 종료했습니다.");
				
				System.out.println("현재 서버에 접속한 인원 : " + clientMap.size() + "명");
			}
		}
	}
}
