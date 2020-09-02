package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
	문제)
	'd:/d_other/고양이.jpg'파일을 'd:/d_other/연습용'폴더에 '고양이_복사본.jpg'파일로 저장하시오
*/
public class FileCopy {	
	
	public void my() { // 내 풀이
		try {
			File f = new File("d:/d_other/고양이.jpg");
			
			if (!f.exists()) {
				System.out.println(f.getName() + " 파일이 존재하지 않습니다.");
				return;
			}
			
			FileInputStream fin = new FileInputStream(f);
			FileOutputStream fout = new FileOutputStream("d:/d_other/연습용/고양이_복사본.jpg");
			
			int c;
			while((c = fin.read()) != -1) {
				fout.write(c);
			}
			
			System.out.println("복사 완료");
			fout.close();
			fin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sem1() {	// 선생님 풀이1
		String sourceFile = "d:/d_other/고양이.jpg";
		String targetFile = "d:/d_other/연습용/고양이_복사본.jpg";
		
		File f = new File(sourceFile);
		if (!f.exists()) {
			System.out.println(f.getName() + " 파일이 존재하지 않습니다.");
			return;
		}
		
		try {
			FileInputStream fin = new FileInputStream(f);
			FileOutputStream fout = new FileOutputStream(targetFile);
			
			int data;
			System.out.println("복사 시작");
			while((data = fin.read()) != -1) {
				fout.write(data);
			}
			fout.flush();
			
			System.out.println("복사 완료");
			fin.close();
			fout.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sem2() {	// 선생님 풀이2, 바이트 배열을 사용해서 성능 향상
		String sourceFile = "d:/d_other/고양이.jpg";
		String targetFile = "d:/d_other/연습용/고양이_복사본.jpg";
		
		File f = new File(sourceFile);
		if (!f.exists()) {
			System.out.println(f.getName() + " 파일이 존재하지 않습니다.");
			return;
		}
		
		try {
			FileInputStream fin = new FileInputStream(f);
			FileOutputStream fout = new FileOutputStream(targetFile);
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			System.out.println("복사 시작");
			while((len = fin.read(temp)) > 0) {
				fout.write(temp, 0, len);
			}
			fout.flush();
			
			System.out.println("복사 완료");
			fin.close();
			fout.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sem3() {	// 선생님 풀이3, 버퍼 사용
		String sourceFile = "d:/d_other/고양이.jpg";
		String targetFile = "d:/d_other/연습용/고양이_복사본.jpg";
		
		File f = new File(sourceFile);
		if (!f.exists()) {
			System.out.println(f.getName() + " 파일이 존재하지 않습니다.");
			return;
		}
		
		try {
			FileInputStream fin = new FileInputStream(f);
			BufferedInputStream bin = new BufferedInputStream(fin);

			FileOutputStream fout = new FileOutputStream(targetFile);
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			System.out.println("복사 시작");
			while((len = bin.read(temp)) > 0) {
				bout.write(temp, 0, len);
			}
			bout.flush();
			
			System.out.println("복사 완료");
			bin.close();
			bout.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FileCopy fc = new FileCopy();
		fc.my();
	}
	
}
