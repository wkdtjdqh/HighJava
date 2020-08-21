package kr.or.ddit.basic;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
	문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student 클래스를 만든다.
		이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 처리를 한다.
		
		이 Student 객체는 List에 저장하여 관리한다.
		
		List에 저장된 데이터들을 학번의 오름차순 정렬이 될 수 있는 내부 정렬 기준을 구현하고,
		총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스를 작성하여
		정렬된 결과를 출력하시오.
		
		등수는 List에 전체 데이터가 추가된 후에 저장되도록 한다.
*/
public class StudentTest {
	
	public void setRanking(List<Student> stdList) {
		for(Student std1 : stdList) {	// 등수를 구할 데이터를 위한 반복문
			int rank = 1;	// 처음에는 1등으로 설정해놓고 시작한다.
			for(Student std2 : stdList) {	// 비교 대상을 나타내기 위한 반복문
				
				// 등수 구할 데이터보다 큰 값을 만나면 rank를 증가시킨다.
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
		
		studentList.add(new Student(20140001, "장성보", 100, 80, 80));
		studentList.add(new Student(20170001, "강지원", 91, 70, 80));
		studentList.add(new Student(20140002, "김인혁", 82, 80, 80));
		studentList.add(new Student(20120001, "이지향", 70, 90, 80));
		studentList.add(new Student(20120002, "김진용", 100, 80, 80));
		
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
		
		System.out.println("학번의 오름차순 정렬");
		Collections.sort(studentList);
		for(Student student : studentList) {
			System.out.println(student);
		}
		
		System.out.println("총점의 내림차순, 총점이 같으면 이름의 오름차순 정렬");
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