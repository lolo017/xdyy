package aisino.reportform.util.base;

import java.util.Scanner;

import com.alibaba.druid.filter.config.ConfigTools;

public class DecryptDruid {
    
	private static final String PRIVATE_KEY_STRING="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALMTjzGSavjjYXCFBVeibIrWwrlheQLE5Plvb4af8bupSuKl5tGn85wnqNLhq/P2OAW7QiHYrBuaQaXTW9NEF0Z+q95X5sZZv8CF+n6z3qtG5aary4RnWWujXIVKkwkbAaJkRjQyztkeyDo3xm3HDGfe9befBTRd4sfYTocaX8bHAgMBAAECgYEAq1wnJV+f/6TOZDbWQuLJjR4vXefZPX4eQlaRWTkKv/IjF7l7o9pcsmv9EDclLaHKQCnOAcduAkfdVApqD0NUJOIYMeubidrS7wni7EA2dQpu2TPpB/ephHstwz+RfnGiR8hNTL9flTyt4kASvebIe+OyvATl1pP32wjJaqWbYKkCQQD3ZiOYED0vWeEH/bPZ4DuoQFe37s+hIiLd985d29Qn0Mw+/AFNA9Mfxb+kzipulFxv+I0h9UcCsyQ9Z3uBAXwbAkEAuU1YuydssQra8gd2YFH9wiskIvTLiH4oKiJf8j5tC6XOmooRJOixkb5yb0FwTbYHRvlH7hd8OdqkTwTMQdoyxQJBAN39YnbyK1Svu4JmHdmkoSNKCOvcd6ZIcSznTN9ff7DyZDMoASrQAcOCt4H0FJMAwSkfx9PuuPctM5l9n46UfH0CPyn7XB8Kz5VM4E31Ytxvtw5Gt+kzjOlfq7ngD9zSR+JbrnNU1I2IKrLacxLBwK/ajcuGrsVCjOKOpqtAawaAaQJBAM4/VSAd2+DbH+4uKj0I3rFaOyqj+LJ1elzfiyCMroWA8o/mHT4J6CQvYSopgwVl0kg+sBXAwvlRLsQGFMU1N/g=";
	
	private static final String PUBLIC_KEY_STRING="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzE48xkmr442FwhQVXomyK1sK5YXkCxOT5b2+Gn/G7qUripebRp/OcJ6jS4avz9jgFu0Ih2KwbmkGl01vTRBdGfqveV+bGWb/Ahfp+s96rRuWmq8uEZ1lro1yFSpMJGwGiZEY0Ms7ZHsg6N8Ztxwxn3vW3nwU0XeLH2E6HGl/GxwIDAQAB";
    public DecryptDruid() {
        super();
        // TODO Auto-generated constructor stub
    }
    public static void main(String[] args){
        DecryptDruid dp = new DecryptDruid();
        System.out.println("请输入要加密的字符串：");
        Scanner sc=new Scanner(System.in);
        String str=sc.next();
        String[] myUserPass ={str};
                for(int i = 0;i < myUserPass.length;i++){
        dp.testDecrypt(dp.testEncrypt(myUserPass[i]));
    }
        //System.out.println(dp.testDecrypt("JKuGVJrPf/08DXsiNiVbdUzZRoDPFJwraXB6Mt6zZ7liSu7R03FfcGhpjJqIzv5CsC2wAuT5FatnTLBl/ZXPjg=="));
    }
    /**
     * 对文字进行解密
     * @throws Exception
     */
    public String testDecrypt(String encry){
        //解密
        String word=encry;
        String decryptword="";
        try {
            decryptword = ConfigTools.decrypt(PUBLIC_KEY_STRING,word);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        System.out.println(decryptword);
        return decryptword;
    }
    /**
     * 文字进行加密
     * @throws Exception 
     */
    public String testEncrypt(String userpass)
    {
        //加密
        String encryptword = "";
        try {
            encryptword = ConfigTools.encrypt(PRIVATE_KEY_STRING,userpass);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        System.out.println(encryptword);
        return encryptword;
    }
}
   