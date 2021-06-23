package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ClassMemberDAO {

	// 외부 클래스 또는 인스턴스에서 해당 클래스로 인스턴스를 생성하지 못하도록 처리

	// 2. 클래스 내부에서 인스턴스를 만들고 메소드를 통해서 반환하도록 처리
	static private ClassMemberDAO dao = new ClassMemberDAO();
	
	// 3. 메소드를 통해서 반환 하도록 처리
	public static ClassMemberDAO getInstance() {
		return dao;
	}
	
	// 1. 회원 가입 기능 

	int createId(Connection conn, ClassMember member){ // 회원 가입

		PreparedStatement pstmt = null;
		int result = 0;
		

			String sql = "insert into classmember (mno, mid, mpw, mname, mdate, mloc) values (classmember_mno_seq.nextval, ?, ?, ?, ?, ?)";
			try {
				pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getMid());
			pstmt.setString(2, member.getMpw());
			pstmt.setString(3, member.getMname());
			pstmt.setString(4, member.getMdate());
			pstmt.setString(5, member.getMloc());
			
			
			result = pstmt.executeUpdate();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		
		return result;
	}
	
	// 2. 회원 정보 수정

	int editInfo(Connection conn, ClassMember member) {

		int result = 0;

		PreparedStatement pstmt = null;
		
		try {
			String sql = "update classmember set mpw=?, mloc =?, mdate =? where mid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMpw());
			pstmt.setString(2, member.getMloc());
			pstmt.setString(3, member.getMdate());
			pstmt.setString(4, member.getMid());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		return result;
	}
	
	// 3. 회원 정보 보기
	ArrayList<ClassMember> getInfo(Connection conn, String mid){

		ArrayList<ClassMember> list = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from classmember where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			
			
			// 결과 받아오기
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			// 데이터를 Dept 객체로 생성 -> list에 저장
			while(rs.next()) {
				list.add(new ClassMember(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),
										rs.getString(6), rs.getInt(7)));
			}
				
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		
		return list ;
	}
	
	// 회원 탈퇴 기능
	int deleteId(Connection conn, ClassMember member) {

		int result = 0;

		PreparedStatement pstmt = null;
		
		try {
			String sql = "delete from classmember where mid = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMid());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		return result;
	}
	
	
	
	// 2. 포인트 수정

		int editPoint(Connection conn, ClassMember member) {

			int result = 0;

			PreparedStatement pstmt = null;
			
			try {
				String sql = "update classmember set mpoint=? where mid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, member.getMpoint());
				pstmt.setString(2, member.getMid());

				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
			
			return result;
		}
}