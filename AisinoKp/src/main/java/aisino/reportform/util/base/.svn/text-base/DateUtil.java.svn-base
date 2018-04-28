package aisino.reportform.util.base;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author 
 * 
 */
public class DateUtil {

	public static void main(String[] args) {

	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd hh:mm:ss");
	}
	/**
	 * 计算下一个月，2015-12 下一个月 2016-01
	 * @param d
	 * @return
	 */
	public static String nextMonth(String d){
		String s;
		if(d.substring(6).equals("2")&&d.substring(5, 6).equals("1")){
			int y=Integer.parseInt(d.substring(0, 4))+1;
			s=Integer.toString(y)+"-01";
		}else if(d.substring(6).equals("9")){
			s=d.substring(0,4)+"-10";
		}else{
			int day=Integer.parseInt(d.substring(6))+1;
			s=d.substring(0,6)+Integer.toString(day);
		}
		return s;
	}

}
