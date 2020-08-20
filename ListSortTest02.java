package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {
	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "ȫ�浿", "010-1191-2222"));
		memList.add(new Member(7, "�̼���", "010-1211-2212"));
		memList.add(new Member(3, "������", "010-1811-2122"));
		memList.add(new Member(2, "������", "010-1115-1222"));
		memList.add(new Member(6, "������", "010-1141-2422"));
		memList.add(new Member(9, "���е�", "010-1211-2226"));
		memList.add(new Member(5, "���", "010-1212-2886"));
		
		System.out.println("���� ��");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("----------------------------------------------");
		
		Collections.sort(memList);
		System.out.println("���� ��");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		Collections.sort(memList, new TelDesc());
		System.out.println("���� ��");
		for(Member mem : memList) {
			System.out.println(mem);
		}
	}
}

// ���� ���� ������ ������ Ŭ������ Comparable �������̽��� �����ؾ� �Ѵ�.
// Collection�� �߰��Ǵ� ������ ��ü�� ���� ������ �־��ִ� ���� ���Ѵ�.

// Comparable�� �����ϴ� Ŭ���������� compareTo() �޼��带 �������ؾ� �Ѵ�.

class Member implements Comparable<Member>{
	private int num;
	private String name;
	private String tel;
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// Member�� �̸��� ������������ �����ϴ� ���� ���� ���� �����ϱ�
	/*
	   @Override
	public int compareTo(Member mem) {
		
		return this.name.compareTo(mem.getName());
	}
	*/
	
	// Member�� ��ȣ�� ������������ �����ϴ� ���� ���� ���� �����ϱ�
	@Override
	public int compareTo(Member mem) {
		/* �񱳹�� 1
		   if (this.num > mem.getNum()) {
			return -1;
		} else if (this.num == mem.getNum()) {
			return 0;
		} else return 1;
		*/
		// �񱳹�� 2 => WrapperŬ������ �̿��ϴ� ���
		return (new Integer(this.num).compareTo(mem.getNum())) * -1; 
	}
}
	
// ��ȭ��ȣ�� ������������ ������ �� �ִ� �ܺ� ���� ������ ����� ������ ����� ����Ͻÿ�
class TelDesc implements Comparator<Member> {
	
	@Override
	public int compare(Member o1, Member o2) {
		/*
		   if (o1.getTel().compareTo(o2.getTel()) < 0) {
			return 1;
		} else if (o1.getTel().compareTo(o2.getTel()) == 0) {
			return 0;
		} else return -1;
		*/
		
		return o1.getTel().compareTo(o2.getTel()) * -1;
	}
}