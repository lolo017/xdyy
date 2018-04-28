package aisino.reportform.util.base.gy;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import aisino.reportform.util.base.ConfigUtil;

import com.alibaba.fastjson.JSONObject;

public class ERPSignUtil {
	//签名
	public static String sign(String json,String secret){
        StringBuilder enValue = new StringBuilder();
        //前后加上secret
        enValue.append(secret);
        enValue.append(json);
        enValue.append(secret);
        // 使用MD5加密(32位大写)
        byte[] bytes = encryptMD5(enValue.toString());
        return byte2hex(bytes);
    }

	//secretMD5加密
    private static byte[] encryptMD5(String data) {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }
    
    //secretHEX加密
    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
    
    /**
     * 
     * @Title: getResponseMsg
     * @Description: 管易接口调用
     * @author 曹梦媛
     * @date 2018年1月2日 上午10:21:49
     * @param: @param start_create
     * @param: @param end_create
     * @param: @param shop_code
     * @param: @return 
     * @return: String   
     * @throws
     */
    public static String getResponseMsg(String method, String start_create, 
    		String end_create, int pageno_no, String shop_code, String item_code) {
		
		try {
			String param_url = ConfigUtil.get("param_url");//"http://v2.api.guanyierp.com/rest/erp_open";
			URL url=new URL(param_url);
			URLConnection urllConnection=url.openConnection();
			HttpURLConnection httpUrlConnection=(HttpURLConnection) urllConnection;
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestProperty("Content-type","application/json");
			
			OutputStream os=httpUrlConnection.getOutputStream();
			
			JSONObject json=new JSONObject();
			
			//系统级参数
			json.put("appkey", ConfigUtil.get("appkey"));
			json.put("sessionkey", ConfigUtil.get("sessionkey"));
			json.put("method", method);//gy.erp.shop.get
			//应用级参数
			json.put("page_no", pageno_no);
			json.put("page_size", ConfigUtil.get("page_size"));	//最大100
			//7天内的单据
			if("gy.erp.shop.get".equals(method)){
				if (start_create != null) {
					json.put("start_create", start_create);	//单据创建时间起
				}
				if (end_create != null) {
					json.put("end_create", end_create);	//单据创建时间止
				}
			}else{
				if (start_create != null) {
					json.put("start_delivery_date", start_create);	//发货时间开始段
				}
				if (end_create != null) {
					json.put("end_delivery_date", end_create);	//发货时间结束段
				}
			}
			
			if (shop_code != null) {
				json.put("shop_code", shop_code);	//店铺编码
			}
			if (item_code != null) {
				json.put("code", item_code);	//商品编码
			}
			
			String sign=ERPSignUtil.sign(json.toString(), ConfigUtil.get("secret"));
			json.put("sign", sign);

			//System.out.println(json.toString());
			os.write(json.toString().getBytes());
			String return_s="";
			InputStream is=httpUrlConnection.getInputStream();
			byte[] b=new byte[1024];
			int len=0;
			String s="";
			while((len=is.read(b))!=-1){
				String ss=new String(b,0,len,"UTF-8");
				s+=ss;
			}
			//System.out.println("返回:"+URLDecoder.decode(s, "UTF-8"));
			return_s=URLDecoder.decode(s, "UTF-8");
			//System.out.println(return_s);
			return return_s;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} 

	} 

    
    public static void main(String[] args) {
    	try {
    		String param_url = "http://v2.api.guanyierp.com/rest/erp_open";//ConfigUtil.get("param_url");
    		URL url=new URL(param_url);
    		URLConnection urllConnection=url.openConnection();
    		HttpURLConnection httpUrlConnection=(HttpURLConnection) urllConnection;
    		httpUrlConnection.setRequestMethod("POST");
    		httpUrlConnection.setDoInput(true);
    		httpUrlConnection.setDoOutput(true);
    		httpUrlConnection.setUseCaches(false);
    		httpUrlConnection.setRequestProperty("Content-type","application/json");
    		
    		OutputStream os=httpUrlConnection.getOutputStream();
    		
    		JSONObject json=new JSONObject();
    		
    		//系统级参数
    		json.put("appkey", ConfigUtil.get("appkey"));
    		json.put("sessionkey", ConfigUtil.get("sessionkey"));
    		json.put("method", "gy.erp.trade.deliverys.get");//gy.erp.shop.get,gy.erp.trade.deliverys.get,gy.erp.items.get
    		//应用级参数
    		json.put("page_no", 1);
    		json.put("page_size", 10);	//最大100,ConfigUtil.get("page_size")
//    		json.put("start_create", "");	//单据创建时间起
//    		json.put("end_create", "");	//单据创建时间止
//    		json.put("shop_code", "");	//店铺编码
    		json.put("outer_code", "112141999470756876");	//店铺编码P515044868454674161,SDO65376104512,SDO65376105408
//    		json.put("code", "6923807806368");	//商品代码
    		
    		String sign=ERPSignUtil.sign(json.toString(), ConfigUtil.get("secret"));
    		json.put("sign", sign);
    		
    		//System.out.println(json.toString());
    		os.write(json.toString().getBytes());
    		String return_s="";
    		InputStream is=httpUrlConnection.getInputStream();
    		byte[] b=new byte[1024];
    		int len=0;
    		String s="";
    		while((len=is.read(b))!=-1){
    			String ss=new String(b,0,len,"UTF-8");
    			s+=ss;
    		}
    		//System.out.println("返回:"+URLDecoder.decode(s, "UTF-8"));
    		return_s=URLDecoder.decode(s, "UTF-8");
    		System.out.println(return_s);
    		//return return_s;
    	} catch (Exception e) {
    		e.printStackTrace();
    		//return e.getMessage();
    	} 
    	
    } 
    

}
