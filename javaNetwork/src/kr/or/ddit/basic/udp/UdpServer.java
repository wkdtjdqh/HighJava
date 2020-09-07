package kr.or.ddit.basic.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
	UDP방식
	1. 비연결 지향
	2. 데이터의 신뢰성이 없다.
	3. 데이터가 순서대로 도착하는 보장이 없다.
	4. TCP방식보다 속도가 빠르다
	
	- DatagramSocket객체와 DatagramPacket객체를 이용하여 통신한다.
	  1) DatagramSocket : 데이터의 송수신과 관련된 작업을 수행한다. (우체부)
	  2) DatagramPacket : 주고받는 데이터와 관련된 작업을 수행한다. (소포)
	  		-> 수신을 위한 생성자와 송신을 위한 생성자를 따로 제공한다.
	- TCP방식의 경우에는 스트림을 이용해서 송수신하지만, UDP방식의 경우에는 데이터그램을 이용해서 송수신한다.
	
	데이터를 바이트형으로만 처리한다.
*/
public class UdpServer {
	public static void main(String[] args) {
		try {
			// 통신할 포트번호를 지정하여 소켓을 생성한다.
			DatagramSocket socket = new DatagramSocket(8888);
			// 송신용 패킷변수와 수신용 패킷변수 선언
			DatagramPacket inpacket, outpacket;
			
			System.out.println("서버 실행 중..");
			
			while(true) {
				// 데이터가 저장될 byte형 배열 변수 선언
				byte[] bMsg = new byte[1024];
				/*
					수신용 패킷객체 생성
						=> 생성자에 데이터가 저장될 byte형 배열과 배열의 길이를 넣어서 생성한다.
				*/
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				/*
					데이터를 수신한다. ==> receive()메서드를 이용한다.
						이 메서드를 데이터가 올 때까지 기다린다.
						수신된 데이터의 패킷정보는 지정된 패킷변수에 저장된다.
				*/
				socket.receive(inpacket);
				
				// 수신받은 패킷에서 상대방의 주소, 포트번호 등을 알 수 있다.
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				
				System.out.println("상대방의 IP정보 : " + address);
				System.out.println("상대방의 PORT정보 : " + port);
				System.out.println();
				/*
					상대방이 보낸 메시지 출력하기
						- 수신용 패킷변수.getLength(); ==> 실제 읽어온 데이터의 길이를 반환한다.
						- 수신용 패킷변수.getData();	  ==> 실제 읽어온 데이터를 byte형 배열로 반환한다. 
											실제 읽어온 데이터는 수신용 패킷을 생성할 때 지정한 배열에도 저장된다.
				*/
				// 실제 읽어온 데이터 길이만큼을 문자열로 변환한다.
//				String receiveMsg = new String(bMsg, 0, inpacket.getLength());
				String receiveMsg = new String(inpacket.getData(), 0, inpacket.getLength());
				
				System.out.println("클라이언트가 보내온 메시지 : " + receiveMsg);
				System.out.println();
				
				// 상대방에게 데이터 송신하기 (수신한 데이터를 그대로 송신하는 예제)
				
				// 송신할 데이터를 byte형 배열로 변환한다.
				byte[] sendMsg = receiveMsg.getBytes();
				
				if ("/end".equals(sendMsg)) {	// '/end' 메시지가 오면 접속 종료
					break;
				}
				/*
					송신용 패킷 객체 생성하기
						==> 생성자에 '전송할 데이터가 저장된 byte형 배열', '전송할 데이터의 길이(배열의 길이)', '상대방 주소 정보', '상대방 포트번호'
							위 4가지를 생성자에 넣어서 생성한다.
				*/
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				// 데이터 송신하기 ==> send()메서드를 이용한다.
				socket.send(outpacket);
				
				System.out.println("송신 완료");
				
			}
			System.out.println("서버 종료");
			socket.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
