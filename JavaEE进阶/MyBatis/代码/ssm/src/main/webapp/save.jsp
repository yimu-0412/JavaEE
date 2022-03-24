<%--
  Created by IntelliJ IDEA.
  User: 13116
  Date: 2022/3/24
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>添加账户信息</h1>
    <form name="accountForm" action="${pageContext.request.contextPath}/account/save" method="post">
         账户名称：<input type="text" name="name"><br>
        账户金额：<input type="text" name="money"><br>
                    <input type="submit" value="保存">
    </form>
</body>
</html>
