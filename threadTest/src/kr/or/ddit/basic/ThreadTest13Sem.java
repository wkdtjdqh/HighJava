package kr.or.ddit.basic;

import java.util.Arrays;

public class ThreadTest13Sem {
	public static void main(String[] args) {
		Horse2[] horseArr = new Horse2[]{
			new Horse2("01번말"), new Horse2("02번말"), new Horse2("03번말"),	
			new Horse2("04번말"), new Horse2("05번말"), new Horse2("06번말"),
			new Horse2("07번말"), new Horse2("08번말"), new Horse2("09번말")
		};
		
		System.out.println("경기 시작");
		GameState gs = new GameState(horseArr);
		
		for (Horse2 hs : horseArr) {
			hs.start();
		}
		gs.start();
		
		for (Horse2 hs : horseArr) {
			try {
				hs.join();
			} catch (InterruptedException e) {}
		}

		try {
			gs.join();
		} catch (InterruptedException e) {}
		
		System.out.println("경기 끝");
		System.out.println();
		System.out.println("경기 결과");
		System.out.println();

		Arrays.sort(horseArr);
		for (Horse2 hs : horseArr) {
			System.out.println(hs);
		}
	}
}

class Horse2 extends Thread implements Comparable<Horse2> {
	public static int currentRank;
	
	private String horseName;
	private int rank;
	private int position;
	
	public Horse2(String horseName) {
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "경주마" + horseName + "는(은) " + rank + "등입니다.";
	}

	@Override
	public int compareTo(Horse2 o) {
		return new Integer(this.rank).compareTo(o.getRank());
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			this.position = i;
			try {
				Thread.sleep((int)(Math.random()*300));
			} catch (InterruptedException e) {}
		}
		
		currentRank++;
		this.rank = currentRank;
	}
}

class GameState extends Thread {
	private Horse2[] horseArr;
	
	public GameState(Horse2[] horseArr) {
		this.horseArr = horseArr;
	}
	
	@Override
	public void run() {
		while(true) {
			if (Horse2.currentRank == horseArr.length) {
				break;
			}
			
			for (int i = 0; i < 20; i++) {System.out.println();}
			
			for (int i = 0; i < horseArr.length; i++) {
				System.out.print(horseArr[i].getHorseName() + " : ");
				for (int j = 1; j <= 50; j++) {
					if (j == horseArr[i].getPosition()) {
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
	}
}