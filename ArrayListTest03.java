package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*	
  	����1]
	5���� ������ �Է¹޾� ArrayList�� ������ �� �̵� �� ������ ���̰� ���� �� ������ ����Ͻÿ�.
	��, �Է��� ������ ������ ���̴� ��� �ٸ���.
	
*/
public class ArrayListTest03 {
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
		} System.out.println(longName);
	}
}
