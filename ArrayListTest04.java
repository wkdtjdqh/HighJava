package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
	문제2]
	문제1에서 별명의 길이가 같은 것이 중복될 경우를 처리하시오. 
*/

public class ArrayListTest04 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> names = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			System.out.print(i+1 + "번째 별명 > ");
			names.add(sc.nextLine());
		}
		
		String longName = names.get(0);
		for (int i = 0; i < names.size(); i++) {
			if (longName.length() < names.get(i).length()) {
				longName = names.get(i);
			}
		}
		
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).length() == longName.length()) {
				System.out.print(names.get(i) + "\t");
			} 
		}
	}
}
