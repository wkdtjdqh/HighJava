package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectIOTest {
	public static void main(String[] args) {
		// 객체를 파일에 저장하는 예제
		Member m1 = new Member("홍길동", 20, "대전");
		Member m2 = new Member("홍길서", 30, "서울");
		Member m3 = new Member("홍길남", 40, "인천");
		Member m4 = new Member("홍길북", 50, "강릉");
		
		try {
			// 객체 파일에 저장하기
			// 파일 저장용 스프링 객체 생성
			FileOutputStream fout = new FileOutputStream("d:/d_other/memObj.bin");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout = new ObjectOutputStream(bout);
			
			// 쓰기 작업 수행
			System.out.println("객체 저장 시작");
			oout.writeObject(m1);
			oout.writeObject(m2);
			oout.writeObject(m3);
			oout.writeObject(m4);
			System.out.println("객체 저장 작업 완료");
			
			// 스트림 닫기
			oout.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ObjectInputStream oin = null;
		try {
			// 저장된 객체를 읽어와 그 내용을 화면에 출력하기
			// 입력용 스트림 객체 생성
			oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/d_other/memObj.bin")));
			
			Object obj;	// 읽어온 객체를 저장할 변수
			while((obj = oin.readObject()) != null) {	// readObject()메서드의 반환값은 Object형이다. 데이터를 끝가지 다 읽어오면 EOFException이 발생한다.
				// 읽어온 데이터를 원래의 객체형으로 형변환 후에 사용한다.
				Member mem = (Member) obj;
				
				System.out.println("--------------------");
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("--------------------");
			}
			
		}  catch (EOFException e) {
			System.out.println("파일 읽기 완료");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {oin.close();} catch (IOException e) {}
		}
	}
}

// 데이터 저장용 클래스 작성(VO와 같은 역할을 하는 클래스)
class Member implements Serializable{
	private static final long serialVersionUID = -1096438140999733430L;
	
	private String name;
	
/*	
	transient
		- 직렬화에서 제외시킬 인스턴스 변수에 지정한다.
		- 직렬화가 되지 않은 인스턴스 변수는 기본값으로 저장된다.
		- 참조변수 -> null, 숫자유형 -> 0, 논리형 -> false
*/	
	private transient int age;
	private transient String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}