package co.fragrance.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbManagement {

//	java <-> DB연결할 때 쓰는 객체ㄴ
	protected Connection conn = null;
//	Select(조회)결과 값 반환 받는 객체
	protected ResultSet rs = null;
//	Query문을 담고 실행하는 객체 2가지 차이점은 따로설명
	protected PreparedStatement pstmt = null; //
	protected Statement stmt = null; // select문에서는 이게 편함

	String driver = "oracle.jdbc.driver.OracleDriver"; // 고정, 접속할때 사용하는 문구
	String url = "jdbc:oracle:thin:@localhost:1521:xe";// 고정, @뒤는 바뀔 수 있음.
	String id = "test";
	String pw = "1234";

	public void conn() {
		try {
//			1.드라이버 로딩
			Class.forName(driver);
//			2.DB연결
			conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	열어둔 DB를 닫는것
	public void disconnect() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
