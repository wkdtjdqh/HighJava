package kr.or.ddit.basic;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Scanner;

public class jdbcTest04 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC11", "java");
			
			System.out.println("계좌번호정보 추가하기");
			System.out.print("계좌번호 : ");
			String bankNo = scan.next();
			
			System.out.print("은행명 : ");
			String bankName = scan.next();
			
			System.out.print("예금주명 : ");
			String bankUser = scan.next();
			
			/*// Statement객체를 이용하여 데이터 추가하기
				String sql = "INSERT INTO BANKINFO(BANK_NO, BANK_NAME, BANK_USER_NAME, BANK_DATE) "
						+ "VALUES('" + bankNo + "', '" + bankName + "', '" + bankUser + "', sysdate)";
				
				stmt = conn.createStatement();
			*/
			
			// 데이터가 들어갈 자리에 물음표로 표시
			String sql = "INSERT INTO BANKINFO(BANK_NO, BANK_NAME, BANK_USER_NAME, BANK_DATE) "
					+ "VALUES(?, ?, ?, sysdate)";
			
			// PreparedStatement객체 생성 => 이 때 처리할 SQL문을 매개변수에 넘겨준다.
			ps = conn.prepareStatement(sql);
			// SQL문의 물음표자리에 들어갈 데이터를 세팅한다.
			// 형식) ps.setString(물음표번호, 데이터)
			ps.setString(1, bankNo);
			ps.setString(2, bankName);
			ps.setString(3, bankUser);
			
			// 실행할 SQL문이 SELECT문일 경우에는 executeQuery()메서드를 사용하고,
			// 실행할 SQL문이 SELECT문이 아닐 경우에는 eqxcuteUpdate()메서드를 사용한다.
			// executeUpdate()메서드의 반환값 ==> 작업에 성공한 레코드 수
//			int cnt = stmt.executeUpdate(sql);
			
			// 데이터 세팅이 완료되면 SQL문이 실행한다.
			int cnt = ps.executeUpdate();
			
			System.out.println("반환값 : " + cnt);
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null) try{stmt.close();}catch(SQLException e){}
			if(ps != null) try{ps.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
	}
}
