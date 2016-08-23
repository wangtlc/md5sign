package md5test.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Portal {
	public final static String md5(String s) {
		String result = null;
		
		//核心包
		result = MD5Util.md5(s);
//		
//		//JDK算法
//		result = jdk(s, result);
		
		//apache commons codec，它其实是对JDK的简单封装
//		result= DigestUtils.md5Hex(s) ;
		
		return result;
	}

	private static String jdk(String s, String result) {
		try {
			result= new String(MessageDigest.getInstance("MD5").digest(s.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
}
