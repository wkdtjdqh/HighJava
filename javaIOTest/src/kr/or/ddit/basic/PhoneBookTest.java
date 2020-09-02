package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

/*	문제)
	이름, 주소, 나이, 전화번호를 멤버변수로 갖는 Phone 클래스를 만들고 Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
	
	- 이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체 출력하는 기능이 있다.
	- 삭제와 검색 기능은 '이름'을 입력받아 처리한다.
	- (Map의 구조는 key로 그사람의 '이름'을 사용하고, value로 'Phone클래스의 인스턴스'로 한다.
	
	- 추가 조건)
		1) 6번 메뉴 전화번호 저장을 추가하고 구현한다.
		   (저장파일명 : phoneData.dat)
		2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 세팅한다.
		3) 프로그램을 종료할 때 Map의 데이터가 변경되었거나 추가 또는 삭제되면 새로 저장한 후 종료되도록 한다.
	
	실행예시)
		다음 메뉴를 선택하세요
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		6. 전화번호 저장
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
	
	private static boolean changeChk;
	Scanner sc = new Scanner(System.in);
	HashMap<String, Phone> PhoneList = new HashMap<>();
		
	private void phAdd() {
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
		changeChk = true;
	}
	
	private void phUpdate() {
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
		changeChk = true;
	}
	
	private void phDelete() {
		System.out.print("삭제할 대상의 이름을 입력 >> ");
		String name = sc.nextLine();
		if (PhoneList.containsKey(name)) {
			PhoneList.remove(name);
			System.out.println("'" + name + "'" + "님이 삭제되었습니다.\n");
			changeChk = true;
		}
	}
	
	private void phSearch() {
		System.out.print("검색할 대상의 이름을 입력 >> ");
		String name = sc.nextLine();
		if (!PhoneList.containsKey(name)) {
			System.out.println("해당 이름의 전화번호 정보가 없습니다.");
		} else {
			System.out.println("이름 : " + PhoneList.get(name).getName());
			System.out.println("주소 : " + PhoneList.get(name).getAddr());
			System.out.println("나이 : " + PhoneList.get(name).getAge());
			System.out.println("번호 : " + PhoneList.get(name).getPh());
			System.out.println();
		}
	}
	
	private void phAll() {
		int num = 0;
		System.out.println("==============================================");
		System.out.println(" 번호          이름       주소         나이               전화번호");
		System.out.println("==============================================");
		if (PhoneList.size() == 0) {
			System.out.println("등록된 전화번호 정보가 없습니다.");
		} else {
			for(String name : PhoneList.keySet()) {
				num++;
				Phone user = PhoneList.get(name);
				System.out.println(" " + num + "\t" + user.getName() + "\t" + user.getAddr() + "\t" + user.getAge() + "\t" + user.getPh());
			}System.out.println();
		}
	}
	
	private void save() {
		try {
			ObjectOutputStream oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d:/d_other/phoneData.dat")));
			/*
			for(String key : PhoneList.keySet()) {
				oout.writeObject(PhoneList.get(key));
			}
			*/
			oout.writeObject(PhoneList);
			oout.close();
		} catch (IOException e) {}
	}
	
	@SuppressWarnings("unchecked")
	private void load() {
		File f = new File("d:/d_other/phoneData.dat");
		if (!f.exists()) {
			System.out.println("불러올 파일이 없습니다. 새로운 파일을 생성합니다.");
		} else {
			try {
				ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
				
				/*
				Object data;
				while((data = oin.readObject()) != null) {
					Phone p = (Phone) data;
					PhoneList.put(p.getName(), p);
				}
				 */
				PhoneList = (HashMap<String, Phone>) oin.readObject();
				
				oin.close();
			} catch (IOException e) {} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
			}
		}
		
	}
	
	private void exit() {
		if (changeChk == true) {
			save();
			System.out.println("변경사항이 자동 저장되었습니다.");
		}
		System.out.println("종료합니다.");
		System.exit(0);
	}
	
	private void phoneInfoManage() {
		load();
		while(true) {
			System.out.println("다음 메뉴를 선택하세요\n1. 전화번호 등록\n2. 전화번호 수정\n3. 전화번호 삭제"
					+ "\n4. 전화번호 검색\n5. 전화번호 전체 출력\n6. 전화번호 저장\n0. 프로그램 종료\n-----------------------");
			int input = Integer.parseInt(sc.nextLine());
			switch (input) {
			case 1: phAdd(); break;
			case 2: phUpdate(); break;
			case 3: phDelete(); break;
			case 4: phSearch(); break;
			case 5: phAll(); break;
			case 6: save(); System.out.println("저장 완료!"); break;
			case 0: exit(); break;
			default: System.out.println("잘못 입력하셨습니다. 다시 입력하세요."); break;
			}
		}
	}
	
	public static void main(String[] args) {
		new PhoneBookTest().phoneInfoManage();
	}
}

class Phone implements Serializable{
	private static final long serialVersionUID = -8614813134983120000L;
	
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