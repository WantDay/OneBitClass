package bitclass;

import java.util.ArrayList;

import home.HomeScreen;
import home.InputReader;
import home.Login;
import member.Member;

public class BitClassInfo {
	private BitClassManager classManager;
	private InputReader ir;
	private String mid;

	public void classMenu(Member member) {
		Login login = new Login();
		ir = new InputReader();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			System.out.println();
			System.out.println("강좌 정보");
			System.out.println("------------------");
			System.out.println("1. 전체 강좌 보기");
			System.out.println("2. 할인 중인 강좌");
			System.out.println("3. 마감 임박 강좌");
			if (member != null) {
				System.out.println("4. 지역 근처 강좌");
			}
			if (member != null) {
				System.out.println("9. 로그아웃");
			} else {
				System.out.println("9. 로그인");
			}
			System.out.println("0. 홈으로 가기");
			System.out.println("------------------");
			System.out.print("번호 입력 : ");
			int select = ir.readInteger();

			switch (select) {
			case 1:
				showTakeClass();
				break;
			case 2:
				showDiscountClasses();
				break;
			case 3:
				showDeadlineClasses();
				break;
			case 4:
				showLocalClasses(member.getMloc());
				break;
			case 0:
				break;
			case 9:
				if (member != null) {
					HomeScreen.isLogin = false;
				} else {
					HomeScreen.isLogin = login.userLogin();
					mid = login.getMid();
				}
				break;
			default:
				System.out.println("올바른 숫자를 입력하세요.");
				break;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 할인 강좌 보기
	void showDiscountClasses() {
		classManager = new BitClassManager(BitClassDAO.getInstance());
		ArrayList<BitClass> list = classManager.getDiscountClass();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	// 마감임박 강좌 보기
	void showDeadlineClasses() {
		classManager = new BitClassManager(BitClassDAO.getInstance());
		ArrayList<BitClass> list = classManager.getDeadLineClass();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	// 내 관심지역 강좌 보기
	void showLocalClasses(String mloc) {
		classManager = new BitClassManager(BitClassDAO.getInstance());
		ArrayList<BitClass> list = classManager.getLocClass(mloc);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	// 전체 강좌 보기
	void showTakeClass() {
		classManager = new BitClassManager(BitClassDAO.getInstance());
		ArrayList<BitClass> list = classManager.takeClass();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
}
