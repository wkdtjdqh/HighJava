package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {
	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		// 입력용 스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		
		// 출력용 스트림 객체 생성
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		int data;	// 읽어온 데이터가 저장될 변수 선언
		
		// read()메서드로 자료 읽기 ==> 더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while((data = input.read()) != -1) {	// 자료 읽기
			output.write(data);	// 자료 출력 또는 읽어온 데이터를 활용
		}
		
		// 출력된 스트림의 데이터를 배열로 변환해서 저장하기
		outSrc = output.toByteArray();	// 바이트 배열로 변환
		
		try {
			// 사용한 스트림객체 닫기
			input.close();
			output.close();
		} catch (IOException e) {
		}
		
		System.out.println("inSrc  => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
	}
}
