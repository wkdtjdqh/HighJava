package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Scanner;

public class HotelManage {
	Scanner sc = new Scanner(System.in);
	HashMap<Integer, Room> room = new HashMap<>();
	
	public void manage() {
		
	}
	
	public void makeRoom() {
		int roomNum = 201;
		String roomName = "싱글룸";
		String name = "-";
		while(true) {
			if (roomNum > 300 && roomNum < 400) {
				roomName = "더블룸";
			} else if (roomNum > 400) {
				roomName = "스위트룸";
			}
			
			room.put(roomNum, new Room(roomNum, roomName, name));
			
			roomNum++;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("*********************************************");
		System.out.println("        호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		new HotelManage().manage();
	}
}

class Room {
	private int RoomNum;
	private String Room;
	private String name;
	
	public Room(int roomNum, String room, String name) {
		super();
		RoomNum = roomNum;
		Room = room;
		this.name = name;
	}

	public int getRoomNum() {
		return RoomNum;
	}

	public void setRoomNum(int roomNum) {
		RoomNum = roomNum;
	}

	public String getRoom() {
		return Room;
	}

	public void setRoom(String room) {
		Room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}