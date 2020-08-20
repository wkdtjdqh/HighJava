package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class LottoTest {
	public static void main(String[] args) {
		while(true) {
			System.out.println("==================");
			System.out.println("Lotto ���α׷�");
			System.out.println("------------------");
			System.out.println("1.Lotto ����");
			System.out.println("2.���α׷� ����");
			System.out.println("==================");
	
			Scanner sc = new Scanner(System.in);
			int input = Integer.parseInt(sc.nextLine());
			switch (input) {
			case 1: 
				System.out.println("�ζǴ� �ϳ��� 1000�� �Դϴ�.");
				System.out.print("�ݾ� �Է� > ");
				int pay = Integer.parseInt(sc.nextLine());
				if (pay < 1000) {
					System.out.println("\n�Էµ� �ݾ��� �ʹ� �����ϴ�.\n");
					break;
				}
				if (pay > 100000) {
					System.out.println("\n�Էµ� �ݾ��� �ʹ� �����ϴ�.\n");
					break;
				}
				int cnt = pay / 1000;
				int result = pay % 1000;
				System.out.println("���� �ݾ� : " + pay + "��, �Ž����� : " + result + "��");
				
				new LottoTest().buyLotto(cnt);
				System.out.println();
				break;
			case 2: 
				System.out.println("�����մϴ�.");
				System.exit(0);
			}
		}
	}
	
	public void buyLotto(int cnt) {
		int count = 0;
		while(count < cnt) {
			HashSet<Integer> lotto = new HashSet<>();
			while(lotto.size() < 6) {
				lotto.add((int)(Math.random() * 44 + 1));
			}
			Iterator<Integer> it = lotto.iterator();
			ArrayList<Integer> newLotto = new ArrayList<>();
			while(it.hasNext()) {
				int num = it.next();
				newLotto.add(num);
			}
			Collections.sort(newLotto);
			count++;
			System.out.println("�ζǹ�ȣ" + count + " : " + newLotto);
		}
		
	}
}
