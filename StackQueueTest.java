package kr.or.ddit.basic;

import java.util.LinkedList;

public class StackQueueTest {
	/* 
	 * Stack ==> LIFO ���Լ������� �ڷᱸ��
	 * Queue ==> FIFO ���Լ������� �ڷᱸ��
	 * 
	 * Stack�� Queue�� LinkedList�� �̿��Ͽ� ����� �� �ִ�.
	 * LinkedList�� ������ �����ͳ��� ������������ �����Ϳ� ���� �������� �����ּҸ� ���´�.
	 */
	
	public static void main(String[] args) {
		/*
		 * Stack�� ���
		 * 1. �ڷ��Է� : push(data);
		 * 2. �ڷ���� : pop(); ==> �ڷḦ ������ �Ŀ� ������ �ڷḦ Stack���� �����.
		 * 			  peek() ==> �������� �ڷḦ �����´�.
		 */
		LinkedList<String> stack = new LinkedList<>();
		
		stack.push("ȫ�浿");
		stack.push("������");
		stack.push("���е�");
		stack.push("������");
		
		System.out.println("���� stack �� : " + stack);
		
		String data = stack.pop();
		System.out.println("������ �� : " + data);
		System.out.println("������ �� : " + stack.pop());
		System.out.println("���� stack �� : " + stack);
		
		String temp = stack.peek();
		System.out.println("peek�� ������ �� : " + temp);
		System.out.println("���� stack �� : " + stack);
		
		stack.push("������");
		System.out.println("�߰��� �� stack �� : " + stack);
		System.out.println("������ �� : " + stack.pop());
		System.out.println("-------------------------------------");
		/*
		 * Queue�� ���
		 * 1. �ڷ��Է� : offer(data)
		 * 2. �ڷ���� : poll() ==> �ڷḦ �������� ������ �ڷḦ Queue���� �����ȴ�.
		 * 			  peek() ==> �������� �ڷḦ �����´�.
		 */
		LinkedList<String> queue = new LinkedList<>();
		
		queue.offer("ȫ�浿");
		queue.offer("������");
		queue.offer("���е�");
		queue.offer("������");
		
		System.out.println("���� queue �� : " + queue);
		
		data = queue.poll();
		System.out.println("������ �� : " + data);
		System.out.println("������ �� : " + queue.poll());
		System.out.println("���� queue �� : " + queue);
		
		queue.offer("������");
		System.out.println("���� queue �� : " + queue);
		
		System.out.println("������ �� : " + queue.poll());
		System.out.println("���� queue �� : " + queue);

		System.out.println("�������� �������� : " + queue.peek());
		System.out.println("���� queue �� : " + queue);
		
		
	}
}
