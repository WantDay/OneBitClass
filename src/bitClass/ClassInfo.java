package bitClass;

import java.util.ArrayList;

import onebitclass.BitClass;
import onebitclass.ClassDAO;
import onebitclass.ClassManager;

public class ClassInfo {
	private ClassManager classManager;
	private InputReader ir;
	public String mid;

	public void classMenu(String mloc) {
		Login login = new Login();
		ir = new InputReader();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			System.out.println();
			System.out.println("강좌 정보");
			System.out.println("------------------");
			System.out.println("1. 전체 강좌");
			System.out.println("2. 할인 중인 강좌");
			System.out.println("3. 마감 임박 강좌");
			if (HomeScreen.isLogin) {
				System.out.println("4. 지역 근처 강좌");
			}
			if (HomeScreen.isLogin) {
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
				showLocalClasses(mloc);
				break;
			case 9:
				if (HomeScreen.isLogin) {
					HomeScreen.isLogin = false;
				} else {
					HomeScreen.isLogin = login.userLogin();
					mid = login.mid;
				}
				break;
			case 0:
				break;
			default:
				System.out.println("올바른 숫자를 입력하세요.");
				break;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 전체 강좌 보기
	void showTakeClass() {
		classManager = new ClassManager(ClassDAO.getInstance());
		ArrayList<BitClass> list = classManager.takeClass();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	void showDiscountClasses() {
		classManager = new ClassManager(ClassDAO.getInstance());
		ArrayList<BitClass> list = classManager.getDiscountClass();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	// 마감임박 강좌 보기
	void showDeadlineClasses() {
		classManager = new ClassManager(ClassDAO.getInstance());
		ArrayList<BitClass> list = classManager.getDeadLineClass();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	// 내 관심지역 강좌 보기
	void showLocalClasses(String mloc) {
		classManager = new ClassManager(ClassDAO.getInstance());
		ArrayList<BitClass> list = classManager.getLocClass(mloc);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
