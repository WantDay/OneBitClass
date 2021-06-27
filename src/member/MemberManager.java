package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

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
		String mid;
		String mpw;
		String mname;

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			System.out.println("회원 가입을 시작합니다.");

			System.out.println("ID를 입력해주세요.");
			mid = ir.readString();
			// ID에 입력하지 않고 enter키 눌렀을 경우.
			if (checkNullInput(mid)) {
				System.out.println("ID 생성을 위해 ID를 한 글자 이상 입력해주세요. \n");
				return;
			}

			// 현재 등록하려는 ID가 이미 DB에 있는 경우 종료
			if (checkDupId(mid)) {
				System.out.println("이미 존재하는 ID입니다. 다시 입력해주세요. \n");
				return;
			}

			System.out.println("PW를 입력해주세요.");
			mpw = ir.readString();
			// PW에 입력하지 않고 enter키 눌렀을 경우.
			if (checkNullInput(mpw)) {
				System.out.println("ID 생성을 위해 PW를 한 글자 이상 입력해주세요. \n");
				return;
			}

			System.out.println("이름을 입력해주세요.");
			mname = ir.readString();

			// 이름에 입력하지 않고 enter키 눌렀을 경우.
			if (checkNullInput(mname)) {
				System.out.println("ID 생성을 위해 올바른 이름을 입력해주세요. \n");
				return;
			}

			System.out.println("휴대전화번호를 입력해주세요. ex) 010-0000-0000");
			String mphone = ir.readString();
			System.out.println("선호하시는 지역을 입력해주세요.");
			String mloc = ir.readString();

			encryption = new Encryption(mpw.getBytes());
			mpw = encryption.hashing();

			member = new Member(0, mid, mpw, mname, mphone, mloc);

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

	private boolean checkDupId(String mid) {
		ArrayList<String> memberIds = dao.getMemberIds(conn);

		for (int i = 0; i < memberIds.size(); i++) {
			if (mid.equals(memberIds.get(i))) {
				return true;
			}
		}
		return false;
	}

	// 회원 정보 수정
	public void editId(String mid) {

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("회원 정보를 수정합니다.");
			System.out.println("수정하실 PW를 입력해주세요. 변경을 원치 않을 경우 기존 정보를 입력해주세요.");
			String mpw = ir.readString();
			// PW에 입력하지 않고 enter키 눌렀을 경우.
			if (checkNullInput(mpw)) {
				System.out.println("정보 수정을 위해 PW를 한 글자 이상 입력해주세요.");
				return;
			}
			System.out.println("지역을 입력해주세요. 변경을 원치 않을 경우 기존 정보를 입력해주세요.");
			String mloc = ir.readString();
			System.out.println("휴대전화번호를 입력해주세요. ex) 010-0000-0000");
			String mphone = ir.readString();

			encryption = new Encryption(mpw.getBytes());
			mpw = encryption.hashing();

			member = new Member(mid, mpw, mloc, mphone);

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

			member = dao.getMemberByMid(conn, mid);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return member;
	}

	// 회원 탈퇴
	public boolean deleteMyId(String mid) {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			System.out.println("ID를 삭제하시겠습니까? (Y/N)");
			String check = ir.readString();
			if (check.equals("N") || check.equals("n")) {
				System.out.println("이전 메뉴로 돌아갑니다.");
				return true;
			} else if (check.equals("Y") || check.equals("y")) {
				dao.deleteId(conn, member);
				System.out.println("회원 정보가 삭제되었습니다.");
				return false;
			} else {
				System.out.println("올바른 문자를 입력해주세요.");
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void editPoint(int total, Member member) {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			dao.editPoint(conn, member);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean checkNullInput(String str) {
		if (str.length() == 0) {
			return true;
		}
		return false;
	}
}
