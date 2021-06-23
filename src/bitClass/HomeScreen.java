package bitClass;

import member.ClassMember;
import member.ClassMemberDAO;
import member.MemberManager;
import onebitclass.ClassDAO;
import onebitclass.ClassManager;

public class HomeScreen {
	static ClassInfo classInfo = new ClassInfo();
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
		manager = new MemberManager(ClassMemberDAO.getInstance());
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
			classInfo.classMenu();
			break;
		case 2:
			isLogin = login.userLogin();
			mid = login.mid;
			break;
		case 3:
			manager.createId();
			break;
		case 0:
			System.out.println("프로그램 종료");
			System.exit(0);
			break;
		}
	}

	static void memHome() {
		manager = new MemberManager(ClassMemberDAO.getInstance());
		ClassMember member = manager.loginInfo(mid);
		ClassManager classManager = new ClassManager(ClassDAO.getInstance());
		InputReader ir = new InputReader();

		System.out.println();
		System.out.println("원 비트 클래스");
		System.out.println("------------------");
		System.out.println("1. 강좌 정보");
		System.out.println("2. 내 정보");
		System.out.println("3. 신청한 강좌");
		System.out.println("4. 등록한 강좌");
		System.out.println("0. 프로그램 종료");
		System.out.println("------------------");
		System.out.print("번호 입력 : ");
		int num = ir.readInteger();

		switch (num) {
		case 1:
			classInfo.classMenu();
			break;
		case 2:
			member.showMyInfo(manager);
			break;
		case 3:
			System.out.println("신청");
			break;
		case 4:
			classManager.showClass(member.getMno());
			break;
		case 0:
			System.out.println("프로그램 종료");
			System.exit(0);
			break;
		}
	}
}
