package member;

import home.HomeScreen;
import home.InputReader;

public class Member {
	// 회원이 입력 데이터를 넣어서 가입 및 관리

	private int mno; // 회원 번호
	private String mid; // 회원 ID
	private String mpw; // 비밀번호
	private String mname; // 이름
	private String mphone; // 전화번호
	private String mloc; // 선호 지역
	private int mpoint; // 보유 포인트

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public String getMloc() {
		return mloc;
	}

	public void setMloc(String loc) {
		this.mloc = loc;
	}

	public int getMpoint() {
		return mpoint;
	}

	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}

	public Member(int mno, String mid, String mpw, String mname, String mphone, String mloc) {
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mphone = mphone;
		this.mloc = mloc;
		mpoint = 0;
	}

	public Member(String mid, String mpw, String mloc, String mphone) {
		this.mid = mid;
		this.mpw = mpw;
		this.mloc = mloc;
		this.mphone = mphone;

	}

	public Member(int mno, String mid, String mpw, String mname, String mphone, String mloc, int mpoint) {
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mphone = mphone;
		this.mloc = mloc;
		this.mpoint = mpoint;
	}

	// 내 정보 보기
	public void showMyInfo(MemberManager manager) {
		InputReader ir = new InputReader();

		System.out.println();
		System.out.println("ID : " + mid);
		System.out.println("이름 : " + mname);
		System.out.println("전화번호 : " + mphone);
		System.out.println("관심 지역 : " + mloc);
		System.out.println("포인트 : " + mpoint);
		System.out.println("------------------------");

		System.out.println("1. 정보 수정");
		System.out.println("2. 포인트 관리");
		System.out.println("3. 회원 탈퇴");
		System.out.println("0. 홈으로 가기");

		System.out.print("번호 입력 : ");

		int select = ir.readInteger();

		switch (select) {
		case 1:
			updateMyInfo(manager);
			break;
		case 2:
			manageMyPoint(manager);
			break;
		case 3:
			deleteMyId(manager);
			HomeScreen.isLogin = false;
			break;
		case 0:
			break;
		default:
			System.out.println("올바른 숫자를 입력하세요.");
			break;
		}
	}

	// 내 정보 수정
	private void updateMyInfo(MemberManager manager) {
		manager.editId(mid);
	}

	// 회원 탈퇴
	private void deleteMyId(MemberManager manager) {
		manager.deleteMyId(mid);
	}

	// 포인트 관리
	private void manageMyPoint(MemberManager manager) {
		// 포인트 충전, 인출, 정산 선택

		System.out.println("1. 포인트 충전");
		System.out.println("2. 포인트 인출");

		InputReader ir = new InputReader();
		int select = ir.readInteger();

		selectPointMenu(select, manager);
	}

	// 포인트 관리 메뉴 선택하기
	private void selectPointMenu(int select, MemberManager manager) {
		switch (select) {
		case 1:
			// 포인트 충전
			chargePoint(manager);
			break;
		case 2:
			// 포인트 인출
			withdrawPoint(manager);
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
	private void chargePoint(MemberManager manager) {
		System.out.println("충전 하실 금액을 입력해주세요.");
		InputReader ir = new InputReader();
		int charge = ir.readInteger();
		if (charge > 0) {
			this.mpoint += charge;

			manager.editPoint(mpoint); // 데이터베이스 최신화된 금액 입력

			System.out.println("충전 완료!");
			System.out.println("현재 보유중인 포인트는 " + mpoint + " 입니다.");
		} else {
			System.out.println("포인트 충전은 최소 1원 이상 가능합니다.");

		}
	}

	// 포인트 인출
	private void withdrawPoint(MemberManager manager) {
		System.out.println("인출하실 금액을 입력해주세요.");
		InputReader ir = new InputReader();
		int withdrawal = ir.readInteger();
		if (withdrawal >= 100 && withdrawal <= mpoint) {
			this.mpoint -= withdrawal;
			manager.editPoint(mpoint); // 데이터베이스 최신화된 금액 입력

			System.out.println("인출 완료!");
			System.out.println("현재 보유중인 포인트는 " + mpoint + " 입니다.");

		} else if (withdrawal > mpoint) {
			System.out.println("남은 포인트보다 더 많이 출금할 수 없습니다.");
		} else if (withdrawal < 100) {
			System.out.println("포인트는 100점 이상부터 출금이 가능합니다.");
		} else {
			System.out.println("포인트 입력 오류입니다. 다시 입력해 주세요.");
		}
	}

	// 포인트 정산
	private void settlePoint() {
		// join을 통해 orders에서 결제된 포인트의 총합을 구함
		int earnedPoint = 0;
		int usedPoint = 0;

		System.out.println("입금된 포인트 : " + earnedPoint);
		System.out.println("사용한 포인트 : " + usedPoint);
	}

	@Override
	public String toString() {
		return "ClassMember [mno=" + mno + ", mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", mphone=" + mphone
				+ ", mloc=" + mloc + ", mpoint=" + mpoint + "]";
	}

}
