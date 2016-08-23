package md5test.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * MD5工具
 */
public class MD5Util {
	private static Logger logger = Logger.getLogger(MD5Util.class);

	public final static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		String todo="testtesttest";
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 1; i++) {
			System.out.println(MD5Util.md5(todo));
		}
		System.out.println(System.currentTimeMillis() - begin);
	}

}