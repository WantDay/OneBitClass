package onebitclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bitClass.HomeScreen;
import bitClass.InputReader;

public class OrderClass {

	private ClassDAO dao;
	private BitClass bitClass;
	private InputReader ir;
	Connection conn = null;
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pw = "tiger";

	public OrderClass(ClassDAO dao) {
		this.dao = dao;
		ir = new InputReader();

	}

	// 신청한 강좌 리스트
	public void showClass2(int mno) {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("내가 신청한 강좌 정보를 출력합니다.");
			System.out.println("강좌명" + "\t" + "지역" + "\t" + "수강료" + "\t" + "시작날짜" + "\t" + "종료날짜");
			System.out.println("-------------------------------------------------");
			List<BitClass> list = dao.getInfo2(conn, mno);
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// '신청한 강좌' 메뉴
		System.out.println();
		System.out.println("9. 로그아웃");
		System.out.println("0. 홈으로 가기");
		System.out.println("------------------");
		System.out.print("번호 입력 : ");
		int num = ir.readInteger();

		selectMyInfoMenu(num);
	}

	// 메뉴 선택
	private void selectMyInfoMenu(int num) {
		switch (num) {
		case 1:
			// 1. 로그아웃
			HomeScreen.isLogin = false;
			break;
		case 0:
			// 0. 홈으로가기
			break;
		default:
			System.out.println("올바른 숫자를 입력하세요.");
			break;
		}
	}

//	// 3. 강좌 정보 가져오기
//	public ArrayList<BitClass> getInfo(Connection conn, int mno) {
//
//		ArrayList<BitClass> list = null;
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			String sql = "select * from classorder where mno = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, mno);
//
//			// 결과 받아오기
//			rs = pstmt.executeQuery();
//
//			list = new ArrayList<>();
//
//			while (rs.next()) {
//				SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
//				list.add(new BitClass(rs.getInt(1), rs.getInt(2), rs.getInt(3), format.format(rs.getDate(4))));
//			}
//			Iterator<BitClass> itr = list.iterator();
//			while (itr.hasNext()) {
//				BitClass bc = (BitClass) itr.next();
//				System.out.print(bc);
//				System.out.println();
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return list;
//	}
}
