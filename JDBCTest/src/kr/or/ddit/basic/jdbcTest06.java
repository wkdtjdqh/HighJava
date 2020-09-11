package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil3;

/*
	회원을 관리하는 프로그램을 작성하시오.
	(오라클 DB의 MYMEMBER테이블 이용)
	
	아래 메뉴의 기능을 모두 구현하시오. (CRUD 구현하기 연습)
	
	메뉴 예시)
		-- 작업 선택 --
		1. 자료 추가
		2. 자료 삭제
		3. 자료 수정
		4. 전체 자료 출력
		0. 작업 끝
		------------
		작업선택 >
*/
public class jdbcTest06 {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Scanner scan = null;
	String sql = "";
	
	public void management(){
		scan = new Scanner(System.in);
		conn = DBUtil3.getConnection();
		
		while(true){
			int input = display();
			
			switch (input) {
			case 1: insert(); break;
			case 2: delete(); break;
			case 3: update(); break;
			case 4: select(); break;
			case 0: end(); break;
			}
		}
	}
	
	private int display() {
		System.out.println("-- 작업 선택 --");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("0. 작업 끝");
		System.out.println("------------");
		System.out.print("작업선택 > ");
		return Integer.parseInt(scan.nextLine());
	}

	private void end() {
		if(ps != null){ try{ ps.close(); } catch(SQLException e){ } }
		if(rs != null){ try{ rs.close(); } catch(SQLException e){ } }
		if(conn != null){ try{ conn.close(); } catch(SQLException e){ } }
		System.out.println("종료합니다.");
		System.exit(0);
	}

	private boolean chk(String id) {
		sql = "SELECT MEM_ID FROM MYMEMBER WHERE MEM_ID = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			if(rs.next()){
				return true;	// 존재하는 아이디
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null){ try{ ps.close(); } catch(SQLException e){ } }
			if(rs != null){ try{ rs.close(); } catch(SQLException e){ } }
		}
		return false;	// 존재하지 않는 아이디
	}
	
	private void select() {
		sql = "SELECT MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR FROM MYMEMBER";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("-----------------------------------------");
			System.out.println(" ID\tNAME\t    TEL\t\tADDRESS");
			System.out.println("-----------------------------------------");
			while(rs.next()){
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
			}
			System.out.println("-----------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null){ try{ ps.close(); } catch(SQLException e){ } }
			if(rs != null){ try{ rs.close(); } catch(SQLException e){ } }
		}
	}

	private void update() {
		System.out.print("UPDATE ID > ");
		String id = scan.nextLine();
		if (!chk(id)) {
			System.out.println("존재하지 않는 ID입니다.");
			return;
		}
		System.out.print("NAME > ");
		String name = scan.nextLine();
		System.out.print("TEL > ");
		String tel = scan.nextLine();
		System.out.print("ADDR > ");
		String addr = scan.nextLine();
		 
		sql = "UPDATE MYMEMBER SET MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR = ? WHERE MEM_ID = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, tel);
			ps.setString(3, addr);
			ps.setString(4, id);
			
			int res = ps.executeUpdate();
			if (res < 1) {
				System.out.println("자료 수정 실패");
				return;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null){ try{ ps.close(); } catch(SQLException e){ } }
			if(conn != null){ try{ conn.close(); } catch(SQLException e){ } }
		}
	}

	private void delete() {
		System.out.print("ID > ");
		String id = scan.nextLine();
		if (!chk(id)) {
			System.out.println("존재하지 않는 ID입니다.");
			return;
		}
		
		sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			int res = ps.executeUpdate();
			if (res < 1) {
				System.out.println("자료 삭제 실패");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null){ try{ ps.close(); } catch(SQLException e){ } }
		}
	}

	private void insert() {
		System.out.print("ID > ");
		String id = scan.nextLine();
		if (chk(id)) {
			System.out.println("이미 있는 ID입니다.");
			return;
		}
		
		System.out.print("NAME > ");
		String name = scan.nextLine();
		System.out.print("TEL > ");
		String tel = scan.nextLine();
		System.out.print("ADDR > ");
		String addr = scan.nextLine();
		
		sql = "INSERT INTO MYMEMBER(MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR) VALUES(?, ?, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, tel);
			ps.setString(4, addr);
			
			int res = ps.executeUpdate();
			if (res < 1) {
				System.out.println("자료 추가 실패");
				return;
			}
			System.out.println("[" + id + "]의 정보가 추가되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null){ try{ ps.close(); } catch(SQLException e){ } }
		}
	}

	public static void main(String[] args) {
		new jdbcTest06().management();
	}
}
