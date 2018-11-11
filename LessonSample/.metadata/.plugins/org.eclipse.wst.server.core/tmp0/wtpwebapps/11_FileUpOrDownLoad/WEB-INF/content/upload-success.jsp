<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传成功</title>
</head>
<body>
	上传成功!
	<br /> 文件标题:
	<s:property value=" + title" />
	<br /> 文件为：
	<a href="<s:property value="'uploadFiles/' + uploadFileName"/>">${uploadFileName}</a>
	<br />
</body>
</html>