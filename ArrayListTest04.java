package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
	����2]
	����1���� ������ ���̰� ���� ���� �ߺ��� ��츦 ó���Ͻÿ�. 
*/

public class ArrayListTest04 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> names = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			System.out.print(i+1 + "��° ���� > ");
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
