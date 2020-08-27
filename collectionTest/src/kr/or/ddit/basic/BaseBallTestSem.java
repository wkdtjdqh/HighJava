package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BaseBallTestSem {
	ArrayList<Integer> numList;
	ArrayList<Integer> userList;
	int strike;
	int ball;
	
	Scanner scan = new Scanner(System.in);
	
	// 게임시작 메서드
	public void gameStart() {
		System.out.println("숫자 야구 게임을 시작합니다.");
		getNum();
		int cnt = 0;
		
		do {
			cnt++;
			inputNum();
			ballCount();
		} while (strike != 3);
		System.out.println();
		System.out.println("축하합니다.\n당신은 " + cnt + "번 만에 성공하셨습니다.");
	}
	
	// 1-9사이의 서로 다른 난수 3개를 만들어서 리스트에 저장하는 메서드
	public void getNum() {
		Set<Integer> numSet = new HashSet<>();
		
		// 1-9사이의 난수 3개를 Set에 저장
		while(numSet.size() < 3) {
			numSet.add((int)(Math.random() * 9)+1);
		}
		
		// 만들어진 난수를 List에 저장하기
		numList = new ArrayList<>(numSet);
		Collections.shuffle(numList);
		
	}
	
	// 사용자 입력
	public void inputNum() {
		int n1, n2, n3;
		
		do {
			System.out.print("숫자입력>>");
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			n3 = scan.nextInt();
			
			if (n1 == n2 || n2 == n3 || n1 == n3) {
				System.out.println("중복되는 숫자는 입력할 수 없습니다. \n다시 입력하세요.");
			}
		} while(n1 == n2 || n2 == n3 || n1 == n3);
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
	}
	
	public void ballCount() {
		strike = 0;
		ball = 0;
		
		for (int i = 0; i < userList.size(); i++) {
			for (int j = 0; j < numList.size(); j++) {
				if (userList.get(i) == numList.get(j)) {
					if (i == j) {
						strike++;
					} else ball++;
				}
			}
		}
		
		System.out.println(userList.get(0) + ", " + userList.get(1) + ", " + userList.get(2));
		System.out.println(strike + "strike, " + ball + "ball");
	}
	
	public static void main(String[] args) {
		BaseBallTestSem test = new BaseBallTestSem();
		test.gameStart();
	}
}
