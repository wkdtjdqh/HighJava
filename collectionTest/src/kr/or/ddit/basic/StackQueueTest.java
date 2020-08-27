package kr.or.ddit.basic;

import java.util.LinkedList;

public class StackQueueTest {
	/* 
	 * Stack ==> LIFO 후입선출방식의 자료구조
	 * Queue ==> FIFO 선입선출방식의 자료구조
	 * 
	 * Stack과 Queue는 LinkedList를 이용하여 사용할 수 있다.
	 * LinkedList의 구조는 데이터끼리 떨어져있으며 데이터와 다음 데이터의 참조주소를 갖는다.
	 */
	
	public static void main(String[] args) {
		/*
		 * Stack의 명령
		 * 1. 자료입력 : push(data);
		 * 2. 자료출력 : pop(); ==> 자료를 꺼내온 후에 꺼내온 자료를 Stack에서 지운다.
		 * 			  peek() ==> 삭제없이 자료를 꺼내온다.
		 */
		LinkedList<String> stack = new LinkedList<>();
		
		stack.push("홍길동");
		stack.push("일지매");
		stack.push("변학도");
		stack.push("강감찬");
		
		System.out.println("현재 stack 값 : " + stack);
		
		String data = stack.pop();
		System.out.println("꺼내온 값 : " + data);
		System.out.println("꺼내온 값 : " + stack.pop());
		System.out.println("현재 stack 값 : " + stack);
		
		String temp = stack.peek();
		System.out.println("peek로 꺼내온 값 : " + temp);
		System.out.println("현재 stack 값 : " + stack);
		
		stack.push("성춘향");
		System.out.println("추가한 후 stack 값 : " + stack);
		System.out.println("꺼내온 값 : " + stack.pop());
		System.out.println("-------------------------------------");
		/*
		 * Queue의 명령
		 * 1. 자료입력 : offer(data)
		 * 2. 자료출력 : poll() ==> 자료를 꺼내오고 꺼내온 자료를 Queue에서 삭제된다.
		 * 			  peek() ==> 삭제없이 자료를 꺼내온다.
		 */
		LinkedList<String> queue = new LinkedList<>();
		
		queue.offer("홍길동");
		queue.offer("일지매");
		queue.offer("변학도");
		queue.offer("강감찬");
		
		System.out.println("현재 queue 값 : " + queue);
		
		data = queue.poll();
		System.out.println("꺼내온 값 : " + data);
		System.out.println("꺼내온 값 : " + queue.poll());
		System.out.println("현재 queue 값 : " + queue);
		
		queue.offer("성춘향");
		System.out.println("현재 queue 값 : " + queue);
		
		System.out.println("꺼내온 값 : " + queue.poll());
		System.out.println("현재 queue 값 : " + queue);

		System.out.println("삭제없이 꺼내오기 : " + queue.peek());
		System.out.println("현재 queue 값 : " + queue);
		
		
	}
}
