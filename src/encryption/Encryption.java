package encryption;

import java.security.MessageDigest;

// 출처 : https://st-lab.tistory.com/100

public class Encryption {
	
	private byte[] password;
	
	public Encryption(byte[] password) {
		this.password = password;
	}

	public String hashing() throws Exception {
		String salt = ".!#";
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");	// SHA-256 해시함수를 사용 
 
		// key-stretching
		for(int i = 0; i < 10000; i++) {
			String temp = byteToString(password) + salt;	// 패스워드와 Salt 를 합쳐 새로운 문자열 생성 
			md.update(temp.getBytes());						// temp 의 문자열을 해싱하여 md 에 저장해둔다 
			password = md.digest();							// md 객체의 다이제스트를 얻어 password 를 갱신한다 
		}
		
		return byteToString(password);
	}
	
	// 바이트 값을 16진수로 변경해준다 
	private String byteToString(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for(byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		
		if (sb.length() > 20) {
			return sb.substring(0, 20).toString();
		} else {
			return sb.toString();
		}
	}
}