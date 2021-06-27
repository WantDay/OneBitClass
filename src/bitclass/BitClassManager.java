package bitclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import home.InputReader;
import member.Member;

public class BitClassManager {

	private BitClassDAO dao;
	private BitClass bitClass;
	private InputReader ir;
	Connection conn = null;
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pw = "tiger";

	public BitClassManager(BitClassDAO dao) {
		this.dao = dao;
		ir = new InputReader();
	}

	// 등록한 강좌 리스트
	public void showCreateClass(Member member) {
		List<BitClass> bitClasses = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			printClassNav("MyCreateClass");
			bitClasses = dao.getClasses(conn, member, "MyCreateClass");

			for (int i = 0; i < bitClasses.size(); i++) {
				bitClasses.get(i).printClass(i+1);
			}
			printNavVar();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// '등록한 강좌' 메뉴
		System.out.println();
		System.out.println("1. 강좌 개설");
		System.out.println("2. 수강료 할인");
		System.out.println("0. 홈으로 가기");
		System.out.println("------------------");
		System.out.print("번호 입력 : ");
		int num = ir.readInteger();

		selectMyInfoMenu(num, member, bitClasses);
	}

	// 강좌 정보 메뉴 선택하기
	private void selectMyInfoMenu(int num, Member member, List<BitClass> bitClasses) {
		switch (num) {
		case 1: // 1. 강좌 개설
			createClass(member);
			break;
		case 2: // 2. 수강료 할인
			setDiscountFee(member, bitClasses);
			break;
		case 0: // 0. 홈으로가기
			break;
		default:
			System.out.println("올바른 숫자를 입력하세요.");
			break;
		}
	}

	// 강좌 개설
	public void createClass(Member member) {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("새로운 강좌를 개설합니다.");
			System.out.println("강좌 제목을 입력해주세요.");
			String title = ir.readString();
			
			if (checkCreateDuplicateClass(member, title)) {
				return;
			}
			if (title.length() > 20) { // 강좌 제목의 길이가 너무 긴 경우
				System.out.println("강좌 제목의 길이가 너무 깁니다. 다시 입력해주세요.");
				return;
			}
			
			System.out.println("강좌가 진행될 지역을 입력해주세요.");
			String cloc = ir.readString();
			
			if (cloc.length() > 10) { // 지역의 길이가 너무 긴 경우
				System.out.println("강좌가 진행될 지역의 길이가 너무 깁니다. 다시 입력해주세요.");
				return;
			}
			
			System.out.println("강좌 시작 날짜를 입력해주세요 ex)21/06/20");
			String startdate = ir.readString();
			System.out.println("강좌 종료 날짜를 입력해주세요 ex)21/06/20");
			String enddate = ir.readString();
			System.out.println("수강료를 입력해주세요. (only 숫자)");
			int fee = ir.readInteger();
			System.out.println("최대 수강 인원을 입력해주세요 (only 숫자, 100명 미만)");
			int numpeople = ir.readInteger();
			
			if (numpeople > 99) { // 최대 수강인원 너무 많은 경우
				System.out.println("최대 수강인원은 100명 미만으로 입력해주세요.");
				return;
			}

			bitClass = new BitClass(0, title, cloc, startdate, enddate, fee, numpeople);

			int result = dao.createClass(conn, bitClass, member.getMno());

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
	private void setDiscountFee(Member member, List<BitClass> bitClasses) {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("원하는 강좌의 수강료 할인을 시작합니다.");
			System.out.println("강좌 번호를 입력해주세요.");
			int selClassNum = ir.readInteger()-1;
			if(selClassNum+1 > bitClasses.size()) {
				System.out.println("해당 번호에 맞는 강의가 없습니다.");
				return;
			}
			System.out.println("할인율을 입력해주세요. (예:10)");
			int discount = ir.readInteger();

			bitClass = bitClasses.get(selClassNum);
			bitClass.setDiscount(discount); 

			int result = dao.editClass(conn, bitClass, member.getMno());

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
	public ArrayList<BitClass> getClassesByDAO(Member member, String type) {
		ArrayList<BitClass> bitClasses = null;
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			
			printClassNav(type);
			bitClasses = dao.getClasses(conn, member, type);

			return bitClasses;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bitClasses;
	}
	
	private void printClassNav(String type) {
		switch (type) {
		case "All": // 전체 강좌 보기
			System.out.println("\n전체 강좌");
			break;
		case "Discount": // 할인 강좌 보기
			System.out.println("\n할인 강좌");
			break;
		case "DeadLine": // 마감 임박 강좌 보기
			System.out.println("\n마감 임박 강좌");
			break;
		case "Local": // 선호 지역 강좌 보기
			System.out.println("\n주변 지역 강좌");
			break;
		case "MyEnrollClass": // 내가 수강 신청한 강좌 보기
			System.out.println("내가 개설한 강좌 정보를 출력합니다.");
			break;
		case "MyCreateClass": // 내가 생성한 강좌 보기
			System.out.println("내가 개설한 강좌 정보를 출력합니다.");
			break;
		}
		
		printTopNavVar();
		printNavVar();
	}

	// 수강 신청 데이터베이스 입력
	public void enrollClass(BitClass bitClass, Member member) {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			dao.enrollClass(conn, bitClass, member);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 신청할때 중복으로 가능한지 비교
	public boolean checkDuplicateClass(Member member, int cno) {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			List<BitClass> dupClass = dao.getClasses(conn, member, "MyEnrollClass");

			for (int i = 0; i < dupClass.size(); i++) { // 내가 기존에 신청한 클래스인지 확인
				if (cno == dupClass.get(i).getCno()) {
					System.out.println("이미 수강신청한 강좌입니다.");
					return true;
				}
			}

			List<BitClass> creClass = dao.getClasses(conn, member, "MyCreateClass"); // 내가 만든 클래스인지 확인

			for (int i = 0; i < creClass.size(); i++) {
				if (cno == creClass.get(i).getCno()) {
					System.out.println("내가 개설한 강좌입니다.");
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 신청할때 중복으로 가능한지 비교
	public boolean checkCreateDuplicateClass(Member member, String title) {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			List<BitClass> dupClass = dao.getClasses(conn, member, "All");

			for (int i = 0; i < dupClass.size(); i++) { // 이미 존재하는 강좌명인지 확인
				if (title.equals(dupClass.get(i).getTitle())) {
					System.out.println("이미 존재하는 강좌명입니다. 다시 입력해주세요.");
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void printTopNavVar() {
		System.out.println("No 강좌명                     지역                수강료    할인율   시작날짜      종료날짜      수강인원  ");
	}
	
	public void printNavVar() {
		System.out.println("--------------------------------------------------------------------------------------------");
	}
}