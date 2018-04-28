<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("doors_id");
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
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		url = sy.contextPath + '/base/doors-information!doNotNeedSessionAndSecurity_saveDoor.sy';
		$.post(url, sy.serializeObject($('form')), function(result) {

			if (result.success) {
				//$dialog.dialog('destroy');
				$grid.datagrid('load');
				var url= sy.contextPath + '/securityJsp/base/DoorsInformationManage.jsp?doors_id=' + result.obj;
				self.location.href=url;
				$pjq.messager.alert('提示', result.msg, 'info');
				parent.$.messager.progress('close');
				
			} else {
				
				parent.$.messager.progress('close');
				$pjq.messager.alert('提示', result.msg, 'error');
				
			}
			
		}, 'json');
		
	};
	var submitForm = function($dialog, $grid, $pjq) {
		parent.$.messager.progress({
			text : '数据处理中....'
		});
		if ($('form').form('validate')) {
			submitNow($dialog, $grid, $pjq);
		}else{
			parent.$.messager.progress('close');//关闭上传进度条
		}
	};
	
	var dy = function($dialog, $grid, $pjq) {
		var url=sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_PrintFP.sy?fpid=' + $("#id").val();
		window.open(url);
	};
	
	
	
	function setnull(num){
		if(num == undefined){
			return "" ;
		} else{
			return num;
		}
	}

	$(function() {
        var cust_name="";
		   
			if ($(':input[name="doorM.doors_id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/base/doors-information!doNotNeedSecurity_getOpenAllInfoById.sy', {
				doors_id : $(':input[name="doorM.doors_id"]').val()
			}, function(result) {
			   count = result.list.length;
			  
				for(var i=0;i<result.main.length;i++){
					$("#doors_id").val(result.main[i].DOORS_ID);
					$("#doors_name").val(result.main[i].DOORS_NAME);
					$("#doors_addrss").val(result.main[i].DOORS_ADDRSS);
					$("#classfy_id").val(result.main[i].CLASSFY_ID);
					$("#doors_status").val(result.main[i].DOORS_STATUS);
					//$("#create_date").val(result.main[i].CREATE_DATE1);
					$("#create_date").datebox('setValue', result.main[i].CREATE_DATE1);
					$("#create_by").val(result.main[i].CREATE_BY);
					$("#classfy_name").combogrid('setValue',result.main[i].CLASSFY_ID_NAME);
				}
				
                for(var j=0;j<result.list.length;j++){
                
                	targetObj=$(
                			'<tr id=tr_'+j+'>'+
                			'<td style="display:none"><input style="width:80px" id="doorD_'+j+'_doors_id" name="doorD['+j+'].doors_id" value="'+setnull(result.list[j].DOORS_ID)+'" /></td>' +
                		    '<td style="display:none"><input style="width:80px" id="doorD_'+j+'_doors_line_id" name="doorD['+j+'].doors_line_id" value="'+setnull(result.list[j].DOORS_LINE_ID)+'" /></td>' +
                		    '<td style="display:none"><input style="width:80px" id="doorD_'+j+'_create_by" name="doorD['+j+'].create_by" value="'+result.list[j].CREATE_BY+'" /></td>' +
                		    '<td style="display:none"><input style="width:80px" id="doorD_'+j+'_create_date" name="doorD['+j+'].create_date" value="'+result.list[j].CREATE_DATE+'" /></td>' +
                			'<td><select style="width:97%" id="doorD_'+j+'_doors_floor" name="doorD['+j+'].doors_floor"  value="'+result.list[j].DOORS_FLOOR+'">' +
                			' <option value="1">一楼</option> ' +
                			' <option value="2">二楼</option> ' + 
                			' <option value="3">三楼</option> ' + 
                			' <option value="4">四楼</option> ' + 
                			' <option value="5">五楼</option> ' + 
                			' <option value="6">六楼</option> ' + 
                			' <option value="all">整栋</option> ' + 
                	        ' </select></td>' + 
                			'<td><input style="width:97%" id="doorD_'+j+'_doors_size" name="doorD['+j+'].doors_size" value="'+result.list[j].DOORS_SIZE+'" /></td>' +
                			'<td><input style="width:97%" id="doorD_'+j+'_doors_rent" name="doorD['+j+'].doors_rent"  value="'+result.list[j].DOORS_RENT+'"   data-options="required:true" /></td>' +
                			'<td><select id="doorD_'+j+'_floor_status" class="easyui-combobox"  name="doorD['+j+'].floor_status" readonly="readonly" value="'+result.list[j].FLOOR_STATUS+'" style="width:97%">' + 
                			' <option value="1">闲置</option> ' +
                			' <option value="2">在租</option> ' + 
                			' <option value="3">转让</option> ' + 
                	        ' </select> </td>' + 
                			'<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'ext-icon-note_add\',plain:true"  title="增行" onclick="addrow()"></a> ' +
                			'<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'ext-icon-note_delete\',plain:true"  title="删行"  onclick="delrow('+j+')"></a></td>' +
                			'</tr>').appendTo("#gr2");
                	$("#doorD_"+j + "_doors_floor").val(result.list[j].DOORS_FLOOR);
                	$("#doorD_"+j + "_floor_status").val(result.list[j].FLOOR_STATUS);
                	$.parser.parse(targetObj);
                }
				parent.$.messager.progress('close');
				
			}, 'json');
		} else {
			addrow();
		}
			
			$('#classfy_name').combogrid({    
				valueField:'CLASSFY_ID',
		    	textField:'CLASSFY_NAME',
		    	editable:false,
		    	url:'<%=contextPath%>/base/doors-information!doNotNeedSessionAndSecurity_getClassfy.sy',  
		    	columns:[[
		  				{field:'CLASSFY_ID',title:'序列',width:60,hidden:true},
		  				{field:'CLASSFY_NAME',title:'名称',width:150} 
		  			]],
		    	onLoadSuccess: function () {
		    		
		    		//$('#classfy_id').combogrid('setValue', tax_type);
		    		
		    	},
		    	onSelect: function(rowIndex, rowData){ 
		    		$('#classfy_id').val(rowData.CLASSFY_ID);
		        },
		        onClickRow:function(rowIndex, rowData){
		        	//$('#classfy_id').val(rowData.CLASSFY_ID);
		        }
			});  

		$("#cust_name").combogrid({
			url: sy.contextPath + '/base/ticket-customer!doNotNeedSessionAndSecurity_queryCus.sy',
			panelWidth:580,
		    idField: 'NAME',
		    width:385,
	        textField: 'NAME',
	        fitColumns: true,  
	        striped: true,
	        mode:'remote',
	        editable: true,  
	        rownumbers: true,
	        collapsible: false,
	        fit: true,
			columns:[[
				{field:'ID',title:'ID',width:60,hidden:true},
				{field:'NAME',title:'名称',width:480},    
				{field:'TAXCODE',title:'税号',width:100},    
				{field:'DZDH',title:'DZDH',width:150,hidden:true}
			]],
			onChange:function(newValue,oldValue){
				//$("#cust_name").val(oldValue);
			},
			onHidePanel:function(){
			},
			onLoadSuccess: function () {
	    		if(cust_name!=''){
				$("#cust_name").combogrid('setValue',cust_name);
	    		}
	    		
	    	},
	    	onSelect:function(rowIndex, rowData){
	    		$('#cust_code').val(rowData.TAXCODE);
		    	$('#cust_id').val(rowData.ID);
				$('#cust_name').val(rowData.NAME);
				$('#addr').val(rowData.DZDH);
				$('#telphone').val(rowData.TELPHONE);
				$('#mobile').val(rowData.MOBILE);
			},
	    	onClickRow:function(rowIndex, rowData){
		    	$('#cust_code').val(rowData.TAXCODE);
		    	$('#cust_id').val(rowData.ID);
				$('#cust_name').val(rowData.NAME);
				$('#addr').val(rowData.DZDH);
				$('#telphone').val(rowData.TELPHONE);
				$('#mobile').val(rowData.MOBILE);
				//$('#gfmc').focus();
				//$('#savecusinfo').attr("checked",false);
	    	}
		});
		
		//$("#tax_type").combogrid('setValue',tax_type);
		$('#tax_type').combogrid('setValue', tax_type);
		$("#cust_name").combogrid('setValue',cust_name);
	});
	
	
	var addrow=function()
	{
		var tablestr='<tr id=tr_'+count+'>'+
		'<td style="display:none"><input style="width:80px" id="doorD_'+count+'_doors_id" name="doorD['+count+'].doors_id" /></td>' +
	    '<td style="display:none"><input style="width:80px" id="doorD_'+count+'_doors_line_id" name="doorD['+count+'].doors_line_id" /></td>' +
	    '<td style="display:none"><input style="width:80px" id="doorD_'+count+'_create_by" name="doorD['+count+'].create_by" /></td>' +
	    '<td style="display:none"><input style="width:80px" id="doorD_'+count+'_create_date" name="doorD['+count+'].create_date" /></td>' +
		'<td><select style="width:97%" id="doorD_'+count+'_doors_floor" name="doorD['+count+'].doors_floor" readonly  data-options="required:true" >' +
		' <option value="1">一楼</option> ' +
		' <option value="2">二楼</option> ' + 
		' <option value="3">三楼</option> ' + 
		' <option value="4">四楼</option> ' + 
		' <option value="5">五楼</option> ' + 
		' <option value="6">六楼</option> ' + 
		' <option value="all">整栋</option> ' + 
        ' </select></td>' + 
		'<td><input style="width:97%" id="doorD_'+count+'_doors_size" name="doorD['+count+'].doors_size" /></td>' +
		'<td><input style="width:97%" id="doorD_'+count+'_doors_rent" name="doorD['+count+'].doors_rent"   data-options="required:true" /></td>' +
		'<td><select id="doorD_'+count+'_floor_status"  name="doorD['+count+'].floor_status" readonly="readonly" class="easyui-combobox"  style="width:97%">' + 
		' <option value="1">闲置</option> ' +
		' <option value="2">在租</option> ' + 
		' <option value="3">转让</option> ' + 
        ' </select> </td>' + 
		'<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'ext-icon-note_add\',plain:true"  title="增行" onclick="addrow()"></a>' +
		'<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'ext-icon-note_delete\',plain:true"  title="删行"  onclick="delrow('+count+')"></a></td>' +
		'</tr>';
		var targetObj=$(tablestr).appendTo("#gr2");
		$.parser.parse(targetObj);
		count++;
	};
	
	
	function delrow(rowid){
		var tb = $("#gr2");
		var tl = tb.find("tr").length;
        if (tl < 3) {
        	alert("最后一行不允许删除！");
        	return;
        }
        var tline_id= $('#doorD_'+rowid+'_doors_line_id').val();
        if(tline_id == ''){
        	$('#tr_'+rowid).remove();
        } else {
        	parent.$.messager.confirm('询问', '您确定要删除这行记录？', function(r) {
      	    if (r) {
	      		$.post(sy.contextPath + '/base/doors-information!doNotNeedSecurity_deleteListById.sy', {
	      					line_id : tline_id
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
		<input type="hidden" id="doors_id" name="doorM.doors_id" class="transinput" value="<%=id%>" readonly="readonly" />
		<input type="hidden" id="create_by" name="doorM.create_by" class="transinput"  readonly="readonly" />
		<input type="hidden"  id="classfy_id" name="doorM.classfy_id" style="width:220px;" data-options="required:true" />
		
		<div style="font-size:14px;color:#333;">
			<table class="table" style="width: 100%;font-size:14px;color:#333;">
				<tr>
					<th style="width:80px;">门市分类</th>
					<td><input id="classfy_name" name="classfy_name" style="width:220px;" class="easyui-combobox" data-options="required:true" /></td>					
					<th style="width:80px;">门市名称</th>
					<td><input id="doors_name" name="doorM.doors_name" style="width:220px;"  class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>门市地址</th>
					<td colspan="3"><input id ="doors_addrss" name="doorM.doors_addrss" class="easyui-validatebox" style = " width:537px;"   /></td>
					
				</tr>
				<tr>
					<th>门市状态</th>
					<td>
					<select class="easyui-combobox" id="doors_status" name="doorM.doors_status" style="width:220px;" readonly>
                      <option value="1" selected>闲置</option>
                      <option value="2">在租</option>
                      <option value="3">转让</option>
                      </select>
					</td>
					<th>创建时间</th>
					<td><input id="create_date" style="width:220px;" style="width:150px" name="doorM.create_date" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"  readonly/></td>
				</tr>
				<tr id="show">
			</table>
<!-- 			<div style="width:100%;text-align:right;"> -->
<!-- 			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-add',plain:true" onclick="addrow();">添加项目</a> -->
<!-- 			</div> -->
			<table id="gr2" class="table" style="width: 100%;font-size:14px;color:#333">
				<tr style="height:25px">
					<th style="width:150px;text-align:center">楼层</th>	
					<th style="width:120px;text-align:center">面积</th>			
					<th style="width:120px;text-align:center">租金</th>
					<th style="width:80px;text-align:center">楼层状态</th>
					<th style="width:70px;text-align:center">操作</th>
				</tr>
			</table>
		</div>
		</fieldset>
	</form>
	
  </body>
</html>
