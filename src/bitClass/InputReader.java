package bitClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {

	public String readString() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String readString = null;

		try {
			readString = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return readString;
	}

	public int readInteger() {
		return Integer.parseInt(readString());
	}
}
