package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class EqualsHashCodeTest {
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setId(1);
		p1.setName("ȫ�浿");
		
		Person p2 = new Person();
//		p2.setId(2);
//		p2.setName("�̼���");
		p2.setId(1);
		p2.setName("ȫ�浿");
		
		System.out.println(p1.equals(p2));	// �⺻������ Object�� ��ӹް� �־� Object�� equals�� �ҷ��� �� �ְ�, Object�� equals�� ==���� ���ϰ� �Ǿ��ִ�.
										    // ����ε� equals�� ����ϱ� ���ؼ��� �����ǰ� �ʿ��ϴ�.
		
		System.out.println("----------------------------------");
		Set<Person> testSet = new HashSet<>();	// HashSet�� HashCode�� �̿��ؼ� ��
		testSet.add(p1);
		testSet.add(p2);
		System.out.println("Set�� ũ�� : " + testSet.size());
		System.out.println(testSet);
		System.out.println("p1 : " + p1.hashCode());
		System.out.println("p2 : " + p2.hashCode());
		System.out.println(p1.equals(p2));
		
		/*
			- equals 		=> �� ��ü�� ������ ������ �˻��ϴ� ������
			- hashCode() 	=> �� ��ü�� ���ϼ��� �˻��ϴ� ������
			
			- HashSet, HashTable, HashMap�� ���� Hash�� �����ϴ� �÷��ǵ��� ��ü�� �ǹ̻��� ���ϼ��� ���ϱ� ���ؼ� hashCode() �޼��带 ȣ���Ͽ� ���Ѵ�.
			   �׷ιǷ�, ��ü�� ������ ���θ� �����Ϸ��� hashCode() �޼��带 ������ �ؾ��Ѵ�.
			   
			- hashCode() �޼��忡�� ����ϴ� '�ؽ� �˰���'�� ���� �ٸ� ��ü�鿡 ���ؼ� ���� hashcode�� ��Ÿ�� ���� �ִ�.
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
	// �� ������ ���� ������ ���� �ǵ��� ������
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		
		// ���� ������ Ŭ�������� �˻�
		if (this.getClass() != obj.getClass()) return false;
		
		// �������� ������ ��
		if (this == obj) return true;
		
		// �Ű������� ���� ��ü �������� ����ȯ
		Person myPerson = (Person)obj;
		
		if (this.name == null && myPerson.getName() != null) return false;
		
		// id�� ���� name�� ��� null�� ���
		if (this.id == myPerson.getId() && this.name.equals(myPerson.getName())) return true;
		if (this.id == myPerson.getId() && this.name == myPerson.getName()) return true;
		
		return false;
	}
	*/
	
}