package onebitclass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import member.ClassMember;

public class ClassDAO {
	private SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");

	// 외부 클래스 또는 인스턴스에서 해당 클래스로 인스턴스를 생성하지 못하도록 처리

	// 2. 클래스 내부에서 인스턴스를 만들고 메소드를 통해서 반환하도록 처리
	static private ClassDAO dao = new ClassDAO();

	// 3. 메소드를 통해서 반환 하도록 처리
	public static ClassDAO getInstance() {
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

	// 3. 강좌 정보 가져오기
	public ArrayList<BitClass> getInfo(Connection conn, int mno) {

		ArrayList<BitClass> list = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from bitclass natural join classmember where mno = ? order by cno";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);

			// 결과 받아오기
			rs = pstmt.executeQuery();

			list = new ArrayList<>();

			while (rs.next()) {
				list.add(new BitClass(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
						format.format(rs.getDate(5)), format.format(rs.getDate(6)), rs.getInt(7), rs.getInt(8),
						rs.getFloat(9), rs.getInt(10), rs.getInt(11)));
			}
			Iterator<BitClass> itr = list.iterator();
			while (itr.hasNext()) {
				BitClass bc = (BitClass) itr.next();
				System.out.print(bc);
				System.out.println();
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
			String sql = "select * from bitclass";
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

	// 4. 할인 강좌 정보 가져오기
	public ArrayList<BitClass> getDiscountClass(Connection conn) {

		ArrayList<BitClass> list = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from bitclass where discount > 0 order by discount";
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

	// 5. 지역 강좌 정보 가져오기
	public ArrayList<BitClass> getLocClass(Connection conn, String loc) {

		ArrayList<BitClass> list = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from bitclass where cloc = ?";
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

	// 6. 마감 임박 강좌 정보 가져오기
	public ArrayList<BitClass> getDeadLineClass(Connection conn) {

		ArrayList<BitClass> list = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from bitclass where ceil(startdate - sysdate) < 7 and 0 < ceil(startdate - sysdate)";
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
		
		void enrollClass(Connection conn, BitClass bitClass, ClassMember member) {

			int result = 0;

			PreparedStatement pstmt = null;

			try {
				String sql = "update bitclass set enroll = ? where title=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bitClass.getEnroll());
				pstmt.setString(2, bitClass.getTitle());
				
				pstmt.executeUpdate();

				String sql2 = "update classmember set mpoint = ? where mno=?"; // 수강생 요금 차감 후 갱신
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, member.getMpoint());
				pstmt.setInt(2, member.getMno());
				
				pstmt.executeUpdate();
				
				String sql3 = "update classmember set mpoint = ? where mno = ?"; // 강사 요금 충전 후 갱신
				pstmt = conn.prepareStatement(sql3);
				int income = bitClass.discountFee();
				System.out.println(income);
				int mno = bitClass.getMno();
				System.out.println(mno);
				pstmt.setInt(1, income);
				pstmt.setInt(2, mno);
				
				pstmt.executeUpdate();
				return;
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