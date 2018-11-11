<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<select>
	<s:iterator value="%{top.parameters.departments}" var="dep">
		<option value="${dep.departmentId}">
			<s:property value="#dep.departmentName" />
		</option>
	</s:iterator>
</select>
