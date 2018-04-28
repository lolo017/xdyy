package aisino.reportform.model.fpmng;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
/**
 * 
* @Title:OrderDataZUnionPkId 
* @Description: OrderDataZ主键类
* Company    JS-YFB LTD
* @author 曹梦媛
* @version V1.0    
* @date 2017年10月9日 下午3:29:46
 */
public class OrderDataZUnionPkId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ODID", nullable = false, length = 36)
	private String odid;	//	ODID	varchar(36)	本表主键	
	@Column(name = "DJHM", length = 36)
	private String djhm;	//	DJHM	varchar(36)	原始单据id	
	public String getOdid() {
		return odid;
	}
	public void setOdid(String odid) {
		this.odid = odid;
	}
	public String getDjhm() {
		return djhm;
	}
	public void setDjhm(String djhm) {
		this.djhm = djhm;
	}
	
	@Override 
    public boolean equals(Object obj) { 
        if(obj instanceof OrderDataZUnionPkId){ 
        	OrderDataZUnionPkId pk=(OrderDataZUnionPkId)obj; 
            if(this.odid.equals(pk.odid)&&this.djhm.equals(pk.djhm)){ 
                return true; 
            } 
        } 
        return false; 
    }

    @Override 
    public int hashCode() { 
        return super.hashCode(); 
    }
	
}
