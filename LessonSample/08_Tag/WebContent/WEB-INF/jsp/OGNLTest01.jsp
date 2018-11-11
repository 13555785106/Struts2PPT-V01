<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OGNL中访问静态成员</title>
</head>
<body>
<h1>OGNL中访问静态成员</h1>
<s:property value="@java.lang.System@getenv()" /><br/>
<s:property value="@java.lang.Math@PI" /><br/>

</body>
</html>