package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
	
	/**
	 * MD5 password of users and customer.
	 * @param data
	 * @return
	 */
	public static String md5(String data) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] md = messageDigest.digest(data.getBytes());
			BigInteger bigInteger = new BigInteger(1, md);
			String hashText = bigInteger.toString(16);
			while (hashText.length() < 32) {
				hashText = "0" + hashText;
			}
			return hashText;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}