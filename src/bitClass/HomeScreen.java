package bitClass;

import java.util.Scanner;

import member.ClassMember;
import member.ClassMemberDAO;
import member.MemberManager;

public class HomeScreen {
	static ClassInfo classInfo = new ClassInfo();
	static boolean isLogin = false;
	static String mid;

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
		Login login = new Login();
//		Join join = new Join();

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("원 비트 클래스");
		System.out.println("------------------");
		System.out.println("1. 강좌 정보");
		System.out.println("2. 로그인");
		System.out.println("3. 회원가입");
		System.out.println("0. 프로그램 종료");
		System.out.println("------------------");
		System.out.print("번호 입력 : ");

		int num = Integer.parseInt(sc.nextLine());

		switch (num) {
		case 1:
			classInfo.classMenu();
			break;
		case 2:
			isLogin = login.userLogin();
			mid = login.mid;
			break;
		case 3:
//			join.userJoin();
		case 0:
			System.out.println("프로그램 종료");
			return;
		}
	}

	static void memHome() {
		MemberManager manager = new MemberManager(ClassMemberDAO.getInstance());
		ClassMember member = manager.loginInfo(mid);
		Scanner sc = new Scanner(System.in);

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
		int num = Integer.parseInt(sc.nextLine());

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
			System.out.println("등록");

			break;
		case 0:
			System.out.println("프로그램 종료");
			return;
		}
	}
}
