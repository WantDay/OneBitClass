package bitClass;

import java.util.Scanner;

<<<<<<< Updated upstream
=======
import onebitclass.ClassDAO;
import onebitclass.ClassManager;

>>>>>>> Stashed changes
public class ClassInfo {
	public void classMenu() {
		// Connection conn = null;
		ClassManager manager = new ClassManager(ClassDao.getInstance());
		Scanner sc = new Scanner(System.in);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			while (true) {
				System.out.println();
				System.out.println("강좌 정보");
				System.out.println("------------------");
				System.out.println("1. 할인 중인 강좌");
				System.out.println("2. 마감 임박 강좌");
				System.out.println("3. 지역 근처 강좌");
				System.out.println("4. 분류별 강좌");
				System.out.println("0. 홈으로 가기");
				System.out.println("------------------");
				System.out.print("번호 입력 : ");
				int select = Integer.parseInt(sc.nextLine());

				switch (select) {
				case 1:
					showDiscountClasses();
					break;
				case 2:
					showDeadlineClasses();
					break;
				case 3:
					showLocalClasses();
					break;
				case 4:
					showClassification();
					break;
				case 0:
					break;
				default:
					System.out.println("올바른 숫자를 입력하세요.");
					break;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} /*catch (SQLException e) {
			e.printStackTrace();
			}*/

	}

	void showDiscountClasses() {
		// dao로 강좌 리스트 받아오기
		// 할인 중인 강좌를 할인 순으로 정렬

		// ArrayList<Class> classes = getClasses();
		// for (Class c : classes) {
		System.out.println("\n할인");
		// }

		// selectClass(classes);
	}

	// 마감임박 강좌 보기
	void showDeadlineClasses() {
		// dao로 강좌 리스트 받아오기
		// 마감 임박인 강좌를 마감이 남은 일자가 작은 순으로 정렬

		// ArrayList<Class> classes = getClasses();
		// for (Class c : classes) {
		System.out.println("\n마감");
		// }

		// selectClass(classes);
	}

	// 내 관심지역 강좌 보기
	void showLocalClasses() {
		// dao로 강좌 리스트 받아오기
		// 자신의 관심지역과 같은 지역을 가나다 순으로 정렬

		// ArrayList<Class> classes = getClasses();
		// for (Class c : classes) {
		System.out.println("\n지역");
		// }

		// selectClass(classes);
	}

	// 분류 보기
	void showClassification() {
		// // dao로 분류 정보 받기
		// ArrayList<String> classification = new ArrayList<String>();
		//
		// for (int i = 0; i < classification.size(); i++) {
		// System.out.println(i + 1 + "번. " + classification.get(i));
		// }
		System.out.println("\n분류");
	}

}
