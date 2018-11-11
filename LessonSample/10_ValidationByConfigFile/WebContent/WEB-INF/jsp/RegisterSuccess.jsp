<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RegisterUser</title>
<s:head/>
</head>
<body>
账号：<s:property value="user.account"/><br/>
密码：<s:property value="user.passwd"/><br/>
手机：<s:property value="user.mobile"/><br/>
邮件：<s:property value="user.email"/><br/>
工资：<s:property value="user.salary"/><br/>
生日：<s:date name="user.birthday" format="yyyy年MM月dd日"/><br/>
爱好：<s:property value="user.hobbies"/><br/>
<s:fielderror/>
<s:actionmessage/>
<s:actionerror/>
<a href="register!input">返回</a>
</body>
</html>