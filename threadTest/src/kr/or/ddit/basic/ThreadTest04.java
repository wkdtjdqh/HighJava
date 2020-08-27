package kr.or.ddit.basic;
/*
	1 ~ 20억까지의 합계를 구하는 프로그램을 하나의 쓰레드가 단독으로 처리할 때와 다중 쓰레드가 협력해서 처리할 때의 경과시간을 비교해보자.
*/
public class ThreadTest04 {
	public static void main(String[] args) {
		// 단독처리 쓰레드
		SumThread th = new SumThread(1L, 2_000_000_000L);
		
		// 다중처리 쓰레드
		SumThread[] sumArr = new SumThread[]{
			new SumThread(			  1L,   500_000_000L),	
			new SumThread(	500_000_001L, 1_000_000_000L),	
			new SumThread(1_000_000_001L, 1_500_000_000L),	
			new SumThread(1_500_000_001L, 2_000_000_000L)	
		};
		
		long startTime = System.currentTimeMillis();
		th.start();
		try {
			th.join();
		} catch (Exception e) {}
		long endTime = System.currentTimeMillis();
		System.out.println("\n단독처리 쓰레드 경과시간 : " + (endTime - startTime) + "ms");
		System.out.println();
		
		startTime = System.currentTimeMillis();
		for (int i = 0; i < sumArr.length; i++) {
			sumArr[i].start();
		}
		try {
			for (int i = 0; i < sumArr.length; i++) {
				sumArr[i].join();
			}
		} catch (Exception e) {}
		endTime = System.currentTimeMillis();
		System.out.println("\n다중처리 쓰레드 경과시간 : " + (endTime - startTime) + "ms");
	}
}

class SumThread extends Thread {
	private long start, end;
	
	public SumThread(long start, long end) {
		this.start = start;
		this.end = end;
	}
	@Override
	public void run() {
		long sum = 0L;
		for (long i = start; i <= end; i++) {
			sum += i;
		} 
		System.out.println("sum : " + sum);
	}
}