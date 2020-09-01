package kr.or.ddit.basic;
/*
	wait(), notify()메서드를 이용하여 두 쓰레드가 번갈아 한번씩 실행되는 예제
	
	wait(), notify(), notifyAll()메서드는 동기화 영역에서만 사용 가능하다.
*/
public class ThreadTest20 {
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}
}

// 공통으로 사용할 객체의 클래스 작성
class WorkObject {
	public synchronized void test1() {
		System.out.println("test1()메서드 실행중 ..");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
		}
	}
	
	public synchronized void test2() {
		System.out.println("------------------- test2()메서드 실행중 ..");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
		}
	}
}

// WorkObject의 test1()메서드만 호출하는 쓰레드
class ThreadA extends Thread {
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			workObj.test1();
		}
		
		synchronized (workObj) {	// 일시정지 풀에 남아있는 마지막 쓰레드를 깨우기 위함
			workObj.notify();
		}
	}
}

// WorkObject의 test2()메서드만 호출하는 쓰레드
class ThreadB extends Thread {
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			workObj.test2();
		}
		
		synchronized (workObj) {
			workObj.notify();
		}
	}
}