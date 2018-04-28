package aisino.reportform.util.base.lzkp;


import java.util.Properties;


/**
 * @author Administrator
 * 西部CA配置文件路径和参数
 */
public final class CaConstant {


	public final static String DEFAULT_CHARSET = "UTF-8";
	private static Properties properties;

	static {
		try {
			properties = new Properties();
			properties.load(CaConstant.class.getResourceAsStream("/pkcs7.properties"));
		} catch (Exception e) {

		}
	}

	/**
	 * 读取配置文件里key的值
	 * 
	 * @param key
	 *            配置文件里的key
	 * @return
	 * @throws java.io.IOException
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

}
