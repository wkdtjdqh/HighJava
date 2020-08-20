package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {
	public static void main(String[] args) {
		// 객체 생성
		ArrayList<Object> list1 = new ArrayList<>();
		
		// 데이터 추가 : add() => 반환값 : true, false
		list1.add("데이터");
		list1.add(new Integer(123));
		list1.add(123);
		
		System.out.println("list1 size : " + list1.size());
		System.out.println("list1 : " + list1);
		
		// 데이터 추가2 : add(index, 추가할 데이터)
		// index번째에 추가할 대이터를 끼원넣는다.
		list1.add(0,"CCC");
		list1.add('a');
		list1.add("CCC");
		list1.add(111);
		
		System.out.println("List : + list1");
		
		// 데이터 변경 : set(index, 변경할 데이터)
		// index번째의 데이터를 새로운 데이터로 덮어쓴다.
		// 반환값 : 원래의 데이터
		String temp = (String)list1.set(0, "ZZZZ");
		System.out.println(temp);
		
		// 데이터 삭제 : remove(index)
		// index번째의 데이터를 삭제한다.
		// 반환값 : 삭제된 데이터
		temp = (String)list1.remove(0);
		System.out.println("삭제된 list : " + list1 );
		System.out.println(temp);
		
		// 데이터 삭제2 : remove(삭제할 데이터)
		// 살제할 데이터를 찾아서 삭제
		// 삭제할 데이터가 여려개면 앞에서부터 삭제된다
		// 삭제할 데이터가 정수형이거나 char형 일 경우에는 반드시 객체로 변환해서 사용해야한다.
		// 반환값 : true, false
		list1.remove("CCC");
		System.out.println(list1);
		
		// 111 데이터 삭제하기
		list1.remove(new Integer(111));	// 정수형 데이터는 객체형으로 변환해서 사용한다.
		System.out.println(list1);
		
		// 'a' 데이터 삭제하기
		list1.remove(new Character('a')); // char형 데이터는 객체형으로 변환해서 사용한다.
		System.out.println(list1);
		
		// 전체 삭제
		list1.clear();
		System.out.println(list1);
		
		// 데이터 꺼내오기 : get(index)
		// index번째의 데이터를 꺼내 반환한다
		list1.add("AA");
		list1.add("BB");
		list1.add("CC");
		list1.add("DD");
		String data = (String)list1.get(1);
		System.out.println(data);
		
		/*
		 * 제네릭 타입(Generic Type)
		 * 	객체를 선언할 때 < > 안에 그 Collection이 사용할 데이터의 타입을 정해주는 것을 말한다.
		 * 	이런식으로 선언하게 되면 그 데이터 타입 이외의 다른 데이터는 저장할 수 없다.
		 * 	그리고, 제네릭으로 선언될 수 있는 데이터 타입은 클래스형 이어야 한다.
		 * 	예) int -> Integer, boolean -> Boolean, char -> Character ..
		 * 
		 * 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다.
		 */
		
		ArrayList<String> list2 = new ArrayList<>();	// String만 저장할 수 있는 List
		list2.add("안녕하세요");
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
		
		// 데이터 삭제 : removeAll(Collection 객체)
		// => 전체 데이터 중 'Collection 객체'가 가지고 있는 데이터 전체를 삭제한다.
		list2.removeAll(list3);
		System.out.println(list2);
		
		list2.clear();
		list2.add("AAA"); list2.add("BBB");
		list2.add("CCC"); list2.add("DDD");
		list2.add("EEE"); list2.add("FFF");
		
		// List의 데이터를 순서대로 차례로 가져와 사용할 경우에는 반복문을 사용한다.
		for(String value : list2) {
			System.out.print(value + "\t");
		} System.out.println("\n");
		
		// 데이터 여부 검사 : contains()
		// 데이터가 있으면 true, 없으면 false
		System.out.println("DDD값 : " + list2.contains("DDD"));
		System.out.println("KKK값 : " + list2.contains("KKK"));
		
		// 데이터 위치 구하기 : indexOf()
		// List에 데이터가 있으면 index를 반환하고 없으면 -1을 반환한다.
		System.out.println(list2.indexOf("AAA"));
		System.out.println(list2.indexOf("KKK"));
		
		// List의 데이터를 배열로 변환하기
		// 방법1. toArray() => List안의 데이터를 Object형 배열로 변환해서 반환한다.
		// 방법2. toArray(new 제네릭타입자료형[0]) => 제네릭 타입의 배열로 변환해서 반환한다.
		Object[] objArr = list2.toArray();
		System.out.println("배열의 개수 : " + objArr.length);
		for(Object obj : objArr) {
			System.out.print(obj + "\t");
		} System.out.println();
		
		String[] strArr = list2.toArray(new String[0]);
		System.out.println("배열의 개수 : " + objArr.length);
		for(String str : strArr) {
			System.out.print(str + "\t");
		} System.out.println();
	}
}
