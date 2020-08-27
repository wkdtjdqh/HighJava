package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*	
  	문제1]
	5명의 별명을 입력받아 ArrayList에 저장한 후 이들 중 별명의 길이가 제일 긴 별명을 출력하시오.
	단, 입력한 각각의 별명의 길이는 모두 다르다.
	
*/
public class ArrayListTest03 {
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
		} System.out.println(longName);
	}
}
