package kr.or.ddit.basic;

import java.io.File;

public class FileTest02 {
	public static void main(String[] args) {
		File f1 = new File("d:/d_other/test.txt");
		System.out.println(f1.getName() + "의 크기 : " + f1.length() + "bytes");
		System.out.println("path : " + f1.getPath());
		System.out.println("absolutePath : " + f1.getAbsolutePath());
		System.out.println();
		
		File f2 = new File(".");
		System.out.println("path : " + f2.getPath());
		System.out.println("absolutePath : " + f2.getAbsolutePath());
		System.out.println();
		
		if (f1.isFile()) {
			System.out.println(f1.getName() + "은 파일입니다.");
		} else if (f1.isDirectory()) {
			System.out.println(f1.getName() + "은 폴더입니다.");
		} else {
			System.out.println(f1.getName() + "은 뭡니까?");
		} System.out.println();
		
		if (f2.isFile()) {
			System.out.println(f2.getName() + "은 파일입니다.");
		} else if (f2.isDirectory()) {
			System.out.println(f2.getName() + "은 폴더입니다.");
		} else {
			System.out.println(f2.getName() + "은 뭡니까?");
		} System.out.println();
		
		File f3 = new File("d:/d_other/sample.exe");
		if (f3.isFile()) {
			System.out.println(f3.getName() + "은 파일입니다.");
		} else if (f3.isDirectory()) {
			System.out.println(f3.getName() + "은 폴더입니다.");
		} else {
			System.out.println(f3.getName() + "은 뭡니까?");
		} System.out.println();
	}
}
