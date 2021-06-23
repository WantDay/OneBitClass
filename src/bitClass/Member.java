package bitClass;

import java.util.Scanner;

public class Member {

	private String id;
	private String name;
	private String birthday;
	private String location;
	private int point;

//	 public Member(String id) {
//	 this.id = id;
////	 데이터베이스에서 정보 보기
////	 id를 기준으로 DB에서 정보 가져오기
//	 getMemberByName(id);
//	
//	 this.name = name;
//	 this.birthday = birthday;
//	 this.location = location;
//	 this.point = point;
//	 }

	// 내 정보 보기
	void showMyInfo() {
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("ID : " + id);
		System.out.println("이름 : " + name);
		System.out.println("생년월일 : " + birthday);
		System.out.println("관심 지역 : " + location);
		System.out.println("포인트 : " + point);
		System.out.println("------------------------");

		System.out.println("1. 정보 수정");
		System.out.println("2. 포인트 관리");
		System.out.println("3. 회원 탈퇴");
		System.out.println("0. 홈으로 가기");

		System.out.print("번호 입력 : ");

		int select = Integer.parseInt(sc.nextLine());

		switch (select) {
		case 1:
			updateMyInfo();
			break;
		case 2:
			manageMyPoint();
			break;
		case 3:
			deleteMyId();
			break;
		case 0:
			break;
		default:
			System.out.println("올바른 숫자를 입력하세요.");
			break;
		}
	}

	// 내 정보 수정
	private void updateMyInfo() {
		System.out.println("수정");
		// dao를 통해 자신의 정보 수정
	}

	// 회원 탈퇴
	private void deleteMyId() {
		System.out.println("탈퇴");
		// dao를 통해 자신의 id 탈퇴

	}

	// 포인트 관리
	private void manageMyPoint() {
		// 포인트 충전, 인출, 정산 선택

		InputReader ir = new InputReader();
		int select = ir.readInteger();

		selectPointMenu(select);
	}

	// 포인트 관리 메뉴 선택하기
	private void selectPointMenu(int select) {
		switch (select) {
		case 1:
			// 포인트 충전
			chargePoint();
			break;
		case 2:
			// 포인트 인출
			withdrawPoint();
			break;
		case 3:
			// 포인트 정산
			settlePoint();
			break;
		default:
			System.out.println("올바른 숫자를 입력하세요.");
			break;
		}
	}

	// 포인트 충전
	private void chargePoint() {
		InputReader ir = new InputReader();
		int charge = ir.readInteger();
		this.point += charge;

		// dao를 통해 포인트 갱신
		System.out.println(charge + "포인트가 정상적으로 충전됐습니다.");
	}

	// 포인트 인출
	private void withdrawPoint() {
		InputReader ir = new InputReader();
		int withdrawal = ir.readInteger();
		this.point -= withdrawal;

		// dao를 통해 포인트 갱신
		System.out.println(withdrawal + "포인트가 정상적으로 인출됐습니다.");
	}

	// 포인트 정산
	private void settlePoint() {
		// join을 통해 orders에서 결제된 포인트의 총합을 구함
		int earnedPoint = 0;
		int usedPoint = 0;

		System.out.println("입금된 포인트 : " + earnedPoint);
		System.out.println("사용한 포인트 : " + usedPoint);
	}
}