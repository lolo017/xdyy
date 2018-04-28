<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<TITLE>欢迎使用发票查询平台</TITLE>
<jsp:include page="inc.jsp"></jsp:include>
<STYLE>
body {
	background: #ebebeb;
	font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei",
		"\9ED1\4F53", Arial, sans-serif;
	color: #222;
	font-size: 12px;
}

* {
	padding: 0px;
	margin: 0px;
}

.top_div {
	background: #008ead;
	width: 100%;
	height: 400px;
}

.ipt1 {
	border: 1px solid #d3d3d3;
	padding: 10px 10px;
	width: 130px;
	border-radius: 4px;
	padding-left: 35px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

.ipt1:focus {
	border-color: #66afe9;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6)
}

.ipt2 {
	border: 1px solid #d3d3d3;
	padding: 10px 10px;
	width: 240px;
	border-radius: 4px;
	padding-left: 35px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

.ipt2:focus {
	border-color: #66afe9;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6)
}

.ts {
	border: 1px solid #d3d3d3;
	padding: 10px 10px;
	width: 50px;
	border-radius: 4px;
	padding-left: 35px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

.ts1 {
	
	border: 1px solid #d3d3d3;
	padding: 10px 10px;
	width: 100px;
	border-radius: 4px;
	padding-left: 17.5px;
	padding-right: 17.5px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

.u_logo {
	background: url("style/images/login/username.png") no-repeat;
	padding: 10px 10px;
	position: absolute;
	top: 43px;
	left: 90px;
}

.p_logo {
	background: url("style/images/login/password.png") no-repeat;
	padding: 10px 10px;
	position: absolute;
	top: 12px;
	left: 90px;
}

a {
	text-decoration: none;
}

.tou {
	background: url("style/images/login/tou.png") no-repeat;
	width: 97px;
	height: 92px;
	position: absolute;
	top: -87px;
	left: 140px;
}

.left_hand {
	background: url("style/images/login/left_hand.png") no-repeat;
	width: 32px;
	height: 37px;
	position: absolute;
	top: -38px;
	left: 150px;
}

.right_hand {
	background: url("style/images/login/right_hand.png") no-repeat;
	width: 32px;
	height: 37px;
	position: absolute;
	top: -38px;
	right: -64px;
}

.initial_left_hand {
	background: url("style/images/login/hand.png") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -12px;
	left: 100px;
}

.initial_right_hand {
	background: url("style/images/login/hand.png") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -12px;
	right: -112px;
}

.left_handing {
	background: url("style/images/login/left-handing.png") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -24px;
	left: 139px;
}

.right_handinging {
	background: url("style/images/login/right_handing.png") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -21px;
	left: 210px;
}
</STYLE>



<SCRIPT type="text/javascript">
var wait=90;
function time(o){
	
	if (wait == 0) {   
		o.removeAttribute("disabled");     
		o.value="获取验证码";   
		wait = 90;  
		$("#hid").val("");
		} else {     
			o.setAttribute("disabled", true);  
			o.value="重新发送(" + wait + ")"; 
			wait--;   
			setTimeout(function() { 
				time(o)   
				},    
				1000)  } 
}
function message(o){
	var number=document.getElementById("phone").value;
	
	$.post(sy.contextPath+ '/base/message!doNotNeedSessionAndSecurity_send.sy', 
			{phone:number}, function(result) {
		if (result.success) {
			
			var yzm=result.msg;
			$("#hid").val(yzm);
			//alert($("#hid").val());
			time(o);
		}

	}, 'json');
	
}
function loginFun() {
	var number=$("#phone").val();
	var pw1=$("#password").val();
	var pw2=$("#hid").val();
	
			if(pw2==""){
				alert("请获取验证码");
				return;
			}
			if(pw1!=pw2){
				alert("验证码错误");
			}else{
				location.replace(sy.contextPath + '/cust.jsp?number='+number);
			}
			
			
			
		
};
	$(function() {
		
		//得到焦点
		$("#password").focus(function() {
			$("#left_hand").animate({
				left : "150",
				top : " -38"
			}, {
				step : function() {
					if (parseInt($("#left_hand").css("left")) > 140) {
						$("#left_hand").attr("class", "left_hand");
					}
				}
			}, 2000);
			$("#right_hand").animate({
				right : "-64",
				top : "-38px"
			}, {
				step : function() {
					if (parseInt($("#right_hand").css("right")) > -70) {
						$("#right_hand").attr("class", "right_hand");
					}
				}
			}, 2000);
		});
		//失去焦点
		$("#password").blur(function() {
			$("#left_hand").attr("class", "initial_left_hand");
			$("#left_hand").attr("style", "left:100px;top:-12px;");
			$("#right_hand").attr("class", "initial_right_hand");
			$("#right_hand").attr("style", "right:-112px;top:-12px");
		});
		
		
		
		
	});
</SCRIPT>



<META name="GENERATOR" content="MSHTML 11.00.9600.17496">
</HEAD>

<BODY>
<form id="forms">
	<DIV class="top_div">
		<div style="position:absolute; width:100%; height:160px; top:20%;text-align:center;height:-130px; z-index:1000;color:#fff "> <h1>欢迎使用发票查询平台</h1> </div>
	</DIV>
	<DIV style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
		<DIV style="width: 165px; height: 96px; position: absolute;">
			<DIV class="tou"></DIV>
			<DIV class="initial_left_hand" id="left_hand"></DIV>
			<DIV class="initial_right_hand" id="right_hand"></DIV>
		</DIV>
		<P style="padding: 30px 0px 10px; position: relative;">
			<lable class="ts">手机号</lable><SPAN class="u_logo"></SPAN> <INPUT id="phone" class="ipt1" type="text"  name="data.loginname" value=""> <input type="button" class="ts1" onclick="message(this)" value="获取验证码"></input>
		</P>
		<P style="position: relative;">
			<input type="password" id="hid" style="display:none" value=""></input><lable class="ts">验证码</lable><SPAN class="p_logo"></SPAN> <INPUT class="ipt2" id="password" type="password"  name="data.pwd" value="">
		</P>
		<DIV style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
			<P style="margin: 0px 35px 20px 45px;">
				<SPAN style="float: center;">

					<A style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;" href="#"  onclick="loginFun();">确认</A> </SPAN>
			</P>
		</DIV>
	</DIV>
	<div style="text-align:center;">
		<p></p>
	</div>
	</form>
</BODY>
</HTML>

