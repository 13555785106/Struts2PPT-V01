<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Login</title>
</head>

<body>
<s:form action="login!execute">
    <s:textfield label="账号" key="account" />
    <s:password label="密码" key="passwd" />
    <s:submit/>
</s:form>
</body>
</html>
