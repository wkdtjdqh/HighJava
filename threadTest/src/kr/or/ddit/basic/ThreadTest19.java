package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
/*
	Vector, Hashtable등의 예전부터 존재하던 Collection객체들은 내부에 동기화 처리가 되어있다.
		-> 내부에 동기화 처리로 인해 메모리를 많이 사용해 평상시에는 잘 사용하지 않는다.
		
	그런데, 최근에 새로 구성된 Collection들은 동기화 처리가 되어있지 않다.
	그래서, 동기화가 필요한 프로그램에서 이런 Collection객체들은 사용하려면 동기화 처리를 한 후에 사용해야 한다.
*/
public class ThreadTest19 {
	private static Vector<Integer> vec = new Vector<>();
	
	// 동기화 처리가 되어있지 않은 List
	private static List<Integer> list = new ArrayList<>();
	
	// 동기화 처리를 한 후의 List
	private static List<Integer> syncList = Collections.synchronizedList(new ArrayList<Integer>());
	
	
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 10000; i++) {
//					vec.add(i);
//					list.add(i);
					syncList.add(i);
				}
			}
		};
		
		Thread[] ths = new Thread[] {
				new Thread(r), new Thread(r), new Thread(r),
				new Thread(r), new Thread(r)
		};
		
		for(Thread th : ths) {
			th.start();
		}
		
		for(Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
//		System.out.println("vec의 개수 : " + vec.size());
		
//		System.out.println("list의 개수 : " + list.size());	
		/*
			배열이 가득차면 더 큰 배열을 만들어 주는 작업을 한다.
			더 큰 배열을 만들어주는 작업에서 제어가 다른 쓰레드가 넘어가면 하던 작업이 멈추게 된다.
			그래서 더 큰 배열을 만들지 못한 상황에서 더 큰 곳에 데이터를 넣으려고 하면서 에러가 나게 되어 데이터를 제대로 담을 수 없게 된다.
		*/
		
		System.out.println("syncList의 개수 : " + syncList.size());	
	}
}
