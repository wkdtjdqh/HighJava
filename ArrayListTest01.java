package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {
	public static void main(String[] args) {
		// ��ü ����
		ArrayList<Object> list1 = new ArrayList<>();
		
		// ������ �߰� : add() => ��ȯ�� : true, false
		list1.add("������");
		list1.add(new Integer(123));
		list1.add(123);
		
		System.out.println("list1 size : " + list1.size());
		System.out.println("list1 : " + list1);
		
		// ������ �߰�2 : add(index, �߰��� ������)
		// index��°�� �߰��� �����͸� �����ִ´�.
		list1.add(0,"CCC");
		list1.add('a');
		list1.add("CCC");
		list1.add(111);
		
		System.out.println("List : + list1");
		
		// ������ ���� : set(index, ������ ������)
		// index��°�� �����͸� ���ο� �����ͷ� �����.
		// ��ȯ�� : ������ ������
		String temp = (String)list1.set(0, "ZZZZ");
		System.out.println(temp);
		
		// ������ ���� : remove(index)
		// index��°�� �����͸� �����Ѵ�.
		// ��ȯ�� : ������ ������
		temp = (String)list1.remove(0);
		System.out.println("������ list : " + list1 );
		System.out.println(temp);
		
		// ������ ����2 : remove(������ ������)
		// ������ �����͸� ã�Ƽ� ����
		// ������ �����Ͱ� �������� �տ������� �����ȴ�
		// ������ �����Ͱ� �������̰ų� char�� �� ��쿡�� �ݵ�� ��ü�� ��ȯ�ؼ� ����ؾ��Ѵ�.
		// ��ȯ�� : true, false
		list1.remove("CCC");
		System.out.println(list1);
		
		// 111 ������ �����ϱ�
		list1.remove(new Integer(111));	// ������ �����ʹ� ��ü������ ��ȯ�ؼ� ����Ѵ�.
		System.out.println(list1);
		
		// 'a' ������ �����ϱ�
		list1.remove(new Character('a')); // char�� �����ʹ� ��ü������ ��ȯ�ؼ� ����Ѵ�.
		System.out.println(list1);
		
		// ��ü ����
		list1.clear();
		System.out.println(list1);
		
		// ������ �������� : get(index)
		// index��°�� �����͸� ���� ��ȯ�Ѵ�
		list1.add("AA");
		list1.add("BB");
		list1.add("CC");
		list1.add("DD");
		String data = (String)list1.get(1);
		System.out.println(data);
		
		/*
		 * ���׸� Ÿ��(Generic Type)
		 * 	��ü�� ������ �� < > �ȿ� �� Collection�� ����� �������� Ÿ���� �����ִ� ���� ���Ѵ�.
		 * 	�̷������� �����ϰ� �Ǹ� �� ������ Ÿ�� �̿��� �ٸ� �����ʹ� ������ �� ����.
		 * 	�׸���, ���׸����� ����� �� �ִ� ������ Ÿ���� Ŭ������ �̾�� �Ѵ�.
		 * 	��) int -> Integer, boolean -> Boolean, char -> Character ..
		 * 
		 * ���׸� Ÿ������ �����ϰ� �Ǹ� �����͸� ������ �� ������ ����ȯ�� �ʿ����.
		 */
		
		ArrayList<String> list2 = new ArrayList<>();	// String�� ������ �� �ִ� List
		list2.add("�ȳ��ϼ���");
		temp = list2.get(0);
		System.out.println(temp);
		
		list2.clear();
		list2.add("AAA"); list2.add("BBB");
		list2.add("CCC"); list2.add("DDD");
		list2.add("EEE"); list2.add("FFF");
		
		ArrayList<String> list3 = new ArrayList<>();
		list3.add("BBB");
		list3.add("DDD");
		list3.add("EEE");
		
		System.out.println(list2);
		System.out.println(list3);
		
		// ������ ���� : removeAll(Collection ��ü)
		// => ��ü ������ �� 'Collection ��ü'�� ������ �ִ� ������ ��ü�� �����Ѵ�.
		list2.removeAll(list3);
		System.out.println(list2);
		
		list2.clear();
		list2.add("AAA"); list2.add("BBB");
		list2.add("CCC"); list2.add("DDD");
		list2.add("EEE"); list2.add("FFF");
		
		// List�� �����͸� ������� ���ʷ� ������ ����� ��쿡�� �ݺ����� ����Ѵ�.
		for(String value : list2) {
			System.out.print(value + "\t");
		} System.out.println("\n");
		
		// ������ ���� �˻� : contains()
		// �����Ͱ� ������ true, ������ false
		System.out.println("DDD�� : " + list2.contains("DDD"));
		System.out.println("KKK�� : " + list2.contains("KKK"));
		
		// ������ ��ġ ���ϱ� : indexOf()
		// List�� �����Ͱ� ������ index�� ��ȯ�ϰ� ������ -1�� ��ȯ�Ѵ�.
		System.out.println(list2.indexOf("AAA"));
		System.out.println(list2.indexOf("KKK"));
		
		// List�� �����͸� �迭�� ��ȯ�ϱ�
		// ���1. toArray() => List���� �����͸� Object�� �迭�� ��ȯ�ؼ� ��ȯ�Ѵ�.
		// ���2. toArray(new ���׸�Ÿ���ڷ���[0]) => ���׸� Ÿ���� �迭�� ��ȯ�ؼ� ��ȯ�Ѵ�.
		Object[] objArr = list2.toArray();
		System.out.println("�迭�� ���� : " + objArr.length);
		for(Object obj : objArr) {
			System.out.print(obj + "\t");
		} System.out.println();
		
		String[] strArr = list2.toArray(new String[0]);
		System.out.println("�迭�� ���� : " + objArr.length);
		for(String str : strArr) {
			System.out.print(str + "\t");
		} System.out.println();
	}
}
