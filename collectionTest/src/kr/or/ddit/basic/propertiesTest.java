package kr.or.ddit.basic;

import java.util.Properties;

public class propertiesTest {
	public static void main(String[] args) {
		/*
			Properties객체는 Map객체보다 축소된 기능의 객체라고 할 수 있다.
			
			Map은 key와 value에 모든 형태의 객체를 사용할 수 있다.
			Properties는 key와 value에 String만 사용할 수 있다.
			
			Map은 put(), get()메서드를 이용하여 데이터를 입출력하지만
			Properties는 setProperty(), getProperty()메서드를 통해서 데이터 입출력을 한다.
			
			Properties는 데이터를 파일로 입출력할 수 있다.
		*/
		Properties prop = new Properties();
		
		// 데이터 입력
		prop.setProperty("name", "장성보");
		prop.setProperty("age", "26");
		prop.setProperty("tel", "010-5045-1742");
		prop.setProperty("addr", "대전");
		
		// 데이터 출력
		String name = prop.getProperty("name");
		int age = Integer.parseInt(prop.getProperty("age"));
		String tel = prop.getProperty("tel");
		String address = prop.getProperty("addr");
		
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("전화번호 : " + tel);
		System.out.println("주소 : " + address);
	}
}
