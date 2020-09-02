package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {
	public static void main(String[] args) {
		// 파일 자료 읽기 예제
		try {
			// 읽어올 파일을 매개변수로 받는 FileInputStream객체 생성
			// 방법1
//			FileInputStream fin = new FileInputStream("d:/d_other/test.txt");

			// 방법2
			File file = new File("d:/d_other/test.txt");
			FileInputStream fin = new FileInputStream(file);
			
			int c;	// 읽어온 데이터를 저장할 변수 선언
			
			while((c = fin.read()) != -1) {
				System.out.print((char) c);	// 읽어온 데이터를 화면에 출력하기
			}
			
			fin.close();
			
		} catch (IOException e) {
			System.out.println("입출력 오류입니다.");
			e.printStackTrace();
		}
	}
}
