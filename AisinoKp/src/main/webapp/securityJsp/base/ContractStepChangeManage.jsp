<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("contract_line_id");
	if (id == null) {
		id = "";
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<jsp:include page="../../inc.jsp"></jsp:include>
    
    <title>My JSP 'FillInvoice.jsp' starting page</title>
<script type="text/javascript">
    var targetObj;
    var count=0;
    var is_click =0;
    //var width=Math.round((window.screen.width-850)/2); 
    //var height=Math.round((window.screen.height-550)/2);
	var submitNow = function($dialog) {
		var url;
		url = sy.contextPath + '/base/contract-info-main!doNotNeedSessionAndSecurity_saveStep.sy';
		$.post(url, sy.serializeObject($('form')), function(result) {

			if (result.success) {
				
				
				var url= sy.contextPath + '/securityJsp/base/ContractStepChangeManage.jsp?contract_line_id=' + result.obj;
				self.location.href=url;
				$.messager.alert('提示', result.msg, 'info');
				parent.$.messager.progress('close');
			} else {
				parent.$.messager.progress('close');
				$.messager.alert('提示', result.msg, 'error');
				
			}
			
		}, 'json');
		
	};
	var submitForm = function($dialog) {
		parent.$.messager.progress({
			text : '数据处理中....'
		});
	  
		if ($('form').form('validate')) {
		  submitNow($dialog);
		} else {
		  parent.$.messager.progress('close');
		}
		
	};
	

	

	
	
	function setnull(num){
		if(num == undefined){
			return "" ;
		} else{
			return num;
		}
	}

	$(function() {
		
		
			if ($('#contract_line_id').val().length > 0) {
				
			/**parent.$.messager.progress({
				text : '数据加载中....'
			});
			*/
			$.post(sy.contextPath + '/base/contract-info-main!doNotNeedSecurity_getStepChargeByid.sy', {
				contract_line_id :$('#contract_line_id').val()
			}, function(result) {
			   count = result.list.length;
				for(var i=0;i<result.main.length;i++){
					$("#contract_id").val(setnull(result.main[i].CONTRACT_ID));
					$("#contract_line_id").val(setnull(result.main[i].CONTRACT_LINE_ID));
					$("#doors_name").val(result.main[i].DOORS_NAME);
					$("#classfy_name").val(result.main[i].CLASSFY_NAME);
					$("#doors_floor").val(result.main[i].DOORS_FLOOR);
					$("#doors_size").val(result.main[i].DOORS_SIZE);
					$("#doors_rent").val(result.main[i].DOORS_RENT);
					
					
					
					//$("#start_date").datebox('setValue', result.main[i].START_DATE);
					//$("#end_date").datebox('setValue', result.main[i].END_DATE);
				
				}
			    
				if(count==0) {
					addrow();
				}

                for(var j=0;j<result.list.length;j++){
                	targetObj=$(
                			'<tr id=tr_'+j+'>'+
                			'<td style="display:none"><input style="width:80px" id="stepchargelist_'+j+'_contract_id" name="stepchargelist['+j+'].contract_id" value="'+setnull(result.list[j].CONTRACT_ID)+'"/></td>' +
                		    '<td style="display:none"><input style="width:80px" id="stepchargelist_'+j+'_contract_line_id" name="stepchargelist['+j+'].contract_line_id" value="'+setnull(result.list[j].CONTRACT_LINE_ID)+'"/></td>' +
                		    '<td style="display:none"><input style="width:80px" id="stepchargelist_'+j+'_step_id" name="stepchargelist['+j+'].step_id" value="'+setnull(result.list[j].STEP_ID)+'"/></td>' +
                		    '<td style="display:none"><input style="width:80px" id="stepchargelist_'+j+'_create_by" name="stepchargelist['+j+'].create_by" value="'+setnull(result.list[j].CREATE_BY)+'"/></td>' +
                		    '<td style="display:none"><input style="width:80px" id="stepchargelist_'+j+'_create_date" name="stepchargelist['+j+'].create_date" value="'+setnull(result.list[j].CREATE_DATE)+'"/></td>' +
                			'<td><input style="width:100%" id="stepchargelist_'+j+'_start_date" name="stepchargelist['+j+'].start_date" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser,required:true" value="'+setnull(result.list[j].START_DATE)+'" /></td>' +
                			'<td><input style="width:100%" id="stepchargelist_'+j+'_end_date" name="stepchargelist['+j+'].end_date" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser,required:true"  value="'+setnull(result.list[j].END_DATE)+'" /></td>' +
                			'<td><select id="stepchargelist_'+j+'_cal_type"  name="stepchargelist['+j+'].cal_type" value="'+setnull(result.list[j].CAL_TYPE)+'" style="width:100%">' + 
                			'<option value="1">按总金额</option> ' +
                			'<option value="2">按单价</option> ' + 
                	        '</select> </td>' + 
                	        '<td><input style="width:98%" id="stepchargelist_'+j+'_doors_rent" name="stepchargelist['+j+'].doors_rent" value="'+setnull(result.list[j].DOORS_RENT)+'"  data-options="required:true" /></td>' +
                			'<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'ext-icon-note_add\',plain:true" title="增行" onclick="addrow()"></a> ' +
                			'<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'ext-icon-note_delete\',plain:true" title="删行" onclick="delrow('+j+')"></a></td>' +
                			'</tr>').appendTo("#gr2");
                	    $("#stepchargelist_"+j + "_cal_type").val(result.list[j].CAL_TYPE);
                	$.parser.parse(targetObj);
                }
				parent.$.messager.progress('close');
				
			}, 'json');
		} 
			
	});
	

	
	var addrow=function()
	{
		
		var tablestr='<tr id=tr_'+count+'>'+
		'<td style="display:none"><input style="width:80px" id="stepchargelist_'+count+'_contract_id" name="stepchargelist['+count+'].contract_id" /></td>' +
	    '<td style="display:none"><input style="width:80px" id="stepchargelist_'+count+'_contract_line_id" name="stepchargelist['+count+'].contract_line_id" /></td>' +
	    '<td style="display:none"><input style="width:80px" id="stepchargelist_'+count+'_step_id" name="stepchargelist['+count+'].step_id" /></td>' +
	    '<td style="display:none"><input style="width:80px" id="stepchargelist_'+count+'_create_by" name="stepchargelist['+count+'].create_by" /></td>' +
	    '<td style="display:none"><input style="width:80px" id="stepchargelist_'+count+'_create_date" name="stepchargelist['+count+'].create_date" /></td>' +
		'<td><input style="width:100%" id="stepchargelist_'+count+'_start_date" name="stepchargelist['+count+'].start_date" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser,required:true"  /></td>' +
		'<td><input style="width:100%" id="stepchargelist_'+count+'_end_date" name="stepchargelist['+count+'].end_date" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser,required:true" /></td>' +
		'<td><select id="stepchargelist_'+count+'_cal_type"  name="stepchargelist['+count+'].cal_type"  style="width:100%">' + 
		'<option value="1">按总金额</option> ' +
		'<option value="2">按单价</option> ' + 
        '</select> </td>' + 
        '<td><input style="width:98%" id="stepchargelist_'+count+'_doors_rent" name="stepchargelist['+count+'].doors_rent"   data-options="required:true" /></td>' +
		'<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'ext-icon-note_add\',plain:true" title="增行" onclick="addrow()"></a> ' +
		'<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'ext-icon-note_delete\',plain:true" title="删行" onclick="delrow('+count+')"></a></td>' +
		'</tr>';
		
		var targetObj=$(tablestr).appendTo("#gr2");
		$.parser.parse(targetObj);
		count++;
	};
	
	//控制行信息


	function delrow(rowid){
		var tb = $("#gr2");
		var tl = tb.find("tr").length;
        if (tl < 3) {
        	//alert("最后一行不允许删除！");
        	//return;
        }
        var tline_id= $('#stepchargelist_'+rowid+'_step_id').val();
        if(tline_id == ''){
        	$('#tr_'+rowid).remove();
        } else {
        	parent.$.messager.confirm('询问', '您确定要删除这行记录？', function(r) {
      	    if (r) {
	      		$.post(sy.contextPath + '/base/contract-info-main!doNotNeedSecurity_deleteStepById.sy', {
	      			step_id : tline_id
	      		}, function(result) {
	      			if (result.success) {
		      			$('#tr_'+rowid).remove();
					} else {
						parent.$.messager.alert('提示', result.msg, 'error');
					}
	      		
	      			}, 'json');
	      		}
      		});
        	
        	
        }		
	}
	

    function myformatter(date){
	    var y = date.getFullYear();
	    var m = date.getMonth()+1;
        var d = date.getDate();
        return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	  }
    
	function myparser(s){
		 if (!s) return new Date();
		    var ss = (s.split('-'));
		    var y = parseInt(ss[0],10);
		    var m = parseInt(ss[1],10);
		    var d = parseInt(ss[2],10);
		 if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		       return new Date(y,m-1,d);
		   } else {
		       return new Date();
		 }
	}

    </script>
    <style>
    .transinput{
    	border:0px solid #c00;
    }
    </style>
  </head>
  
  <body  class="easyui-layout" data-options="fit:true,border:false">
    <form method="post" class="form">
    <fieldset>
		<input type="hidden" id="contract_id" name="contract_id" class="transinput"  readonly="readonly" />
		<input type="hidden" id="contract_line_id" name="contract_line_id" class="transinput" value="<%=id%>" readonly="readonly" />
		
		<legend>阶段收费设置</legend>
		<div style="font-size:14px;color:#333;">
			<table class="table" style="width: 100%;font-size:14px;color:#333;">
				<tr>
					<th style="width:80px;">门市名称</th>
					<td><input id="doors_name" name="doors_name"  style="width:100%;" readonly/></td>					
					<th style="width:80px;">门市分类</th>
					<td><input id="classfy_name" name="classfy_name" style="width:100%;" class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<th style="width:80px;">楼层</th>
					<td><select style="width:98%" id="doors_floor" name="doors_floor"   data-options="required:true" >
                		<option value="1">一楼</option> 
                		<option value="2">二楼</option> 
                	    <option value="3">三楼</option> 
                		<option value="4">四楼</option> 
                		<option value="5">五楼</option> 
                		<option value="6">六楼</option> 
                		<option value="all">整栋</option> 
                	    </select>
					</td>
										
					<th style="width:80px;">面积</th>
					<td><input id="doors_size" name="doors_size" style="width:100%;" class="easyui-validatebox" /></td>
					<th style="width:80px;">单价</th>
					<td><input id="doors_rent" name="doors_rent" style="width:100%;" class="easyui-validatebox" /></td>
				</tr>
			
			</table>
			<table id="gr2" class="table" style="width: 100%;font-size:14px;color:#333">
				<tr style="height:25px">
					<th style="width:80px;text-align:center">开始时间</th>	
					<th style="width:80px;text-align:center">结束时间</th>			
					<th style="width:80px;text-align:center">计算方式</th>
					<th style="width:80px;text-align:center">租金</th>
					<th style="width:60px;text-align:center">操作</th>
				</tr>
			</table>
		</div>
		</fieldset>
	</form>
	
  </body>
</html>
