package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import encryption.Encryption;
import home.InputReader;

public class MemberManager {

	private MemberDAO dao;
	private Member member;
	private InputReader ir;
	private Encryption encryption;
	Connection conn = null;
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pw = "tiger";

	public MemberManager(MemberDAO dao) {
		this.dao = dao;
		ir = new InputReader();
	}

	// 회원 가입
	// 사용자에게 Scanner 클래스로 입력 받아 ->
	// dao ClassMemberDAO 메소드로 저장
	public void createId() {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("회원 가입을 시작합니다.");
			System.out.println("ID를 입력해주세요.");
			String mid = ir.readString();
			System.out.println("PW를 입력해주세요.");
			String mpw = ir.readString();
			System.out.println("이름을 입력해주세요.");
			String mname = ir.readString();
			System.out.println("생년월일을 입력해주세요. ex)91/07/21");
			String mdate = ir.readString();
			System.out.println("선호하시는 지역을 입력해주세요.");
			String mloc = ir.readString();

			encryption = new Encryption(mpw.getBytes());
			mpw = encryption.hashing();

			member = new Member(0, mid, mpw, mname, mdate, mloc);

			int result = dao.createId(conn, member);

			if (result > 0) {
				System.out.println("입력 되었습니다.");
			} else {
				System.out.println("입력 실패!!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// 회원 정보 수정

	public void editId(String mid) {

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("회원 정보를 수정합니다.");
			System.out.println("수정하실 PW를 입력해주세요. 변경을 원치 않을 경우 기존 정보를 입력해주세요.");
			String mpw = ir.readString();
			System.out.println("지역을 입력해주세요. 변경을 원치 않을 경우 기존 정보를 입력해주세요.");
			String mloc = ir.readString();
			System.out.println("생년월일을 입력해주세요. ex)91/07/21");
			String mdate = ir.readString();

			encryption = new Encryption(mpw.getBytes());
			mpw = encryption.hashing();

			member = new Member(mid, mpw, mloc, mdate);

			int result = dao.editInfo(conn, member);

			if (result > 0) {
				System.out.println("정보가 수정 되었습니다.");
			} else {
				System.out.println("수정 실패!!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 로그인 회원 관리
	public Member loginInfo(String mid) {
		try {

			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			List<Member> list = dao.getInfo(conn, mid);

			member = list.get(0);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return member;
	}

	// 회원 탈퇴
	public void deleteMyId(String mid) {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			dao.deleteId(conn, member);
			System.out.println("회원 정보가 삭제되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editPoint(int total) {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			dao.editPoint(conn, member);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
