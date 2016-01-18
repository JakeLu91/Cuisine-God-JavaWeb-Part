package util;

public class IDGenerator {
	public static void main(String[] args) {
		
	}
	
	
	public static String createAnID(int numberOfDigit) {
		String result = "";
		for (int i = 0; i < numberOfDigit; i ++) {
			result += randomDigit();
		}
		
		return result;
	}
	
	public static char randomDigit() {
		char digit = '0';
		int a1 = (int)(Math.random() * 10);
		
		int b1 = (int)(Math.random() * 100);
		int b2 = b1 % 25;
		
		if (a1 <= 4) {
			digit = (char) ('a' + b2);
		} else {
			digit = (char) ('A' + b2);
		}
		
		return digit;
	}
}
