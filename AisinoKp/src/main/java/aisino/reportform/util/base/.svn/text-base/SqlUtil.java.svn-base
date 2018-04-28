package aisino.reportform.util.base;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.base.Sqlcondition;

/**
 * SQL语句处理工具类
 * 
 * @author 廖宸宇
 * @date 2015-3-25
 */
public class SqlUtil {
	/**
	 * 预览处理SQL，将所有{}内的条件替换为1=1
	 */
	public static String formatSqlForPreview(String sqlStr,String paramStr){
		int locationa;
        int locationb;
        String param;
		String[] pArray=paramStr.split("\\+");
		for(int i=0;i<pArray.length;i++){
		param="$"+pArray[i]+"$";
        while( sqlStr.indexOf(param)!=-1){
        	locationa=sqlStr.indexOf(param)+1;
        	locationb=sqlStr.indexOf(param)-1;
            while(locationa<=sqlStr.length()){
            	if(sqlStr.charAt(locationa)=='}'){
            		break;
            	}
            	locationa++;
            	
            }
            while(locationb>-1){
            	if(sqlStr.charAt(locationb)=='{'){
            		break;
            	}
            	locationb--;
            	
            }
            sqlStr=sqlStr.replace(sqlStr.substring(locationb, locationa+1), "1=1");
        }
		}
		return sqlStr;
		
	}
	
	/**
	 * grid时处理SQL，参数值为空时，转换为1=1
	 */
	public static String formatSqlForGrid(String sqlStr,String param){
		int locationa;
		int locationb;
		param="$"+param+"$";
		while( sqlStr.indexOf(param)!=-1){
        	locationa=sqlStr.indexOf(param)+1;
        	locationb=sqlStr.indexOf(param)-1;
            while(locationa<=sqlStr.length()){
            	if(sqlStr.charAt(locationa)=='}'){
            		break;
            	}
            	locationa++;
            	
            }
            while(locationb>-1){
            	if(sqlStr.charAt(locationb)=='{'){
            		break;
            	}
            	locationb--;
            	
            }
            sqlStr=sqlStr.replace(sqlStr.substring(locationb, locationa+1), "1=1");
            
        }
		return sqlStr;
	}
	/**
	 * grid时处理SQL，替换参数
	 */
	public static String formatSqlForGrid(List<Sqlcondition> conditionList,String sqlStr){
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String param;
		try{
		for(Sqlcondition con : conditionList){
			if(con.getConditiontype().equals("3")&&(ServletActionContext.getRequest().getParameter(con.getEncondition())==null||ServletActionContext.getRequest().getParameter(con.getEncondition())=="")){
				//如果类型是3且为(空或"")，就从session中取值
				param=(String)ReflectionUtil.getValue(sessionInfo.getUser(), ReflectionUtil.getPropertyName(sessionInfo.getUser(), con.getEncondition()));
			}else{
				//否则从request取值
				
					param=ServletActionContext.getRequest().getParameter(con.getEncondition());
				
			}
			if(con.getConditiontype().equals("3")&&con.getEncondition().equalsIgnoreCase("userid")&&(ServletActionContext.getRequest().getParameter(con.getEncondition())==null||ServletActionContext.getRequest().getParameter(con.getEncondition())=="")){
				param=(String)ReflectionUtil.getValue(sessionInfo.getUser(), ReflectionUtil.getPropertyName(sessionInfo.getUser(), "id"));
				
			}
			//如果param有值就替换
			if(param!=null&&param.length() >0){
				//param = URLDecoder.decode(param, "UTF-8");
				//System.out.println("参数"+con.getEncondition()+":"+param);
				sqlStr=sqlStr.replace("$"+con.getEncondition()+"$", param);
			}else{
		    //否则param就替换为1=1
				sqlStr=formatSqlForGrid(sqlStr, con.getEncondition());
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		sqlStr=sqlStr.replace("{", " ");
		sqlStr=sqlStr.replace("}", " ");
		
		
		return sqlStr;
	}

}
