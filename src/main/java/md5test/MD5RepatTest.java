package md5test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import lombok.extern.log4j.Log4j;
import md5test.util.MD5Portal;
import md5test.util.StringUtil;
@Log4j
public class MD5RepatTest {
	private static String fileName107K = "file_session_n657AEF58EE53E42D083A32D215A7BC65-1";
	private static String fileName21K = "file_session_n054E9662B5F37C8BA3B5E33D42300098-1";
	private static String fileName2K = "file_session_nFC1515E6A0B84EF310B31B18BC9DB753-1";
	private static String fileName = fileName21K;
	private static String file = "E:/综合资料库沉淀-核心库-方案库-产品库/003各分支项目、系统组事项、部门、人员/安徽移动/安徽移动前后端分离==新资料/00空间申请计算，会话序列化真实导出/会话序列化结果文件/" + fileName;
	private static Map<String, String> md5AndSessionStr = new HashMap<String, String>();

	// 统计结果
	private static int countResult = 1;
	// 替换字符数
	private static int replaceNum = 1;
	// 替换字符是否为随机数
	private static boolean isUseRandom = true;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// 读取安徽实际序列化的会话为基准：21K
		String sessionStr = IOUtils.toString(new FileInputStream(file));
		// // 相同字符串，计算多次MD5串是否会不同
		// check1(sessionStr);
		// // 修改字符串局部，计算多次MD5串是否会相同。逐个字符
		// check2(sessionStr);
		// 修改字符串局部，计算多次MD5串是否会相同。和原串对比
		check3(sessionStr);
	}

	private static void check1(String sessionStr) {
		String md5Str1 = MD5Portal.md5(sessionStr);
		for (int i = 0; i < 100; i++) {// 共计算100万次，分100轮完成，每轮1万次
			log.info("轮次=》" + i);
			for (int j = 0; j < 10000; j++) {
				String md5Str2 = MD5Portal.md5(sessionStr);
				if (!md5Str1.equals(md5Str2)) {
					System.err.println("算出不同的值了！");
				}
			}
		}
		log.info("计算完成！");
	}

	private static void check2(String sessionStr) {
		// 随机修改字符串中某个字符
		String md5Str = MD5Portal.md5(sessionStr);
		md5AndSessionStr.put(md5Str, sessionStr);
		for (int i = 0; i < sessionStr.length(); i++) {// 将字符串从头到尾轮训替换
			// log.info("轮次=》" + i);
			try {
				String newSessionStr = StringUtil.doReplace(sessionStr, i, replaceNum, isUseRandom);// 命中的字符修改为'x'
				String newMd5Str = MD5Portal.md5(newSessionStr);
				if (md5AndSessionStr.containsKey(newMd5Str)) {
					log.info("有重复,个数：" + (countResult) + "，md5Str=" + md5Str + ",总长度为：sessionStr=" + sessionStr.length() + ",newSessionStr="
							+ newSessionStr.length() + "，重复概率为：" + ((double) countResult / newSessionStr.length()));
					countResult++;
				} else {
					md5AndSessionStr.put(newMd5Str, sessionStr);
				}
			} catch (Exception e) {
				log.info("调度跳过！边界值忽略！");
			}
		}
		log.info("计算完成！");
	}

	private static void check3(String sessionStr) {
		// 随机修改字符串中某个字符
		String md5Str = MD5Portal.md5(sessionStr);
		for (int i = 0; i < sessionStr.length(); i++) {// 将字符串从头到尾轮训替换
			// log.info("轮次=》" + i);
			try {
				String newSessionStr = StringUtil.doReplace(sessionStr, i, replaceNum, isUseRandom);// 命中的字符修改为'x'
				String newMd5Str = MD5Portal.md5(newSessionStr);
				if (md5Str.equals(newMd5Str)) {
					log.info("有重复,个数：" + (countResult) + "，md5Str=" + md5Str + ",总长度为：sessionStr=" + sessionStr.length() + ",newSessionStr="
							+ newSessionStr.length() + "，重复概率为：" + ((double) countResult / newSessionStr.length()));
					countResult++;
				}
			} catch (Exception e) {
				log.info("调度跳过！边界值忽略！");
			}
		}
		log.info("计算完成！");
	}
}
