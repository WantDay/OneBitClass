package bitclass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import member.Member;

public class BitClassDAO {
	private SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");

	static private BitClassDAO dao = new BitClassDAO();

	public static BitClassDAO getInstance() {
		return dao;
	}

	// 강좌 개설 기능
	public int createClass(Connection conn, BitClass bitClass, int mno) { // 강좌 개설, 강사 번호 입력.
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "insert into bitclass (cno, mno, title, cloc, startdate, enddate, fee, numpeople) values (bitclass_cno_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, mno);
			pstmt.setString(2, bitClass.getTitle());
			pstmt.setString(3, bitClass.getCloc());
			pstmt.setString(4, bitClass.getStartDate());
			pstmt.setString(5, bitClass.getEndDate());
			pstmt.setInt(6, bitClass.getFee());
			pstmt.setInt(7, bitClass.getNumPeople());

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

	// 강좌 할인 적용 기능
	public int editClass(Connection conn, BitClass bitClass, int mno) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "update bitclass set discount = ? where cno = ? and mno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bitClass.getDiscount());
			pstmt.setInt(2, bitClass.getCno());
			pstmt.setInt(3, mno);

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

	// 공통 부분 묶기
	public ArrayList<BitClass> getClasses(Connection conn, Member member, String type) {
		ArrayList<BitClass> bitClasses = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			switch (type) {
			case "All": // 전체 강좌 보기
				sql = "select cno, mno, title, cloc, startdate, enddate, fee, discount, rate, numpeople, enroll from bitclass order by startdate, cno";
				pstmt = conn.prepareStatement(sql);
				break;
			case "Discount": // 할인 강좌 보기
				sql = "select cno, mno, title, cloc, startdate, enddate, fee, discount, rate, numpeople, enroll "
						+ "from bitclass where discount > 0 order by discount desc, cno";
				pstmt = conn.prepareStatement(sql);
				break;
			case "DeadLine": // 마감 임박 강좌 보기
				sql = "select cno, mno, title, cloc, startdate, enddate, fee, discount, rate, numpeople, enroll "
						+ "from bitclass where ceil(startdate - sysdate) < 7 and 0 < ceil(startdate - sysdate) order by startdate, cno";
				pstmt = conn.prepareStatement(sql);
				break;
			case "Local": // 선호 지역 강좌 보기
				sql = "select cno, mno, title, cloc, startdate, enddate, fee, discount, rate, numpeople, enroll "
						+ "from bitclass where cloc = ? order by cno";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getMloc());
				break;
			case "MyEnrollClass": // 내가 수강 신청한 강좌 보기
				sql = "select b.cno, c.mno, title, cloc, startdate, enddate, fee, discount, rate, numpeople, enroll "
						+ "from bitclass b, classorder c where b.cno = c.cno and c.mno = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, member.getMno());
				break;
			case "MyCreateClass": // 내가 생성한 강좌 보기
				sql = "select cno, mno, title, cloc, startdate, enddate, fee, discount, rate, numpeople, enroll from bitclass natural join classmember where mno = ? order by cno";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, member.getMno());
				break;
			}

			// 결과 받아오기
			rs = pstmt.executeQuery();
			bitClasses = new ArrayList<>();

			while (rs.next()) {
				bitClasses.add(new BitClass(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
						       format.format(rs.getDate(5)), format.format(rs.getDate(6)), rs.getInt(7), rs.getInt(8),
						       rs.getFloat(9), rs.getInt(10), rs.getInt(11)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bitClasses;
	}

	// 수강인원을 추가하는 메소드
	public void countMember(Connection conn, BitClass bitClass) {

		PreparedStatement pstmt = null;

		try {
			String sql = "update bitclass set enroll = ? where title=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bitClass.getEnroll());
			pstmt.setString(2, bitClass.getTitle());

			pstmt.executeUpdate();

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
	}

	// 수강 신청 후 데이터베이스에 정보 입력
	public void enrollClass(Connection conn, BitClass bitClass, Member member) {
		PreparedStatement pstmt = null;
		String sql = null;

		sql = "update bitclass set enroll = ? where title=?";
		pstmtExecuteUpdate(conn, bitClass, member, pstmt, sql, "BitClass");

		sql = "update classmember set mpoint = ? where mno=?"; // 수강생 요금 차감 후 갱신
		pstmtExecuteUpdate(conn, bitClass, member, pstmt, sql, "Student");

		sql = "update classmember set mpoint = (mpoint + ?) where mno = ?"; // 강사 요금 충전 후 갱신
		pstmtExecuteUpdate(conn, bitClass, member, pstmt, sql, "Teacher");

		sql = "insert into classorder values (classorder_orderno_seq.nextval, ?, ?, sysdate)";
		pstmtExecuteUpdate(conn, bitClass, member, pstmt, sql, "Order");
	}

	private void pstmtExecuteUpdate(Connection conn, BitClass bitClass, Member member, PreparedStatement pstmt,
			String sql, String type) {
		try {
			pstmt = conn.prepareStatement(sql);

			switch (type) {
			case "BitClass": // BitClass의 수강인원을 갱신
				pstmt.setInt(1, bitClass.getEnroll());
				pstmt.setString(2, bitClass.getTitle());
				break;
			case "Student": // 수강생의 포인트를 갱신
				pstmt.setInt(1, member.getMpoint());
				pstmt.setInt(2, member.getMno());
				break;
			case "Teacher": // 강사의 포인트를 갱신
				pstmt.setInt(1, bitClass.discountFee());
				pstmt.setInt(2, bitClass.getMno());
				break;
			case "Order": // 주문테이블에 내용 추가
				pstmt.setInt(1, member.getMno());
				pstmt.setInt(2, bitClass.getCno());
				break;
			}

			pstmt.executeUpdate();
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
	}
}