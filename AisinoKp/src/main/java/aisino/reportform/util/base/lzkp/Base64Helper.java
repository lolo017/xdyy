package aisino.reportform.util.base.lzkp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * 
 * <p>
 * base64 helper
 * </p>
 * 
 * @author lichunhui lichunhui1314@126.com
 * @version 1.0 Created on 2014-11-19 下午11:15:41
 */
public class Base64Helper {

	private static final Logger LOGGER = Logger.getLogger(Base64Helper.class);

	private static final String CHARSET = "UTF-8";

	/**
	 * <p>
	 * 解base64
	 * </p>
	 * 
	 * @param res
	 * @return String
	 * @author: lichunhui lichunhui1314@126.com
	 * @date: Created on 2014-11-17 上午11:17:06
	 */
	public static String encode(String res) {
		try {
			Base64 base = new Base64();
			if (res != null && !"".equals(res)) { // 即将解密串不为null并且不为“”
				return new String(base.encode(res.getBytes(CHARSET)));
			} else {
				LOGGER.warn("即将解密串为null或者为“” ");
				return "";
			}
		} catch (Exception e) {
			LOGGER.error("未知：" + e);
			return "";
		}
	}

	/**
	 * 
	 * <p>
	 * base64编码
	 * </p>
	 * 
	 * @param res
	 * @return byte[]
	 * @author: lichunhui lichunhui1314@126.com
	 * @date: Created on 2014-11-17 上午11:41:19
	 */
	public static byte[] encode(byte[] res) {
		try {
			Base64 base = new Base64();
			return base.encode(res);
		} catch (Exception e) {
			LOGGER.error("未知：" + e);
			return null;
		}
	}

	/**
	 * 
	 * <p>
	 * 解base64编码
	 * </p>
	 * 
	 * @param str
	 * @return
	 * @author: lichunhui lichunhui1314@126.com
	 * @date: Created on 2014-11-17 上午11:41:41
	 */
	public static String decode(String str) {

		try {
			return new String(new Base64().decode(str.getBytes()), CHARSET);
		} catch (Exception e) {
			LOGGER.error("未知：" + e);
			return null;
		}
	}

	public static byte[] decodeByte(byte[] str) {
		return new Base64().decode(str);
	}

	public static byte[] decodeToByte(String str) {
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bt;
	}

	/**
	 * 
	 * <p>
	 * 解base64编码
	 * </p>
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 *             byte[]
	 * @author: lichunhui lichunhui1314@126.com
	 * @date: Created on 2014-11-17 上午11:41:58
	 */
	public static byte[] decode(byte[] str) throws UnsupportedEncodingException {
		return new Base64().decode(str);
	}
}
