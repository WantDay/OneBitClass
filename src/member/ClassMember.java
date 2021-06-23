package member;

import java.util.Date;
import java.util.Scanner;

import bitClass.InputReader;

public class ClassMember {
	// 회원이 입력 데이터를 넣어서 가입 및 관리

	private int mno = 0; // 회원 번호
	private String mid; // 회원 ID
	private String mpw; // 비밀번호
	private String mname; // 이름
	private String mdate; // 생년 월일
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

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
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

	public ClassMember(int mno, String mid, String mpw, String mname, String mdate, String mloc) {
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mdate = mdate;
		this.mloc = mloc;
		mpoint = 0;
	}

	public ClassMember(String mid, String mpw, String mloc, String mdate) {
		this.mid = mid;
		this.mpw = mpw;
		this.mloc = mloc;
		this.mdate = mdate;

	}

	public ClassMember(int mno, String mid, String mpw, String mname, String mdate, String mloc,
			int mpoint) {
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mdate = mdate;
		this.mloc = mloc;
		this.mpoint = mpoint;
	}

	// 내 정보 보기
	void showMyInfo() {
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("ID : " + mid);
		System.out.println("이름 : " + mname);
		System.out.println("생년월일 : " + mdate);
		System.out.println("관심 지역 : " + mloc);
		System.out.println("포인트 : " + mpoint);
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
		this.mpoint += charge;

		// dao를 통해 포인트 갱신
		System.out.println(charge + "포인트가 정상적으로 충전됐습니다.");
	}

	// 포인트 인출
	private void withdrawPoint() {
		InputReader ir = new InputReader();
		int withdrawal = ir.readInteger();
		this.mpoint -= withdrawal;

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

	@Override
	public String toString() {
		return "ClassMember [mno="	+ mno
				+ ", mid="
				+ mid
				+ ", mpw="
				+ mpw
				+ ", mname="
				+ mname
				+ ", mdate="
				+ mdate
				+ ", mloc="
				+ mloc
				+ ", mpoint="
				+ mpoint
				+ "]";
	}

}
