package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {
	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "홍길동", "010-1191-2222"));
		memList.add(new Member(7, "이순신", "010-1211-2212"));
		memList.add(new Member(3, "성춘향", "010-1811-2122"));
		memList.add(new Member(2, "강감찬", "010-1115-1222"));
		memList.add(new Member(6, "일지매", "010-1141-2422"));
		memList.add(new Member(9, "변학도", "010-1211-2226"));
		memList.add(new Member(5, "김삿갓", "010-1212-2886"));
		
		System.out.println("정렬 전");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("----------------------------------------------");
		
		Collections.sort(memList);
		System.out.println("정렬 후");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		Collections.sort(memList, new TelDesc());
		System.out.println("정렬 후");
		for(Member mem : memList) {
			System.out.println(mem);
		}
	}
}

// 내부 정렬 기준을 포함할 클래스는 Comparable 인터페이스를 구현해야 한다.
// Collection에 추가되는 데이터 자체에 정렬 기준을 넣어주는 것을 말한다.

// Comparable을 구현하는 클래스에서는 compareTo() 메서드를 재정의해야 한다.

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

	// Member의 이름을 오름차순으로 정렬하는 내부 정렬 기준 구현하기
	/*
	   @Override
	public int compareTo(Member mem) {
		
		return this.name.compareTo(mem.getName());
	}
	*/
	
	// Member의 번호를 내림차순으로 정렬하는 내부 정렬 기준 구현하기
	@Override
	public int compareTo(Member mem) {
		/* 비교방법 1
		   if (this.num > mem.getNum()) {
			return -1;
		} else if (this.num == mem.getNum()) {
			return 0;
		} else return 1;
		*/
		// 비교방법 2 => Wrapper클래스를 이용하는 방법
		return (new Integer(this.num).compareTo(mem.getNum())) * -1; 
	}
}
	
// 전화번호의 내림차순으로 정렬할 수 있는 외부 정렬 기준을 만들고 정렬한 결과를 출력하시오
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