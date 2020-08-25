package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class BookManagement {
	Scanner sc;
	HashMap<Integer, Book> books = new HashMap<>();
	
	public void bookInfoInsert() {
		int number = 0;
		try {
			number = Collections.max(books.keySet());
		} catch (Exception e) {
			number = 1;
		}
		if (!books.containsKey(number)) {
			System.out.print("제목 >> ");
			String title = sc.nextLine();
			System.out.print("지은이 >> ");
			String name = sc.nextLine();
			System.out.print("장르 >> ");
			String category = sc.nextLine();
			System.out.println("도서 번호는 [" + number + "] 입니다.");
			books.put(number, new Book(number, title, name, category, true));
			number++;
		} 
	}
	
	public void bookInfoUpdate() {
		boolean stat = true;
		System.out.print("수정할 도서번호 >> ");
		int bookNum = Integer.parseInt(sc.nextLine());
		System.out.print("제목 >> ");
		String title = sc.nextLine();
		System.out.print("지은이 >> ");
		String name = sc.nextLine();
		System.out.print("장르 >> ");
		String category = sc.nextLine();
		System.out.print("대출가능여부(y/n) >> ");
		String status = sc.nextLine();
		if (status.equals("n")) {
			stat = !stat;
		}
		
		if (!books.containsKey(bookNum)) {
			System.out.println("해당 도서가 존재하지않습니다.");
		} else {
			books.put(bookNum, new Book(bookNum, title, name, category, stat));
		}
	}
	
	public void bookInfoDelete() {
		System.out.print("삭제할 도서번호 >> ");
		int bookNum = Integer.parseInt(sc.nextLine());
		if (!books.containsKey(bookNum)) {
			System.out.println("해당 도서정보가 없습니다.");
		} else {
			books.remove(bookNum);
			System.out.println("해당 도서정보가 삭제되었습니다.");
		}
	}
	
	public void bookInfoSearch() {
		System.out.print("검색할 도서번호 >> ");
		int bookNum = Integer.parseInt(sc.nextLine());
		if (!books.containsKey(bookNum)) {
			System.out.println("해당 도서정보가 없습니다.");
		} else {
			System.out.println("도서번호\t제목\t지은이\t장르\t대출가능여부");
			System.out.println("----------------------------------------------------");
			Book book = books.get(bookNum);
			String st;
			if (book.isBookStatus()) {
				st = "대출가능";
			} else {
				st = "대출불가능";
			}
			System.out.println(" " + book.getBookNum() + "\t" + book.getBookTitle() + "\t" + book.getWriter() + "\t" + book.getBookCat()
					+ "\t" + st);
		}
	}
	
	public void AllSearch() {
		System.out.println("도서번호\t제목\t지은이\t장르\t대출가능여부");
		System.out.println("----------------------------------------------------");
		ArrayList<Integer> list = new ArrayList<>(books.keySet());
		Collections.sort(list);
		for(Integer key : list) {
			Book book = books.get(key);
			String st;
			if (book.isBookStatus()) {
				st = "대출가능";
			} else {
				st = "대출불가능";
			}
			System.out.println(" " + book.getBookNum() + "\t" + book.getBookTitle() + "\t" + book.getWriter() + "\t" + book.getBookCat()
					+ "\t" + st);
		}
	}
	
	public void rentalIn() {
		System.out.println("대여가능한 도서 정보입니다.");
		System.out.println("도서번호\t제목\t지은이\t장르\t대출가능여부");
		System.out.println("----------------------------------------------------");
		ArrayList<Integer> list = new ArrayList<>(books.keySet());
		Collections.sort(list);
		for(Integer key : list) {
			Book book = books.get(key);
			String st;
			if (book.isBookStatus()) {
				st = "대출가능";
			} else {
				st = "대출불가능";
			}
			if (book.isBookStatus() == true) {
				System.out.println(" " + book.getBookNum() + "\t" + book.getBookTitle() + "\t" + book.getWriter() + "\t" + book.getBookCat()
						+ "\t" + st);
			}
		}
		System.out.println("----------------------------------------------------");
		System.out.print("대여할 도서번호를 입력 >> ");
		int bookNum = Integer.parseInt(sc.nextLine());
		if (books.get(bookNum).isBookStatus()) {
			Book book = books.get(bookNum);
			System.out.println(" " + book.getBookNum() + "\t" + book.getBookTitle() + "\t" + book.getWriter() + "\t" + book.getBookCat());
			book.setBookStatus(false);
			System.out.println("위에 해당하는 도서를 대여하셨습니다.");
		} else {
			System.out.println("해당 도서는 대출이 불가능합니다.");
		}
	}
	
	public void rentalOut() {
		System.out.print("반납하실 도서번호를 입력 >> ");
		int input = Integer.parseInt(sc.nextLine());
		Book book = books.get(input);
		if (books.containsKey(input)) {
			if (!book.isBookStatus()) {
				System.out.println(" " + book.getBookNum() + "\t" + book.getBookTitle() + "\t" + book.getWriter() + "\t" + book.getBookCat());
				book.setBookStatus(true);
				System.out.println("위에 해당하는 도서의 반납이 완료되었습니다.");
			} else {
				System.out.println("해당 도서는 대여상태가 아닙니다.");
			}
		}
	}
	
	public void rental() {
		System.out.println("1.대여 2.반납");
		int input = Integer.parseInt(sc.nextLine());
		switch (input) {
		case 1: rentalIn(); break;
		case 2: rentalOut(); break;
		}
		
	}
	
	public void manager() {
		while(true) {
			sc = new Scanner(System.in);
			System.out.println("사용할 메뉴를 선택하세요.");
			System.out.println("1. 도서 정보 등록");
			System.out.println("2. 도서 정보 수정");
			System.out.println("3. 도서 정보 삭제");
			System.out.println("4. 도서 정보 검색");
			System.out.println("5. 전체 도서 정보 출력");
			System.out.println("6. 도서 반납 및 대여");
			System.out.println("0. 프로그램 종료");
			
			int input = Integer.parseInt(sc.nextLine());
			switch (input) {
			case 1: bookInfoInsert(); break;
			case 2: bookInfoUpdate(); break;
			case 3: bookInfoDelete(); break;
			case 4: bookInfoSearch(); break;
			case 5: AllSearch(); break;
			case 6: rental(); break;
			case 0: 
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
				break;
			default: System.out.println("잘못입력하셨습니다. 다시 입력하세요."); break;
			}
		}
	}
	
	public static void main(String[] args) {
		new BookManagement().manager();
	}
	
}

class Book {
	private int bookNum;
	private String bookTitle;
	private String writer;
	private String bookCat;
	private boolean bookStatus;
	
	public Book(int bookNum, String bookTitle, String writer, String bookCat,
			boolean bookStatus) {
		super();
		this.bookNum = bookNum;
		this.bookTitle = bookTitle;
		this.writer = writer;
		this.bookCat = bookCat;
		this.bookStatus = bookStatus;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getBookCat() {
		return bookCat;
	}

	public void setBookCat(String bookCat) {
		this.bookCat = bookCat;
	}

	public boolean isBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(boolean bookStatus) {
		this.bookStatus = bookStatus;
	}
	
}