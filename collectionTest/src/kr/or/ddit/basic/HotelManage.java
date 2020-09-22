package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class HotelManage {
	Scanner sc = new Scanner(System.in);
	private HashMap<Integer, Room> room = new HashMap<>();
	
	// 저장
	private void save() {
		try {
			ObjectOutputStream oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d:/d_other/room.dat")));
			oout.writeObject(room);
			oout.close();
		} catch (IOException e) {}
		System.out.println("저장되었습니다.");
	}
	
	// 불러오기
	@SuppressWarnings("unchecked")
	private void load() {
		File f = new File("d:/d_other/room.dat");
		if (!f.exists()) {
			System.out.println("불러올 파일이 없습니다. 새로운 파일을 생성합니다.");
			makeRoom();
			return;
		}
		try {
			ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			room = (HashMap<Integer, Room>) oin.readObject();
			oin.close();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 방종류 설정
	private String Roomkind(int roomNum) {
		String roomName = "싱글룸";
		if (roomNum > 300 && roomNum < 400) {
			roomName = "더블룸";
		} else if (roomNum > 400) {
			roomName = "스위트룸";
		}
		return roomName;
	}
	
	// 방 생성
	private void makeRoom() {
		int roomNum = 201;
		while(true) {
			if (roomNum > 409) {
				break;
			}
			String roomName = Roomkind(roomNum);
			room.put(roomNum, new Room(roomNum, roomName));
			room.get(roomNum).setName("-");
			
			if (roomNum % 100 == 9) {
				roomNum += 91;
			}
			roomNum++;
		}
	}
	
	// 체크아웃
	private void checkOut() {
		System.out.println("-------------------------");
		System.out.println("  체크아웃 작업");
		System.out.println("-------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방번호 입력 >> ");
		int roomNum = Integer.parseInt(sc.nextLine());
		if (!room.get(roomNum).getName().equals("-")) {
			room.get(roomNum).setName("-");
			System.out.println("체크아웃 하였습니다.");
		}
	}

	private void roomStatus() {
		System.out.println("-------------------------");
		System.out.println("  현재 객실 상태");
		System.out.println("-------------------------");
		System.out.println(" 방번호      방종류      투숙객 이름");
		System.out.println("-------------------------");
		
		ArrayList<Integer> sortList = new ArrayList<>(room.keySet());
		Collections.sort(sortList);
		for(int number : sortList) {
			System.out.println("  " + room.get(number).getroomNum() + "\t" + room.get(number).getroom() + "\t" + room.get(number).getName());
		}
		
	}
	// 체크인
	private void checkIn() {
		System.out.println("-------------------------");
		System.out.println("  체크인 작업");
		System.out.println("-------------------------");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~409 : 스위트룸");
		System.out.println("-------------------------");
		System.out.print("방 번호 입력 >> ");
		int roomNum = Integer.parseInt(sc.nextLine());
		if (!room.containsKey(roomNum)) {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
		} else {
			System.out.print("체크인하시는 분 성함 >>");
			String chkInName = sc.nextLine();
			String roomName = Roomkind(roomNum);
			if (room.get(roomNum).getName().equals("-")) {
				room.get(roomNum).setName(chkInName);
				System.out.println("체크인이 완료되었습니다.");
			} else {
				System.out.println(roomName + "호 객실은 이미 손님이 있습니다.");
			}
		}
	}
	
	// 호텔운영시작
	private void manage() {
		load();
		
		while(true) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인    2.체크아웃    3.객실상태    4.저장    0.업무종료");
			System.out.println("-----------------------------------------------------------");
			int input = Integer.parseInt(sc.nextLine());
			switch (input) {
			case 1: checkIn(); break;
			case 2: checkOut(); break;
			case 3: roomStatus(); break;
			case 4: save(); break;
			case 0: 
				System.out.println("업무를 종료합니다.");
				System.exit(0); break;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("*********************************************");
		System.out.println("        호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		new HotelManage().manage();
	}
}

class Room implements Serializable{
	private static final long serialVersionUID = -8897764372949801477L;
	private int roomNum;
	private String room;
	private String name;
	
	public Room(int roomNum, String room) {
		this.roomNum = roomNum;
		this.room = room;
	}

	public int getroomNum() {
		return roomNum;
	}

	public void setroomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getroom() {
		return room;
	}

	public void setroom(String room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}