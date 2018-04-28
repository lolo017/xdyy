<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%
	//String contextPath = request.getContextPath();
	//SecurityUtil securityUtil = new SecurityUtil(session);
	//String phone=request.getParameter("number");

%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.IOException"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="inc.jsp"></jsp:include>

<title>欢迎使用发票查询平台</title>
<script>

	var timeReceive;
	var timeReceiveCount = 20000;
	var flag = 1;
	
	function doFunction() {
		alert("三秒一遍！");
		timeReceive = setTimeout("doFunction()", timeReceiveCount);
	}

	function OnOrOffFunction() {
		alert(flag);
		if (flag == 1) {
			flag = -1;
			//clearTimeout(timeReceive);
			$.post(sy.contextPath
					+ '/base/lzkp/order!doNotNeedSecurity_setFlag.sy', {
				
			}, function(result) {
				if (result.success) {
					document.getElementById("btName").innerHTML = "<span class=\"l-btn-left\"><span class=\"l-btn-text ext-icon-package_add l-btn-icon-left l-btn-focus\">开启自动开票</span></span>";
				}

			}, 'json');

			
		} else {
			flag = 1;
			//doFunction();
			$.post(sy.contextPath
					+ '/base/lzkp/order!doNotNeedSecurity_setFlag.sy', {
				
			}, function(result) {
				if (result.success) {
					document.getElementById("btName").innerHTML = "<span class=\"l-btn-left\"><span class=\"l-btn-text ext-icon-package_add l-btn-icon-left l-btn-focus\">关闭自动开票</span></span>";
				}

			}, 'json');
			
		}
	}

	//timeReceive = setTimeout("doFunction()",timeReceiveCount);

	var grid;
	var viewOrder = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '订单明细',
			url : sy.contextPath + '/securityJsp/base/zjjk/ViewOder.jsp?id='
					+ id,
			height : 500,
			width : 800
		});
	};
	//图片预览
	function yl(pdf){
		  
                                         window.open('azyl.jsp?pdf='+pdf);		
		



		
		
		
		
	}
	

	//关闭预览
	function gb(){
		
		
	}
	//发送邮件
	function sendemail(pdf,id){
		var mail=$("#em").val();
		if(mail!=null&&mail!=""){
		$.post(sy.contextPath + '/base/mail!doNotNeedSessionAndSecurity_sendqqmail.sy', 
				{
					PDF:pdf,
					email:mail,
					Id:id
				},
			 function(result) {
					if(result.success){
					/* alert(id); */
						alert("发送邮件成功");
					}
					
				},'json');
		}else{
			alert("请输入邮箱");
		}
	}
	//加载发票信息
	$(function() {
		$("#order_bn").val(<%=request.getParameter("number")%>);
		grid = $('#grid')
				.datagrid(
						{
							title : '',
							url : sy.contextPath
									+ '/base/korder-list!doNotNeedSessionAndSecurity_fpxz.sy',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							idField : 'CSALEID',
							sortOrder : 'desc',
							pageSize : 20,
							
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [ {
								width : '100',
								title : 'pdf地址',
								field : 'pdf_mapper',
								sortable : true,
								hidden : true
							},
							{
								
								width : '300',
								title : 'id',
								field : 'ID',
								align : 'center',
								sortable : true,
								hidden : true
							},
							{
								title : '操作',
								field : 'action',
								width : '500',
								height: '50',
								align : 'center',
								formatter : function(value, row) {
									var str = '';
									str += sy.formatString(
													'<input type="button"  style="font-size:40px;"   value="预览/下载" onclick="yl(\'{0}\');"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
													row.pdf_mapper);
									str += sy.formatString(
											'<input type="button" style="font-size:40px;"  value="下载到邮箱" onclick="sendemail(\'{0}\',\'{1}\')"/>',
											row.pdf_mapper,row.ID);
									return str;
								}
							}, 
							{
								width : '120',
								title : '发票类型',
								field : 'TYPE',
								align : 'center',
								sortable : true
							}

							] ],
							columns : [ [ {
										width : '300',
										title : '开票时间',
										field : 'KPRQ',
										align : 'center',
										sortable : true
									},
									
									
									 ] ],
							toolbar : '#toolbar',
							onBeforeLoad : function(param) {
								parent.$.messager.progress({
									text : '数据加载中....'
								});
							},
							onLoadSuccess : function(data) {
								$('.iconImg').attr('src', sy.pixel_0);
								parent.$.messager.progress('close');
							}
						});
	});
	//自动开票
	function autoKP(){
		$.post(sy.contextPath + '/base/zjjk/order!doNotNeedSecurity_findOneOrder.sy', 
			 function(result) {
						var order_id = result.order_id;
						if(order_id != null && order_id != "" && order_id != "null"){
							invoice(order_id);
						}
				},'json');
		
		timeReceive = setTimeout("autoKP()", timeReceiveCount);
	}
	
	function invoice(id){
		setTaxType(id);
	}
	
	//设置税收分类编码
	function setTaxType(id){
		$.post(sy.contextPath + '/base/zjjk/order!doNotNeedSecurity_setTaxType.sy', 
				{id:id},
			 function(result) {
					if(result.success){
					/* alert(id); */
						opencar(id);
					}
					
				},'json');
	}
	
	//打开金穗卡
	function opencar(id){
	      var a=new ActiveXObject("TaxCardX.GoldTax");
		  a.OpenCard();
		  /* alert(a.RetCode); */
		  if(a.RetCode=="1011" || a.RetCode=="3001"){
			 activex = a;
			 kp(id);
			}else{
			 alert("金税盘开启失败，错误信息："+a.RetMsg);
			}
	      
	}
	
	//发票关闭
	function closecar(){
		is_open=0;
		var a = activex;
		a.CloseCard();
		//alert(a.RetCode +"#"+ a.RetMsg);
	}
	
	//发票信息查询
	function queryInv(InfoKind,InfoTypeCode,InfoNumber,order_id){
		activex.InfoKind = InfoKind;
		activex.InfoTypeCode = InfoTypeCode;
		activex.InfoNumber = InfoNumber;
		activex.QryInv();
		
		if (activex.RetCode == "7011"){//查询成功
			$.post(sy.contextPath + '/base/zjjk/order!doNotNeedSecurity_saveInvoice.sy', 
				{invoice_number:InfoNumber,
				 invoice_code:InfoTypeCode,
				 invoice_type:InfoKind,
				 tax_amount:activex.InfoTaxAmount,
				 amount:activex.InfoAmount,
				 xml:activex.info,
				 order_id : order_id
				}, function(result) {
					//alert(result.success);
						if(result.success){
							closecar();
							grid.datagrid('reload');
						}
						
			}, 'json');
		}
	}
	
	function kp(id){
		$.post(sy.contextPath + '/base/zjjk/order!doNotNeedSecurity_findOrder.sy', 
				{id:id},
			 function(result) {
					
		    if(result.headList.RED_INVOICE_CODE != null && result.headList.RED_INVOICE_CODE != '' &&
		    		result.headList.RED_INVOICE_NUMBER != null && result.headList.RED_INVOICE_NUMBER != ''){
		    	//如果是红票，那么直接去查询并写入数据库。
		    	queryInv(result.headList.INVOICE_TYPE,result.headList.RED_INVOICE_CODE,result.headList.RED_INVOICE_NUMBER,result.headList.CSALEID);
		    }else{
					
			var gfdzdh ="";
			if(result.headList.CUST_ADDR != null){
				gfdzdh = gfdzdh + result.headList.CUST_ADDR;
			}
			
			if(result.headList.TEL != null){
				gfdzdh = gfdzdh +"."+ result.headList.TEL;
			}
			
			if(result.headList.MOBILE != null){
				gfdzdh = gfdzdh +"."+ result.headList.MOBILE;
			}
			var xfdzdh = "";
			if(result.companyList.ADDR != null){
				xfdzdh = xfdzdh + result.companyList.ADDR;
			}
			
			if(result.companyList.TEL != null){
				xfdzdh = xfdzdh +"."+ result.companyList.TEL;
			}
			
			if(result.companyList.MOBILE != null){
				xfdzdh = xfdzdh +"."+ result.companyList.MOBILE;
			}
			var kpr = "";
			var fhr = "";
			var skr = "";
			var notes = "";
			if(result.companyList.KP_PERSON != null){
				kpr = result.companyList.KP_PERSON;
			}
			
			if(result.companyList.CHECK_PERSON != null){
				fhr = result.companyList.CHECK_PERSON;
			}
			
			if(result.companyList.RECEIPT_PERSON != null){
				skr = result.companyList.RECEIPT_PERSON;
			}
			
			if(result.headList.NOTES != null){
				notes = result.headList.NOTES;
			}
			
			var a = activex;
			a.InvInfoInit();
			var KPInfoKind = result.headList.INVOICE_TYPE;//发票种类（0增值税专用发票;1废旧物资发票;2增值税普通发票）
	        a.InfoKind=KPInfoKind;
			a.InfoClientName=result.headList.CUSTNAME;  //购方名称
			a.InfoClientTaxCode=result.headList.CUSTCODE; //购方税号
			a.InfoClientBankAccount=result.headList.BANKDOCNAME+"."+result.headList.ACCOUNT; //购方开户行及账号
			a.InfoClientAddressPhone=gfdzdh;  //购方地址电话
			a.InfoSellerBankAccount=result.companyList.BANK+"."+result.companyList.BANK_NO;//销方开户行及账号
			a.InfoSellerAddressPhone=xfdzdh;//销售方地址电话
			//a.InfoTaxRate = 17;//税率（已授权的税率，17%传17）
			a.InfoInvoicer=kpr;//开票人
			a.InfoChecker=fhr;//复核人  可为空
			a.InfoCashier=skr;//收款人  可为空
			a.InfoNotes = notes;//备注
			
			//发票明细信息
		    a.ClearInvList();
			

			//多行重复使用的数据

			a.InvListInit();//循环插入费用项目列表与a.AddInvList()一起使用
		    for(var i=0;i<result.itemsList.length;i++){
		    	a.ListGoodsName =result.itemsList[i].INVNAME;  ////商品名称
		 	    a.ListStandard =result.itemsList[i].INVSPEC;  //商品规格
		 	    a.ListUnit =result.itemsList[i].UNIT;          //商品单位
		 	  	a.ListNumber =result.itemsList[i].NNUMBER;      //商品数量
		 	  	a.ListPrice = result.itemsList[i].NTAXPRICE;       //商品单价(含税)
		 	  	//a.ListAmount 金额： 数量 单价 金额 如果设置了其中两项，另一项可以不传，由接口软件计算 如传入则就符合计算关系
		 	  	a.InfoTaxRate = result.itemsList[i].NTAXRATE;     //税率
		 	  	//a.ListTaxItem 税目 4位数数字，商品所属类别	
		 	  	//a.ListGoodsTaxNo = 109050901;
		 	  	a.ListPriceKind ="1";//含税价标志 0为不含税价，1为含税价
		 	  	//a.ListTaxAmount 税额  可以不传 由接口软件计算，如传入则就符合计算关系
		 		a.AddInvList();
		    }

			//传入开票
		   a.Invoice();//传入开票数据
		   
		   
		   
		   //开票成功后返回代码为 4011，然后通过发票代码，发票号码把此发发票查询出来存到数据库
		   if(a.RetCode=="4011"){			   
			   queryInv(KPInfoKind,a.InfoTypeCode,a.InfoNumber,result.headList.CSALEID);
			}else{
				//记录开票错误日志
				$.post(sy.contextPath + '/base/zjjk/order!doNotNeedSecurity_saveErrorLog.sy', 
						{error_code:a.RetCOde,
						 error_msg:a.RetMsg,
						 order_id : id
						}, function(result) {
								
					}, 'json');
			 a.CloseCard();
			}
		  		
				}//开正票
				
				}, 'json');	
		
	}
</script>
</head>

<body onload="" class="easyui-layout"
	data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<form id="searchForm">
			<table style="font-size:50px;">
				<tr>	
					<td><input value="1" name="phone" id="order_bn" style="width: 100px;display:none" /></td>
					<td>邮箱:</td><td><input value="" name="email" id="em" style="width: 150px;height:50px;" /></td>
					<td> 
					
					</td>
					<td><a style="font-size:50px;"  href="javascript:void(0);" class=""
						data-options="iconCls:'ext-icon-zoom',plain:true"
						onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">&nbsp;&nbsp;&nbsp;查询</a>
						<!-- <a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom_out',plain:true"
						onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置列表</a> -->
					</td>
				</tr>
				<!--  
				<tr>
					<td colspan="7"><a id="btName" href="javascript:void(0);"
						class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-package_add'"
						onclick="OnOrOffFunction()">关闭自动开票</a></td>
				</tr>
				-->
			</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table style="font-size:30px; border-spacing:   30px" id="grid" data-options="fit:true,border:false"></table>
	</div>
	
</body>
</html>
