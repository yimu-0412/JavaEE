<%--
  Created by IntelliJ IDEA.
  User: 13116
  Date: 2022/2/26
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user/quick4" method="post">
        <input type="text" name="userList[0].name"> <br>
        <input type="text" name="userList[0].age"> <br>
        <input type="text" name="userList[1].name"><br>
        <input type="text" name="userList[1].age"><br>
        <input type="submit" value="提交"><br>
    </form>
</body>
</html>
