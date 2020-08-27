package kr.or.ddit.basic;

public class ThreadTest02 {
	public static void main(String[] args) {
		/*
			Thread를 사용하는 방법
			방법1)
				Thread클래스를 상속한 Class를 작성하고, 이 클래스의 인스턴스를 생성한 후 이 인스턴스의 start()메서드를 호출해서 실행한다.
		*/		
		MyThread th1 = new MyThread();
		th1.start();
		
		/*
			방법2)
				Runnable인터페이스를 구현한 Class를 작성하고, 이 클래스의 인스턴스를 생성한 후 이 인스턴스를 Thread객체를 생성할 때 생성자의 매개값으로 넘겨준다.
				이때 생성된 Thread의 인스턴스의 start()메서드를 호출해서 실행한다.
		*/
		Runnable r1 = new MyRun();
		Thread th2 = new Thread(r1);
		th2.start();
		
		/*
			방법3) 익명 구현체를 이용하는 방법
				메서드 이름없이 인터페이스를 구현하듯이 사용이 가능하다.
		*/
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 200; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {}
				}
			}
		};
		Thread th3 = new Thread(r2);
		th3.start();
	}
}

// 방법1
class MyThread extends Thread {
	@Override
	public void run() {
		// 이 run()메서드 안에 Thread에서 처리할 내용을 기술한다.
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
			try {
				// Thread.sleep(ms); 이 메서드는 주어진 시간(ms단위)동안 작업을 잠시 멈춘다.
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
	}
}

// 방법2
class MyRun implements Runnable {
	@Override
	public void run() {
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
	}
}