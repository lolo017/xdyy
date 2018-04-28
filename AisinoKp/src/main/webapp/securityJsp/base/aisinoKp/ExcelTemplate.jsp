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
<style type="text/css">
.target{
	width: 120px;
	height: 50px;
	text-align:center;
	
}
.assigned{border: 2px dashed #399BFF;}
.drag{
	display:inline/inline-block;
	margin-top: 10px;
}
.right td{
    background: #E0ECFF;
    color: #444;
    width: 120px;
    height: 50px;
   }

</style>
<script type="text/javascript">
function cmp( x, y ){ 
	// If both x and y are null or undefined and exactly the same 
	if ( x === y ) { 
	 return true; 
	} 
	 
	// If they are not strictly equal, they both need to be Objects 
	if ( ! ( x instanceof Object ) || ! ( y instanceof Object ) ) { 
	 return false; 
	} 
	 
	//They must have the exact same prototype chain,the closest we can do is
	//test the constructor. 
	if ( x.constructor !== y.constructor ) { 
	 return false; 
	} 
	  
	for ( var p in x ) { 
	 //Inherited properties were tested using x.constructor === y.constructor
	 if ( x.hasOwnProperty( p ) ) { 
	 // Allows comparing x[ p ] and y[ p ] when set to undefined 
	 if ( ! y.hasOwnProperty( p ) ) { 
	  return false; 
	 } 
	 
	 // If they have the same strict value or identity then they are equal 
	 if ( x[ p ] === y[ p ] ) { 
	  continue; 
	 } 
	 
	 // Numbers, Strings, Functions, Booleans must be strictly equal 
	 if ( typeof( x[ p ] ) !== "object" ) { 
	  return false; 
	 } 
	 
	 // Objects and Arrays must be tested recursively 
	 if ( ! Object.equals( x[ p ], y[ p ] ) ) { 
	  return false; 
	 } 
	 } 
	} 
	 
	for ( p in y ) { 
	 // allows x[ p ] to be set to undefined 
	 if ( y.hasOwnProperty( p ) && ! x.hasOwnProperty( p ) ) { 
	 return false; 
	 } 
	} 
	return true; 
	};
	//判断数组中是否包含一个元素
	function isInArray(arr,value){
	    for(var i = 0; i < arr.length; i++){
	        if(cmp(arr[i],value)){
	            return true;
	        }
	    }
	    return false;
	}
	//属性字段按页面顺序放入arr
	function importExcl(){
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		var arr=new Array();
		$('.right').find('tr').each(function(){
			//console.log($(this).children('td:eq(0)'));
			$(this).find('td').each(function(){
				//console.log($($(this).context.firstElementChild).children('a:eq(0)').context.innerHTML);
				$(this).find('div a').each(function(){
					//console.log($(this).attr('name'));
					arr.push($(this).attr('name'));
				});
			});
		});
		//console.log(arr);
		
		var formData = new FormData();
		formData.append("file",$("#plsc_file")[0].files[0]);
		formData.append("tablename",$('#tablename').combobox('getValue'));
		formData.append("column",arr);
		$.ajax({ 
			url : sy.contextPath+'/base/fpmng/excltemplate!importExcel.sy',
			type : 'POST', 
			dataType: 'json',
			data : formData, 
			// 告诉jQuery不要去处理发送的数据
			processData : false, 
			// 告诉jQuery不要去设置Content-Type请求头
			contentType : false,
			beforeSend:function(){
				//console.log("正在进行，请稍候");
			},
			success : function(result) { 
				//if(result.success){
					//console.log("成功"+responseStr);
					alert(result.msg);
				//}else{
					//console.log("失败"+responseStr);
					//alert(result.msg);
				//}
				parent.$.messager.progress('close');
			}, 
			/* error : function(responseStr) { 
				console.log("error");
			}  */
		});
	};
	//删除模板
	function deleteTemplate(){
		$.post(sy.contextPath+'/base/fpmng/excltemplate!delete_template.sy',{table_name:$('#tablename').combobox('getValue')},function(result){
			if(result){
				parent.$.messager.alert('完成', result.msg);
				loadField();
			}
		},'json');
	}
	//保存模板
	function saveTemplate(){
		var arr=new Array();
		var name_arr=new Array();
		$('.right').find('tr').each(function(){
			$(this).find('td').each(function(){
				$(this).find('div a').each(function(){
					arr.push($(this).attr('name'));
					name_arr.push($(this).context.innerText);
					
				});
			});
		});
		//console.log(arr);
		$.post(sy.contextPath+'/base/fpmng/excltemplate!save_template.sy',{array:arr.join(','),name_arr:name_arr.join(','),table_name:$('#tablename').combobox('getValue')},function(result){
			if(result.success){
				parent.$.messager.alert('完成', result.msg);
			}
		},'json');
	}
	//加载模板
	function loadField(){
		$.post(sy.contextPath+'/base/fpmng/excltemplate!load.sy',{tablename:$('#tablename').combobox('getValue')},
			function(result){
			//清空页面
			$('.sourceArea table').empty();
			$('.right table').empty();
			var count=-1;
			if(result.success){
				
				result.obj.forEach(function(item,index,input){
					
					if(index%9==0){
						count++;
						$('.sourceArea table').append('<tr></tr>');
						$('.right table ').append('<tr></tr>');
					}
					$('.sourceArea table tr').eq(count).append('<td><a  name="'+item.name+'" class="drag">'+item.remark+'</a></td>');
					
					$('.right table tr').eq(count).append('<td><div class="target"></div></td>');
					$('.drag').linkbutton({}); 
					});
			}else{
				
				result.obj[0].forEach(function(item,index,input){
					
					if(index%9==0){
						count++;
						$('.sourceArea table').append('<tr></tr>');
						$('.right table ').append('<tr></tr>');
					}
					if(!isInArray(result.obj[1],item)){
						
						$('.sourceArea table tr').eq(count).append('<td><a  name="'+item.name+'" class="drag">'+item.remark+'</a></td>');
					}
					$('.right table tr').eq(count).append('<td><div class="target"></div></td>');
					$('.drag').linkbutton({}); 
				});
				var count=-1;
				result.obj[1].forEach(function(item,index,input){
					if(index%9==0){
						//$('.right table ').append('<tr></tr>');
						
						count++;
					}
					$('.right table tr td div').eq(parseInt(index%9+count*9)).append('<a  name="'+item.name+'" class="drag">'+item.remark+'</a>');
					$('.target').eq(index).addClass('assigned');
					$('.drag').linkbutton({}); 
				});
				
			}
			
			
			$('.drag').draggable({
				proxy:'clone',
				revert:true,
				cursor:'auto',
				onStartDrag:function(){
					$(this).draggable('options').cursor='not-allowed';
					$(this).draggable('proxy').addClass('dp');
				},
				onStopDrag:function(){
					$(this).draggable('options').cursor='auto';
					//alert($(this).attr("name"));
				}
			});
			//为.drag添加linkbutton样式
			
			$('.target').droppable({
				
				onDragEnter:function(e,source){
					$(source).draggable('options').cursor='auto';
					$(source).draggable('proxy').css('border','1px solid red');
					$(this).addClass('over');
				},
				onDragLeave:function(e,source){
					$(source).draggable('options').cursor='not-allowed';
					$(source).draggable('proxy').css('border','1px solid #ccc');
					$(this).removeClass('over');
					//console.log(source);
					if($(this).context.firstElementChild===source){
						$(this).removeClass('assigned');
					}
					
					},
				onDrop:function(e,source){
					
					if ($(this).hasClass('assigned')) {
	                    //如果有边框样式则表示已有内容
	                    //$(this).empty().append(source);
	                    return;
					}else{
						$(this).addClass('assigned');
	                    $(this).empty().append(source);

					}
				}
				
			});
		},'json');
	}
	
	$(function(){
		loadField();
		
		$('#tablename').combobox({
			onChange:function(newValue,oldValue){
				loadField();
			}
		});
		/* $('.item').droppable({
			
			onDragEnter:function(e,source){
				$(source).draggable('options').cursor='auto';
				$(source).draggable('proxy').css('border','1px solid red');
				$(this).addClass('over');
			},
			onDragLeave:function(e,source){
				$(source).draggable('options').cursor='not-allowed';
				$(source).draggable('proxy').css('border','1px solid #ccc');
				$(this).removeClass('over');
			},
			onDrop:function(e,source){
				$(this).append(source);
				$(this).removeClass('over');
			}
		}); */
	});
</script>
	
	
	
	
	
</script>
<title>excel导入编辑</title>
</head>

<body class="easyui-layout" data-options="fit:true,border:false" style="overflow:auto;">
	<div data-options="region:'north',title:'选择模板导入',split:true" style="height:100px;">
		
		<div style="margin: 10px">
			选择导入表:
			<select class="easyui-combobox" id="tablename" name="tablename">
				<option value="t_buyerinfo">购方信息导入</option>
				<option value="t_ssflbm">税收分类编码导入</option>
				<option value="t_orderdata">订单信息导入</option>
			</select>
			<a class="easyui-linkbutton" data-options="iconCls:'ext-icon-page_white_excel'" onclick="saveTemplate();">保存模板</a>
			<a class="easyui-linkbutton" data-options="iconCls:'ext-icon-xhtml_delete'" onclick="deleteTemplate();">删除模板</a>
			<a class="easyui-linkbutton" data-options="iconCls:'ext-icon-page_white_excel'" onclick="importExcl();">导入Excel</a>
			
	        <input type="file" id="plsc_file" class="easyui-filebox" name="file" data-options="prompt:'点击选择文件'" data-options="required:true"/>
		</div>
	</div>
	
	<div data-options="region:'center'" style="padding:5px;background:#eee;">
		<div class="sourceArea" style="height: 100px;">
		
			<table >
				<!-- <tr>
					
					<td><a id="d1" name="ssflbm" class="drag easyui-linkbutton">购方名称</a></td>
					<td><a id="d2" name="sh" class="drag easyui-linkbutton">购方号码</a></td>
					<td><a id="d3" name="gfmc" class="drag easyui-linkbutton">银行账户</a></td>
				</tr> -->
				
			</table>
			
	</div>
	<hr style="margin-top: 20px">
	<div style="margin-top: 40px" class="right">
			<table >
				
			</table>`
	</div>
	</div>
	
	<!-- <div id="target" style="background: red;width: 500px;height: 400px;border: 1px">
		
	</div> -->
	
</body>
</html>
