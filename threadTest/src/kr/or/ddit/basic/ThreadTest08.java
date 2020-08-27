package kr.or.ddit.basic;

public class ThreadTest08 {
	public static void main(String[] args) {
		Thread ut = new UpperThread();
		Thread lt = new LowerThread();
		
//		ut.setPriority(9);
//		lt.setPriority(3);
		
		System.out.println("ut의 우선순위 : " + ut.getPriority());
		System.out.println("lt의 우선순위 : " + lt.getPriority());
		
		ut.start();
		lt.start();
	}
}

// 대문자를 출력하는 쓰레드
class UpperThread extends Thread{
	@Override
	public void run() {
		for (char c = 'A'; c <= 'Z'; c++) {
			System.out.println(c);
			
			for (long i = 0; i <= 5000000000L; i++) {	// 아무것도 안하는 반복문, 시간 때우기 용도
				
			}
		}
	}
}

class LowerThread extends Thread{
	@Override
	public void run() {
		for (char c = 'a'; c <= 'z'; c++) {
			System.out.println(c);
			
			for (long i = 0; i <= 5000000000L; i++) {
				
			}
		}
	}
}