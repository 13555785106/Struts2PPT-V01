<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>optiontransferselect</title>
<s:head />
</head>
<body>
	<h1>optiontransferselect</h1>
	<s:form>
		<s:optiontransferselect 
		label="请选择雇员" 
		name="willSelectEmployee"
		leftTitle="雇员列表"
		rightTitle="已选雇员"
		list="%{getEmployees(80)}" 
		listKey="employeeId"
		listValue="name"
		multiple="true"
		headerKey="hk"
		headerValue="待选择的雇员"
		addToLeftLabel="向左移动"
		addToRightLabel="向右移动"
		selectAllLabel="全部选择"
		addAllToRightLabel="全部右移"
		addAllToLeftLabel="全部左移"
		leftDownLabel="下移"
		leftUpLabel="上移"
		rightDownLabel="下移"
		rightUpLabel="上移"
		doubleName="selectedEmployee"
		doubleHeaderKey="dhk"
		doubleHeaderValue="已经选择的雇员"
		doubleList="%{getEmployees(50)}"
		doubleListKey="employeeId" 
		doubleListValue="name" />
		<s:token/>
	</s:form>
</body>
</html>