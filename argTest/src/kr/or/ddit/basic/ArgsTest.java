package kr.or.ddit.basic;

public class ArgsTest {
	public int sumArr(int[] data) {
		int total = 0;
		for (int i = 0; i < data.length; i++) {
			total += data[i];
		}
		return total;
	}
	
	/*
		가변형 인수 - 메서드의 매개변수의 개수가 호출될 때 마다 다를 경우에 사용한다.
				    이 가변형 인수는 메서드 내에서는 배열로 처리된다.
				    가변형 인수는 하나의 메서드에 하나만 사용할 수 있다.
	*/
	public int sumArg(int...data) {
		int total = 0;
		for (int i = 0; i < data.length; i++) {
			total += data[i];
		}
		return total;
	}
	
	/* 	하나의 메서드에 하나의 가변형 인수만 사용할 수 있다.
		public int testArg(int...data, int...data2) {
			return 0;
		}
	*/
	
	// 가변형 변수와 일반 변수를 같이 사용할 경우에는 가변형 변수를 제일 뒤에 배치해야 한다.
	public int testArg(String name, int...data) {
		return 0;
	}
	
	public static void main(String[] args) {
		int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		ArgsTest test = new ArgsTest();
		int result = test.sumArr(num);
		System.out.println(result);
		System.out.println();
		
		// 10, 20, 30, 40
		System.out.println(test.sumArr(new int[]{10, 20, 30, 40}));
		System.out.println();
		
		System.out.println(test.sumArg(1, 2, 3, 4, 5, 6, 7, 8, 9));
		System.out.println(test.sumArg(10, 20, 30, 40));
	}
}
