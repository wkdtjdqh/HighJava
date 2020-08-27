package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
	컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오.
	
	컴퓨터의 가위바위보는 난수를 이용해서 구한다.
	사용자의 가위바위보는 showInputDialog()를 이용해서 입력받는다.
	
	입력시간은 5초로 제한한다.
	5초안에 입력이 없으면 패배처리한다.
	
	입력후 승패를 구해 출력한다.
	
	결과 예시)
	컴퓨터 : 가위
	사용자 : 바위
	결과 : 당신이 이겼습니다.
*/
public class ThreadTest07 {
	public static void main(String[] args) {
		Thread th1 = new GameThread();
		Thread th2 = new CountDownThread();
		th1.start();
		th2.start();
	}
}

class GameThread extends Thread {	// 게임시작 쓰레드
	public static boolean inChk;
	
	public String play(String input, String computer) {	// 승패 결정 메서드
		String result = "";
		if (input.equals("가위")) {
			switch (computer) {
			case "가위":	result = "비겼습니다."; break;
			case "바위":	result = "당신이 졌습니다."; break;
			case "보":	result = "당신이 이겼습니다."; break;
			}
		} else if (input.equals("바위")) {
			switch (computer) {
			case "바위":	result = "비겼습니다."; break;
			case "보":	result = "당신이 졌습니다."; break;
			case "가위":	result = "당신이 이겼습니다."; break;
			}
		} else if (input.equals("보")) {
			switch (computer) {
			case "보":	result = "비겼습니다."; break;
			case "가위":	result = "당신이 졌습니다."; break;
			case "바위":	result = "당신이 이겼습니다."; break;
			}
		} else {
			result = "당신이 졌습니다.";
		}
		return result;
	}
	
	@Override
	public void run() {
		String[] com = {"가위", "바위", "보"};
		String input = "";
		do{
			input = JOptionPane.showInputDialog("가위, 바위, 보를 선택하세요");	// 사용자의 가위바위보 선택
			if (input == null) {
				System.out.println("가위바위보를 포기하고 종료합니다.");
				inChk = true;
				System.exit(0);
			}
		} while(!(input.equals("가위") || input.equals("바위") || input.equals("보")));
		inChk = true;
		
		String computer = com[(int)(Math.random()*3)];	// 컴퓨터의 가위바위보 선택
		
		String result = play(input, computer);	// 사용자와 컴퓨터의 승패 결정
		
		System.out.println("---- 결과 ----");
		System.out.println("컴퓨터 : " + computer);
		System.out.println("사용자 : " + input);
		System.out.println("-------------");
		System.out.println("결과 : " + result);
	}
}

class CountDownThread extends Thread {	// 카운트다운 쓰레드
	@Override
	public void run() {
		for (int i = 10; i > 0; i--) {
			if (GameThread.inChk) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("입력이 없어 당신이 졌습니다.");
		System.exit(0);
	}
}