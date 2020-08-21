package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import javax.jws.soap.SOAPBinding.Use;

/*	문제)
	이름, 주소, 나이, 전화번호를 멤버변수로 갖는 Phone 클래스를 만들고 Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
	
	- 이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체 출력하는 기능이 있다.
	- 삭제와 검색 기능은 '이름'을 입력받아 처리한다.
	- (Map의 구조는 key로 그사람의 '이름'을 사용하고, value로 'Phone클래스의 인스턴스'로 한다.
	
	실행예시)
		다음 메뉴를 선택하세요
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
	-----------------------
	번호입력 >> 1
	
	새롭게 등록할 전화번호 정보를 입력하세요.
	이름 >> 홍길동
	주소 >> 대전시 중구 대흥동
	나이 >> 30
	전화번호 >> 010-1111-1111
	
	'홍길동' 전화번호 등록 완료!!
	
	새로 등록할 때 같은 사람을 입력하면 이미 등록된 사람입니다.
	
	전체출력
	==============================================
	  번호    이름        전화번호            나이            주소
	==============================================
	  1    홍길동  010-1111-1111   30     대전시 중구 대흥동
	  ...
	==============================================
	출력완료 이후 메뉴선택으로 이동
*/
public class PhoneBookTest {
	
	Scanner sc = new Scanner(System.in);
	HashMap<String, Phone> PhoneList = new HashMap<>();
		
	public void phAdd() {
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = sc.nextLine();
		
		if (PhoneList.containsKey(name)) {
			System.out.println("이미 등록된 사람입니다.");
		} else {
			System.out.print("주소 >> ");
			String addr = sc.nextLine();
			System.out.print("나이 >> ");
			int age = Integer.parseInt(sc.nextLine());
			System.out.print("번호 >> ");
			String ph = sc.nextLine();
			Phone phone = new Phone(name, addr, age, ph);
			PhoneList.put(phone.getName(), phone);
			
			System.out.println(phone.getName() + "님이 등록되었습니다.");
		}
	}
	
	public void phUpdate() {
		System.out.print("수정할 대상의 이름을 입력 >> ");
		String name = sc.nextLine();
		if (PhoneList.containsKey(name) == true) {
			System.out.print("주소 >> ");
			String addr = sc.nextLine();
			System.out.print("나이 >> ");
			int age = Integer.parseInt(sc.nextLine());
			System.out.print("번호 >> ");
			String ph = sc.nextLine();
			
			PhoneList.put(name, new Phone(name, addr, age, ph));
		}
	}
	
	public void phDelete() {
		System.out.print("삭제할 대상의 이름을 입력 >> ");
		String name = sc.nextLine();
		Phone result = PhoneList.remove(name);
		if (result.equals(name)) {
			System.out.println("'" + name + "'" + "님이 삭제되었습니다.\n");
		}
	}
	
	public void phSearch() {
		System.out.print("검색할 대상의 이름을 입력 >> ");
		String name = sc.nextLine();
		System.out.println("이름 : " + PhoneList.get(name).getName());
		System.out.println("주소 : " + PhoneList.get(name).getAddr());
		System.out.println("나이 : " + PhoneList.get(name).getAge());
		System.out.println("번호 : " + PhoneList.get(name).getPh());
		System.out.println();
	}
	
	public void phAll() {
		int num = 0;
		System.out.println("========================================================");
		System.out.println(" 번호       이름                 주소                나이                전화번호");
		System.out.println("========================================================");
		
		for(String name : PhoneList.keySet()) {
			num++;
			Phone user = PhoneList.get(name);
			System.out.println("  " + num + "\t" + user.getName() + "\t" + user.getAddr() + "\t" + user.getAge() + "\t" + user.getPh());
		}System.out.println();
	}
	
	public void phoneInfoManage() {
		while(true) {
			System.out.println("다음 메뉴를 선택하세요\n1. 전화번호 등록\n2. 전화번호 수정\n3. 전화번호 삭제"
					+ "\n4. 전화번호 검색\n5. 전화번호 전체 출력\n0. 프로그램 종료\n-----------------------");
			int input = Integer.parseInt(sc.nextLine());
			switch (input) {
			case 1: phAdd(); break;
			case 2: phUpdate(); break;
			case 3: phDelete(); break;
			case 4: phSearch(); break;
			case 5: phAll(); break;
			case 0: 
				System.out.println("종료합니다.");
				System.exit(0);
			}
		}
	}
	
	public static void main(String[] args) {
		new PhoneBookTest().phoneInfoManage();
	}
}

class Phone {
	private String name;
	private String addr;
	private int age;
	private String ph;
	
	public Phone(String name, String addr, int age, String ph) {
		super();
		this.name = name;
		this.addr = addr;
		this.age = age;
		this.ph = ph;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	
}