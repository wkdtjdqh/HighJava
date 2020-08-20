package kr.or.ddit.basic;

import java.util.LinkedList;

public class StackTest {
	public static void main(String[] args) {
		Browser b = new Browser();
		
		b.goURL("1.���̹�");
		b.goURL("2.����");
		b.goURL("3.����");
		b.goURL("4.����");
		
		b.history();
		System.out.println("�ڷΰ���");
		b.goBack();
		b.history();
		
		System.out.println("�ڷΰ���");
		b.goBack();
		b.history();
		
		System.out.println("�����ΰ���");
		b.goForward();
		b.history();
		
		System.out.println("���ο� ����Ʈ ����");
		b.goURL("5.����Ʈ");
		b.history();
	}
}

class Browser {
	private LinkedList<String> back;		// ���� �湮������ ����� ���� ����
	private LinkedList<String> forward; 	// ���� �湮������ ����� ���� ����
	private String currentURL;				// ���� ������
	
	// ������
	public Browser() {
		back = new LinkedList<>();
		forward = new LinkedList<>();
		currentURL = "";
	}
	
	// ����Ʈ�� �湮�ϴ� �޼���, �Ű������� �湮�� URL �Է�
	public void goURL(String url) {
		if (currentURL != null && !"".equals(currentURL)) {
			back.push(currentURL); // ���� �������� back stack������ �߰��Ѵ�.
		}
		currentURL = url; // ���ο� ���� �������� �����Ѵ�.
		
	}
	
	//�ڷ� ����
	public void goBack() {
		if (!back.isEmpty()) {
			forward.push(currentURL);	// ���� �������� forward ���ÿ� �߰��Ѵ�.
			currentURL = back.pop();	// back ���ÿ��� 1���� �����͸� ������ ���� �������� �����Ѵ�.
		}
	}
	
	//������ ����
	public void goForward() {
		if (!forward.isEmpty()) {
			back.push(currentURL);		// ���� �������� back ���ÿ� �߰��Ѵ�.
			currentURL = forward.pop();	// forward ���ÿ��� 1���� �����͸� ������ ���� �������� �����Ѵ�.
		}
	}
	
	// �湮 ����� Ȯ��
	public void history() {
		System.out.println("------------------------------------");
		System.out.println("          ��       ��      ��     ��");
		System.out.println("------------------------------------");
		System.out.println("    back : " + back);
		System.out.println("       ���� : " + currentURL);
		System.out.println(" forward : " + forward);
		System.out.println("------------------------------------");
	}
}