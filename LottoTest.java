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
			System.out.println("Lotto 프로그램");
			System.out.println("------------------");
			System.out.println("1.Lotto 구입");
			System.out.println("2.프로그램 종료");
			System.out.println("==================");
	
			Scanner sc = new Scanner(System.in);
			int input = Integer.parseInt(sc.nextLine());
			switch (input) {
			case 1: 
				System.out.println("로또는 하나에 1000원 입니다.");
				System.out.print("금액 입력 > ");
				int pay = Integer.parseInt(sc.nextLine());
				if (pay < 1000) {
					System.out.println("\n입력된 금액이 너무 적습니다.\n");
					break;
				}
				if (pay > 100000) {
					System.out.println("\n입력된 금액이 너무 많습니다.\n");
					break;
				}
				int cnt = pay / 1000;
				int result = pay % 1000;
				System.out.println("받은 금액 : " + pay + "원, 거스름돈 : " + result + "원");
				
				new LottoTest().buyLotto(cnt);
				System.out.println();
				break;
			case 2: 
				System.out.println("감사합니다.");
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
			System.out.println("로또번호" + count + " : " + newLotto);
		}
		
	}
}
