package home;

import bitclass.BitClassDAO;
import bitclass.BitClassInfo;
import bitclass.BitClassManager;
import member.Member;
import member.MemberDAO;
import member.MemberManager;

public class HomeScreen {
	static BitClassInfo classInfo = new BitClassInfo();
	static public boolean isLogin = false;
	static String mid;
	static MemberManager manager;

	public static void main(String[] args) {
		while (true) {
			if (isLogin) {
				memHome();
			} else {
				nonMemHome();
			}
		}
	}

	static void nonMemHome() {
		manager = new MemberManager(MemberDAO.getInstance());
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
	}

	static void memHome() {
		manager = new MemberManager(MemberDAO.getInstance());
		Member member = manager.loginInfo(mid);
		BitClassManager classManager = new BitClassManager(BitClassDAO.getInstance());
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
		int num = ir.readInteger();

		switch (num) {
		case 1:
			classInfo.classMenu(member);
			break;
		case 2:
			member.showMyInfo(manager);
			break;
		case 3:
			classManager.showMyClassInfo(member);
			break;
		case 4:
			classManager.showClass(member.getMno());
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
	}
}
