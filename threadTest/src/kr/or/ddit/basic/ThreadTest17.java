package kr.or.ddit.basic;
/*
	은행의 입출금 작업을 쓰레드로 처리하는 예제
	synchronized를 이용한 동기화 처리
*/
public class ThreadTest17 {
	private int balance;	// 잔액이 저장될 변수
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	// 입금을 처리하는 메서드
	public void deposit(int money) {
		balance += money;
	}
	
	// 출금을 처리하는 메서드
	// 동기화 영역에서 호출하는 메서드도 동기화 처리를 해줘야 안전하다.
	public synchronized boolean withdraw(int money) {
		if (balance >= money) {	// 잔액 확인
			for(int i = 1; i <= 100000000; i++){}
			balance -= money;
			System.out.println("메서드 안에서 balance = " + balance);
			// System.out.println("메서드 안에서 balance = " + getBalance());	getBalance에서 동기화 처리를 해줘야 한다.	
			return true;
		} else return false;
	}

	public static void main(String[] args) {
		final ThreadTest17 account = new ThreadTest17();
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
