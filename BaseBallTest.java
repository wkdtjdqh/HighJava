package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/*	����)
	Set�� �̿��Ͽ� ���� �߱� ���� ���α׷��� �ۼ��Ͻÿ�.
	����Ʈ�� �־ �����.
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
			System.out.println("���ڸ� �Է��ϼ���");
			String inputNum = sc.nextLine();
			ArrayList<Integer> ballNum = new ArrayList<>();
			ballNum.add(Integer.parseInt(inputNum.substring(0, 1)));
			ballNum.add(Integer.parseInt(inputNum.substring(1, 2)));
			ballNum.add(Integer.parseInt(inputNum.substring(2, 3)));
			System.out.println("������ ��ȣ : " + ballNum);
			
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
			System.out.println("��� : " + S + "S " + B + "B");
			if (S == 3) {
				System.out.println("������ϴ�.");
				break;
			}
		}
	}
}
