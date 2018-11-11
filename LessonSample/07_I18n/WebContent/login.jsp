<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>login-input</title>
</head>
<body>
	<s:i18n name="org.sample.action.user.LoginAction">
		<s:text name="system.name" />
		<s:form action="/user/login">
			<s:actionerror />
			<s:textfield name="account" key="account.label" />
			<s:password name="passwd" key="passwd.label" />
			<s:submit name="login" key="login.label" />
		</s:form>
	</s:i18n>
</body>
</html>