package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class EqualsHashCodeTest {
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setId(1);
		p1.setName("홍길동");
		
		Person p2 = new Person();
//		p2.setId(2);
//		p2.setName("이순신");
		p2.setId(1);
		p2.setName("홍길동");
		
		System.out.println(p1.equals(p2));	// 기본적으로 Object를 상속받고 있어 Object의 equals를 불러올 수 있고, Object의 equals는 ==으로 비교하게 되어있다.
										    // 제대로된 equals를 사용하기 위해서는 재정의가 필요하다.
		
		System.out.println("----------------------------------");
		Set<Person> testSet = new HashSet<>();	// HashSet은 HashCode를 이용해서 비교
		testSet.add(p1);
		testSet.add(p2);
		System.out.println("Set의 크기 : " + testSet.size());
		System.out.println(testSet);
		System.out.println("p1 : " + p1.hashCode());
		System.out.println("p2 : " + p2.hashCode());
		System.out.println(p1.equals(p2));
		
		/*
			- equals 		=> 두 객체의 내용이 같은지 검사하는 연산자
			- hashCode() 	=> 두 객체의 동일성을 검사하는 연산자
			
			- HashSet, HashTable, HashMap과 같이 Hash로 시작하는 컬렉션들은 객체의 의미상의 동일성을 비교하기 위해서 hashCode() 메서드를 호출하여 비교한다.
			   그로므로, 객체가 같은지 여부를 결정하려면 hashCode() 메서드를 재정의 해야한다.
			   
			- hashCode() 메서드에서 사용하는 '해싱 알고리즘'은 서로 다른 객체들에 대해서 같은 hashcode를 나타낼 수도 있다.
		*/
	}
}

class Person{
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/*
	// 두 데이터 값이 같으면 참이 되도록 재정의
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		
		// 같은 유형의 클래스인지 검사
		if (this.getClass() != obj.getClass()) return false;
		
		// 참조값이 같은지 비교
		if (this == obj) return true;
		
		// 매개변수를 현재 객체 유형으로 형변환
		Person myPerson = (Person)obj;
		
		if (this.name == null && myPerson.getName() != null) return false;
		
		// id가 같고 name이 모두 null일 경우
		if (this.id == myPerson.getId() && this.name.equals(myPerson.getName())) return true;
		if (this.id == myPerson.getId() && this.name == myPerson.getName()) return true;
		
		return false;
	}
	*/
	
}