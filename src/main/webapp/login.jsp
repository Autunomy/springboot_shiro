<%@ page contentType="text/html;utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>用户登陆</h1>
<form action="${pageContext.request.contextPath}/user/login" method="post">
    用户名:<input type="text" name="username"> <br>
    密码  :<input type="password" name="password"> <br>
    请输入验证码:<input type="text" name="code">
    <img src="${pageContext.request.contextPath}/user/getImage" alt=""> <br>
    <button>登陆</button>
</form>
</body>
</html>