<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value='/jslib/theme/sea/login.css'/>" >
</head>
<body>
<div class="login_bg">
    <div class="border"></div>
    <div class="bgd"></div>
    <div class="content">
        <!--<img src="img/logo.png" alter="logo">-->
        <div>
            <h1 id="con_title">贸易信息管理系统</h1>
            <p class="ver">version&nbsp1.0</p>
        </div>
        <div>
            <form>
            <input type="text" id="txt_user" value=""  name="username">
            <input type="text" id="txt_password" value="">
            </form>
            <p class="user">注册账号</p>
            <p class="password">忘记密码？</p>
        </div>
        <div>
            <input type="button" id="btn_login" value="登录" onclick="funLogin()">
            <input type="button" id="btn_reset" value="重置">
            <p class="about">关于鼐威欣</p>
        </div>
    </div>
</div>
<div class="bg">
    <img src="<c:url value='/jslib/theme/sea/images/bg.png'/>"  alter="">
</div>
<script>
    function funLogin()
    {
        window.location.href = "<c:url value='/admin/main'/>";
    }

</script>
</body>
</html>
