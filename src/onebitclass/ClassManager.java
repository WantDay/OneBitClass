package onebitclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import member.ClassMember;

public class ClassManager {
	
	private ClassDAO dao;
	private Scanner sc;
	private BitClass bitClass;
	Connection conn = null;
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pw = "tiger";
		
	public ClassManager(ClassDAO dao){
		this.dao = dao;
		sc = new Scanner(System.in);

	}

	// 강좌 개설
	public void createClass(int mno) {
	
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
					
			System.out.println("새로운 강좌를 개설합니다.");
			System.out.println("강좌 제목을 입력해주세요.");
			String title = sc.nextLine();
			System.out.println("강좌가 진행될 지역을 입력해주세요.");
			String cloc = sc.nextLine();
			System.out.println("강좌 시작 날짜를 입력해주세요 ex)2021/06/20");
			String startdate = sc.nextLine();
			System.out.println("강좌 종료 날짜를 입력해주세요 ex)2021/06/20");
			String enddate = sc.nextLine();
			System.out.println("수강료를 입력해주세요. (only 숫자)");
			int fee = Integer.parseInt(sc.nextLine());
			System.out.println("최대 수강 인원을 입력해주세요 (only 숫자)");
			int numpeople = Integer.parseInt(sc.nextLine());
	
			bitClass = new BitClass(0, title, cloc, startdate, enddate, fee, numpeople);

			int result = dao.createClass(conn, bitClass, mno);
			
			
			
			
			if(result > 0 ) {
				System.out.println("입력 되었습니다.");
			} else {
				System.out.println("입력 실패!!!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		
	}
	// 내가 개설한 강좌 정보 보기 
	public void showClass(int mno) { 
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			
			System.out.println("내가 개설한 강좌 정보를 출력합니다.");
			System.out.println("ID를 다시 한 번 입력해주세요.");
			String mid = sc.nextLine();
			List<BitClass> list = dao.getInfo(conn, mno);
			
			for(BitClass bitClass : list) {
				System.out.println(bitClass);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
