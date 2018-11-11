<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>iterator</title>
</head>
<body>
	<h1>iterator</h1>
	<s:set
		value="{
	#{'name':'John','sex':'男','age':28},
	#{'name':'Tom','sex':'男','age':36},
	#{'name':'Jones','sex':'女','age':28},
	#{'name':'Gavin','sex':'男','age':28}}"
		var="students" />
	<table>
		<tr>
			<th>序号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>年龄</th>
		</tr>
		<s:iterator value="#students" var="student" status="st">
			<tr>
				<td><s:property value="#st.count" /></td>
				<td><s:property value="#student.name" /></td>
				<td><s:property value="#student.sex" /></td>
				<td><s:property value="#student.age" /></td>
			</tr>
		</s:iterator>
	</table>
	<table>
		<tr>
			<th>序号</th>
			<th>姓名</th>
			<th>邮件</th>
			<th>工资</th>
		</tr>
		<s:iterator value="employees" var="employee" status="st">
			<tr>
				<td><s:property value="#st.count" /></td>
				<td><s:property value="#employee.name" /></td>
				<td><s:property value="#employee.email" /></td>
				<td><s:property value="#employee.salary" /></td>
			</tr>
		</s:iterator>
	</table>
	<s:debug />

</body>
</html>