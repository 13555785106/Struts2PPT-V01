<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>date</title>
</head>
<body>
	<h1>date</h1>
	<s:bean name="java.util.Date" var="now">
		<s:param name="year" value="115" />
	</s:bean>
	<s:date name="#now" />
	<br />
	<s:date name="#now" nice="true" />
	<br />
	<s:date name="#now" format="yyyy-MM-dd HH:mm:ss.SSS" />
	<br />

</body>
</html>