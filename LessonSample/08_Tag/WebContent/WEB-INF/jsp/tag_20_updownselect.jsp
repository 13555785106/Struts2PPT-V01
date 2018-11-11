<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>updownselect</title>
<s:head />
</head>
<body>
	<h1>updownselect</h1>
<s:form>

<s:updownselect name="department" label="请选择部门名称"
	labelposition="top"
	moveUpLabel="向上移动"
	moveDownLabel="向下移动"
	list="departments"
	listKey="departmentId"
	listValue="departmentName"
	selectAllLabel="全部选择"
	cssStyle="width:240px"
	/>

</s:form>
</body>
</html>