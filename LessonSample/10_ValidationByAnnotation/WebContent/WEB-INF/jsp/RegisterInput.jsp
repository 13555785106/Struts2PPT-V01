<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RegisterUser</title>
<s:head />
</head>
<body>
<s:property value="fieldErrors" />
	<s:form validate="true">
	
		<s:generator separator="," val="hobbies" var="strHobbies" />
		<s:textfield name="account" label="账号" requiredLabel="true" requiredPosition="left" />
		<s:textfield name="passwd" label="密码" requiredLabel="true" requiredPosition="left" />
		<s:textfield name="retryPasswd" label="重复密码" requiredLabel="true" requiredPosition="left" />
		<s:textfield name="mobile" label="手机" />
		<s:textfield name="email" label="邮箱" />
		<s:textfield name="salary" label="工资" />
		<s:textfield name="birthday" label="生日" />
		<s:checkboxlist name="hobbies" label="爱好" requiredLabel="true" requiredPosition="left" list="{'足球', '篮球', '排球','煤球'}" value="#strHobbies.{?#this.length()==2}.{#this}" />
		<s:submit value="确定" />
	</s:form>
	<s:debug />
</body>
</html>