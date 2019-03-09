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

<title>My JSP 'upload.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form method="post" action="editUserInfo"
		enctype="multipart/form-data">
		选择一个文件: <input type="file" name="headPic" /> <br/> <br/>
		
		<input type="text" name="nickname">
		<input type="text" name="token" value="D0CBB1D88E9658DD26F6C12624E1AA96">
		<input type="text" name="">
		<input type="text" name="">
		
		
		 <input type="submit" value="上传" />
	</form>
</body>
</html>
