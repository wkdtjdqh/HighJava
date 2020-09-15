package kr.or.ddit.mvc.controller;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberController {
	private IMemberService service;
	private Scanner scan = new Scanner(System.in);
	
	public MemberController() {
		service = MemberServiceImpl.getInstance();
	}
	
	public void management(){
		while(true){
			int input = display();
			
			switch (input) {
			case 1: insert(); break;
			case 2: delete(); break;
			case 3: update(); break;
			case 4: update2(); break;
			case 5: select(); break;
			case 0: end(); break;
			}
		}
	}
	
	private int display() {
		System.out.println("-- 작업 선택 --");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 자료 선택 수정");
		System.out.println("5. 전체 자료 출력");
		System.out.println("0. 작업 끝");
		System.out.println("------------");
		System.out.print("작업선택 > ");
		return Integer.parseInt(scan.nextLine());
	}

	private void end() {
		System.out.println("종료합니다.");
		System.exit(0);
	}
	
	private void select() {
		List<MemberVO> memList = service.getAllMember();
		
		if (memList == null || memList.size() == 0) {
			System.out.println("-------------------");
			System.out.println("회원 정보가 하나도 없습니다.");
			System.out.println("-------------------");
		} else {
			System.out.println("-----------------------------------------");
			System.out.println(" ID\tNAME\t    TEL\t\tADDRESS");
			System.out.println("-----------------------------------------");
			for(MemberVO memVO : memList) {
				System.out.println(memVO.getMem_id() + "\t" + memVO.getMem_name() + "\t"
									+ memVO.getMem_tel() + "\t" + memVO.getMem_addr());
			}
			System.out.println("-----------------------------------------");
		}
	}
	/*
	private void update3() {

	}
	*/
	private void update2() {
		int cnt = 0;
		String memId = null;
		do {
			System.out.print("UPDATE ID > ");
			memId = scan.nextLine();
			cnt = service.getMemberCount(memId);
			if (cnt > 0) {
				break;
			}
			System.out.println("해당 ID가 없습니다.");
		} while (cnt == 0);
		
		MemberVO memVo = service.getMember(memId);
		
		System.out.println("수정 할 항목을 선택하세요.");
		System.out.println("1.NAME 2.TEL 3.ADDRESS 0.CANCEL");
		int input = Integer.parseInt(scan.nextLine());
		switch (input) {
		case 0:
			return;
		case 1:
			System.out.print("NAME > ");
			String memName = scan.nextLine();
			memVo.setMem_name(memName);
			break;
		case 2:
			System.out.print("TEL > ");
			String memTel = scan.nextLine();
			memVo.setMem_tel(memTel);
			break;
		case 3:
			System.out.print("ADDR > ");
			String memAddr = scan.nextLine();
			memVo.setMem_addr(memAddr);
			break;
		}
		
		int res = service.updateMember(memVo);
		if (res < 1) {
			System.out.println("회원 정보 수정 실패");
		} else {
			System.out.println("회원 정보 수정 성공");
		}
	}
	
	private void update() {
		int cnt = 0;
		String memId = null;
		do {
			System.out.print("UPDATE ID > ");
			memId = scan.nextLine();
			cnt = service.getMemberCount(memId);
			if (cnt > 0) {
				break;
			}
			System.out.println("해당 ID가 없습니다.");
		} while (cnt == 0);
		System.out.print("NAME > ");
		String memName = scan.nextLine();
		System.out.print("TEL > ");
		String memTel = scan.nextLine();
		System.out.print("ADDR > ");
		String memAddr = scan.nextLine();
		 
		MemberVO memVo = new MemberVO();
		memVo.setMem_addr(memAddr);
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		
		int res = service.updateMember(memVo);
		if (res < 1) {
			System.out.println("회원 정보 수정 실패");
		} else {
			System.out.println("회원 정보 수정 성공");
		}
	}

	private void delete() {
		int cnt = 0;
		String memId = null;
		do {
			System.out.print("ID > ");
			memId = scan.nextLine();
			cnt = service.getMemberCount(memId);
			if (cnt > 0) {
				break;
			}
			System.out.println("해당 ID가 없습니다.");
		} while (cnt == 0);
		
		int res = service.deleteMember(memId);
		if (res < 1) {
			System.out.println("회원 삭제 실패");
		} else {
			System.out.println("회원 삭제 성공");
		}
	}

	private void insert() {
		int cnt = 0;
		String memId = null;
		do {
			System.out.print("ID > ");
			memId = scan.nextLine();
			cnt = service.getMemberCount(memId);
			if (cnt == 0) {
				break;
			}
			System.out.println("해당 ID가 존재합니다.");
		} while (cnt > 0);
		
		System.out.print("NAME > ");
		String memName = scan.nextLine();
		System.out.print("TEL > ");
		String memTel = scan.nextLine();
		System.out.print("ADDR > ");
		String memAddr = scan.nextLine();
		
		// insert할 데이터를 MemberVO객체에 담는다
		MemberVO memVo = new MemberVO();
		memVo.setMem_addr(memAddr);
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		
		int res = service.insertMember(memVo);
		if (res < 1) {
			System.out.println("회원 등록 실패");
		} else {
			System.out.println("회원 등록 성공");
		}
	}

	public static void main(String[] args) {
		new MemberController().management();
	}
}
