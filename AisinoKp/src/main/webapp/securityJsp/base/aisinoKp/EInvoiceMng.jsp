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
					+ '/base/fpmng/e-invoice!getInvoiceGrid.sy',
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
							<%if (securityUtil.havePermission("/base/fpmng/e-invoice!checkOrder")) {%>
								str += sy
									.formatString(
											'<img class="iconImg ext-icon-vcard" title="查看" onclick="checkOrder(\'{0}\');"/>',
											row.OHID);
 							<%}%>
							return str;
						}
					}, {
						width : '100',
						title : '发票代码',
						field : 'FPDM',
						halign : 'center',
					}, {
						width : '80',
						title : '发票号码',
						field : 'FPHM',
						halign : 'center',
					} ] ],
			columns : [ [  {
				width : '160',
				title : '单据号码',
				field : 'OHID',
				align : 'center',
				hidden : true
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
				hidden : true,
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
				title : '签章(蓝)',
				field : 'IS_QZ',
				align : 'center',
				//hidden : true,
				formatter : function(value, row) {
					switch(value) {
						case '0' : return '否'; break;
						case '1' : return '是'; break;
						default : return '未知'; break;
					}
				}
			}, {
				width : '60',
				title : '签章(红)',
				field : 'IS_QZ_H',
				align : 'center',
				//hidden : true,
				formatter : function(value, row) {
					switch(value) {
						case '0' : return '否'; break;
						case '1' : return '是'; break;
						default : return '未知'; break;
					}
				}
			}, {
				width : '60',
				title : '清单',
				field : 'HAS_QD',
				align : 'center',
				formatter : function(value, row) {
					switch(value) {
						case '0' : return '否'; break;
						case '1' : return '是'; break;
						default : return '未知'; break;
					}
				}
			}, {
				width : '100',
				title : '红字发票代码',
				field : 'RED_FPDM',
				halign : 'center',
			}, {
				width : '80',
				title : '红字发票号码',
				field : 'RED_FPHM',
				halign : 'center',
			}, {
				width : '100',
				title : '红字合计税额',
				field : 'TAX_AMOUNT_H',
				halign : 'center',
				//sortable : true
			}, {
				width : '100',
				title : '错误日志',
				field : 'ERRLOG',
				halign : 'center',
				//sortable : true,
				//hidden : true
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
	
	
	
	//查看发票据明细
	function checkOrder(id){
		
		dialog = parent.sy.modalDialog({
			title : '查看发票',
			url : sy.contextPath + '/securityJsp/base/aisinoKp/CheckInvoice.jsp?id=' + id+'&type='+$("#qz_lx").val(),
			height : 580,
			width : 800,
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
			parent.$.messager.progress('close');
			grid.datagrid('load');
		},'json');
	}
	
	
	
	
	//已开发票回写E店宝接口
	function returnInvoiceToEdb() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		
		//alert(param.join(','));
		//根据ohids查找开票信息
		var url=sy.contextPath + '/base/fpmng/e-invoice!returnInvoiceToEdb.sy';
		$.post(url,function(result){
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
	
	//作废发票恢复单据
	function invalidReopen() {
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
			}
		},'json'); 
	}
	
	//所选订单开红票
	function einvoice_red() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		var param=new Array();
		//获取勾选订单头主键
		$("input[name='check']").each(function(){
			if ($(this).is(':checked')) {
				param.push($(this).val());
			}
		});
		
		if(param.length>0){
		//根据ohids查找开票信息
		var url=sy.contextPath + '/base/fpmng/order!einvoice.sy';
		$.post(url,{ ohids : param.join(','), qz_lx : "1", },function(result){
			if(result.success){
				//开具电子发票成功
				alert(result.msg);
				<%if (securityUtil.havePermission("/base/fpmng/invoice!postPdf")) {%>
					//alert("贝誉推送PDF接口");
					/* var url_byjk=sy.contextPath + '/base/fpmng/invoice!postPdf.sy';
					$.post(url_byjk,{ ohids : param.join(',') },function(result1){
						if(result1.success){
							alert(result1.msg);
						} else {
							alert(result1.msg);
						}
					},'json'); */
				<%}%>
				parent.$.messager.progress('close'); 
			} else {
				parent.$.messager.progress('close'); 
				alert(result.msg);
			}
		},'json');
		
		grid.datagrid('load');
		}else{
			parent.$.messager.alert('提示', "请选择要冲红的发票！", 'info');
			parent.$.messager.progress('close'); 
		}
	}//einvoice_red()所选订单开票结束
	
	
	//所选订单签章
	function zj_qz() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		var param=new Array();
		//获取勾选订单头主键
		$("input[name='check']").each(function(){
			if ($(this).is(':checked')) {
				param.push($(this).val());
			}
		});
		
		if(param.length>0){
		//根据ohids查找开票信息
		var url=sy.contextPath + '/base/fpmng/e-invoice!zj_qz.sy';
		$.post(url,{ ohids : param.join(',') , qz_lx : $("#qz_lx").val(), },function(result){
			if(result.success){
				alert(result.msg);
				<%if (securityUtil.havePermission("/base/fpmng/e-invoice!postPdf")) {%>
					//alert("推送PDF接口");
					/* var url_byjk=sy.contextPath + '/base/fpmng/e-invoice!postPdf.sy';
					$.post(url_byjk,{ ohids : param.join(','), },function(result1){
						if(result1.success){
							alert(result1.msg);
						};
						parent.$.messager.progress('close'); 
						grid.datagrid('load');
					},'json'); */
				<%}%>
				parent.$.messager.progress('close'); 
				grid.datagrid('load');
			};
		},'json');
		}else{
			parent.$.messager.alert('提示', "请选择要签章的发票！", 'info');
			parent.$.messager.progress('close'); 
		}
		
	}//zj_qz()所选订单签章结束
	
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
	
	//发送短信
	function sendMessage(){
		var param=new Array();	//选中ohids
		$("input[name='check']").each(function(){
			if ($(this).is(':checked')) {
				param.push($(this).val());
			}
		});
		var url=sy.contextPath + '/base/fpmng/send-message!sendMessage.sy';
		$.post(url,{ ohids : param.join(',') },
		function(result){
			if(result.success){
				alert(result.msg);
			} else {
				alert(result.msg);
			}
		},'json'); 
	}
	
	
	//PDF推送
	function postPdf(){
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		var param=new Array();	//选中ohids
		$("input[name='check']").each(function(){
			if ($(this).is(':checked')) {
				param.push($(this).val());
			}
		});
		var url=sy.contextPath + '/base/fpmng/e-invoice!postPdf.sy';
		$.post(url,{ ohids : param.join(','),qz_lx:$('#qz_lx').val() },
		function(result){
			if(result.success){
				alert(result.msg);
				parent.$.messager.progress('close');
				grid.datagrid('load');
			} else {
				//alert("短信发送失败了");
				parent.$.messager.progress('close');
				grid.datagrid('load');
			}
		},'json'); 
	}
	
	//已开发票回写中间库
	function returnInvoiceToMiddleDb() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		
		//alert(param.join(','));
		//根据ohids查找开票信息
		var url=sy.contextPath + '/base/fpmng/e-invoice!returnInvoiceToMiddleDb.sy';
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
					<!-- <td><label>签章标志:</label></td> -->
					<td><select id="is_qz" name="is_qz" style="width: 80px;">
							<option value="">签章标志</option>
							<option value="1">是</option>
							<option value="0">否</option>
					</select></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><select id="has_qd" name="has_qd" style="width: 80px;">
							<option value="">清单标志</option>
							<option value="1">是</option>
							<option value="0">否</option>
					</select></td>
					<td><div class="datagrid-btn-separator"></div></td>
					
				</tr>
			</table>
			<table>
				<tr>
					<td><input id="fpdm" name="fpdm" style="width: 120px;" placeholder="请输入发票代码" /></td>
					<td><div class="datagrid-btn-separator"></div></td>
					
					<td><input id="fphm" name="fphm" style="width: 120px;" placeholder="请输入发票号码" /></td>
					<td><div class="datagrid-btn-separator"></div></td>
					
					<!-- <td><label>购方名称:</label></td> -->
					<td><input id="gfmc" name="gfmc" style="width: 120px;" placeholder="请输入购方名称" /></td>
					<td><div class="datagrid-btn-separator"></div></td>
					
					<td>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom',plain:true"
						onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">查询</a></td>
				</tr>
			</table>

			<table>
				<tr>
					<%if (securityUtil.havePermission("/base/fpmng/invoice!exportInvoiceExcel")) {%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						 onclick="exportInvoiceExcel();">导出发票</a></td>
					<%} %>
					<%
						if (securityUtil.havePermission("/base/fpmng/e-invoice!einvoice_red")) {
					%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						 onclick="einvoice_red();">发票冲红</a></td>
					<%
						}
					%>
					<td>发票类型：
						<select id="qz_lx" name="qz_lx" style="width: 50px;">
							<option value="1">红字</option>
							<option value="0">蓝字</option>
					</select>
					</td>
					<%
						if (securityUtil.havePermission("/base/fpmng/e-invoice!zj_qz")) {
					%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						 onclick="zj_qz();">签章</a></td>
					<%
						}
					%>
					<!-- 添加短信的按钮 -->
					<%if (securityUtil.havePermission("/base/fpmng/send-message!sendMessage")) {%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						 onclick="sendMessage();">发送短信</a></td>
					<%} %>
					
					
					<%
						if (securityUtil.havePermission("/base/fpmng/e-invoice!postPdf")) {%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						 onclick="postPdf();">PDF推送</a></td>
					<%
						}
					%>
				</tr>
				
			</table>


		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>
