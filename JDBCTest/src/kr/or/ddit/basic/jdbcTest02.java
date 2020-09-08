package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제1) 사용자로부터 Lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력하시오.

public class jdbcTest02 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC11", "java");
			
			stmt = conn.createStatement();
			
			System.out.print("lprod_id를 입력하세요 > ");
			Scanner scan = new Scanner(System.in);
			int value = Integer.parseInt(scan.nextLine());
			String sql = "SELECT * FROM LPROD WHERE LPROD_ID > " + value;
			
			rs = stmt.executeQuery(sql);
			
			System.out.println(" == SQL문의 처리 결과 ==");
			
			while(rs.next()){
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("-------------------------------------");
			}
					
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {}
			if (stmt != null) try { rs.close(); } catch (SQLException e) {}
			if (conn != null) try { rs.close(); } catch (SQLException e) {}
		}
	}
}
