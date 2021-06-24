package bitClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import encryption.Encryption;

public class Login {
	private String mid;
	private String mpw;
	private Encryption encryption;
	
	public boolean userLogin() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		InputReader ir = new InputReader();

		try {
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 연결
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";

			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			String sql = "SELECT * FROM ClassMember WHERE mid = ? and mpw = ?";
			pstmt = conn.prepareStatement(sql);

			System.out.println();
			System.out.print("ID(대소문자 유의) : ");
			mid = ir.readString();
			pstmt.setString(1, mid);
			System.out.print("PW(대소문자 유의) : ");
			mpw = ir.readString();

			encryption = new Encryption(mpw.getBytes());
			mpw = encryption.hashing();
			
			pstmt.setString(2, mpw);

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
		} catch (Exception e) {
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
	
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
}
