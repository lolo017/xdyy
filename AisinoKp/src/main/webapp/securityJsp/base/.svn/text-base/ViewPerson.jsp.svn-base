<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"> 

//设置分页控件  
var p = $('#khList').datagrid('getPager');  
$(p).pagination({  
    pageSize: 50,//每页显示的记录条数，默认为10  
    pageList: [20,50,100],//可以设置每页记录条数的列表  
    beforePageText: '第',//页数文本框前显示的汉字  
    afterPageText: '页    共 {pages} 页',  
    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'

});  
var pp = $('#iList').datagrid('getPager');  
$(pp).pagination({  
    pageSize: 50,//每页显示的记录条数，默认为10  
    pageList: [20,50,100],//可以设置每页记录条数的列表  
    beforePageText: '第',//页数文本框前显示的汉字  
    afterPageText: '页    共 {pages} 页',  
    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'

});  
</script>
<div id="khtt" class="easyui-tabs" style="height:464;">   
           <div title="基本信息" style="overflow:auto;">  

		
		<table class="tableForm datagrid-toolbar"
			style="background-color:white;width: 100%; height: 100%;">
			<tr>
				<th>
					企业名称
				</th>
				<td>
					<input  id="cust_name" type="text" style="width:200">
				</td>
				<th>
					完整度
				</th>
				<td>
					<input  id="integrity" type="text" style="width:200">
				</td>
				
								
				
			</tr>

            <tr>
				<th>
					行业
				</th>
				<td>
					<input  id="customer_ind_id" type="text" style="width:200">		
                    </td>
				<th>
					主要产品
				</th>
				<td>
					<input id="mainProduct" type="text" style="width:200">		
                    </td> 
				
				
			</tr>
		 <tr>
		 <th>
					工作时间
				</th>
				<td>
					<input  id="sWork" type="text" style="width:100">
						<input id="eWork" type="text" style="width:100">
				</td>		
		 </tr>
		 <tr>
		 <th>
					IT服务
				</th>
				<td>
					<input  id="it_type" type="text" style="display:none"
						value="${xx.IT_TYPE}">
					<select id="it" name="it" disabled="disabled" style="height:20">   
                        <option value="-1">未采集</option>                                          
                        <option value="0">信息部门</option>
                        <option value="1">专职网管</option>
                        <option value="2">外包公司</option>
                    </select>
				</td>	
		 <th>
		 pos需求
		 </th>
		 <td>
		 <input  id="is_need_pos" type="text" style="display:none"
						value="${xx.is_need_pos}">
		 <select id="pos"  disabled="disabled" style="width:200;height:20" >   
                        <option value="-1">未采集</option>                                          
                        <option value="0">有</option>
                        <option value="1">无</option>
         </select>
		 </td>
		 </tr>
			
		</table>

	
	</div>
	<div title="应用软件" style=""> 
	
		<table class="tableForm datagrid-toolbar"
			style="background-color:white;width: 100%; height: 100%;">
			<tr>    
			    <th nowrap>
					完整度
				</th>
				<td>
					<input name=cust_name id="cust_name" type="text" style="width:150">
				</td>
			</tr>
			<tr>
				<th nowrap>
					财务软件
				</th>
				<td>				    				    
					<input id="is_fin_soft" type="text"
						style="display:none" value="${xx.IS_FIN_SOFT}">
				    <select id="fin" name="fin" disabled="disabled" style="width:150;height:20">   
                         <option value="-1">未采集</option>
                        <option value="0">有</option>
                        <option value="1">无(有需求)</option>   
                        <option value="2">无(无需求)</option>      
                    </select>  
				</td>				
				<th nowrap>
					软件品牌
				</th>
				<td>
				    <input id="fin_soft_name" type="text"
						style="display:none" value="${xx.FIN_SOFT_NAME}">
				    <select id="fin_name" disabled="disabled" style="width:150;height:20">   
                        <option value="-1">未采集</option>                                          
                        <option value="0">oracle</option> 
                        <option value="1">sap</option> 
                        <option value="2">Ibm</option> 
                        <option value="3">用友</option> 
                        <option value="4">金蝶</option> 
                        <option value="5">航信</option> 
                        <option value="6">浪潮</option> 
                        <option value="7">速达</option> 
                        <option value="8">鼎捷</option> 
                        <option value="9">金和</option> 
                        <option value="10">神码</option> 
                        <option value="11">新中大</option> 
                        <option value="12">天思</option> 
                        <option value="13">天心</option> 
                        <option value="14">72</option> 
                        <option value="15">八百客</option> 
                        <option value="16">金算盘</option> 
                        <option value="17">其他</option>   
                    </select>  
				</td>						
				

       
        
			</tr>
			<tr>
			<th >
					ERP系统
				</th>
				<td>
				    <input  id="is_erp_soft" type="text"
						style="display:none" value="${xx.IS_ERP_SOFT}">
					<select id="erp"  disabled="disabled" style="width:150;height:20">   
                         <option value="-1">未采集</option>
                        <option value="0">有</option>
                        <option value="1">无(有需求)</option>   
                        <option value="2">无(无需求)</option>    
                    </select>  
				</td>
				<th nowrap>
					软件品牌
				</th>
				<td>
				    <input  id="erp_soft_name" type="text"
						style="display:none" value="${xx.ERP_SOFT_NAME}">
				    <select id="erp_name" name="erp_name" disabled="disabled" style="width:150;height:20">   
                        <option value="-1">未采集</option>                                          
                        <option value="0">oracle</option> 
                        <option value="1">sap</option> 
                        <option value="2">Ibm</option> 
                        <option value="3">用友</option> 
                        <option value="4">金蝶</option> 
                        <option value="5">航信</option> 
                        <option value="6">浪潮</option> 
                        <option value="7">速达</option> 
                        <option value="8">鼎捷</option> 
                        <option value="9">金和</option> 
                        <option value="10">神码</option> 
                        <option value="11">新中大</option> 
                        <option value="12">天思</option> 
                        <option value="13">天心</option> 
                        <option value="14">72</option> 
                        <option value="15">八百客</option> 
                        <option value="16">金算盘</option> 
                        <option value="17">其他</option>   
                    </select>
				</td>
				
			
			</tr>
			<tr>
			<th >
					CRM系统
				</th>
				<td>
				    <input id="is_crm_soft" type="text"
						style="display:none" value="${xx.IS_CRM_SOFT}">
					<select id="crm"  disabled="disabled" style="width:150;height:20">   
                         <option value="-1">未采集</option>
                        <option value="0">有</option>
                        <option value="1">无(有需求)</option>   
                        <option value="2">无(无需求)</option>      
                    </select>  
				</td>
				<th nowrap>
					软件品牌
				</th>
				<td>
				    <input  id="crm_soft_name" type="text"
						style="display:none" value="${xx.CRM_SOFT_NAME}">
				    <select id="crm_name" disabled="disabled" style="width:150;height:20">   
                        <option value="-1">未采集</option>                                          
                        <option value="0">oracle</option> 
                        <option value="1">sap</option> 
                        <option value="2">Ibm</option> 
                        <option value="3">用友</option> 
                        <option value="4">金蝶</option> 
                        <option value="5">航信</option> 
                        <option value="6">浪潮</option> 
                        <option value="7">速达</option> 
                        <option value="8">鼎捷</option> 
                        <option value="9">金和</option> 
                        <option value="10">神码</option> 
                        <option value="11">新中大</option> 
                        <option value="12">天思</option> 
                        <option value="13">天心</option> 
                        <option value="14">72</option> 
                        <option value="15">八百客</option> 
                        <option value="16">金算盘</option> 
                        <option value="17">其他</option>   
                    </select>
				</td>
			</tr>
		<tr>
		<th nowrap>
					OA办公软件
				</th>
				<td>				    				    
					<input id="is_oa_soft" type="text"
						style="display:none" value="${xx.IS_OA_SOFT}">
				    <select id="oa" disabled="disabled" style="width:150;height:20">   
                        <option value="-1">未采集</option>
                        <option value="0">有</option>
                        <option value="1">无(有需求)</option>   
                        <option value="2">无(无需求)</option>      
                    </select>  
				</td>						
				<th nowrap>
					软件品牌
				</th>
				<td>
				    <input  id="oa_soft_name" type="text"
						style="display:none" value="${xx.OA_SOFT_NAME}">
				    <select id="oa_name"  disabled="disabled" style="width:150;height:20">   
                       <option value="-1">未采集</option>                                          
                        <option value="0">oracle</option> 
                        <option value="1">sap</option> 
                        <option value="2">Ibm</option> 
                        <option value="3">用友</option> 
                        <option value="4">金蝶</option> 
                        <option value="5">航信</option> 
                        <option value="6">浪潮</option> 
                        <option value="7">速达</option> 
                        <option value="8">鼎捷</option> 
                        <option value="9">金和</option> 
                        <option value="10">神码</option> 
                        <option value="11">新中大</option> 
                        <option value="12">天思</option> 
                        <option value="13">天心</option> 
                        <option value="14">72</option> 
                        <option value="15">八百客</option> 
                        <option value="16">金算盘</option> 
                        <option value="17">其他</option>                       
                    </select>  
				</td>	
		</tr>
			
			
			<tr>
			<th nowrap>
					人力资源系统
				</th>
				<td>				    				    
					<input  id="is_hr_soft" type="text"
						style="display:none" value="${xx.IS_HR_SOFT}">
				    <select id="hr" name="hr" disabled="disabled" style="width:150;height:20">   
                        <option value="-1">未采集</option>
                        <option value="0">有</option>
                        <option value="1">无(有需求)</option>   
                        <option value="2">无(无需求)</option>     
                    </select>  
				</td>
										
				<th nowrap>
					软件品牌
				</th>
				<td>
				    <input  id="hr_soft_name" type="text"
						style="display:none" value="${xx.HR_SOFT_NAME}">
				    <select id="hr_name"  disabled="disabled" style="width:150;height:20">   
                        <option value="-1">未采集</option>                                          
                        <option value="0">oracle</option> 
                        <option value="1">sap</option> 
                        <option value="2">Ibm</option> 
                        <option value="3">用友</option> 
                        <option value="4">金蝶</option> 
                        <option value="5">航信</option> 
                        <option value="6">浪潮</option> 
                        <option value="7">速达</option> 
                        <option value="8">鼎捷</option> 
                        <option value="9">金和</option> 
                        <option value="10">神码</option> 
                        <option value="11">新中大</option> 
                        <option value="12">天思</option> 
                        <option value="13">天心</option> 
                        <option value="14">72</option> 
                        <option value="15">八百客</option> 
                        <option value="16">金算盘</option> 
                        <option value="17">其他</option>                     
                    </select>  
				</td>	
				
			
			</tr>
			
		
			
					
		</table>
		
		

	</div>
	<div id="qtsbTab" title="财务设备" style="height:300"> 

		
		<table id="iList" cellspacing="0" cellpadding="0">  
        <thead>  
        <tr>  
            <th field="def_type" width="240" align='center '>设备类型</th>
            <th field="def_dcount" width="240" align='center '>数量</th>  
            <th field="def_date" width=240" align='center '>使用年限</th>  
            
              
        </tr>  
        </thead>  
        </table>

	</div>
	<div id="fwjlTab"  title="服务记录" style=""> 
		<table id="khList" cellspacing="0" cellpadding="0">  
    <thead>  
        <tr>  
            <th field="cust_name" width="240" align='center '>客户名称</th>
            <th field="emp_name" width="160" align='center '>服务员工</th>  
            <th field="SERVICE_TYPE_NAME" width="160" align='center '>服务类型</th>  
            <th field="dis_date" width="160" align='center '>服务时间</th> 
            
              
        </tr>  
    </thead>  
</table>
	</div>
	<div title="销售记录" style=""> 
	</div>
	</div>

