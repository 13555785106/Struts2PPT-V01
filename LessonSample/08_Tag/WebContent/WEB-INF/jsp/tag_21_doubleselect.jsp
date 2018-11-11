<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>doubleselect</title>
<s:head />
</head>
<body>
	<h1>doubleselect</h1>
	<s:form>
		<s:doubleselect label="请选择雇员姓名" name="department"
			doubleName="employee" list="departments" listKey="departmentId"
			listValue="departmentName"
			doubleList="%{getEmployees(top.departmentId)}"
			doubleListKey="employeeId" doubleListValue="name" />
	</s:form>
</body>
</html>