package home.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * JDBC드라이버를 로딩하고 DB시스템에 접속하여 Connection객체를 반환하는 역할을 수행하는 클래스
 * 
 * @author wkdtj
 *
 */
public class DBUtil {
private static ResourceBundle bundle;	// ResourceBundle객체 변수 선언
	
	// static 초기화 블럭 ==> 드라이버 로딩을 수행한다.
	static{
		 bundle = ResourceBundle.getBundle("dbinfo");	// ResourceBundle객체 생성
		try {
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	// DB시스템에 접속한 후 접속 정보를 갖는 Connection객체를 반환하는 메서드
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(bundle.getString("url"), bundle.getString("user"), bundle.getString("pass"));
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패!!");
			e.printStackTrace();
			return null;
		}
	}
}
