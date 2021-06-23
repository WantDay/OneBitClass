package member;

import java.util.Scanner;

import onebitclass.ClassDAO;
import onebitclass.ClassManager;

public class membermain {

	public static void main(String[] args) {
		MemberManager manager = new MemberManager(ClassMemberDAO.getInstance());
		ClassManager classManager = new ClassManager(ClassDAO.getInstance());
			
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			// 회원 가입
//			manager.createId();
//			// 정보 수정
//			manager.editId();
			// 정보 보기
//			ClassMember member = manager.showMyInfo();
			// 포인트 확인
//			manager.showPoint();
//			// 포인트 충전
//			manager.addPoint();
//			// 포인트 인출
			//classManager.createClass();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
