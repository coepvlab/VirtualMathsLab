package in.ac.coep.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

/**
 * Encrypt string using md5 algorithm
 * </p>
 */

public class Md5Encryption {
	protected static Logger logger = Logger.getLogger("MD5 Encryption");

	public static String md5(String input) {
		// required for password encoder

		String md5 = null;

		if (null == input)
			return null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(input.getBytes(), 0, input.length());
			md5 = new BigInteger(1, digest.digest()).toString(16);

			if (md5.length() < 32) {
				md5 = "0" + md5;
			}

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return md5;
	}

	public static String digestMd5(String password) {
		String base64;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(password.getBytes());
			base64 = new BASE64Encoder().encode(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return "{MD5}" + base64;

	}

	public static void main(String[] args) {
//		System.out.println(md5("123"));
		System.out.println(digestMd5("3fcb8a9589a85084"));
	}

}
