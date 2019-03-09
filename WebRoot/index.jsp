<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>接口测试工具</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="jquery-3.3.1.min.js">
	</script>
<script>
	'use strict';

	function addData() {
		var form = $('#form');
		form.append('<li><input type="text"></input>&nbsp;&nbsp;<input type="text"></input></li>');
	}
	function postAjax() {
		var form = document.getElementById('form');
		var map = new Map();
		var json = {};
		var lis = form.getElementsByTagName('li');
		for (var i = 0; i < lis.length; i++) {
			var inputs = lis[i].getElementsByTagName("input");

			json[inputs[0].value] = inputs[1].value;

		}
		console.log(json);
		var url = document.getElementById('url').value;

		console.log(url)

		function ajaxLog(s) {
			console.log(s);
		}


		/* 	$.ajax({
				type : "POST",
				url : url,
				data : JSON.stringify({
					phone:"15252478436",
					password:"1234567"
					
				}),
				
				
				dataType : "json", // 预期服务器返回的数据类型。如果不指定，jQuery 将自动根据 HTTP 包 MIME 信息来智能判断，比如XML MIME类型就被识别为XML。
		
				contentType : "application/json", //发送信息至服务器时内容编码类型。             
				headers:{
					token:token
				},
				success : function(data) {
					console.log("data= ", data);
			
				}
			}); */
	
		$.post(url, json)
		.done(function(data) {
			console.log('成功, 收到的数据: ' + JSON.stringify(data));
		});

	}
</script>
</head>

<body>

	<div>
		<button onClick="addData()">添加数据</button>
		<button onClick="">上传文件</button>
		<button onClick="postAjax()">提交</button>
		<br> <label>URL:</label> <input id="url" type="text"></input><br>
		
	</div>
	<div>
		<ul id="form">
			<li>
			<input value="token"></input>
			<input type="text" value="D0CBB1D88E9658DD26F6C12624E1AA96"></input>
			</li>
		</ul>
		<label id="test-response-text"></label>
	</div>

</body>
</html>
