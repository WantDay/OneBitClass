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

	// 1. 강좌 개설 기능
	int createClass(Connection conn, BitClass bitClass, int mno) { // 강좌 개설, 강사 번호 입력.

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

	// 2. 강좌 할인 적용
	int editInfo(Connection conn, BitClass bitClass, int mno) {

		int result = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "update bitclass set discount = ? where title=? and mno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bitClass.getDiscount());
			pstmt.setString(2, bitClass.getTitle());
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

	// 3. 내가 생성한 강좌 정보 가져오기
	public ArrayList<BitClass> getCreClass(Connection conn, Member member) {

		ArrayList<BitClass> list = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select cno, mno, title, cloc, startdate, enddate, fee, discount, rate, numpeople, enroll from bitclass natural join classmember where mno = ? order by cno";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getMno());

			// 결과 받아오기
			rs = pstmt.executeQuery();

			list = new ArrayList<>();

			while (rs.next()) {
				list.add(new BitClass(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
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

		return list;
	}

	// 4. 전체 강좌 정보 가져오기
	public ArrayList<BitClass> getTakeClass(Connection conn) {

		ArrayList<BitClass> list = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select cno, mno, title, cloc, startdate, enddate, fee, discount, rate, numpeople, enroll from bitclass";
			pstmt = conn.prepareStatement(sql);

			// 결과 받아오기
			rs = pstmt.executeQuery();

			list = new ArrayList<>();

			while (rs.next()) {
				list.add(new BitClass(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
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

		return list;
	}

	// 공통 부분 묶기
	public ArrayList<BitClass> showClassList(Connection conn, String loc, int type) {

		ArrayList<BitClass> list = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			if (type == 1) { // 할인 강좌 보기
				sql = "select cno, mno, title, cloc, startdate, enddate, fee, discount, rate, numpeople, enroll "
						+ "from bitclass where discount > 0 order by discount";
				pstmt = conn.prepareStatement(sql);
			} else if (type == 2) { // 마감 임박 강좌 보기

				sql = "select cno, mno, title, cloc, startdate, enddate, fee, discount, rate, numpeople, enroll "
						+ "from bitclass where ceil(startdate - sysdate) < 7 and 0 < ceil(startdate - sysdate)";
				pstmt = conn.prepareStatement(sql);
			} else if (type == 3) { // 선호 지역 강좌 보기
				sql = "select cno, mno, title, cloc, startdate, enddate, fee, discount, rate, numpeople, enroll "
						+ "from bitclass where cloc = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, loc);
			}
			// 결과 받아오기
			rs = pstmt.executeQuery();
			list = new ArrayList<>();

			while (rs.next()) {
				list.add(new BitClass(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
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
		return list;
	}

	// 6. 지역 강좌 정보 가져오기
	public ArrayList<BitClass> getLocClass(Connection conn, String loc) {

		ArrayList<BitClass> list = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select cno, mno, title, cloc, startdate, enddate, fee, discount, rate, numpeople, enroll from bitclass where cloc = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc);

			// 결과 받아오기
			rs = pstmt.executeQuery();

			list = new ArrayList<>();

			while (rs.next()) {
				list.add(new BitClass(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
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

		return list;
	}

	// 7. 마감 임박 강좌 정보 가져오기
	public ArrayList<BitClass> getDeadLineClass(Connection conn) {

		ArrayList<BitClass> list = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select cno, mno, title, cloc, startdate, enddate, fee, discount, rate, numpeople, enroll from bitclass where ceil(startdate - sysdate) < 7 and 0 < ceil(startdate - sysdate)";
			pstmt = conn.prepareStatement(sql);

			// 결과 받아오기
			rs = pstmt.executeQuery();

			list = new ArrayList<>();

			while (rs.next()) {
				list.add(new BitClass(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
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

		return list;
	}

	// 8. 수강인원을 추가하는 메소드
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

	// 9. 수강 신청 후 데이터베이스에 정보 입력
	public void enrollClass(Connection conn, BitClass bitClass, Member member) {
		PreparedStatement pstmt = null;
		String sql = null;

		sql = "update bitclass set enroll = ? where title=?";
		pstmtExecuteUpdate(conn, bitClass, member, pstmt, sql, 1);

		sql = "update classmember set mpoint = ? where mno=?"; // 수강생 요금 차감 후 갱신
		pstmtExecuteUpdate(conn, bitClass, member, pstmt, sql, 2);

		sql = "update classmember set mpoint = (mpoint + ?) where mno = ?"; // 강사 요금 충전 후 갱신
		pstmtExecuteUpdate(conn, bitClass, member, pstmt, sql, 3);

		sql = "insert into classorder values (classorder_orderno_seq.nextval, ?, ?, sysdate)";
		pstmtExecuteUpdate(conn, bitClass, member, pstmt, sql, 4);
	}

	private void pstmtExecuteUpdate(Connection conn, BitClass bitClass, Member member, PreparedStatement pstmt,
			String sql, int type) {
		try {
			pstmt = conn.prepareStatement(sql);
			if (type == 1) {
				pstmt.setInt(1, bitClass.getEnroll());
				pstmt.setString(2, bitClass.getTitle());
			} else if (type == 2) {
				pstmt.setInt(1, member.getMpoint());
				pstmt.setInt(2, member.getMno());
			} else if (type == 3) {
				pstmt.setInt(1, bitClass.discountFee());
				pstmt.setInt(2, bitClass.getMno());
			} else {
				pstmt.setInt(1, member.getMno());
				pstmt.setInt(2, bitClass.getCno());
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

	// 10. 내가 수강한 강좌 정보 가져오기
	public ArrayList<BitClass> getMyClassInfo(Connection conn, Member member) {

		ArrayList<BitClass> list = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select b.cno, c.mno, title, cloc, startdate, enddate, fee, discount, rate, numpeople, enroll "
					+ "from bitclass b, classorder c where b.cno = c.cno and c.mno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getMno());

			// 결과 받아오기
			rs = pstmt.executeQuery();

			list = new ArrayList<>();

			while (rs.next()) {
				list.add(new BitClass(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
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
		return list;
	}
}