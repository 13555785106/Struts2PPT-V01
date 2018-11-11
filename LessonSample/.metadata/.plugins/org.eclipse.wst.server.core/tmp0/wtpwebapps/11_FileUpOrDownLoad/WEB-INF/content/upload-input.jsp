<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简单的文件上传</title>
</head>
<body>
	<s:form action="upload" enctype="multipart/form-data">
		<s:textfield name="title" label="文件标题" />
		<s:file name="upload" label="选择文件" />
		<s:submit value="上传" />
	</s:form>
</body>
</html>