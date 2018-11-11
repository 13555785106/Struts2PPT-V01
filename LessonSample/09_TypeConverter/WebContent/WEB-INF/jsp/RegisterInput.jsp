<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RegisterUser</title>

</head>
<body>
	<s:fielderror />
	<s:form theme="simple">
		<s:generator separator="," val="user.hobbies" var="strHobbies" />
		<table>
			<tr>
				<td>账号:</td>
				<td><s:textfield name="user.account" /></td>
				<td><s:iterator value="fieldErrors['user.account']">
						<s:property />
					</s:iterator></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><s:textfield name="user.passwd" /></td>
				<td><s:iterator value="fieldErrors['user.passwd']">
						<s:property />
					</s:iterator></td>
			</tr>
			<tr>
				<td>生日:</td>
				<td><s:textfield name="user.birthday" /></td>
				<td><s:iterator value="fieldErrors['user.birthday']">
						<s:property />
					</s:iterator></td>
			</tr>
			<tr>
				<td>爱好:</td>
				<td><s:checkboxlist name="user.hobbies"
						list="{'足球', '篮球', '排球'}"
						value="#strHobbies.{?#this.length()==2}.{#this}" /></td>
				<td><s:iterator value="fieldErrors['user.hobbies']">
						<s:property />
					</s:iterator></td>
			</tr>
			<tr>
				<td colspan="3" align="center"><s:submit value="确定" /></td>
			</tr>
		</table>
	</s:form>
	<s:debug/>
</body>
</html>