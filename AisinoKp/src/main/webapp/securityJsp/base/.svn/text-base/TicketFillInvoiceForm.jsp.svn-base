<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	String type =  request.getParameter("type");
	if (type==null){
		type="1";
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
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		url = sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_saveInvoice.sy';
		$.post(url, sy.serializeObject($('form')), function(result) {

			if (result.success) {
				
				//$pjq.messager.alert('提示', result.msg, 'info');
				parent.sy.progressBar('close');//关闭上传进度条
				alert("保存成功！现在转入打印界面");
				var url=sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_PrintFP.sy?fpid=' + result.obj.id;
				//window.open(url,'发票打印','height=550, width=850, top='+height+', left='+width+',toolbar=no');
				window.open(url);
				//window.location.href=sy.contextPath + '/securityJsp/base/TicketFillInvoiceForm.jsp?id=' + result.obj.id;
				//$grid.datagrid('load');
				
				$dialog.dialog('destroy');
				$grid.datagrid('load');
				
			} else {
				
				parent.sy.progressBar('close');//关闭上传进度条
				$pjq.messager.alert('提示', result.msg, 'error');
				
			}
			
		}, 'json');
		
	};
	var submitForm = function($dialog, $grid, $pjq) {
		var blue_bill_id = $("#blue_bill_id").val();
		var blue_reason = $("#blue_reason").val();
		var sum =  $("#sum").val();
		if(blue_bill_id !="" && blue_reason ==""){
			alert("红冲原因必须填写");
			$("#blue_reason").focus();
			return;
		}
		if(blue_bill_id =="" && sum <0){
			alert("没有找到对应的蓝字订单，请通过界面上的发票红冲按钮进行红冲！");
			return;
		}
		parent.$.messager.progress({
			text : '数据处理中....'
		});
		if(is_click == 0) {
			if ($('form').form('validate')) {
				is_click = 1;
				submitNow($dialog, $grid, $pjq);
				is_click = 0;
			}
		} else {
			
		}
	};
	
	var dy = function($dialog, $grid, $pjq) {
		var url=sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_PrintFP.sy?fpid=' + $("#id").val();
		window.open(url);
	};
	
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '发票修改',
			width : 1000,
			height:550,
			url : sy.contextPath
					+ '/securityJsp/base/TicketFillInvoiceForm.jsp?id='+id,
			buttons : [ {
				text : '保存',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			},
			{
				text : '打印',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.dy(
							dialog, grid, parent.$);
				}
			}
			
			]
		});
	};
	
	var getFirstInvoiceFromName=function(name){
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$.post(sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_getFirstInvoiceNumber.sy', {type_id:name}, 
				function(result) {
			if (result.ID != undefined) {
				$('form').form('load', {
					'invoiceHead.fp_id' : result.ID,
					'invoiceHead.invoice_type' : result.FPDM,
					'invoiceHead.invoice_code' : pad(result.FPHM,8)
				});
			} else{
				parent.$.messager.alert('提示','没有票了，请重新领取票！','error');
			}
			parent.$.messager.progress('close');
		}, 'json');
	};
	
	function pad(num, n) {  
	    var len = num.toString().length;  
	    while(len < n) {  
	        num = "0" + num;  
	        len++;  
	    }  
	    return num;  
	}
	
	function setnull(num){
		if(num == undefined){
			return "" ;
		} else{
			return num;
		}
	}

	$(function() {
        var cust_name="";
        var tax_type="";
        var typeid = $("#type").val();
		var myDate = new Date();
		var dateStr=myDate.getMonth()+1+"月30日";
		//$("#ssq").val(dateStr);
		   
			if ($(':input[name="invoiceHead.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/base/ticket-store!doNotNeedSecurity_getOpenAllInfoById.sy', {
				head_id : $(':input[name="invoiceHead.id"]').val(),type:typeid
			}, function(result) {
			   count = result.list.length;
			  
				for(var i=0;i<result.main.length;i++){
					if(typeid == "-1"){
						$("#blue_bill_id").val(result.main[i].ID);
						$("#htext").text("红冲单对应的蓝字单 发票代码：" + result.main[i].INVOICE_TYPE + ",发票号码：" + result.main[i].INVOICE_CODE);
						$("#htext").css({ "font-size": "12px" });
						$("#id").val("");
					} else {
						$("#invoice_type").val(result.main[i].INVOICE_TYPE);
						$("#invoice_code").val(result.main[i].INVOICE_CODE);
						$("#blue_bill_id").val(result.main[i].BLUE_BILL_ID);
						$("#blue_reason").val(result.main[i].BLUE_REASON);
						tax_type = result.main[i].TAX_TYPE;
						$("#id").val(result.main[i].ID);
					}
					
					$("#cust_id").val(result.main[i].CUST_ID);
					$("#fp_id").val(result.main[i].FP_ID);
					$("#total_amount").val(typeid * result.main[i].TOTAL_AMOUNT);
					$("#sum").val(typeid * result.main[i].TOTAL_AMOUNT);
					$("#ch_amount").val(result.main[i].CH_AMOUNT);
					$("#create_date").val(result.main[i].CREATE_DATE1);
					$("#creater").val(result.main[i].CREATER);
					$("#org_id").val(result.main[i].ORG_ID);
					$("#serial_number").val(result.main[i].SERIAL_NUMBER);
					$("#tax_type_id").val(result.main[i].TAX_TYPE_ID);
					$("#order_date").val(result.main[i].ORDER_DATE1);
					$("#update_date").val(result.main[i].UPDATE_DATE);
					$("#update_by").val(result.main[i].UPDATE_BY);
					$("#cust_code").val(result.main[i].CUST_CODE);
					$("#telphone").val(result.main[i].TELPHONE);
					$("#mobile").val(result.main[i].MOBILE);
					$("#addr").val(result.main[i].ADDR);
					//$("#tax_type").val(result.main[i].TAX_TYPE);
					cust_name = result.main[i].CUST_NAME;
					
					//$("#cust_name").combogrid('setValue',result.main[i].CUST_NAME);
					//$("#tax_type").combogrid('setValue',result.main[i].TAX_TYPE);
					
				}
				
				
				


                for(var j=0;j<result.list.length;j++){
                	targetObj=$(
                			'<tr id=tr_'+j+'>'+
                			'<td style="display:none"><input style="width:80px" id="invoicelist_'+j+'_id" name="invoicelist['+j+'].id" value="'+setnull(result.list[j].ID)+'" /></td>' +
                		    '<td style="display:none"><input style="width:80px" id="invoicelist_'+j+'_line_id" name="invoicelist['+j+'].line_id" value="'+setnull(result.list[j].LINE_ID)+'" /></td>' +
                		    '<td style="display:none"><input style="width:80px" id="invoicelist_'+j+'_product_id" name="invoicelist['+j+'].product_id" value="'+result.list[j].PRODUCT_ID+'" /></td>' +
                		    '<td style="display:none"><input style="width:80px" id="invoicelist_'+j+'_unit" name="invoicelist['+j+'].unit" value="'+result.list[j].UNIT+'" /></td>' +
                			'<td><input style="width:200px" id="invoicelist_'+j+'_product_name" name="invoicelist['+j+'].product_name"  value="'+result.list[j].PRODUCT_NAME+'" data-options="required:true" /></td>' +
                		//	'<td style="display:none"><input style="width:100px" id="invoicelist_'+j+'_product_code" name="invoicelist['+j+'].product_code" value="'+result.list[j].PRODUCT_CODE+'" /></td>' +
                			'<td style="display:none"><input style="width:60px" id="invoicelist_'+j+'_specy_type" name="invoicelist['+j+'].specy_type" value="'+result.list[j].SPECY_TYPE+'" /></td>' +
                			'<td><input style="width:60px" id="invoicelist_'+j+'_quantity" name="invoicelist['+j+'].quantity"  value="'+ typeid * result.list[j].QUANTITY+'" class="easyui-numberbox" onchange="cgrow('+j+')" data-options="required:true" /></td>' +
                			'<td><input style="width:60px" id="invoicelist_'+j+'_tax_type" name="invoicelist['+j+'].tax_type"  value="'+result.list[j].TAX_TYPE+'"   data-options="required:true" /></td>' +
                			'<td><input style="width:80px" id="invoicelist_'+j+'_unit_price" name="invoicelist['+j+'].unit_price" value="'+  result.list[j].UNIT_PRICE+'"   onchange="cgrow('+j+')"  data-options="required:true" /></td>' +
                			'<td><select id="invoicelist_'+j+'_tax_rate"  name="invoicelist['+j+'].tax_rate" value="'+result.list[j].TAX_RATE+'" style="width:70px">' + 
                			' <option value="0.17">17%</option> ' +
                			' <option value="0.13">13%</option> ' + 
                			' <option value="0.11">11%</option> ' + 
                			' <option value="0.06">6%</option> ' + 
                			' <option value="0.05">5%</option> ' + 
                			' <option value="0.04">4%</option> ' + 
                	        ' <option value="0.03">3%</option> ' +
                			' <option value="0">0%</option> ' +
                	        ' </select> </td>' + 
                			'<td><input style="width:80px" id="invoicelist_'+j+'_amount" name="invoicelist['+j+'].amount" value="'+ typeid * result.list[j].AMOUNT+'" readonly="readonly"  /></td>' +
                			'<td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="addrow()">新增</a> ' +
                			'<a href="javascript:void(0);" class="easyui-linkbutton" onclick="delrow('+j+')">删除</a></td>' +
                			'</tr>').appendTo("#gr2");
                	  
                	 $("#invoicelist_"+j + "_tax_rate").val(result.list[j].TAX_RATE);
                	 _bindComgrid(j);
                	$.parser.parse(targetObj);
                	_changePlusAmout();
    				
                }

				parent.$.messager.progress('close');
				
			}, 'json');
		} else {
			addrow();
		}
			
			$('#tax_type').combogrid({    
				valueField:'NAME',
		    	textField:'NAME',
		    	editable:false,
		    	width:310,
		    	url:'<%=contextPath%>/base/ticket-store!doNotNeedSessionAndSecurity_getFpName.sy',  
		    	columns:[[
		  				{field:'ID',title:'ID',width:60,hidden:true},
		  				{field:'NAME',title:'名称',width:270} 
		  			]],
		    	onLoadSuccess: function () {
		    		
		    		$('#tax_type').combogrid('setValue', tax_type);
		    		
		    	},
		    	onSelect: function(rowIndex, rowData){ 
		    		//$('#tax_type').val(rowData.NAME);
		    		$('#tax_type').combogrid('setValue', rowData.NAME);
		    		$('#tax_type_id').val(rowData.ID);
					getFirstInvoiceFromName(rowData.ID,rowData.NAME);
		        },
		        onClickRow:function(rowIndex, rowData){
		        	//$('#tax_type').val(rowData.NAME);
		        	$('#tax_type').combogrid('setValue', rowData.NAME);
		        	$('#tax_type_id').val(rowData.ID);
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
	
	
	function _bindComgrid(count){
		$("#invoicelist_"+count+"_product_name").combogrid({
			url: sy.contextPath + '/base/product-list!doNotNeedSecurity_getProductComList.sy',
			panelWidth:650,
			panelHeight:150,
		    idField: 'PRODUCT_NAME',
	        textField: 'PRODUCT_NAME',
	        fitColumns: true,  
	        striped: true,
	        mode:'remote',
	        editable: true,  
	        rownumbers: true,
	        collapsible: false,
	        fit: true,
			columns:[[   
				{field:'PRODUCT_NAME',title:'产品名称',width:350}, 
				{field:'TAX_PRICE',title:'含税单价',width:150}, 
				{field:'TAX_CATEGORY',title:'税种',width:150},    
				{field:'RATE',title:'税率',width:150}
			]],
			
			onHidePanel:function(){
			},
			onSelect:function(rowIndex, rowData){
				$("#invoicelist_"+count+"_product_name").val(rowData.PRODUCT_NAME);
				$("#invoicelist_"+count+"_product_number").val(rowData.PRODUCT_NUMBER);
				$("#invoicelist_"+count+"_tax_type").val(rowData.TAX_CATEGORY);
				$("#invoicelist_"+count+"_tax_rate").val(rowData.RATE);
				$("#invoicelist_"+count+"_unit_price").val(rowData.TAX_PRICE);
				$("#invoicelist_"+count+"_product_id").val(rowData.ID);
				$("#invoicelist_"+count+"_unit").val(rowData.UNIT);
				cgrow(count);
			},
	    	onClickRow:function(rowIndex, rowData){
	    		$("#invoicelist_"+count+"_product_name").val(rowData.PRODUCT_NAME);
				$("#invoicelist_"+count+"_product_number").val(rowData.PRODUCT_NUMBER);
				$("#invoicelist_"+count+"_tax_type").val(rowData.TAX_CATEGORY);
				$("#invoicelist_"+count+"_tax_rate").val(rowData.RATE);
				$("#invoicelist_"+count+"_unit_price").val(rowData.TAX_PRICE);
				$("#invoicelist_"+count+"_product_id").val(rowData.ID);
				$("#invoicelist_"+count+"_unit").val(rowData.UNIT);
				cgrow(count);
	    		
// 				$("#invoicelist_"+count+"_product_name").val(rowData.PRODUCT_NAME);
// 				$("#invoicelist_"+count+"_product_number").val(rowData.PRODUCT_NUMBER);
// 				$("#invoicelist_"+count+"_tax_type").val(rowData.TAX_CATEGORY);
// 				$("#invoicelist_"+count+"_tax_rate").val(rowData.RATE);
// 				$("#invoicelist_"+count+"_unit_price").val(rowData.TAX_PRICE);
// 				$("#invoicelist_"+count+"_product_id").val(rowData.ID);
				//$('#xssr').change();
	    	}
		});
	}
	
	var DX = function (n) {
		if (n != 0){
        if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
            return "数据非法";
        var unit = "仟佰拾亿仟佰拾万仟佰拾元角分", str = "";
            n += "00";
        var p = n.indexOf('.');
        if (p >= 0)
            n = n.substring(0, p) + n.substr(p+1, 2);
            unit = unit.substr(unit.length - n.length);
        for (var i=0; i < n.length; i++)
            str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
        return str.replace(/零(仟|佰|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
		}else {
		return "零元整"
			}
    	};
	

	

	
	var addrow=function()
	{
		var tablestr='<tr id=tr_'+count+'>'+
		'<td style="display:none"><input style="width:80px" id="invoicelist_'+count+'_id" name="invoicelist['+count+'].id" /></td>' +
	    '<td style="display:none"><input style="width:80px" id="invoicelist_'+count+'_line_id" name="invoicelist['+count+'].line_id" /></td>' +
	    '<td style="display:none"><input style="width:80px" id="invoicelist_'+count+'_product_id" name="invoicelist['+count+'].product_id" /></td>' +
	    '<td style="display:none"><input style="width:80px" id="invoicelist_'+count+'_unit" name="invoicelist['+count+'].unit" /></td>' +
		'<td><input style="width:200px" id="invoicelist_'+count+'_product_name" name="invoicelist['+count+'].product_name"  data-options="required:true" /></td>' +
		'<td style="display:none"><input style="width:100px" id="invoicelist_'+count+'_product_code" name="invoicelist['+count+'].product_code" /></td>' +
		'<td style="display:none"><input style="width:60px" id="invoicelist_'+count+'_specy_type" name="invoicelist['+count+'].specy_type"  /></td>' +
		'<td><input style="width:60px" id="invoicelist_'+count+'_quantity" name="invoicelist['+count+'].quantity"  class="easyui-numberbox" onchange="cgrow('+count+')" data-options="required:true" /></td>' +
		'<td><input style="width:60px" id="invoicelist_'+count+'_tax_type" name="invoicelist['+count+'].tax_type"     data-options="required:true" /></td>' +
		'<td><input style="width:80px" id="invoicelist_'+count+'_unit_price" name="invoicelist['+count+'].unit_price"  onchange="cgrow('+count+')"  data-options="required:true" /></td>' +
		//'<td><input style="width:60px" name="zysaleorderlist['+count+'].tax_code" class="easyui-numberbox" data-options="precision:2"/></td>' +
		'<td><select id="invoicelist_'+count+'_tax_rate"  name="invoicelist['+count+'].tax_rate" style="width:70px">' + 
		' <option value="0.17">17%</option> ' +
		' <option value="0.13">13%</option> ' + 
		' <option value="0.11">11%</option> ' + 
		' <option value="0.06">6%</option> ' + 
		' <option value="0.05">5%</option> ' + 
		' <option value="0.04">4%</option> ' + 
        ' <option value="0.03">3%</option> ' +
		' <option value="0">0%</option> ' +
        ' </select> </td>' + 
		'<td><input style="width:80px" id="invoicelist_'+count+'_amount" name="invoicelist['+count+'].amount"  readonly="readonly"  /></td>' +
		'<td><a href="javascript:void(0);"class="easyui-linkbutton" onclick="addrow()">新增</a> ' +
		'<a href="javascript:void(0);"class="easyui-linkbutton" onclick="delrow('+count+')">删除</a></td>' +
		'</tr>';
		var targetObj=$(tablestr).appendTo("#gr2");
		$.parser.parse(targetObj);
		_bindComgrid(count);
		count++;
	};
	
	
	function delrow(rowid){
		var tb = $("#gr2");
		var tl = tb.find("tr").length;
        if (tl < 3) {
        	alert("最后一行不允许删除！");
        	return;
        }
        var tline_id= $('#invoicelist_'+rowid+'_line_id').val();
        if(tline_id == ''){
        	$('#tr_'+rowid).remove();
        	_changePlusAmout();
        } else {
        	parent.$.messager.confirm('询问', '您确定要删除这行记录？', function(r) {
      	    if (r) {
	      		$.post(sy.contextPath + '/base/ticket-store!doNotNeedSecurity_deleteListById.sy', {
	      					line_id : tline_id
	      		}, function(result) {
	      			if (result.success) {
		      			$('#tr_'+rowid).remove();
		      			_changePlusAmout();
					} else {
						parent.$.messager.alert('提示', result.msg, 'error');
					}
	      		
	      			}, 'json');
	      		}
      		});
        	
        	
        }		
	}
	
	//控制行信息
	function cgrow(rowid) {
		$("#invoicelist_"+rowid +"_amount").val($("#invoicelist_" + rowid + "_quantity").val() * $("#invoicelist_" + rowid + '_unit_price').val());
		_changePlusAmout();
	}	//控制行信息
	
	function _changePlusAmout(){
		var total = 0;
		for (i=0;i<count;i++){
			 var a1 = $("#invoicelist_" + i + "_quantity").val();
			 var a2 = $("#invoicelist_" + i + "_unit_price").val();
			 var a3 = $("#invoicelist_" + i + "_amount").val(); 
			 if(a1 != undefined && a2 != undefined){
				 total = total +  a1 * a2;
				 if(a3 != a1 * a2){
					 $("#invoicelist_" + i + "_amount").val(a1 * a2);
				 }
			 } else {
		    }
		}
		$("#sum").val(total.toFixed(2));
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
			<input type="hidden" id="id" name="invoiceHead.id" class="transinput" value="<%=id%>" readonly="readonly" />
			<input type="hidden" id="type" name="type" class="transinput" value="<%=type%>" readonly="readonly" />
			<input type="hidden" id="cust_id" name="invoiceHead.cust_id" class="transinput"  readonly="readonly" />
			<input type="hidden" id="total_amount" name="invoiceHead.total_amount" class="transinput"  readonly="readonly" />
			<input type="hidden" id="ch_amount" name="invoiceHead.ch_amount" class="transinput"  readonly="readonly" />
			<input type="hidden" id="create_date" name="invoiceHead.create_date" class="transinput"  readonly="readonly" />
			<input type="hidden" id="blue_bill_id" name="invoiceHead.blue_bill_id" class="transinput"  readonly="readonly" />
			<input type="hidden" id="creater" name="invoiceHead.creater" class="transinput"  readonly="readonly" />
			<input type="hidden" id="org_id" name="invoiceHead.org_id" class="transinput"  readonly="readonly" />
			<input type="hidden" id="serial_number" name="invoiceHead.serial_number" class="transinput"  readonly="readonly" />
			<input type="hidden" id="tax_type_id" name="invoiceHead.tax_type_id" class="transinput"  readonly="readonly" />
			<input type="hidden" id="order_date" name="invoiceHead.order_date" class="transinput"  readonly="readonly" />
			<input type="hidden" id="update_date" name="invoiceHead.update_date" class="transinput"  readonly="readonly" />
			<input type="hidden" id="update_by" name="invoiceHead.update_by" class="transinput"  readonly="readonly" />
			<input type="hidden" id="fp_id" name="invoiceHead.fp_id" class="transinput"  readonly="readonly" />
			<table class="table" style="width: 100%;font-size:14px;">
				<tr>
					<th>发票种类：</th>
					<td><input  id= "tax_type" name="invoiceHead.tax_type" class="easyui-combobox"  ></input>
					</td>
				</tr>
				<tr>
					<th>发票代码</th>
					<td><input  id="invoice_type" name="invoiceHead.invoice_type" style="width:305px" class="easyui-validatebox"
					 data-options="required:true,missingMessage:'请先领用发票'"  readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>发票号码</th>
					<td><input id="invoice_code" name="invoiceHead.invoice_code" style="width:305px" class="easyui-validatebox" 
					data-options="required:true,missingMessage:'请先领用发票'" readonly="readonly" /></td>
				</tr>
			</table>
		</fieldset>
		<a class="easyui-linkbutton" id="htext" data-options="iconCls:'ext-icon-bullet_error',plain:true" >请确认发票号码、发票代码与票面上一致</a>
		<fieldset>
		<legend>新税票填开</legend>
		<div style="font-size:14px;color:#333;">
			<table class="table" style="width: 100%;font-size:14px;color:#333;">
				<tr>

					<th>客户名称</th>
					<td><input id="cust_name" name="invoiceHead.cust_name" class="easyui-combobox" data-options="required:true" /></td>					
					<th>客户税号</th>
					<td>
					<input id="cust_code" name="invoiceHead.cust_code" class="easyui-validatebox" />
					</td>
				</tr>
				<tr>
					<th>客户电话号码</th>
					<td><input id ="telphone" name="invoiceHead.telphone" class="easyui-validatebox" style = " width:380px;"   /></td>
					<th>客户手机号码</th>
					<td><input id="mobile" name="invoiceHead.mobile" class="easyui-validatebox"  /></td>
				</tr>
				<tr>
					<th>地址</th>
					<td><input id="addr"  style="width:380px" name="invoiceHead.addr" class="easyui-validatebox" /></td>
					<th>合计金额</th>
					<td><input id="sum"  style="width:150px" name="sum" class="easyui-validatebox" readonly/></td>
				</tr>
				<tr id="show">
				    <th>红冲原因</th>
					<td colspan="3"> 
						<input id="blue_reason" style="width:380px"  name="invoiceHead.blue_reason" ></input>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						保存购方信息<input id="savecusinfo" type="checkbox" name="savecusinfo" checked="checked"></input>
					</td>
				</tr>
			</table>
<!-- 			<div style="width:100%;text-align:right;"> -->
<!-- 			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-add',plain:true" onclick="addrow();">添加项目</a> -->
<!-- 			</div> -->
			<table id="gr2" class="table" style="width: 100%;font-size:14px;color:#333">
				<tr style="height:25px">
					<th style="width:200px;text-align:center">项目名称</th>	
					<th style="width:60px;text-align:center">数量</th>			
					<th style="width:80px;text-align:center">税种</th>
					<th style="width:60px;text-align:center">含税单价</th>
					<th style="width:60px;text-align:center">税率（%）</th>
					<th style="width:80px;text-align:center">金额</th>
					<th style="width:160px;text-align:center">操作</th>

				</tr>
<!-- 				<tr> -->
<!-- 					<td> -->
<!-- 					<input id="pmmc" name = "pmmc" type ="text" data-options="required:true" class="easyui-combogrid"/> -->
<!-- 					</td> -->
<!-- 					<td><input style="width:60px" name="kssl" class="easyui-validatebox" /></td> -->
<!-- 					<td><input style="width:80px" name="sz" id="sz" class="easyui-validatebox" readonly="readonly" /></td> -->
<!-- 					<td><input style="width:60px" id="tax_price" name="tax_price" readonly="readonly" data-options="precision:2,validType:'money'" /></td> -->

<!-- 					<td><input style="width:60px" id="xssr" name="xssr" class="easyui-numberbox" data-options="required:true,precision:2,validType:'money'" /></td>
<!-- 					--> 
<!-- 					<td><input style="width:60px" id="slv" name="slv" readonly = "readonly"  data-options="min:0,max:100,precision:2,required:true" /></td> -->
<!-- 					<td><input style="width:60px" id="ssq" name="ssq" class="easyui-validatebox" data-options="required:true" /></td>
<!-- 					<td><input style="width:60px" name="kce" class="easyui-numberbox" data-options="precision:2"/></td>--> 
<!-- 					<td><input  id="sjje" name="sjje" class="easyui-numberbox" data-options="required:true,precision:2,validType:'money'" /></td> -->
<!-- 					<td><input style="width:60px" id="dxje" name="dxje" style="450px" class="easyui-validatebox" /></td>
<!-- 					<td><input style="width:100%" id="bz" name="bz" class="easyui-validatebox" /></td>-->
<!-- 				</tr> -->

			</table>
		</div>
		</fieldset>
	</form>
	<div style="width:100%;font-size:12px;color:#aa0000;text-align:center">如果无法打印发票，请安装Adobe Reader</div>
  </body>
</html>
