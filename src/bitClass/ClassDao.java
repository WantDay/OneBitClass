package bitClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClassDao {
	// 싱글톤 패턴 : 여러 개의 인스턴스 생성을 막는 디자인 패턴
	// 1) 외부 클래스나 인스턴스가 해당 클래스에 인스터스를 생성하지 못하도록 처리
	private ClassDao() {
	}

	// 2) 클래스 내부에서 인스턴스 생성
	static private ClassDao dao = new ClassDao();

	// 3) 메소드를 통해 반환하도록 처리
	public static ClassDao getInstance() {
		return dao;
	}

	// 1. 전체 데이터 검색 기능
	// 반환 타입 List<Dept>
	// 매개변수 Connection 객체 -> Statement 생성에 필요
	ArrayList<OneBitClass> getClassList(Connection conn) {
		ArrayList<OneBitClass> list = null;

		// 데이터베이스 테이블을 이용하여 select 결과를 list에 저장
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from dept order by deptno";

		try {
			stmt = conn.createStatement();

			// 결과
			rs = stmt.executeQuery(sql);
			list = new ArrayList<>();

			// 데이터를 Dept 객체로 생성 -> list 저장
			OneBitClass d = new OneBitClass(rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(45), rs.getInt(6), rs.getInt(7), rs.getInt(8),
					rs.getInt(9));

			list.add(d);

		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	// 2. DEPT 테이블에 데이터 저장
	// 반영 횟수 반환
	// 사용자로부터 데이터 받기 -> Dept 객체
	int insertDept(Connection conn, OneBitClass obc) {
		int result = 0;

		// 전달받은 Dept 객체 데이터를 Dept 테이블에 저장 -> 결과 값 반환
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO bitclass VALUES (bitclass_cno_seq.NEXTVAL, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obc.getTitle());
			pstmt.setString(2, obc.getCloc());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
