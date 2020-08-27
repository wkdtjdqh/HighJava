package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 10마리의 말들이 경주하는 경마 프로그램 작성하기
 말은 Horse라는 이름의 클래스로 구성한다. (이 각각의 말들은 하나의 경기를 진행하는 쓰레드가 된다.)
 이 클래스는 말이름(String), 등수(int), 말의 현재위치(int)를 멤버변수로 갖는다.
 그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬기준이 있다. (Comparable 인터페이스 구현하기)
 경기 구간은 1 ~ 50 구간으로 되어 있다.
 경기중 중간 중간에 각 말들의 위치를 나타내시오.
 */

public class ThreadTest13 {
	public static ArrayList<Horse> hs;

	public static void main(String[] args) {
		hs = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			hs.add(new Horse(String.valueOf(i+1) + "번 말"));
		}
		
		Ground gr = new Ground();
		for(Horse h : hs) {
			h.start();
		}
		gr.start();
		
		for (Horse h : hs) {
			try {
				h.join();
			} catch (InterruptedException e) {
			}
		}
		try {
			gr.join();
		} catch (InterruptedException e) { }
		
		Collections.sort(hs);
		
		System.out.println("\n----순위----");
		for (int i = 0; i < hs.size(); i++) {
			System.out.println(hs.get(i).getRank() + "위 : " + hs.get(i).gethname());
		}
		System.out.println("-----------");
	}
}

class Horse extends Thread implements Comparable<Horse>{
	private String hname;
	private int rank;
	private int now;
	String[] way = new String[50];
	public static boolean out;
	public static int rankNum;

	public Horse(String hname) {
		this.hname = hname;
	}

	public int getRank() {
		return rank;
	}

	public String gethname() {
		return hname;
	}

	public void sethname(String hname) {
		this.hname = hname;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getNow() {
		return now;
	}

	public void setNow(int now) {
		this.now = now;
	}

	@Override
	public String toString() {
		return "[" + hname + "]" + Arrays.toString(way);
	}

	@Override
	public void run() {
		while (true) { // 경기 시작
			for (int i = 0; i < 50; i++) { // 구간 설정
				way[i] = "";
			}
			way[now] = "▶";

			try {
				Thread.sleep((int) (Math.random() * 200) + 101);
			} catch (Exception e) {
			}
			if (now == 49) {
				System.out.println(hname + " 도착");
				rankNum++;
				setRank(rankNum);
				out = true;
				return;
			}
			now++;
		}
	}

	@Override
	public int compareTo(Horse o) {
		return new Integer(this.rank).compareTo(o.getRank()); 
	}
}

class Ground extends Thread {
	@Override
	public void run() {
		
		while(true){
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			
			System.out.println(ThreadTest13.hs.get(0).toString());
			System.out.println(ThreadTest13.hs.get(1).toString());
			System.out.println(ThreadTest13.hs.get(2).toString());
			System.out.println(ThreadTest13.hs.get(3).toString());
			System.out.println(ThreadTest13.hs.get(4).toString());
			System.out.println(ThreadTest13.hs.get(5).toString());
			System.out.println(ThreadTest13.hs.get(6).toString());
			System.out.println(ThreadTest13.hs.get(7).toString());
			System.out.println(ThreadTest13.hs.get(8).toString());
			System.out.println(ThreadTest13.hs.get(9).toString());

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (Horse.rankNum == 10) {
				break;
			}
		}
		
	}
}