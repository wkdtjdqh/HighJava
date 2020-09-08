package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
	Lprod 테이블에 새로운 데이터 추가하기
	
	lprod_gu와 lprod_nm은 직접 입력받아서 처리하고, lprod_id는 현재의 lprod_id중 제일 큰 값보다 1 크게한다.
	그리고, 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받는다.
*/
public class jdbcTest05 {
	
	public static void inputData(){
		
	}
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "", gu = "", nm = "";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC11", "java");
			
			Scanner scan = new Scanner(System.in);
			
			while(true){
				
				System.out.print("LPROD_GU >> ");
				gu = scan.nextLine();
				
				System.out.println(gu);
				sql = "SELECT * FROM LPROD WHERE LPROD_GU = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, gu);
				rs = ps.executeQuery();
				
				if(!rs.next()){
					ps.close();
					break;
				}
				System.out.println("입력하신 LPROD_GU가 이미 존재합니다. 다시 입력하세요.");
			}
			System.out.print("LPROD_NM >> ");
			nm = scan.nextLine();
			
			sql = "INSERT INTO LPROD VALUES((SELECT NVL(MAX(LPROD_ID), 0)+1 FROM LPROD), ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, gu);
			ps.setString(2, nm);
			
			int cnt = ps.executeUpdate();
			
			if (cnt > 0) {
				System.out.println("입력되었습니다.");
			} else {
				System.out.println("입력에 실패하였습니다.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(ps != null) try{ps.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
	}
}
