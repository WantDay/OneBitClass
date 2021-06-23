package bitClass;

import java.util.Scanner;

import member.ClassMember;
import member.ClassMemberDAO;
import member.MemberManager;

public class HomeScreen2 {
	static ClassInfo classInfo = new ClassInfo();
	static boolean isLogin = false;
	static ClassMember member;
	static String mid;
	static MemberManager manager;

	public static void main(String[] args) {
		while (true) {
			if(isLogin) {
				memHome();
			}else {
				nonMemHome();
			}
		}
	}

	static void nonMemHome() {
		manager = new MemberManager(ClassMemberDAO.getInstance());
		Login login = new Login();
//		Join join = new Join();
		
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("원 비트 클래스");
		System.out.println("------------------");
		System.out.println("1. 현재 들을 수 있는 강좌 정보"); // 추후에 헷갈리지 않게 정보 변경
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
			manager.createId();
		case 0:
			System.out.println("프로그램 종료");
			return;
		}
	}

	static void memHome() {
		manager = new MemberManager(ClassMemberDAO.getInstance());
		member = manager.loginInfo(mid);
 		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("원 비트 클래스");
		System.out.println("------------------");
		System.out.println("1. 현재 들을 수 있는 강좌 정보"); // 추후에 헷갈리지 않게 정보 변경
		System.out.println("2. 내 정보");
		System.out.println("3. 신청한 강좌"); // join 해서 orders로 정보 보기
		System.out.println("4. 등록한 강좌"); // 강좌에서 midx로 번호 찾기
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
			
			// 회원 탈퇴
			manager.deleteId(mid);
			break;
		case 3:
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
