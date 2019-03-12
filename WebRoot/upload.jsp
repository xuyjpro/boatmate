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

<title>UPLOAD</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script src="jquery-3.3.1.min.js"></script>

<script>

	'use strict';



	function init() {
		console.log("hello")

		$("#addData").click(function() {

			var form = $('#uploadForm');
			form.append('<li><input type="text"></input>&nbsp;&nbsp;<input type="text"></input></li>');
		});

		$("#btnImportOK").click(function() {

			var formData = new FormData($("#uploadForm")[0]);
			$.ajax({
				type : "POST",
				data : formData,
				url : "/Home/Upload",
				contentType : false,
				processData : false,
			}).success(function(data) {

				console.log(data.msg);


			}).error(function(data) {
				alert(data);
				console.log(data);
			});

		});
		$("#picshow").hide();
	}
</script>

</head>

<body>

	<!-- <form id="uploadForm" action="Upload" method="post"
		enctype="multipart/form-data">
		<input id="File1" name="fileupload" accept="image/gif, image/jpeg"
			multiple="multiple" type="file" value="" /> <input id="btnImportOK"
			type="button" value="上传" />


	</form> -->
	<form action="publishStuff" method="post" enctype="multipart/form-data">
		<input name="token" value="D0CBB1D88E9658DD26F6C12624E1AA96"/>
		<input name="content" value="快点来吧丢了钥匙！！！"/>
		
		<input id="files" name="pictures" accept="image/gif, image/jpeg"
			multiple="multiple" type="file" value="" />
		
		 <input id=""
			type="submit" value="上传" />
	</form>

</body>
</html>
