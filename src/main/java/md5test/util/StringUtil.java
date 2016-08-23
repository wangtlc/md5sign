package md5test.util;

import java.util.Random;

public class StringUtil {
	/**
	 * <pre>
	 * 替换str4Replace里面
	 * 第index4Replace位为其它字符，index4Replace从（0）开始到（str长度-1）
	 * replaceNum为替换的字符个数
	 * </pre>
	 */
	public static String doReplace(String str4Replace, int index4Replace, int replaceNum, boolean isUseRandom) {
		if (index4Replace < 0 || index4Replace >= str4Replace.length()) {
			throw new RuntimeException("传入的位置不合法！不在边界里！");
		}
		if ((index4Replace + replaceNum) > str4Replace.length()) {
			throw new RuntimeException("传入的位置不合法！长度溢出！");
		}
		String to = "";
		for (int j = 0; j < replaceNum; j++) {
			to += makeChar(isUseRandom);
		}
		return str4Replace.substring(0, index4Replace) + to + str4Replace.substring(index4Replace+replaceNum);
	}

	/**
	 * 生成单字符
	 */
	public static char makeChar(boolean isUseRandom) {
		if (!isUseRandom) {
			return 'x';
		}
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		int number = random.nextInt(62);// [0,62)
		return str.charAt(number);
	}

	public static void main(String[] args) {
		String todo = "abcdef";
		System.out.println(doReplace(todo, 1, 3, false));
	}
}
