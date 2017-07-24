<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String name=request.getParameter("username");%>
<html>
<head>
    <title>鼐威欣贸易信息管理系统</title>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <link href="<c:url value='/jslib/theme/sea/mainframe.css'/>" rel="stylesheet" type="text/css"/>

    <style>
        /*新增右下角弹窗样式*/
        .window {
            /*position: fixed;
            bottom: 10px;
            right: 10px;*/
            width: 100%;
            background-color: #fbfdff;
            /*box-shadow: 0 0 8px 2px #c7c7c7;*/
            font-family: "微软雅黑";
            font-size: 14px;
        }

        .window-title {
            height: 35px;
            line-height: 35px;
            margin: 0;
            background-color: #509be1;
        }

        .window-title-icon {
            display: inline-block;
            height: 18px;
            width: 18px;
            margin-bottom: -3px;
            margin-left: 13px;
            margin-right: 5px;
        }

        .window-title-text {
            color: #fff;
            font-size: 16px;
            padding-right: 10px;
        }

        .window-icon {
            float: right;
            width: 35px;
            height: 35px;
        }

        .window-icon:hover {
            background-color: #d44027;
        }

        .window-content {
            padding: 10px;
            line-height: 23px;
        }

        .td-label {
            width: 70px;
            text-align: right;
            font-weight: 700;
            color: #fa4a03;
            vertical-align: top;
        }

        .color-green {
            color: #24ca07;
        }

        .window-foot {
            overflow: hidden;
            text-align: right;
            background: url(/aj-zhyy/app/index1/img/split-line.png) left top no-repeat;
            padding: 10px;
        }

        a.window-foot-link {
            float: right;
            color: #fff;
            text-decoration: none;
            background-color: #509be1;
            padding: 5px 15px;
            margin-right: 15px;
            border-radius: 8px;
        }

        .window-foot-link:hover {
            color: #000;
        }

        .mini-tabs-bodys{
            border:0px;
        }
        *{
            padding:0;
            /*margin:0;
            font-family: "Microsoft YaHei";
            font-size:16px;
            list-style: none;
            color: #bbbbbb;*/
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
        /*span {
            width: 163px;
            padding-left:4px;
            line-height: 30px;
            padding-bottom:4px;
            transition: color 0.3s;
            color: #fafafa;
        }*/
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
        <td id="top_right" style="height:100%;width:130px;">
            <div id="user">
                <div class="user-logo"></div>

                <b id="jt"></b>

                <div class="user-info">

                    <input type="hidden" value="" id="lesuserid">

                    <p id="username"><%=name%></p>

                    <p id="realname">(沈云)</p>


                </div>
                <div class="userid">
                    <ul id="userManage">


                        <li class="config" id="set_user">
                            设置
                        </li>
                        <li id="exit">
                            退出
                        </li>
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
    <div class="mCSB_container menu_container">
        <ol>
            <c:forEach items="${powerList}" var="power">
                <c:if test="${power.leaf == 1}">
                <li class="pli">
                    <div style="padding: 6px 0;">
                        <img src="<c:url value='/jslib/theme/sea/images/sy.png'/>" >
                        <span id="span1">${power.powerName}</span>
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

    <%--<div class="mCSB_container menu_container">--%>
        <%--<div onclick="PageMain.funAddTab(this, '<c:url value="/pages/baseinfo/dict.jsp"/>')" style="color: white; cursor: pointer;">字典</div>--%>
        <%--<div onclick="PageMain.funAddTab(this, '<c:url value="/pages/baseinfo/dm.jsp"/>')" style="color: white; cursor: pointer;">编码</div>--%>
        <%--<div onclick="PageMain.funAddTab(this, '<c:url value="/pages/sysinfo/power.jsp"/>')" style="color: white; cursor: pointer;">权限</div>--%>
        <%--<div onclick="PageMain.funAddTab(this, '<c:url value="/pages/sysinfo/user.jsp"/>')" style="color: white; cursor: pointer;">用户</div>--%>
        <%--<div onclick="PageMain.funAddTab(this, '<c:url value="/pages/sysinfo/role.jsp"/>')" style="color: white; cursor: pointer;">角色</div>--%>
        <%--<div onclick="PageMain.funAddTab(this, '<c:url value="/pages/baseinfo/business.jsp"/>')" style="color: white; cursor: pointer;">业务类型</div>--%>
        <%--<div onclick="PageMain.funAddTab(this, '<c:url value="/pages/baseinfo/customer.jsp"/>')" style="color: white; cursor: pointer;">客户</div>--%>
        <%--<div onclick="PageMain.funAddTab(this, '<c:url value="/pages/orderinfo/goods.jsp"/>')" style="color: white; cursor: pointer;">商品</div>--%>
        <%--<div onclick="PageMain.funAddTab(this, '<c:url value="/pages/orderinfo/blueprint.jsp"/>')" style="color: white; cursor: pointer;">商品销售单价方案</div>--%>
        <%--<div onclick="PageMain.funAddTab(this, '<c:url value="/pages/orderinfo/order.jsp?orderType=0"/>')" style="color: white; cursor: pointer;">采购订单</div>--%>
        <%--<div onclick="PageMain.funAddTab(this, '<c:url value="/pages/orderinfo/order.jsp?orderType=1"/>')" style="color: white; cursor: pointer;">出货订单</div>--%>
        <%--<div onclick="PageMain.funAddTab(this, '<c:url value="/pages/orderinfo/order_add.jsp"/>')" style="color: white; cursor: pointer;">订单详情</div>--%>
    <%--</div>--%>

</div>

<div id="pagediv" style="position:absolute;z-index:2;width:100%;height:100%;overflow:hidden;">
    <div id="pageTabs" class="mini-tabs" activeIndex="0" style="width:100%;height:100%; " plain="true">
    </div>
</div>
</body>

<script>
    var username1=<%=name%>;
    var PageMain = {
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
                }
                else
                {
                    $(this).attr("tag", true);
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
            console.log(tab)
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
