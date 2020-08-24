package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/*	문제)
	Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
	리스트에 넣어서 섞어라.
*/
public class BaseBallTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		HashSet<Integer> ballSet = new HashSet<>();
		while(ballSet.size() < 3) {
			ballSet.add((int)(Math.random() * 9 + 1));
		}
		
		Iterator<Integer> it = ballSet.iterator();
		ArrayList<Integer> BaseBallList = new ArrayList<>();
		while(it.hasNext()) {
			int ball = it.next();
			BaseBallList.add(ball);
		}
		Collections.shuffle(BaseBallList);
		
		while(true) {
			int S = 0, B = 0;
			System.out.println("숫자를 입력하세요");
			String inputNum = sc.nextLine();
			ArrayList<Integer> ballNum = new ArrayList<>();
			ballNum.add(Integer.parseInt(inputNum.substring(0, 1)));
			ballNum.add(Integer.parseInt(inputNum.substring(1, 2)));
			ballNum.add(Integer.parseInt(inputNum.substring(2, 3)));
			System.out.println("선택한 번호 : " + ballNum);
			
			for (int i = 0; i < BaseBallList.size(); i++) {
				int count = 0;
				if (BaseBallList.get(i) == ballNum.get(i)) {
					S++;
				}
				for (int j = 0; j < ballNum.size(); j++) {
					if (count < 1) {
						if (BaseBallList.get(i) == ballNum.get(j) && i != j) {
							B++;
							count++;
						} 
					}
				}
			}
			System.out.println("결과 : " + S + "S " + B + "B");
			if (S == 3) {
				System.out.println("맞췄습니다.");
				break;
			}
		}
	}
}
