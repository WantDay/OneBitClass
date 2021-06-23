package bitClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
<<<<<<< Updated upstream
=======
	public String mid;

>>>>>>> Stashed changes
	public boolean userLogin() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Scanner sc = new Scanner(System.in);

		try {
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println();
			System.out.println("드라이버 로드 성공!");

			// 2. 연결
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";

			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			System.out.println("데이터베이스 연결 성공!!!");

			String sql = "SELECT * FROM ClassMember WHERE mid = ? and mpw = ?";
			pstmt = conn.prepareStatement(sql);

			System.out.println();
			System.out.print("ID(대소문자 유의) : ");
			pstmt.setString(1, sc.nextLine());
			System.out.print("PW(대소문자 유의) : ");
			pstmt.setString(2, sc.nextLine());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println();
				System.out.println("로그인 완료! 환영합니다.");
				return true;
			} else {
				System.out.println();
				System.out.println("아이디와 비밀번호를 다시 확인해주세요.");
				return false;
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 찾지못함!!!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패!!!");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
