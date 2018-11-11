<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IfElse</title>
</head>
<body>
<h1>IfElse</h1>
<s:set var="score" value="70"/>
分数：<s:property value="#score"/><br/>
等级：<s:if test="#score>100 || #score<0">
分数无效<br/>
</s:if>
<s:elseif test="#score>=90">
A<br/>
</s:elseif>
<s:elseif test="#score>=80">
B<br/>
</s:elseif>
<s:elseif test="#score>=70">
C<br/>
</s:elseif>
<s:elseif test="#score>=60">
D<br/>
</s:elseif>
<s:else>
E<br/>
</s:else>
</body>
</html>