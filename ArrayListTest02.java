package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*	문제
 *	5명의 사람 이름을 입력받아 ArrayList에 저장한 후
 *	이들 중 '김'씨 성의 이름을 모두 출력하시오.
 *	단, 입력은 Scanner 객체를 이용한다.
 */
public class ArrayListTest02 {
	public static void main(String[] args) {
		ArrayList<String> names = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("5명의 이름을 입력하세요.");
		int count = 1;
		while(count <= 5) {
			names.add(sc.nextLine());
			count++;
		}
		for(String name : names) {
			char eq = name.charAt(0);
			if (eq == '김') {
				System.out.print(name + "\t");
			}
		}
		/*for(String name : names) {
			if (name.indexOf("김") == 0) {
				System.out.print(name + "\t");
			}
		}
		for(String name : names) {
			if (name.startsWith("김")) {
				System.out.print(name + "\t");
			}
		}*/
	}
}