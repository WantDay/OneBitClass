package home;

import bitclass.BitClassDAO;
import bitclass.BitClassInfo;
import bitclass.BitClassManager;
import member.Member;
import member.MemberDAO;
import member.MemberManager;

public class HomeScreen {
	private BitClassInfo classInfo;
	private BitClassManager classManager;
	private Member member;
	private MemberManager manager;
	private String mid;
	private boolean isLogin = false;

	// 싱글톤 패턴으로 구성
	static private HomeScreen home = new HomeScreen();
	public static HomeScreen getInstance() {
		return home;
	}

	public static void main(String[] args) {
		while (true) {
			if (home.isLogin) {
				home.memHome();
			} else {
				home.nonMemHome();
			}
		}
	}

	private void nonMemHome() {
		manager = new MemberManager(MemberDAO.getInstance());
		classInfo = new BitClassInfo();
		Login login = new Login();
		InputReader ir = new InputReader();

		System.out.println();
		System.out.println("원 비트 클래스");
		System.out.println("------------------");
		System.out.println("1. 강좌 정보");
		System.out.println("2. 로그인");
		System.out.println("3. 회원가입");
		System.out.println("0. 프로그램 종료");
		System.out.println("------------------");
		System.out.print("번호 입력 : ");

		try {
			int num = ir.readInteger();

			switch (num) {
			case 1:
				classInfo.classMenu(null);
				mid = classInfo.getMid();
				break;
			case 2:
				isLogin = login.userLogin();
				mid = login.getMid();
				break;
			case 3:
				manager.createId();
				break;
			case 0:
				System.out.println("프로그램 종료");
				System.exit(0);
				break;
			default:
				System.out.println("올바른 숫자를 입력하세요.");
				break;
			}
		} catch (NumberFormatException e) {
			System.out.println("메뉴는 숫자로 입력해주세요.");
		}
	}

	private void memHome() {
		classManager = new BitClassManager(BitClassDAO.getInstance());
		classInfo = new BitClassInfo();
		manager = new MemberManager(MemberDAO.getInstance());
		member = manager.loginInfo(mid);
		InputReader ir = new InputReader();

		System.out.println();
		System.out.println("원 비트 클래스");
		System.out.println("------------------");
		System.out.println("1. 강좌 정보");
		System.out.println("2. 내 정보");
		System.out.println("3. 신청한 강좌");
		System.out.println("4. 등록한 강좌");
		System.out.println("9. 로그아웃");
		System.out.println("0. 프로그램 종료");
		System.out.println("------------------");
		System.out.print("번호 입력 : ");
		
		try {
			int num = ir.readInteger();

			switch (num) {
			case 1:
				classInfo.classMenu(member);
				break;
			case 2:
				member.showMyInfo(manager);
				break;
			case 3:
				classInfo.showClasses(member, "MyEnrollClass");
				break;
			case 4:
				classManager.showCreateClass(member);
				break;
			case 9:
				isLogin = false;
				break;
			case 0:
				System.out.println("프로그램 종료");
				System.exit(0);
				break;
			default:
				System.out.println("올바른 숫자를 입력하세요.");
			}
		} catch (NumberFormatException e) {
			System.out.println("메뉴는 숫자로 입력해주세요.");
		}
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
}