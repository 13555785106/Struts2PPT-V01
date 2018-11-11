<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>radio</title>
<s:head />
</head>
<body>
	<h1>radio</h1>
	<s:form >
		<s:radio name="city" label="请选择您喜欢的城市" labelposition="top"
			list="{'北京', '上海', '广州'}"/>
		<s:radio name="country" label="请选择您想旅游的国家" labelposition="top"
			list="#{'cn':'中国','jp':'日本','gb':'英国','us':'美国','de':'德国','fr':'法国'}"
			listKey="key" listValue="value" />

	</s:form>
</body>
</html>