<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>�ɹ�ҳ��</title>
</head>
<body>
	��վ���ʴ���Ϊ��${applicationScope.counter}<br/>
	${sessionScope.user}�����Ѿ���¼��<br/>
	${requestScope.tip}<br/>
	��ϵͳ��ȡCookieֵ��${cookie.user.value}
</body>
</html>