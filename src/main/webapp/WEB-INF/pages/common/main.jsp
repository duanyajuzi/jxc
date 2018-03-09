<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String name=request.getParameter("username");%>
<html>
<head>
    <title>鼐威欣贸易信息管理系统</title>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <link href="<c:url value='/jslib/theme/sea/mainframe.css'/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='/jslib/scroll/jquery.mCustomScrollbar.css'/>" rel="stylesheet" type="text/css"/>
    <script src="<c:url value='/jslib/scroll/jquery.mCustomScrollbar.js'/>"></script>
    <style>
        *{
            padding:0;
        }
        ol{
            cursor: pointer;
        }
        .nav1,.nav2,.nav3{
            display:none;
        }
        ol>li{
            transition: background 0.6s;
            color:#ffffff;
            list-style-type: none;
            font-size: 16px;
            text-align: center;
        }
        ol>li>div:hover{
            background: #43AAD7;
        }
        .nav1>li {
            background: #35404d;
            display: block;
            line-height: 30px;
            transition: color 0.6s;
        }
        img{
            margin-left:10px;
            margin-bottom:-2px;
        }
        .nav1 li{
            padding:4px 0;
            font-size: 14px;
        }
        .nav1 li:hover{
            background: #43AAD7;
        }
        .current{
            background: #43AAD7 !important;
        }
    </style>
</head>
<body style="margin:0;padding:0;overflow:hidden;">

<table id="toptab" style="background:rgba(0,36,78,.9);">
    <tbody>
    <tr>
        <td id="top_left" style="height:48px;width:530px;">
            <span id="xtname"></span>
        </td>
        <td id="top_sys"></td>
        <td id="top_right" style="height:100%;width:150px;">
            <div id="user">
                <div class="user-logo"></div>
                <b id="jt"></b>
                <div class="user-info">
                    <input type="hidden" value="" id="lesuserid">
                    <p id="username">${OnLineUser.userName}</p>
                    <p id="realname">(${OnLineUser.realName})</p>
                </div>
                <div class="userid">
                    <ul id="userManage">
                        <li class="config" id="set_user">设置 </li>
                        <li id="exit">退出</li>
                    </ul>
                </div>
            </div>
        </td>
    </tr>
    </tbody>
</table>


<div id="menubar"
     style="position:absolute;top:0;left:0;z-index:1;width:1px;height:1px;overflow:hidden;">
</div>
<div class="menu_change menu_open" data-state="0" style="display:none;">
</div>
<div id="menudiv"
     style="position:absolute;top:0;left:0;z-index:1;width:1px;height:1px;overflow:hidden;overflow-y:auto;border-right:1px solid #ccc;">
    <div class="menu_container">
        <ol>
            <c:forEach items="${powerList}" var="power">
                <c:if test="${power.leaf == 1}">
                <li class="pli">
                    <div style="padding: 6px 0;">
                        <img src="<c:url value='/jslib/theme/sea/images/sy.png'/>" >
                        <span id="span1">${power.powerName}</span>
                        <img class="ge_img" src="<c:url value='/jslib/theme/sea/images/next_0.png'/>" style="float: right;padding:6px;">
                    </div>
                    <ul class="nav1">
                    <c:forEach items="${powerList}" var="leafPower">
                        <c:if test="${power.id == leafPower.pid}">
                            <li onclick="PageMain.funAddTab(this, '<c:url value="${leafPower.url}"/>')">${leafPower.powerName}</li>
                        </c:if>
                    </c:forEach>
                    </ul>
                </li>
                </c:if>
            </c:forEach>
        </ol>
    </div>
</div>

<div id="pagediv" style="position:absolute;z-index:2;width:100%;height:100%;overflow:hidden;">
    <div id="pageTabs" class="mini-tabs" activeIndex="0" style="width:100%;height:100%; " plain="true">
    </div>
</div>
</body>

<script>
    var PageMain = {
        menuFlag:true,
        init : function()
        {
            this.menu_state = 1;
            var me = this;
            $(window).resize(function(){
                me.funResize();
            });

            $(".pli div").bind("click", function(){
                if($(this).attr("tag") == "true")
                {
                    $(this).attr("tag", false);
                    $(this).parent().find("ul").hide();
                    $(this).find(".ge_img").attr("src", "<c:url value='/jslib/theme/sea/images/next_0.png'/>");
                }
                else
                {
                    $(this).attr("tag", true);
                    $(this).find(".ge_img").attr("src", "<c:url value='/jslib/theme/sea/images/next_1.png'/>");
                    $(this).parent().find("ul").show();
                }
            });
            $(".nav1 li").bind("click", function(){
                $(".nav1 li").removeClass("current");
                $(this).addClass("current");
            });
            me.funResize();

            //左边菜单栏是否收起来
            $('.menu_change').on('click', function (e) {
                var $this = $(this);
                var state = $this.attr("data-state");
                me.menu_state = state;
                if (state == "1") {
                    $this.removeClass('menu_close').addClass('menu_open');
                    $this.attr("data-state", "0");
                } else {
                    $this.attr("data-state", "1");
                    $this.removeClass('menu_open').addClass('menu_close');
                }
                //$(window).resize();
                me.funResize();
            });


            $("#userManage,.user-logo").bind("mouseover", function(){
                PageMain.menuFlag = false;
            });
            $("#userManage,.user-logo").bind("mouseout", function(){
                PageMain.menuFlag = true;
                PageMain.funMenuInfo();
            });
            $(".user-logo").bind("click", function(){
              $("#userManage").show();
              $("#jt").css("display", "inline");
                PageMain.funMenuInfo();
            });
            $("#exit").bind("click", function(){
               window.location.href = "<c:url value='/user/logout'/>";
            });
           $(".menu_container").mCustomScrollbar();
        },
        funMenuInfo : function()
        {
            window.setTimeout(function(){
                if(PageMain.menuFlag)
                {
                    $("#userManage").hide();
                    $("#jt").css("display", "none");
                }
            },1000);
        },
        funResize : function()
        {
            var menu_width = 193;
            var top_height = $("#toptab").height();
            var window_height = $(window).height();
            var window_width = $(window).width();

            $(".menu_change").show();
            if (this.menu_state == 1) {
                $('#menubar').css({top: top_height, left: 0, width: '6px', height: window_height + 'px'});
                $("#menudiv").css({
                    "width":menu_width,
                    "height":window_height - top_height,
                    "top":top_height+1
                });

                $('#pagediv').css({
                    top: top_height,
                    left: (menu_width + 6) + 'px',
                    width: (window_width - menu_width - 6 - 1) + 'px',
                    height: (window_height - top_height) + 'px'
                });
                $('#menudiv').show();
                $(".menu_change").css({left: menu_width});
            }
            else
            {
                $('#menubar').css({top: top_height, left: 0, width: '16px', height: window_height + 'px'});
                $('#menudiv').hide();
                $('.menu_change').show();
                $(".menu_change").css({left: 0});
                $('#pagediv').css({
                    top: top_height,
                    left: '16px',
                    width: (window_width - 16 - 1) + 'px',
                    height: (window_height - top_height) + 'px'
                });
            }
            $(".menu_change").css({height: $('#menudiv').height()});
            $(".menu_change").css({"line-height": $('#menudiv').height()});
            mini.parse();
        },
        funAddTab : function(obj, strUrl){
            var pageTabs = mini.get("pageTabs");
            var tab = pageTabs.getTab($(obj).text());
            if(!tab)
            {
                tab = { title: $(obj).text(), name:$(obj).text(), url: strUrl, showCloseButton: true };
                pageTabs.addTab(tab);
            }
            pageTabs.activeTab(tab);
        }
    };

    $(function(){
        PageMain.init();
    })

</script>
</html>
