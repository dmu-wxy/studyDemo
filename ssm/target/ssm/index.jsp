<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/8/17
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="account/findAll">查看用户</a>
    <h3>测试保存</h3>
    <form method="post" action="account/saveAccount">
        姓名：<input type="text" name="name" /><br>
        金额：<input type="text" name="money" /><br>
        <input type="submit" value="保存" />
    </form>
</body>
</html>
