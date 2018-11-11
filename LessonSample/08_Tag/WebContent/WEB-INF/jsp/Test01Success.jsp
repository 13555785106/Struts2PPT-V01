<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test01</title>
</head>
<body>
	<s:property value="author" />
	<br /> ${requestScope["struts.valueStack"]}
	<br />
	<br />
	${requestScope["struts.valueStack"].context["com.opensymphony.xwork2.util.ValueStack.ValueStack"]}
	<br />
	<br />
	<br />
	<c:forEach items='${requestScope["struts.valueStack"].root}' var="e">
--${e}--<br />
	</c:forEach>
	<br />
	<br />
	<br />
	<c:forEach items='${requestScope["struts.valueStack"].context}'
		var="entry">
${entry.key}<br />
	</c:forEach>

	<s:debug />

</body>
</html>