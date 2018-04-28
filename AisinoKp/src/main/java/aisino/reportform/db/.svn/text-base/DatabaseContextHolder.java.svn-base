package aisino.reportform.db;
/**
 * 
 * 数据源切换
 *
 */
public class DatabaseContextHolder {
	public static final String DATA_SOURCE_1 = "pos";  
    public static final String DATA_SOURCE_2 = "crm_scjs";
    public static final String DATA_SOURCE_3 = "jsy";
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setCustomerType(String customerType) {
		contextHolder.set(customerType);
	}

	public static String getCustomerType() {
		return contextHolder.get();
	}

	public static void clearCustomerType() {
		contextHolder.remove();
	}
}