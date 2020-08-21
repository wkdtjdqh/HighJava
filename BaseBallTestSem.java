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
	
	// ���ӽ��� �޼���
	public void gameStart() {
		System.out.println("���� �߱� ������ �����մϴ�.");
		getNum();
		int cnt = 0;
		
		do {
			cnt++;
			inputNum();
			ballCount();
		} while (strike != 3);
		System.out.println();
		System.out.println("�����մϴ�.\n����� " + cnt + "�� ���� �����ϼ̽��ϴ�.");
	}
	
	// 1-9������ ���� �ٸ� ���� 3���� ���� ����Ʈ�� �����ϴ� �޼���
	public void getNum() {
		Set<Integer> numSet = new HashSet<>();
		
		// 1-9������ ���� 3���� Set�� ����
		while(numSet.size() < 3) {
			numSet.add((int)(Math.random() * 9)+1);
		}
		
		// ������� ������ List�� �����ϱ�
		numList = new ArrayList<>(numSet);
		Collections.shuffle(numList);
		
	}
	
	// ����� �Է�
	public void inputNum() {
		int n1, n2, n3;
		
		do {
			System.out.print("�����Է�>>");
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			n3 = scan.nextInt();
			
			if (n1 == n2 || n2 == n3 || n1 == n3) {
				System.out.println("�ߺ��Ǵ� ���ڴ� �Է��� �� �����ϴ�. \n�ٽ� �Է��ϼ���.");
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
