package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest1 {
	public static void main(String[] args) throws UnknownHostException {
		// InetAddress Class ==> IP주소를 다루기 위한 클래스
		
		// 네이버 사이트의 IP정보 가져오기
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		
		System.out.println("Naver Host Name : " + naverIp.getHostName());
		System.out.println("Naver Host Address : " + naverIp.getHostAddress());
		System.out.println();
		
		// 자신의 컴퓨터의 IP정보 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		
		System.out.println("My Host Name : " + localIp.getHostName());
		System.out.println("My Host Address : " + localIp.getHostAddress());
		System.out.println();
		
		// IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] ipArr = InetAddress.getAllByName("www.naver.com");
		for(InetAddress ip : ipArr){
			System.out.println(ip.toString());
		}
	}
}
