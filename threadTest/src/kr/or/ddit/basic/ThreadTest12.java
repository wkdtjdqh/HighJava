package kr.or.ddit.basic;

// 3개의 쓰레드가 각각 알파벳을 A~Z까지 출력하는데 출력을 끝낸 순서대로 결과를 나타내는 프로그램
public class ThreadTest12 {
	public static void main(String[] args) {
		DisplayChar[] disp = new DisplayChar[] {
			new DisplayChar("홍길동"),	
			new DisplayChar("이순신"),	
			new DisplayChar("고길동")	
		};
		
		for (DisplayChar dc: disp) {
			dc.start();
		}
		
		for (DisplayChar dc: disp) {
			try {
				dc.join();
			} catch (InterruptedException e) { }
		}
		
		System.out.println();
		System.out.println("경기 결과" );
		System.out.println("순위 : " + DisplayChar.setRank);
	}
}

// A~Z까지 출력하는 메서드
class DisplayChar extends Thread {
	public static String setRank = "";
	private String name;
	
	public DisplayChar(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (char c = 'A'; c <= 'Z'; c++) {
			System.out.println(name + "의 출력 : " + c);
			
			try {
				Thread.sleep((int)(Math.random()*300)+201);
			} catch (Exception e) {
			}
		}
		System.out.println(name + " 출력 끝");
		
		DisplayChar.setRank += name + " ";
	}
}
