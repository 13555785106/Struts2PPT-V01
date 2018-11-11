<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>property</title>
</head>
<body>
	<h1>property</h1>
	<s:set var="str" value="'<span style=\u0022color:red\u0022>ABC</span>'"/>
	<s:property value="str"/>
	<s:property value="str" escapeHtml="false"/>
	<s:debug />
</body>
</html>