package bitClass;

import java.util.Scanner;

public class HomeScreen {
	public static void main(String[] args) {
		
		while(true) {
			nonMemHome();
		}
	}

	static void nonMemHome() {
		Login login=new Login();
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
			ClassMain.classInfo();
			break;
		case 2:
			login.userLogin();
			break;
		// case 9:
		// Join.main(null);
		case 0:
			System.out.println("프로그램 종료");
			return;
		}
	}

	static void memHome() {
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
			ClassMain.classInfo();
			break;
		case 2:
		
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
