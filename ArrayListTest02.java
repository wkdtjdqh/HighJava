package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*	����
 *	5���� ��� �̸��� �Է¹޾� ArrayList�� ������ ��
 *	�̵� �� '��'�� ���� �̸��� ��� ����Ͻÿ�.
 *	��, �Է��� Scanner ��ü�� �̿��Ѵ�.
 */
public class ArrayListTest02 {
	public static void main(String[] args) {
		ArrayList<String> names = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("5���� �̸��� �Է��ϼ���.");
		int count = 1;
		while(count <= 5) {
			names.add(sc.nextLine());
			count++;
		}
		for(String name : names) {
			char eq = name.charAt(0);
			if (eq == '��') {
				System.out.print(name + "\t");
			}
		}
		/*for(String name : names) {
			if (name.indexOf("��") == 0) {
				System.out.print(name + "\t");
			}
		}
		for(String name : names) {
			if (name.startsWith("��")) {
				System.out.print(name + "\t");
			}
		}*/
	}
}