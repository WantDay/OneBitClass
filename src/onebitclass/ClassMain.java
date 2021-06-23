package onebitclass;

import java.util.Scanner;

import member.ClassMemberDAO;
import member.MemberManager;

public class ClassMain {

	public static void main(String[] args) {

		MemberManager memberManager = new MemberManager(ClassMemberDAO.getInstance());
		ClassManager classManager = new ClassManager(ClassDAO.getInstance());

		Scanner sc = new Scanner(System.in);
			
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}