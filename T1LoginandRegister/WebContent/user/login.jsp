<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>登录界面</h1>
<form action="${pageContext.request.contextPath }/LoginServlet" method="post">
<p style="color:red;font-weight: 900">${msg }</p>
  用户名:<input type="text" name="username"></br>
  密  码:<input type="text" name="password"></br>
  <input type="submit" value="登录"></br>
</form>
<a href="regist.jsp">注册账号</a>
</body>
</html>