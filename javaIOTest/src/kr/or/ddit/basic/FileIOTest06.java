package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileIOTest06 {
	public static void main(String[] args) {
		
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			
			FileOutputStream fout = new FileOutputStream("d:/d_other/outTest.txt");
			
			// 기본 인코딩 방식으로 저장
//			OutputStreamWriter osw = new OutputStreamWriter(fout);
			
			// 인코딩 방식을 지정해서 저장하기
			OutputStreamWriter osw = new OutputStreamWriter(fout, "utf-8");
			
			System.out.println("파일에 저장될 내용을 입력하세요.");
			System.out.println("입력을 마치려면 Ctrl + z");
			
			int c;
			while((c = isr.read()) != -1) {
				osw.write(c);
			}
			
			isr.close();
			osw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
