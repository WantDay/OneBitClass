package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MemberManager {
	
	private ClassMemberDAO dao;
	private Scanner sc;
	private ClassMember member;
	Connection conn = null;
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pw = "tiger";
		
	public MemberManager(ClassMemberDAO dao){
		this.dao = dao;
		sc = new Scanner(System.in);

	}

	// 회원 가입
	// 사용자에게 Scanner 클래스로 입력 받아 -> 
	// dao ClassMemberDAO 메소드로 저장
	public void createId() {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
		
			System.out.println("회원 가입을 시작합니다.");
			System.out.println("ID를 입력해주세요.");
			String mid = sc.nextLine();
			System.out.println("PW를 입력해주세요.");
			String mpw = sc.nextLine();
			System.out.println("이름을 입력해주세요.");
			String mname = sc.nextLine();
			System.out.println("생년월일을 입력해주세요. ex)91/07/21");
			String mdate = sc.nextLine();
			System.out.println("선호하시는 지역을 입력해주세요.");
			String mloc = sc.nextLine();
	
			member = new ClassMember(0, mid, mpw, mname, mdate, mloc);

			int result = dao.createId(conn, member);
			
			if(result > 0 ) {
				System.out.println("입력 되었습니다.");
			} else {
				System.out.println("입력 실패!!!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// 회원 정보 수정

	void editId() {

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
		
			System.out.println("회원 정보를 수정합니다.");
			System.out.println("ID를 다시 한 번 입력해주세요.");
			String mid = sc.nextLine();
			System.out.println("수정하실 PW를 입력해주세요. 변경을 원치 않을 경우 기존 정보를 입력해주세요.");
			String mpw = sc.nextLine();
			System.out.println("지역을 입력해주세요. 변경을 원치 않을 경우 기존 정보를 입력해주세요.");
			String mloc = sc.nextLine();
			System.out.println("생년월일을 입력해주세요. ex)91/07/21");
			String mdate = sc.nextLine();
			
			member = new ClassMember(mid, mpw, mloc, mdate);

			int result = dao.editInfo(conn, member);
			
			if(result > 0 ) {
				System.out.println("정보가 수정 되었습니다.");
			} else {
				System.out.println("수정 실패!!!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// 회원 정보 확인 
	public ClassMember showMyInfo(String mid) {
		
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			
			System.out.println("회원 정보를 출력합니다.");

			List<ClassMember> list = dao.getInfo(conn, mid);
			
			member = list.get(0);
			System.out.println("이름 : " + member.getMname());
			System.out.println("생년월일 : " + member.getMdate());
			System.out.println("관심 지역 : " + member.getMloc());
			System.out.println("포인트 : " + member.getMpoint());
			System.out.println("------------------------");	
			
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;	
	}
	
	//로그인 회원 관리
	public ClassMember loginInfo(String mid) {
		try {
			
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			List<ClassMember> list = dao.getInfo(conn, mid);
			
			member = list.get(0);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return member;
	} 
	// 회원 탈퇴
	public void deleteId(String mid) {
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			
			int result = dao.deleteId(conn, member);
			System.out.println("회원 정보가 삭제되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 포인트 관리 
	// 현재 보유중인 포인트 보기
	void showPoint() {
		System.out.println("현재 보유중인 포인트는 "+ member.getMpoint()+ " 입니다.");
		
	}
	
	void addPoint() {
		System.out.println("충전 하실 금액을 입력해주세요.");
		int inputPoint = Integer.parseInt(sc.nextLine());
		int total = member.getMpoint() + inputPoint;
		member.setMpoint(total);		
		
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			
			int result = dao.editPoint(conn, member);
			
			
			System.out.println("충전 완료!");
			System.out.println("현재 보유중인 포인트는 "+ member.getMpoint()+" 입니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void subPoint() {
		System.out.println("인출 하실 금액을 입력해주세요.");
		int inputPoint = Integer.parseInt(sc.nextLine());
		int total = member.getMpoint() - inputPoint;
		member.setMpoint(total);		
		
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			
			int result = dao.editPoint(conn, member);
			
			
			System.out.println("인출 완료!");
			System.out.println("현재 보유중인 포인트는 "+ member.getMpoint()+" 입니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
