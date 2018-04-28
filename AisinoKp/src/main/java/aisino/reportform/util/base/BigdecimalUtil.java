package aisino.reportform.util.base;

import java.math.BigDecimal;

public class BigdecimalUtil {
	public static BigDecimal add(BigDecimal bigDecimal1,BigDecimal bigDecimal2){
		if(bigDecimal1.compareTo(new BigDecimal(0))==-1&&bigDecimal2.compareTo(new BigDecimal(0))==-1){
			bigDecimal1=bigDecimal1.negate();
			bigDecimal2=bigDecimal2.negate();
			return bigDecimal1.add(bigDecimal2).negate();
		}
		if(bigDecimal1.compareTo(new BigDecimal(0))==-1&&bigDecimal2.compareTo(new BigDecimal(0))==1){
			bigDecimal1=bigDecimal1.negate();
			if(bigDecimal1.compareTo(bigDecimal2)==0){
				return new BigDecimal(0);
			}else if(bigDecimal1.compareTo(bigDecimal2)==1){
				return bigDecimal1.subtract(bigDecimal2).negate();
			}else if(bigDecimal1.compareTo(bigDecimal2)==-1){
				return bigDecimal2.subtract(bigDecimal1);
			}
		}
		if(bigDecimal1.compareTo(new BigDecimal(0))==1&&bigDecimal2.compareTo(new BigDecimal(0))==-1){
			bigDecimal2=bigDecimal2.negate();
			if(bigDecimal1.compareTo(bigDecimal2)==0){
				return new BigDecimal(0);
			}else if(bigDecimal2.compareTo(bigDecimal1)==1){
				return bigDecimal2.subtract(bigDecimal1).negate();
			}else if(bigDecimal2.compareTo(bigDecimal1)==-1){
				return bigDecimal1.subtract(bigDecimal2);
			}
		}
		return bigDecimal1.add(bigDecimal2);
	}
	public static void main(String[] args) {
		BigDecimal bigvalue=new BigDecimal("-112273.96");
		BigDecimal bigvalue1=new BigDecimal("112272.96");
		//System.out.println(add(bigvalue, bigvalue1));
//		System.out.println(bigvalue.divide(bigvalue1, 0, BigDecimal.ROUND_UP));
		System.out.println(bigvalue.negate());
	}
}
