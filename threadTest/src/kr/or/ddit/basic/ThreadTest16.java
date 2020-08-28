package kr.or.ddit.basic;

public class ThreadTest16 {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		TestThread th1 = new TestThread("Test1", sObj);
		TestThread th2 = new TestThread("Test2", sObj);
		
		th1.start();
		th2.start();
	}
}

class ShareObject {
	private int sum;
	
	// 동기화 처리 하기
	// public synchronized void add() {	// 동기화 방법1 : 메서드 자체에 동기화 설정을 한다. synchronized
	public void add() {
		synchronized (this) {	// 동기화 방법2 : 동기화 블럭으로 설정한다.
			int n = sum;
			
			n += 10; // 10증가
			
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
		}
	}
}

class TestThread extends Thread {
	private ShareObject sObj;
	
	public TestThread(String name, ShareObject sObj) {
		super(name);	// 쓰레드의 name설정
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			sObj.add();
		}
	}
}