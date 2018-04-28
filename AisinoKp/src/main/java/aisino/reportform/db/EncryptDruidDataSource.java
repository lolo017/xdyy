package aisino.reportform.db;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
/**
 * 
* @Title:EncryptDruidDataSource 
* @Description: 数据库链接信息解密
* Company    JS-YFB LTD
* @author 吕振宇
* @version V1.0    
* @date 2017年9月8日 上午11:07:33
 */
@SuppressWarnings("serial")
public class EncryptDruidDataSource extends DruidDataSource{
	
	private static final String PRIVATE_KEY_STRING="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALMTjzGSavjjYXCFBVeibIrWwrlheQLE5Plvb4af8bupSuKl5tGn85wnqNLhq/P2OAW7QiHYrBuaQaXTW9NEF0Z+q95X5sZZv8CF+n6z3qtG5aary4RnWWujXIVKkwkbAaJkRjQyztkeyDo3xm3HDGfe9befBTRd4sfYTocaX8bHAgMBAAECgYEAq1wnJV+f/6TOZDbWQuLJjR4vXefZPX4eQlaRWTkKv/IjF7l7o9pcsmv9EDclLaHKQCnOAcduAkfdVApqD0NUJOIYMeubidrS7wni7EA2dQpu2TPpB/ephHstwz+RfnGiR8hNTL9flTyt4kASvebIe+OyvATl1pP32wjJaqWbYKkCQQD3ZiOYED0vWeEH/bPZ4DuoQFe37s+hIiLd985d29Qn0Mw+/AFNA9Mfxb+kzipulFxv+I0h9UcCsyQ9Z3uBAXwbAkEAuU1YuydssQra8gd2YFH9wiskIvTLiH4oKiJf8j5tC6XOmooRJOixkb5yb0FwTbYHRvlH7hd8OdqkTwTMQdoyxQJBAN39YnbyK1Svu4JmHdmkoSNKCOvcd6ZIcSznTN9ff7DyZDMoASrQAcOCt4H0FJMAwSkfx9PuuPctM5l9n46UfH0CPyn7XB8Kz5VM4E31Ytxvtw5Gt+kzjOlfq7ngD9zSR+JbrnNU1I2IKrLacxLBwK/ajcuGrsVCjOKOpqtAawaAaQJBAM4/VSAd2+DbH+4uKj0I3rFaOyqj+LJ1elzfiyCMroWA8o/mHT4J6CQvYSopgwVl0kg+sBXAwvlRLsQGFMU1N/g=";
	
	private static final String PUBLIC_KEY_STRING="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzE48xkmr442FwhQVXomyK1sK5YXkCxOT5b2+Gn/G7qUripebRp/OcJ6jS4avz9jgFu0Ih2KwbmkGl01vTRBdGfqveV+bGWb/Ahfp+s96rRuWmq8uEZ1lro1yFSpMJGwGiZEY0Ms7ZHsg6N8Ztxwxn3vW3nwU0XeLH2E6HGl/GxwIDAQAB";
	
	@Override
	public void setUrl(String url){
        try {
            url = ConfigTools.decrypt(PUBLIC_KEY_STRING,url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setUrl(url);
    }
    
    @Override
    public void setPassword(String password){
        try {
        	password = ConfigTools.decrypt(PUBLIC_KEY_STRING,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setPassword(password);
        
    }
    
    
    
    @Override
    public void setUsername(String username){
        try {
            username = ConfigTools.decrypt(PUBLIC_KEY_STRING,username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setUsername(username);
    }
    
}
