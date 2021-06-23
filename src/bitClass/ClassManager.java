package bitClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClassManager {
	private ClassDao dao;
	private Scanner sc;

	public ClassManager(ClassDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}

	void classList() {
		// Connection 객체 생성
		Connection conn = null;

		// 연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			List<OneBitClass> list = dao.getClassList(conn);

			System.out.println("강좌 정보 리스트");
			System.out.println("-------------------------------------");
			System.out.println("강좌번호 \t 강좌제목 \t 수강료");
			System.out.println("-------------------------------------");

			for (OneBitClass bitClass : list) {
				System.out.printf("%d \t %s \t %d \n", bitClass.getCno(), bitClass.getTitle(),
						bitClass.getFee());
			}
			System.out.println("-------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 내 정보 보기
	void classInfoMenu() {

		InputReader ir = new InputReader();
		int select = 0;

		// 강좌 리스트업( 지난 강좌 )

		// System.out.println("ID : " + id);
		// System.out.println("이름 : " + name);
		// System.out.println("생년월일 : " + birthday);
		// System.out.println("관심 지역 : " + location);
		// System.out.println("포인트 : " + point);
		// System.out.println("------------------------");
		//
		System.out.println("1. 강좌 개설");
		System.out.println("2. 수강료 할인");
		System.out.println("3. 지난 강좌 보기");
		System.out.println("0. 홈으로 가기");

		select = ir.readInteger();

		selectMyInfoMenu(select);

	}

	// 강좌 정보 메뉴 선택하기
	private void selectMyInfoMenu(int select) {
		switch (select) {
		case 1:
			// 1. 강좌 개설
			openClass();
			break;
		case 2:
			// 2. 강좌 관리
			manageClass();
			break;
		case 0:
			// 0. 홈으로가기
			break;
		default:
			System.out.println("올바른 숫자를 입력하세요.");
			break;
		}
	}

	// 강좌 개설
	private void openClass() {
		// dao를 통해 강좌 개설
	}

	// 강좌 관리
	private void manageClass() {
		// dao를 통해 강좌 관리

	// 강좌 할인율 적용
	private void discountClass() {
		
	
	
	}
		
	}
}