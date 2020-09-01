package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
	은행의 입출금 작업을 쓰레드로 처리하는 예제
	Lock객체를 이용한 동기화 처리
*/
public class ThreadTest18 {
	private int balance;	// 잔액
	
	// Lock객체 생성 ==> 되도록이면 private final로 만든다.
	private final Lock lock = new ReentrantLock();
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	// 입금하는 메서드
	public void deposit(int money) {
		// Lock객체는 lock()메서드로 락을 설정하고 반드시 unlock()메서드로 락을 해제해 주어야 한다.
		lock.lock();	// 락 설정
		
		balance += money;
		
		lock.unlock();	// 락 해제
	}
	
	// 출금하는 메서드
	public boolean withdraw(int money) {
		boolean chk = false;
		
		lock.lock();	// 락 설정
		
		if (balance >= money) {	// 잔액 확인
			for(int i = 1; i <= 100000000; i++){}	// 시간 지연
			balance -= money;
			System.out.println("메서드 안에서 balance = " + getBalance());
			chk = true;
		} else chk = false;
		
		lock.unlock();	// 락 해제
		
		// 만약 try-catch블럭이 사용되는 부분에서 unlock()메서드를 호출할 때는 finally영역에서 호출하도록 한다.
		
		return chk;
	}
	
	public static void main(String[] args) {
		final ThreadTest18 account = new ThreadTest18();
		account.setBalance(10000);	// 잔액을 10000원으로 설정한다.
		
		// Runnable의 익명 구현체
		Runnable r1 = new Runnable() {	// 익명 구현체 안에서 지역번수를 사용하려면 final이어야 한다.
			@Override
			public void run() {
				boolean result = account.withdraw(6000);	// 6000원 출금
				System.out.println("쓰레드에서 result = " + result + ", balance = " + account.getBalance());
			}
		};
		
		Thread th1 = new Thread(r1);
		Thread th2 = new Thread(r1);
		
		th1.start();
		th2.start();
	}
}
