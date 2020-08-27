package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {
	public static void main(String[] args) {
		Thread th1 = new DataInputThread();
		Thread th2 = new CountThread();
		th1.start();
		th2.start();
	}
}

// 데이터를 입력하는 쓰레드
class DataInputThread extends Thread {
	public static boolean inputCheck;	// 입력 여부를 확인하기 위한 변수 => 쓰레드에서 공통으로 사용할 변수다.
	
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요");
		inputCheck = true;
		System.out.println("입력값 : " + str);
	}
}

// 카운트 다운을 진행하는 쓰레드
class CountThread extends Thread {
	@Override
	public void run() {
		for(int i = 10; i > 0; i--) {
			if (DataInputThread.inputCheck) {	// 입력이 완료되었는지 검사해서 완료되었으면 쓰레드를 종료시킨다.
				return;	// run()메서드가 종료되면 쓰레드도 종료된다.
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {}
		}
		System.out.println("지정된 시간이 경과했습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
}