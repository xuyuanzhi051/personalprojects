<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function _change(){
	var img=document.getElementById("img");
	img.src="/T1LoginandRegister/VerifyCodeServlet?a="+new Date().getTime();
}
</script>
</head>
<body>
<h1>用户注册</h1>
<p style="color:red ;font-weight: 900">${msg }</p>
<form action="${ pageContext.request.contextPath }/RegistServlet" method="post">
    用户名:<input type="text" name="username">${errors.username }</br>
    密   码:<input type="text" name="password"> ${errors.password }</br>
    验证码:<input type="text" name="verifyCode" size=3>
    <img id="img" src="/T1LoginandRegister/VerifyCodeServlet">
    <a href="javascript:_change()">换一张</a></br>
    <input type="submit" value="注册">
</form>
</body>
</html>