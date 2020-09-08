package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//문제2) lprod_id값을 2개 입력받아서 두 값 중 작은 값부터 큰 값 사이의 자료들을 출력하시오.
public class jdbcTest03 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC11", "java");
			
			stmt = conn.createStatement();
			
			System.out.print("첫번째 lprod_id를 입력하세요 > ");
			Scanner scan = new Scanner(System.in);
			int value1 = Integer.parseInt(scan.nextLine());
			System.out.print("두번째 lprod_id를 입력하세요 > ");
			int value2 = Integer.parseInt(scan.nextLine());
			
			if (value2 < value1) {
				int temp = value1;
				value1 = value2;
				value2 = temp;
			}

			String sql = "SELECT * FROM LPROD WHERE LPROD_ID BETWEEN " + value1 + " AND " + value2;

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
