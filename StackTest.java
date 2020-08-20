package kr.or.ddit.basic;

import java.util.LinkedList;

public class StackTest {
	public static void main(String[] args) {
		Browser b = new Browser();
		
		b.goURL("1.네이버");
		b.goURL("2.구글");
		b.goURL("3.다음");
		b.goURL("4.야후");
		
		b.history();
		System.out.println("뒤로가기");
		b.goBack();
		b.history();
		
		System.out.println("뒤로가기");
		b.goBack();
		b.history();
		
		System.out.println("앞으로가기");
		b.goForward();
		b.history();
		
		System.out.println("새로운 사이트 접속");
		b.goURL("5.네이트");
		b.history();
	}
}

class Browser {
	private LinkedList<String> back;		// 이전 방문내역이 저장될 스택 변수
	private LinkedList<String> forward; 	// 다음 방문내역이 저장될 스택 변수
	private String currentURL;				// 현재 페이지
	
	// 생성자
	public Browser() {
		back = new LinkedList<>();
		forward = new LinkedList<>();
		currentURL = "";
	}
	
	// 사이트를 방문하는 메서드, 매개변수에 방문할 URL 입력
	public void goURL(String url) {
		if (currentURL != null && !"".equals(currentURL)) {
			back.push(currentURL); // 현재 페이지를 back stack변수에 추가한다.
		}
		currentURL = url; // 새로운 현재 페이지로 변경한다.
		
	}
	
	//뒤로 가기
	public void goBack() {
		if (!back.isEmpty()) {
			forward.push(currentURL);	// 현재 페이지를 forward 스택에 추가한다.
			currentURL = back.pop();	// back 스택에서 1개의 데이터를 꺼내와 현재 페이지로 설정한다.
		}
	}
	
	//앞으로 가기
	public void goForward() {
		if (!forward.isEmpty()) {
			back.push(currentURL);		// 현재 페이지를 back 스택에 추가한다.
			currentURL = forward.pop();	// forward 스택에서 1개의 데이터를 꺼내와 현재 페이지로 설정한다.
		}
	}
	
	// 방문 기록을 확인
	public void history() {
		System.out.println("------------------------------------");
		System.out.println("          방       문      기     록");
		System.out.println("------------------------------------");
		System.out.println("    back : " + back);
		System.out.println("       현재 : " + currentURL);
		System.out.println(" forward : " + forward);
		System.out.println("------------------------------------");
	}
}