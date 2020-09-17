package kr.or.ddit.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImp;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;


public class BoardController {
	private IBoardService service;
	private Scanner scan =  new Scanner(System.in);
	private String view = " ";
	
	public BoardController() {
		service = new BoardServiceImp();
	}
	
	public static void main(String[] args) {
		new BoardController().display();
	}
	
	public void display() {
		while(true) {
			List<BoardVO> mainViewAll = new ArrayList<>();
			if (" ".equals(view) || "".equals(view) || view == null) {
				mainViewAll = service.searchAll();
			} else {
				mainViewAll = service.searchTitle(view);
			}
			
			System.out.println("-------------------------------------------------");
			System.out.println("NO\t제 목\t작성자\t조회수");
			for(BoardVO viewPost : mainViewAll) {
			System.out.println(" " + viewPost.getBOARD_NO() + "\t" + viewPost.getBOARD_TITLE() 
							+ "\t" + viewPost.getBOARD_WRITER() +"\t" + viewPost.getBOARD_CNT());
			}
			System.out.println("-------------------------------------------------");
			System.out.println("1. 새글작성 2. 게시글보기 3. 검색 0. 작업끝");
			System.out.print("작업선택 >> ");
			view = " ";
			int input = Integer.parseInt(scan.nextLine());
			switch (input) {
			case 1: newPost(); break;
			case 2: viewPost(); break;
			case 3: searchPost(); break;
			case 0: 
				System.out.println("게시판 프로그램 종료");
				System.exit(0);
			}
		}
	}

	private void searchPost() {
		System.out.print("검색할 제목 입력 >> ");
		view = scan.nextLine();
	}
	
	private void viewPost() {
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int boardNo = scan.nextInt();
		System.out.println(boardNo);
		scan.nextLine();
		// 조회수 올리기
		int res = service.updateCnt(boardNo);
		if (res < 1) {
			return;
		} else {
			BoardVO boardVo = service.viewBoard(boardNo);
			
			System.out.println(boardVo.getBOARD_NO() + "번 글 내용");
			System.out.println("--------------------------------------------------------");
			System.out.println("제목 : " + boardVo.getBOARD_TITLE());
			System.out.println("작성자 : " + boardVo.getBOARD_WRITER());
			System.out.println("내용 : " + boardVo.getBOARD_CONTENT());
			System.out.println("작성일 : " + boardVo.getBOARD_DATE());
			System.out.println("조회수 : " + boardVo.getBOARD_CNT());
			System.out.println("--------------------------------------------------------");
			System.out.println("1. 수정 2. 삭제 3. 리스트로 가기");
			System.out.print("작업선택 >> ");
			switch (Integer.parseInt(scan.nextLine())) {
			case 1: updatePost(boardVo.getBOARD_NO()); break;
			case 2: deletePost(boardVo.getBOARD_NO()); break;
			case 3: break;
			}
		}
	}

	private void deletePost(int boardNo) {
		int res = service.deleteBoard(boardNo);
		if (res < 1) {
			System.out.println("삭제 작업이 실패했습니다.");
		} else {
			System.out.println(boardNo + "번 글이 삭제되었습니다.");
		}
	}

	private void updatePost(int boardNo) {
		System.out.println("수정 작업하기");
		System.out.println("----------------------------------------------");
		System.out.print("제목 >> ");
		String title = scan.nextLine();
		System.out.print("내용 >> ");
		String content = scan.nextLine();
		
		BoardVO boardVo = new BoardVO();
		boardVo.setBOARD_NO(boardNo);
		boardVo.setBOARD_TITLE(title);
		boardVo.setBOARD_CONTENT(content);
		
		int res = service.updateBoard(boardVo);
		if (res < 1) {
			System.out.println("수정 작업이 실패했습니다.");
		} else {
			System.out.println(boardNo + "번 글이 수정되었습니다.");
		}
	}

	private void newPost() {
		BoardVO boardVo = new BoardVO();
		System.out.println("새 글 작성하기");
		System.out.println("------------------------------------");
		System.out.print("제목 >> ");
		boardVo.setBOARD_TITLE(scan.nextLine());
		System.out.print("작성자 >> ");
		boardVo.setBOARD_WRITER(scan.nextLine());
		System.out.print("내용 >> ");
		boardVo.setBOARD_CONTENT(scan.nextLine());

		int result = service.insertBoard(boardVo);
		if (result < 1) {
			System.out.println("글 작성에 실패했습니다.");
		} else {
			System.out.println("새 글이 추가되었습니다.");
		}
	}
}
