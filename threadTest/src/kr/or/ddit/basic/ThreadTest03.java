package kr.or.ddit.basic;

public class ThreadTest03 {
	public static void main(String[] args) {
		// 쓰레드가 수행되는 시간 체크해 보기
		MyThread1 th1 = new MyThread1();
		
		//1970년 1월 1일 0시 0분 0초 (표준시간)로 부터 경과한 시간을 ms단위로 반환한다.
		long startTime = System.currentTimeMillis();
		th1.start();
		try {
			th1.join(); // 현재 실행중인 쓰레드에서 대상이 되는 쓰레드(th1)가 종료될 때 까지 기다린다.
		} catch (InterruptedException e) {e.printStackTrace();}
		long endTime = System.currentTimeMillis();
		System.out.println("쓰레드 수행 시간 : " + (endTime - startTime) + "ms");
	}
}

class MyThread1 extends Thread {
	@Override
	public void run() {
		long sum = 0L;
		for(long i=1L; i<=1_000_000_000L; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}