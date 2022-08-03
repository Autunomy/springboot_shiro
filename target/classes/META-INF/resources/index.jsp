<%@ page contentType="text/html;utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
    <h1>主页</h1>
    <a href="${pageContext.request.contextPath}/user/logout">注销</a>

    <ul>
        <%--只有user和admin才能看见--%>
        <shiro:hasAnyRoles name="user,admin">
            <li>
                <a href="">用户管理</a>
                <ul>
                    <shiro:hasPermission name="user:add:*">
                        <li><a href="">添加用户</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:select:*">
                        <li><a href="">查询用户</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:update:*">
                        <li><a href="">修改用户</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:delete:*">
                        <li><a href="">删除用户</a></li>
                    </shiro:hasPermission>
                </ul>
            </li>

        </shiro:hasAnyRoles>
        <%--具有admin角色用户才能看见--%>
        <shiro:hasRole name="admin">
            <li><a href="">商品管理</a></li>
            <li><a href="">订单管理</a></li>
            <li><a href="">物流管理</a></li>
        </shiro:hasRole>
    </ul>
</body>
</html>