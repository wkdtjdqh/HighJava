package kr.or.ddit.basic.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import javax.sound.sampled.Port;

public class UdpClient {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// 송신용, 수신용 패킷변수 선언
		DatagramPacket outpacket, inpacket;
		
		// 수신 받은 데이터가 저장될 byte형 배열 선언
		byte[] bMsg = new byte[1024];
		
		try {
			DatagramSocket socket = new DatagramSocket();
			
			// 접속할 서버의 주소 생성
			InetAddress address = InetAddress.getByName("localhost");	// IP주소를 입력해도 된다.
			
			while(true) {
				// 전송할 메시지를 입력받는다.
				System.out.println("보낸 메시지 입력 : ");
				String msg = scan.nextLine();
				
				// 전송할 패킷객체 생성
				outpacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, 8888);
				
				// 전송하기
				socket.send(outpacket);
				
				if ("/end".equals(msg)) {	// '/end'가 입력되면 접속 종료
					break;
				}
				
				// 서버에서 온 데이터를 받아서 출력하기
				// 수신용 패킷객체 생성
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 데이터 수신
				socket.receive(inpacket);
				
				// 출력
				System.out.println("서버의 응답 데이터 : " + new String(bMsg, 0, inpacket.getLength()));
			}
			
			System.out.println("통신 끝");
			socket.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
