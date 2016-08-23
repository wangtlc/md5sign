package todo;
import lombok.extern.log4j.Log4j;

/**
 * WEB端 【只接收消息】
 * 
 * @author wangtlc
 * 
 */
@Log4j
public class TestWeb {

	public static void main(String[] args) throws Exception {
		String sign = "aT4OytLvN8xfNPx34oV4TaEbsLhpgFzT9vbZq6IKDi5WolkA/7pSiYT5p6g2q/L9PgO9OWx5gvsarGfAdQO/TXjosUJrgAHUChhgWOw1/4Q0lhZzoxJrjvCH9vVUbyJkUK4D022ABuQv+ceY0QFfAFpFBTqRSfTNU7UoO60Sh7Y=";
		log.info(assertSignTrue(sign, "hello"));
	}

	/**
	 * 【验签测试】
	 * 
	 * @param signature
	 *            签名
	 * @param data
	 *            报文
	 * @return
	 * @throws Exception
	 */
	private static boolean assertSignTrue(String signature, String data) throws Exception {
		log.info("报文体------------" + data);
		log.info("APP侧公钥-----------" + ConfigUtil.getString("public"));
		log.info("报文签名-----------" + signature);
		boolean result = RSACoder.verify(data.getBytes("UTF-8"), ConfigUtil.getString("public"), signature);
		if (!result) {
			throw new Exception("验签失败!");
		} else {
			log.info("验签结果:成功!");
		}
		return true;
	}
}
