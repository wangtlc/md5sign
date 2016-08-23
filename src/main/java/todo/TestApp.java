package todo;
import lombok.extern.log4j.Log4j;

/**
 * APP端 【只发消息】
 * 
 * @author wangtlc
 * 
 */
@Log4j
public class TestApp {

	public static void main(String[] args) throws Exception {
		String data = "hello";
		log.info("原：" + data);
		log.info("签名" + sign(data));
	}

	/**
	 * @param signature
	 *            签名
	 * @param data
	 *            报文
	 * @return
	 * @throws Exception
	 */
	private static String sign(String data) throws Exception {
		String signature = RSACoder.sign(data.getBytes(), ConfigUtil.getString("private"));
		return signature;
	}
}
