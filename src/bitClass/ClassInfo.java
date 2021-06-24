package bitClass;

import java.util.ArrayList;

import onebitclass.BitClass;
import onebitclass.ClassDAO;
import onebitclass.ClassManager;

public class ClassInfo {
	private InputReader ir;
	private ClassManager classManager;

	public void classMenu() { // 비회원은 지역정보가 없어서 지역정보를 포함하지 않는 정보를 인출
		ir = new InputReader();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			System.out.println();
			System.out.println("강좌 정보");
			System.out.println("------------------");
			System.out.println("1. 할인 중인 강좌");
			System.out.println("2. 마감 임박 강좌");
			System.out.println("3. 전체 강좌 보기");
			System.out.println("0. 홈으로 가기");
			System.out.println("------------------");
			System.out.print("번호 입력 : ");
			int select = ir.readInteger();

			switch (select) {
			case 1:
				showDiscountClasses();
				break;
			case 2:
				showDeadlineClasses();
				break;
			case 3:
				showTakeClass();
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

	public void classMenu(String mloc) {
		ir = new InputReader();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			System.out.println();
			System.out.println("강좌 정보");
			System.out.println("------------------");
			System.out.println("1. 할인 중인 강좌");
			System.out.println("2. 마감 임박 강좌");
			System.out.println("3. 지역 근처 강좌");
			System.out.println("4. 전체 강좌 보기");
			System.out.println("0. 홈으로 가기");
			System.out.println("------------------");
			System.out.print("번호 입력 : ");
			int select = ir.readInteger();

			switch (select) {
			case 1:
				showDiscountClasses();
				break;
			case 2:
				showDeadlineClasses();
				break;
			case 3:
				showLocalClasses(mloc);
				break;
			case 4:
				showTakeClass();
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

	// 전체 강좌 보기
	void showTakeClass() {
		classManager = new ClassManager(ClassDAO.getInstance());
		ArrayList<BitClass> list = classManager.takeClass();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
