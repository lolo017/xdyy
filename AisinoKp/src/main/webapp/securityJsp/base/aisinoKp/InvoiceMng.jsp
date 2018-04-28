<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%@ page import="aisino.reportform.model.base.SessionInfo"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	//UserInfo userInfo = (UserInfo) application.getAttribute("userInfo");
	SessionInfo sessionInfo = (SessionInfo) session
			.getAttribute("sessionInfo");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="../../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;	//显示列表
	var dialog;	//对话框
	var is_open = 0;	//金税盘开启状态
	var activex;	//金税盘实例
	var ssContent;	//税收分类编码设置
	var loadflag=0;	//页面grid加载标志
	
	$(function() {
		
		//grid加载数据
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath
					+ '/base/fpmng/invoice!getInvoiceGrid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			queryParams:{flag:loadflag},
			singleSelect : false,
			idField : 'OHID',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 5, 10, 15, 20, 30, 50, 100, 200, 300,
					500, 1000 ],
			frozenColumns : [ [
					{
						field : 'checkop',
						title : '<input id=\"checkAll\" type=\"checkbox\"  >',
						width : 30,
						formatter : function(value, row) {
							return "<input type=\"checkbox\"  name=\"check\" value=\"" + row.OHID + "\" >";
						}
					},
					{
						width : '40',
						title : '操作',
						field : 'action',
						align : 'center',
						formatter : function(value, row) {
							var str = '';
							<%if (securityUtil.havePermission("/base/fpmng/order!checkOrder")) {%>
								str += sy
									.formatString(
											'<img class="iconImg ext-icon-vcard" title="查看" onclick="checkOrder(\'{0}\');"/>',
											row.OHID);
 							<%}%>
							return str;
						}
					},
					{
						width : '100',
						title : '发票代码',
						field : 'FPDM',
						halign : 'center',
					},
					{
						width : '60',
						title : '发票号码',
						field : 'FPHM',
						halign : 'center',
					} ] ],
			columns : [ [  {
				width : '160',
				title : '单据号码',
				field : 'OHID',
				halign : 'center',
				//hidden : true
			}
			,{
				width : '150',
				title : '开票日期',
				field : 'CREATETIME',
				halign : 'center',
				sortable : true
			}, {
				width : '200',
				title : '购方名称',
				field : 'GFMC',
				halign : 'center',
			}, {
				width : '140',
				title : '购方税号',
				field : 'GFSH',
				halign : 'center'
			}, /* {
				width : '60',
				title : '开票人',
				field : 'KPR',
				align : 'center',
			}, */ {
				width : '60',
				title : '发票种类',
				field : 'FPZL',
				align : 'center',
				formatter : function(value, row) {
					switch(value) {
						case '0' : return '专票'; break;
						case '2' : return '普票'; break;
						case '41' : return '卷票'; break;
						case '51' : return '电子'; break;
						default : return '其他'; break;
					}
				}
			}, {
				width : '40',
				title : '税率',
				field : 'SLV',
				align : 'center',
				//hidden : true
			}, {
				width : '100',
				title : '合计金额',
				field : 'AMOUNT',
				halign : 'center',
				//sortable : true
			}, {
				width : '100',
				title : '合计税额',
				field : 'TAX_AMOUNT',
				halign : 'center',
				//sortable : true
			}, {
				width : '100',
				title : '税价合计',
				field : 'TOTAL',
				halign : 'center',
				//sortable : true
			}, {
				width : '60',
				title : '打印标志',
				field : 'IS_PRINT',
				align : 'center',
				hidden : true,
				formatter : function(value, row) {
					switch(value) {
						case '0' : return '否'; break;
						case '1' : return '是'; break;
						default : return '未知'; break;
					}
				}
			}, {
				width : '60',
				title : '作废标志',
				field : 'IS_ZF',
				align : 'center',
				formatter : function(value, row) {
					switch(value) {
						case '0' : return '否'; break;
						case '1' : return '是'; break;
						default : return '未知'; break;
					}
				}
			}, {
				width : '60',
				title : '清单标志',
				field : 'HAS_QD',
				align : 'center',
				formatter : function(value, row) {
					switch(value) {
						case '0' : return '否'; break;
						case '1' : return '是'; break;
						default : return '未知'; break;
					}
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(data) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
				
			},
			onClickRow:function(rowIndex,rowData){
				$(this).datagrid('unselectRow',rowIndex);
			},
			onLoadSuccess : function(data) {
				//console.log(data);
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
				/* if(data.total==0){
					$.messager.alert('提示',
							"没有数据",
							'info');
				} */
				
				//提示: 本页显示记录存在的负数订单数量, 存在则提示
				var has_f=0;
				for (var rNo=0; rNo < data.rows.length; rNo++) {
					if(data.rows[rNo].TOTAL < 0) {
						has_f++;
					}
				}
				if (has_f > 0 && has_f < data.rows.length ) {
					alert("本页存在"+has_f+"张单据总金额为负!");
				}
				
				//checkbox全选
				$("#checkAll").on("click",function () {
					console.log($(this).is(':checked'));
			        if ($(this).is(':checked')) {
			            $("input[name='check']").prop("checked", true);
			        } else {
			            $("input[name='check']").prop("checked", false);
			        }
				});
			}, //end onLoadSuccess;
			
		});
			
	});
	
	
	//打开金穗卡
	function opencar(){
		if(is_open==0){
			var a=new ActiveXObject("TaxCardX.GoldTax"); //创建金税盘实例
			a.CertPassWord=12345678;
			a.OpenCard();
			if(a.RetCode=="1011" || a.RetCode=="3001"){
				activex = a;
				is_open=1;
	        	var mess="金税盘开启成功！";
				//alert(mess);
			} else {
				//alert("金税盘开启失败,错误代码："+a.RetCode+"，错误信息："+a.RetMsg);
				alert("金税盘开启失败,错误代码："+a.RetCode);
			}
	    }else{
	    	closecar();
	    	//alert("金税盘已打开，无需再打开！");
	    	opencar();
	    }
	}
	
	
	//关闭金税盘
	function closecar(){
		var a = activex;
		a.CloseCard();
		is_open=0;
	}
	
	//打印发票
	function printInvoice(){
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		var param=new Array();	//选中ohids
		//获取勾选订单头主键
		$("input[name='check']").each(function(){
			if ($(this).is(':checked')) {
				param.push($(this).val());
			}
		});
		
		//根据ohids查找开票信息
		var url=sy.contextPath + '/base/fpmng/order!invoice.sy';
		$.post(url,{ ohids : param.join(',') },function(result){
			if(result.success){
				//打开金穗盘
				opencar();
				var a = activex;
			//传入发票信息
				for(var kpNo=0; kpNo < result.obj.length; kpNo++){
					var fpList = result.obj[kpNo];	//开票信息字符串
					var fpHead = fpList[0];	//发票头信息
					
					//alert(fpHead.FPDM+fpHead.FPHM);
					//打印发票
					a.InfoKind = fpHead.FPZL;	//发票种类
					a.InfoTypeCode = fpHead.FPDM;	//发票代码
					a.InfoNumber = fpHead.FPHM;	//发票号码
					a.GoodsListFlag = 0;	//清单标志 0:打印发票,1:打印清单
					a.InfoShowPrtDlg = 1;	//是否显示边距确认对话框 0:否,1:是
					a.PrintInv();	//调用接口打印发票
					
					if (a.RetCode == "5011") {	//打印成功
						
						//查询发票,并将相关信息回写数据库
						queryInv(fpHead.FPZL,fpHead.FPDM,fpHead.FPHM);
						//回写数据库
						if (a.RetCode == "7011"){
							var is_print = 1;
							var is_zf= 0;
							rewriteDb(fpHead.OHID,is_print,is_zf);
						}
					} else {
						//记录开票错误日志
						updateErrLog(a.RetMsg,fpHead.OHID);
					}
					
				}//一张发票开具回写完毕
				
				closecar();//关闭金税盘
				//alert("金税盘已关闭!");
			}
		},'json');
				parent.$.messager.progress('close');
				grid.datagrid('load');
	}
	
	//打印清单
	function printList(){
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		var param=new Array();	//选中ohids
		//获取勾选订单头主键
		$("input[name='check']").each(function(){
			if ($(this).is(':checked')) {
				param.push($(this).val());
			}
		});
		
		//根据ohids查找开票信息
		var url=sy.contextPath + '/base/fpmng/order!invoice.sy';
		$.post(url,{ ohids : param.join(',') },function(result){
			if(result.success){
				//打开金穗盘
				opencar();
				var a = activex;
			//传入发票信息
				for(var kpNo=0; kpNo < result.obj.length; kpNo++){
					var fpList = result.obj[kpNo];	//开票信息字符串
					var fpHead = fpList[0];	//发票头信息
					
					//打印发票
					a.InfoKind = fpHead.FPZL;	//发票种类
					a.InfoTypeCode = fpHead.FPDM;	//发票代码
					a.InfoNumber = fpHead.FPHM;	//发票号码
					a.GoodsListFlag = 1;	//清单标志 0:打印发票,1:打印清单
					a.InfoShowPrtDlg = 1;	//是否显示边距确认对话框 0:否,1:是
					a.PrintInv();	//调用接口打印发票
					
					if (a.RetCode == "5011") {	//打印成功
						
						//查询发票,并将相关信息回写数据库
						queryInv(fpHead.FPZL,fpHead.FPDM,fpHead.FPHM);
						//回写数据库
						if (a.RetCode == "7011"){
							var is_print = 0;
							var is_zf= 0;
							rewriteDb(fpHead.OHID,is_print,is_zf);
						}
					} else {
						//记录开票错误日志
						updateErrLog(a.RetMsg,fpHead.OHID);
					}
					
				}//一张发票开具回写完毕
				
				closecar();//关闭金税盘
				//alert("金税盘已关闭!");
			} else {
				
			}
		},'json');
				parent.$.messager.progress('close');
				grid.datagrid('load');
	}
	
	//作废发票
	function invalidInvoice(){
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		var param=new Array();	//选中ohids
		//获取勾选订单头主键
		$("input[name='check']").each(function(){
			if ($(this).is(':checked')) {
				param.push($(this).val());
			}
		});
		
		//根据ohids查找开票信息
		var url=sy.contextPath + '/base/fpmng/order!invoice.sy';
		$.post(url,{ ohids : param.join(',') },function(result){
			if(result.success){
				//打开金穗盘
				opencar();
				var a = activex;
			//传入发票信息
				for(var kpNo=0; kpNo < result.obj.length; kpNo++){
					var fpList = result.obj[kpNo];	//开票信息字符串
					var fpHead = fpList[0];	//发票头信息
					
					//作废发票
					a.InfoKind = fpHead.FPZL;	//发票种类
					a.InfoTypeCode = fpHead.FPDM;	//发票代码
					a.InfoNumber = fpHead.FPHM;	//发票号码
					a.CancelInv();	//调用接口作废发票
					
					if (a.RetCode == "6011") {	//作废成功
						
						//查询发票,并将相关信息回写数据库
						queryInv(fpHead.FPZL,fpHead.FPDM,fpHead.FPHM);
						//回写数据库
						if (a.RetCode == "7011"){
							var is_print = 0;
							var is_zf= 1;
							rewriteDb(fpHead.OHID,is_print,is_zf);
						}
					} else {
						//记录开票错误日志
						updateErrLog(a.RetMsg,fpHead.OHID);
					}
					
				}//一张发票开具回写完毕
				
				closecar();//关闭金税盘
				//alert("金税盘已关闭!");
			} else {
				alert(result.msg);
			}
			parent.$.messager.progress('close');
			grid.datagrid('load');
		},'json');
	}
	
	//查看单据明细
	function checkOrder(id){
		dialog = parent.sy.modalDialog({
			title : '单据明细',
			url : sy.contextPath + '/securityJsp/base/aisinoKp/OrderForm.jsp?id=' + id,
			height : 560,
			width : 1020,
		});
	}
	
	//所选发票修复
	function recheckInvoice() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		var param=new Array();	//选中ohids
		//获取勾选订单头主键
		$("input[name='check']").each(function(){
			if ($(this).is(':checked')) {
				param.push($(this).val());
			}
		});
		
		//根据ohids查找开票信息
		var url=sy.contextPath + '/base/fpmng/order!invoice.sy';
		$.post(url,{ ohids : param.join(',') },function(result){
			if(result.success){
				//打开金穗盘
				opencar();
				var a = activex;
			//传入发票信息
				for(var kpNo=0; kpNo < result.obj.length; kpNo++){
					var fpList = result.obj[kpNo];	//开票信息字符串
					var fpHead = fpList[0];	//发票头信息
					//查询发票,并将相关信息回写数据库
					queryInv(fpHead.FPZL,fpHead.FPDM,fpHead.FPHM);
					//回写数据库
					if (a.RetCode == "7011"){
						var is_print = 0;
						var is_zf = a.CancelFlag;
						rewriteDb(fpHead.OHID,is_print,is_zf);
					} else {
						updateErrLog(a.RetMsg,fpHead.OHID);
					}
					
				}//一张发票开具回写完毕
				
				closecar();//关闭金税盘
				//alert("金税盘已关闭!");
			} else {
				alert(result.msg);
			}
		},'json');
			parent.$.messager.progress('close');
			grid.datagrid('load');
	}
	
	
	
	//发票信息查询
	function queryInv(InfoKind,InfoTypeCode,InfoNumber){
		//opencar();//测试完需注释
		
		var a = activex;
		//alert("InfoKind"+InfoKind+",InfoTypeCode"+InfoTypeCode+",InfoNumber"+InfoNumber+",order_id"+order_id);
		a.InfoKind = InfoKind;
		a.InfoTypeCode = InfoTypeCode;
		a.InfoNumber = InfoNumber;
		//alert("开始查询");
		a.QryInv();	//调用接口查询发票
		
		//closecar();//测试完需注释
	}
	
	
	//回写开票信息等至本地数据库
	function rewriteDb(order_id,is_print,is_zf){
		//opencar();//测试完需注释
		var a = activex;
		/* alert(a.InfoTypeCode+a.InfoNumber
				+"\n打印标志:"+a.PrintFlag
				+"\n自设打印标志:"+is_print
				+"\n作废标志:"+a.CancelFlag
				+"\n报送状态:"+a.UploadFlag
				+"\ninfo:"+a.info); */
				//alert("税价合计"+a.InfoAmount+a.InfoTaxAmount);
		$.post(sy.contextPath + '/base/fpmng/order!doNotNeedSecurity_updateInvoice.sy', 
			{
				errlog : a.RetMsg,	//错误描述信息
				fpdm : a.InfoTypeCode,	//发票代码
				fphm : a.InfoNumber,	//发票号码
				fpzl : a.InfoKind,	//发票种类
				amount : a.InfoAmount,	//不含税金额
				tax_amount : a.InfoTaxAmount,	//税额
				createtime : a.InfoDate,	//开票时间
				xml : a.info,
				ohid : order_id,	//订单头主键
				
				is_print : is_print,	//打印标志
				is_zf : is_zf,	//作废标志
				
			}, function(result) {
				if(result.success){
					//alert(result.msg);
					//grid.datagrid('load');
				} else {
					alert(result.msg);
				}
					
		}, 'json');
		//closecar();//测试完需注释
	}
	
	//开票错误信息回写数据库
	function updateErrLog(retMsg,ohid) {
		//记录开票错误日志
		$.post(sy.contextPath + '/base/fpmng/order!doNotNeedSecurity_updateErrLog.sy', 
			{
				errlog : retMsg,	//错误描述信息
				ohid : ohid	//订单头主键
			}, function(result) {
				if(result.success){
					//alert("错误信息已保存!");
				} else {
					alert(result.msg);
				}
					
		}, 'json');
	}
	
		
	
	//已开发票回写中间库
	function returnInvoiceToMiddleDb() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		
		//alert(param.join(','));
		//根据ohids查找开票信息
		var url=sy.contextPath + '/base/fpmng/invoice!returnInvoiceToMiddleDb.sy';
		$.post(url,function(result){
			if(result.success){
				parent.$.messager.progress('close');
				grid.datagrid('load');
				alert(result.msg);
			} else {
			}
		},'json'); 
	}
	
	//作废发票恢复单据
	function invalidToOrder() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		var param=new Array();	//选中ohids
		//获取勾选订单头主键
		$("input[name='check']").each(function(){
			if ($(this).is(':checked')) {
				param.push($(this).val());
			}
		});
		
		//alert(param.join(','));
		//根据ohids查找开票信息
		var url=sy.contextPath + '/base/fpmng/invoice!invalidToOrder.sy';
		$.post(url,{ ohids : param.join(',') },function(result){
			if(result.success){
				parent.$.messager.progress('close');
				grid.datagrid('load');
				alert(result.msg);
			} else {
				parent.$.messager.progress('close');
				alert(result.msg);
			}
		},'json'); 
	}
	
	
	//导出excel表
 	function exportInvoiceExcel() {
 			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/base/fpmng/invoice!exportInvoiceExcel.sy',
				sy.serializeObject($('#searchForm')), function(result) {
					if(result.success){
						//parent.$.messager.alert('提示', result.msg, 'info');
						parent.$.messager.progress('close');
						window.location.href = sy.contextPath + '/base/fpmng/invoice!download.sy?fileName='+result.msg;
					}
				}, 'json'); 
	}; 
	
	//回写发票信息
	function returnInvoiceToTheDb() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		var url=sy.contextPath + '/base/fpmng/invoice!returnInvoiceToTheDb.sy';
		$.post(url,function(result){
			if(result.success){
				parent.$.messager.progress('close');
				grid.datagrid('load');
				alert(result.msg);
			} else {
			}
		},'json'); 
	}
</script>
<title>单据管理</title>
</head>

<body class="easyui-layout" data-options="fit:true,border:false" style="overflow:auto;">
	<div id="toolbar" style="display: none;">
		<form id="searchForm" method="post">
			<table>
				<tr>
					<td><label>开票日期:</label></td>
					<td colspan="4"><input id="createtime1" name="createtime1" class="Wdate"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
						style="width: 100px;" />-<input id="createtime2"
						name="createtime2" class="Wdate"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
						style="width: 100px;" /></td>
						
					<td><div class="datagrid-btn-separator"></div></td>
					<td><select id="fpzl" name="fpzl" style="width: 80px;">
							<option value="">发票种类</option>
							<option value="0">专票</option>
							<option value="2">普票</option>
					</select></td>
				
					<!-- <td><label>作废标志:</label></td> -->
					<td><select id="is_zf" name="is_zf" style="width: 80px;">
							<option value="">作废标志</option>
							<option value="1">是</option>
							<option value="0">否</option>
					</select></td>
					<td><div class="datagrid-btn-separator"></div></td>
					
					<!-- <td><label>清单标志:</label></td> -->
					<td><select id="has_qd" name="has_qd" style="width: 80px;">
							<option value="">清单标志</option>
							<option value="1">是</option>
							<option value="0">否</option>
					</select></td>
					<td><div class="datagrid-btn-separator"></div></td>
					
					<!-- <td><label>打印状态:</label></td> -->
					<td><select id="is_print" name="is_print" style="width: 80px;">
							<option value="">打印状态</option>
							<option value="1">已打印</option>
							<option value="0">未打印</option>
					</select></td>
					<td><div class="datagrid-btn-separator"></div></td>
					
				</tr>
			</table>
			<table>
				<tr>
					<!-- <td><label>发票代码:</label></td> -->
					<td><input id="fpdm" name="fpdm" style="width: 120px;" placeholder="请输入发票代码" /></td>
					<td><div class="datagrid-btn-separator"></div></td>
					
					<!-- <td><label>发票号码:</label></td> -->
					<td><input id="fphm" name="fphm" style="width: 120px;" placeholder="请输入发票号码" /></td>
					<td><div class="datagrid-btn-separator"></div></td>
					
					<!-- <td><label>购方名称:</label></td> -->
					<td><input id="gfmc" name="gfmc" style="width: 120px;" placeholder="请输入购方名称" /></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<!-- <td><label>单据号码:</label></td> -->
					<td><input id="djhm" name="djhm" style="width: 120px;" placeholder="请输入单据号码" /></td>
					<td><div class="datagrid-btn-separator"></div></td>
					
					<td>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom',plain:true"
						onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">查询</a></td>
				</tr>
			</table>

			<table>
				<tr>
					<%if (securityUtil.havePermission("/base/fpmng/invoice!printInvoice")) {%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						 onclick="printInvoice();">打印发票</a></td>
					<%} %>
					<%if (securityUtil.havePermission("/base/fpmng/invoice!printList")) {%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						 onclick="printList();">打印清单</a></td>
					<%} %>
					<%if (securityUtil.havePermission("/base/fpmng/invoice!invalidInvoice")) {%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						 onclick="invalidInvoice();">作废发票</a></td>
					<%} %>
					<%if (securityUtil.havePermission("/base/fpmng/invoice!invalidToOrder")) {%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						 onclick="invalidToOrder();">生成单据</a></td>
					<%} %>
					<%if (securityUtil.havePermission("/base/fpmng/invoice!exportInvoiceExcel")) {%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						 onclick="exportInvoiceExcel();">导出发票</a></td>
					<%} %>
					<%if (securityUtil.havePermission("/base/fpmng/invoice!returnInvoiceToMiddleDb")) {%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						 onclick="returnInvoiceToMiddleDb();">回写中间库</a></td>
					<%} %>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						 onclick="recheckInvoice();">修复发票</a></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
					onclick="returnInvoiceToTheDb();">回写发票</a></td>
				</tr>
				
			</table>


		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>
