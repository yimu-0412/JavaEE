<%--
  Created by IntelliJ IDEA.
  User: 13116
  Date: 2022/2/26
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script>
    var userList = new Array();
    userList.push({name:"zhangsan",age:19});
    userList.push({name:"lisi",age:45});

    $.ajax({
        type:"post",
        contentType:"application/json;charset=UTF-8",
        url:"${pageContext.request.contextPath}/user/quick5",
        data:JSON.stringify(userList),
    });
</script>
</body>
</html>
