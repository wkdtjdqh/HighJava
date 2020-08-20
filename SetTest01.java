package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class SetTest01 {
	/*
		Set�� Ư¡(List�� ��)
		1. List
			- �������� ����(index)�� �ִ�.
			- �ߺ��Ǵ� �����͸� ������ �� �ִ�.
		2. Set
			- �������� ����(index)�� ����.
			- �ߺ��Ǵ� �����͸� ������ �� ����.
	*/	
	public static void main(String[] args) {
		HashSet hs1 = new HashSet<>();
		
		// ������ �߰� : add()�޼��� �̿�
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set�� ���� : " + hs1.size());
		System.out.println("Set ������ : " + hs1);
		
		// Set�� �ߺ��Ǵ� �����͸� �߰��ϸ� false�� ��ȯ�ϰ� �����ʹ� �߰����� �ʴ´�.
		boolean isAdd = hs1.add("FF");
		boolean isAdd2 = hs1.add("FF");
		System.out.println("�ߺ����� ���� �� : " + isAdd);
		System.out.println("Set ������ : " + hs1);
		System.out.println("�ߺ��� �� " + isAdd2);
		System.out.println("Set ������ : " + hs1);
		
		// Set�� �����͸� �����Ϸ��� �����ϴ� ����� ���� ���� ������
		// �ش� �ڷḦ ������ �� �߰��ϴ� ����� ����Ѵ�.
		// �����ϱ� : remove(������ �ڷ�) => ��ȯ�� : ��������(true), ��������(false)
		//		   clear() => ��ü����
		
		// "FF"���ڿ��� "EE"���ڿ��� �����ϱ�
		hs1.remove("FF");
		System.out.println("���� �� Set : " + hs1);
		hs1.add("EE");
		System.out.println("Set ������ : " + hs1);
		
//		hs1.clear();
//		System.out.println("Clear �� Set : " + hs1);
		
		HashSet<Integer> intSet = new HashSet<>();
		intSet.add(10);
		intSet.add(7);
		intSet.add(9);
		intSet.add(3);
		intSet.add(5);
		
		// Set�� ��� �������� �հ� ���ϱ�
		/*
			Set�� �����ʹ� ����(index)�� ���� ������ Listó�� index�� �����͸� �ϳ��� �ҷ��� �� ����.
			�׷��� �����͸� �ϳ��� ��� ���ؼ��� Iterator�� ��ü�� ��ȯ�ؼ� ����ؾ� �Ѵ�.
			
			- Set���� �����͸� Iterator�� ��ü�� ��ȯ�� �ִ� �޼��� => iterator()
		*/
		
		Iterator<Integer> it = intSet.iterator();	// Set�����͸� Iterator�� ��ȯ�ϱ�
		
		// Iterator�� hasNext() �޼��� => Iterator�� �����͸� ����Ű�� �����͸� ������°�� ������ ��ġ�� �̵���Ų �� �� �ڸ��� �����Ͱ� ������ true, ������ false
		
		int sum = 0;
		while(it.hasNext()) {
			// Iterator�� next() �޼��� => �����͸� ������° ��ġ�� �̵��� �� �� �ڸ��� �����͸� ��ȯ�Ѵ�.
			int num = it.next();
			sum += num;
		}
		System.out.println("���� : " + sum);
		
		// �츮�� �л��� �� ��ȣ�� �̿��Ͽ� ��÷�ϴ� ���α׷��� �ۼ��� ����
		// ��ȣ�� 1������ 25������ �ְ�, ��÷�� �ο��� 3���̴�.
		// ��÷�ڸ� ����� ����.
		int[] numArr = new int[3];
		for (int i = 0; i < numArr.length; i++) {
			numArr[i] = (int)(Math.random()*25)+1;	//(�ִ밪-�ּҰ�+1) + �ּҰ�
			for (int j = 0; j < i; j++) {
				if (numArr[i] == numArr[j]) {
					i--;
					continue;
				}
			}
		}
		System.out.println(Arrays.toString(numArr));
		
		
		HashSet<Integer> numSet = new HashSet<>();
		while(numSet.size() < 3) {
			numSet.add((int)(Math.random() * 25 + 1));
		}
		System.out.println("��÷�� ��ȣ : " + numSet);
		
		// Set������ �ڷḦ List������ ��ȯ�ϱ�
		ArrayList<Integer> testList = new ArrayList<>(numSet);
		System.out.println("List ������ ���");
		for(int test : testList) {
			System.out.println(test);
		}
	}
}
