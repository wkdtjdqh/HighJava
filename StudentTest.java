package kr.or.ddit.basic;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
	����) �й�, �̸�, ��������, ��������, ��������, ����, ����� ����� ���� Student Ŭ������ �����.
		�� Ŭ������ �����ڿ����� �й�, �̸�, ��������, ��������, ���������� �Ű������� �޾Ƽ� �ʱ�ȭ ó���� �Ѵ�.
		
		�� Student ��ü�� List�� �����Ͽ� �����Ѵ�.
		
		List�� ����� �����͵��� �й��� �������� ������ �� �� �ִ� ���� ���� ������ �����ϰ�,
		������ �������� �����ϴµ� ������ ������ �̸��� ������������ ������ �Ǵ� �ܺ� ���� ���� Ŭ������ �ۼ��Ͽ�
		���ĵ� ����� ����Ͻÿ�.
		
		����� List�� ��ü �����Ͱ� �߰��� �Ŀ� ����ǵ��� �Ѵ�.
*/
public class StudentTest {
	
	public void setRanking(List<Student> stdList) {
		for(Student std1 : stdList) {	// ����� ���� �����͸� ���� �ݺ���
			int rank = 1;	// ó������ 1������ �����س��� �����Ѵ�.
			for(Student std2 : stdList) {	// �� ����� ��Ÿ���� ���� �ݺ���
				
				// ��� ���� �����ͺ��� ū ���� ������ rank�� ������Ų��.
				if(std1.getSum() < std2.getSum()) {
					rank++;
				}
			}
			std1.setRank(rank);
		}
	}
	
	public static void main(String[] args) {
		StudentTest stdTest = new StudentTest();
		
		ArrayList<Student> studentList = new ArrayList<>();
		
		studentList.add(new Student(20140001, "�强��", 100, 80, 80));
		studentList.add(new Student(20170001, "������", 91, 70, 80));
		studentList.add(new Student(20140002, "������", 82, 80, 80));
		studentList.add(new Student(20120001, "������", 70, 90, 80));
		studentList.add(new Student(20120002, "������", 100, 80, 80));
		
/*		for (int i = 0; i < studentList.size(); i++) {
			int rank = 1;
			for (int j = 0; j < studentList.size(); j++) {
				if (studentList.get(i).getSum() < studentList.get(j).getSum()) {
					rank ++;
				} 
			}
			studentList.get(i).setRank(rank);
		}
*/		
		stdTest.setRanking(studentList);
		
		System.out.println("�й��� �������� ����");
		Collections.sort(studentList);
		for(Student student : studentList) {
			System.out.println(student);
		}
		
		System.out.println("������ ��������, ������ ������ �̸��� �������� ����");
		Collections.sort(studentList, new SumDescNameAsc());
		for(Student student : studentList) {
			System.out.println(student);
		}
	}
}

class Student implements Comparable<Student>{
	private int studentNum;
	private String studentName;
	private int kor;
	private int eng;
	private int mat;
	private int sum;
	private int rank;
	
	@Override
	public String toString() {
		return "Student [studentNum=" + studentNum + ", studentName="
				+ studentName + ", kor=" + kor + ", eng=" + eng + ", mat="
				+ mat + ", sum=" + sum + ", rank=" + rank + "]";
	}
	
	public Student(int studentNum, String studentName, int kor, int eng, int mat) {
		super();
		this.studentNum = studentNum;
		this.studentName = studentName;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.sum = kor + eng + mat;
	}

	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(Student sd) {
		return new Integer(this.studentNum).compareTo(sd.getStudentNum());
	}
}

class SumDescNameAsc implements Comparator<Student>{
	@Override
	public int compare(Student s1, Student s2) {
		if (s1.getSum() == s2.getSum()) {
			return s1.getStudentName().compareTo(s2.getStudentName());
		} else return new Integer(s1.getSum()).compareTo(s2.getSum()) * -1;
	}
}