package onebitclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bitClass.HomeScreen;
import bitClass.InputReader;

public class ClassManager {

	private ClassDAO dao;
	private BitClass bitClass;
	private InputReader ir;
	Connection conn = null;
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pw = "tiger";

	public ClassManager(ClassDAO dao) {
		this.dao = dao;
		ir = new InputReader();

	}

	// 등록한 강좌 리스트
	public void showClass(int mno) {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("내가 개설한 강좌 정보를 출력합니다.");
			System.out.println("강좌명"	+ "\t"
								+ "지역"
								+ "\t"
								+ "수강료"
								+ "\t"
								+ "시작 날짜"
								+ "\t"
								+ "종료 날짜"
								+ "\t"
								+ "수강 인원");
			System.out.println(
					"--------------------------------------------------------------------");
			List<BitClass> list = dao.getInfo(conn, mno);
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// '등록한 강좌' 메뉴
		System.out.println();
		System.out.println("1. 강좌 개설");
		System.out.println("2. 수강료 할인");
		System.out.println("9. 로그아웃");
		System.out.println("0. 홈으로 가기");
		System.out.println("------------------");
		System.out.print("번호 입력 : ");
		int num = ir.readInteger();

		selectMyInfoMenu(num, mno);
	}

	// 강좌 정보 메뉴 선택하기
	private void selectMyInfoMenu(int num, int mno) {
		switch (num) {
		case 1:
			// 1. 강좌 개설
			createClass(mno);
			break;
		case 2:
			// 2. 수강료 할인
			discountFee();
			break;
		case 9:
			HomeScreen.isLogin = false;
			break;
		case 0:
			// 0. 홈으로가기
			break;
		default:
			System.out.println("올바른 숫자를 입력하세요.\n");
			showClass(mno);
			break;
		}
	}

	// 강좌 개설
	public void createClass(int mno) {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("새로운 강좌를 개설합니다.");
			System.out.println("강좌 제목을 입력해주세요.");
			String title = ir.readString();
			System.out.println("강좌가 진행될 지역을 입력해주세요.");
			String cloc = ir.readString();
			System.out.println("강좌 시작 날짜를 입력해주세요 ex)2021/06/20");
			String startdate = ir.readString();
			System.out.println("강좌 종료 날짜를 입력해주세요 ex)2021/06/20");
			String enddate = ir.readString();
			System.out.println("수강료를 입력해주세요. (only 숫자)");
			int fee = ir.readInteger();
			System.out.println("최대 수강 인원을 입력해주세요 (only 숫자)");
			int numpeople = ir.readInteger();

			bitClass = new BitClass(0, title, cloc, startdate, enddate, fee, numpeople);

			int result = dao.createClass(conn, bitClass, mno);

			if (result > 0) {
				System.out.println("입력 되었습니다.");
			} else {
				System.out.println("입력 실패!!!");
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	// 수강료 할인
	private void discountFee() {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("원하는 강좌의 수강료 할인을 시작합니다.");
			System.out.println("강좌 제목을 입력해주세요.");
			String title = ir.readString();
			System.out.println("할인율을 입력해주세요. (예:10)");
			int discount = ir.readInteger();

			bitClass = new BitClass(0, title, discount);

			int result = dao.editInfo(conn, bitClass);

			if (result > 0) {
				System.out.println("입력 되었습니다.");
			} else {
				System.out.println("입력 실패!!!");
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	// 전체 강좌 정보 멤버 객체 생성

	public ArrayList<BitClass> takeClass() {
		ArrayList<BitClass> list = null;
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			list = dao.getTakeClass(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 할인 강좌 정보 멤버 객체 생성

	public ArrayList<BitClass> getDiscountClass() {
		ArrayList<BitClass> list = null;
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			list = dao.getDiscountClass(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 내 지역 주변 강좌 보기

	public ArrayList<BitClass> getLocClass(String mloc) {
		ArrayList<BitClass> list = null;
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			list = dao.getLocClass(conn, mloc);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 마감 임박 강좌 보기 (일주일 이내)

	public ArrayList<BitClass> getDeadLineClass() {
		ArrayList<BitClass> list = null;
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			list = dao.getDeadLineClass(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
