<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>optgroup</title>
<s:head />
</head>
<body>
	<h1>optgroup</h1>
	<s:form>

		<s:select label="选择雇员" name="employee" size="20" list="#{}"
			listKey="key" listValue="value">
			<s:iterator value="departments" var="department">
				<s:set var="deps" value="%{getEmployees(#department.departmentId)}"/>
				<s:if test="%{#deps.size()>0}">
					<s:optgroup label="%{#department.departmentName}"
						list="#deps"
						listKey="employeeId" listValue="name" />
				</s:if>
			</s:iterator>
		</s:select>
	</s:form>
</body>
</html>