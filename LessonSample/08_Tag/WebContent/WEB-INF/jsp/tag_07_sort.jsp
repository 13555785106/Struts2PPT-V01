<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sort</title>
</head>
<body>
	<h1>sort</h1>
	<s:bean var="strLengthComparator" name="com.sample.StrLengthComparator" />
	<s:sort source="{'00','01','012','0123','01234','012345'}"
		comparator="#strLengthComparator" var="descList" />
	<s:iterator value="#attr.descList">
		<s:property /><br/>
	</s:iterator>
</body>
</html>