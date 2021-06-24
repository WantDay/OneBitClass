package bitClass;

import java.util.Scanner;

import member.ClassMember;
import member.ClassMemberDAO;
import member.MemberManager;
import onebitclass.ClassDAO;
import onebitclass.ClassManager;
import onebitclass.OrderClass;

public class HomeScreen {
	static ClassInfo classInfo = new ClassInfo();
	static boolean isLogin = false;
	static ClassMember member;

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
		// Join join = new Join();

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
			mid =classInfo.mid;
			break;
		case 2:
			isLogin = login.userLogin();
			mid = login.mid;
			break;
		case 3:
			// join.userJoin();
			break;
		case 0:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("올바른 숫자를 입력하세요.");
			break;
		}
	}

	static void memHome() {
<<<<<<< Updated upstream
		MemberManager manager = new MemberManager(ClassMemberDAO.getInstance());
		member = manager.loginInfo(mid);
		ClassManager manager2 = new ClassManager(ClassDAO.getInstance());
		
		Scanner sc = new Scanner(System.in);
=======
		manager = new MemberManager(ClassMemberDAO.getInstance());
		ClassMember member = manager.loginInfo(mid);
		OrderClass orderClass = new OrderClass(ClassDAO.getInstance());
		ClassManager classManager = new ClassManager(ClassDAO.getInstance());
		InputReader ir = new InputReader();
>>>>>>> Stashed changes

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
		int num = Integer.parseInt(sc.nextLine());

		switch (num) {
		case 1:
			classInfo.classMenu();
			break;
		case 2:
			manager.showMyInfo(mid);
			break;
		case 3:
			orderClass.showClass2(member.getMno());
			break;
		case 4:
			manager2.showClass(member.getMno());
			break;
		case 9:
			isLogin = false;
			break;
		case 0:
			System.out.println("프로그램 종료");
			
			break;
		default:
			System.out.println("올바른 숫자를 입력하세요.");
			break;
		}
	}
}
