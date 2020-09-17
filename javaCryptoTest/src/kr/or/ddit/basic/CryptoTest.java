package kr.or.ddit.basic;

import kr.or.ddit.util.CryptoUtil;

public class CryptoTest {
	public static void main(String[] args) throws Exception {
		String testData = "Hello, 하이";
		
		System.out.println("MD5 : " + CryptoUtil.md5(testData));
		System.out.println("SHA256 : " + CryptoUtil.sha256(testData));
		System.out.println("SHA512 : " + CryptoUtil.sha512(testData));
		System.out.println("--------------------------------------------------------------------------------------");
		
		AES256Util aes256 = new AES256Util();
		// 암호화 작업
		String str = aes256.encrypt(testData);
		System.out.println("AES256 암호화하기 전 : " + testData);
		System.out.println("AES256 암호화하기 후 : " + str);
		System.out.println("--------------------------------------------------------------------------------------");
		
		// 복호화 작업
		String deStr = aes256.decrypt(str);
		System.out.println("AES256 복호화하기 전 : " + str);
		System.out.println("AES256 복호화하기 후 : " + deStr);
	}
}
