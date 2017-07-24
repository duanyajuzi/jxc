<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>贸易信息管理系统</title>
    <script src="<c:url value='/jslib/jquery/jquery-2.1.1-min.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/jslib/theme/sea/login.css'/>" >
</head>
<body>
<form id="loginform" action="${ctx}/user/login" method="post" onsubmit="return funSubmitInfo()">
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
                    <input type="text" id="userName" value="${userName}"  name="userName">
                    <input type="password" id="userPwd" value="" name="userPwd">
                </form>
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
            $("#loginform").submit();
            //window.location.href = "<c:url value='/admin/main'/>";
        }
        function funSubmitInfo()
        {
            if(document.getElementById("userName").value == "")
            {
                alert("登录用户名不能为空！");
                return false;
            }
            else if(document.getElementById("userPwd").value == "")
            {
                alert("登录密码不能为空！");
                return false;
            }
            return true;
        }


        $(function(){
            <c:if test="${not empty errorinfo}">
            alert("${errorinfo}");
            </c:if>
        });
    </script>
</body>
</html>
