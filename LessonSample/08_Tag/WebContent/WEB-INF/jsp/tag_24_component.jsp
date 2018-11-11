<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>component</title>
<s:head />
</head>
<body>
	<h1>component</h1>
	<s:form>
		<s:component template="template01.jsp">
			<s:param name="departments" value="departments" />
		</s:component>


	</s:form>
	<s:component templateDir="template2" theme="theme2"
		template="template01.jsp">
		<s:param name="departments" value="departments" />
	</s:component>
</body>
</html>