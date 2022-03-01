<%--
  Created by IntelliJ IDEA.
  User: 13116
  Date: 2022/2/26
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user/uploadFile2" method="post" enctype="multipart/form-data">
        名称 <input type="text" name="name"><br>
        文件 <input type="file" name="uploadFile1">
        <br>
        名称 <input type="text" name="name"><br>
        文件 <input type="file" name="uploadFile2">
        <br>
        <input type="submit" name="提交">
    </form>
</body>
</html>
