<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/sysinfo/js/power_add.js'/>"></script>
    <title></title>
    <style>
        .mini-grid-cell-inner, .mini-grid-headerCell-inner {
            padding: 3px 0 !important;
        }
        .mini-textbox-border{
            height: 24px;
        }
        .mini-textbox-input{
            height: 24px;
            line-height: 24px;
        }
        .mini-textbox{
            height: 27px;
        }

        html, body {
            margin: 0px;
            padding: 0;
            border: 0;
            width: 100%;
            height: 100%;
            overflow: hidden;
        }
        *{
            padding:0px;
            margin:0;
        }
        .mask{
            width:100%;
            height:100%;
            background: #000000;
            margin:0;
            padding:0;
            opacity: 0.4;
            filter:alpha(opacity:40);
            position:fixed;
            top:0;
            left:0;
            border:none;
            display:none;
        }
        #chooseWindow{
            position:absolute;
            left:50%;
            top:50%;
            margin-left:-250px;
            margin-top:-100px;
            background: #ffffff;
            width:500px;
            height:300px;
            display:none;
        }
        .chooseTitle{
            width:inherit;
            height:40px;
            background: navajowhite;
            padding-top: 12px;
            padding-bottom: 0;
        }
        .chooseTitle p{
            margin-left:20px;
            font-size: 22px;
            letter-spacing: 0.05em;
            font-family: 微软雅黑;
            color:#333333;
        }
        #closeIcon{
            position: absolute;
            right:20px;
            top:14px;
        }
        .chooseIcon{
            width:499px;
            border:1px solid #cccccc;
            border-top:transparent;
        }
        .chooseIcon li{
            float:left;
            width:98px;
            height:120px;
            list-style: none;
            position: relative;
            cursor: pointer;
        }
        .chooseIcon li img{
            position: relative;
            left:50%;
            top:60%;
            align:center;
            margin-left:-18px;
            margin-top:36px;
            margin-bottom:10px;
        }
        .chooseIcon li p{
            text-align: center;
            font-size: 14px;
            font-family: 微软雅黑;
            color:#333333;
        }
    </style>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
        <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div id="powerFormAdd">
       
                	<input id="id" name="id"  class="mini-hidden" />
            <table class="form-table" border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;">
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">权限名称：</td>
                    <td style="width:32%;">
                        <input name="name1" id="name1" class="mini-textbox" style="width:200px;"  required="true" maxlength="60" requiredErrorText="权限名称不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">权限编号：</td>
                    <td style="width:32%;">
                        <input name="powerNo" id="powerNo" class="mini-textbox" style="width:200px;"  required="true" maxlength="40" requiredErrorText="权限编号不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">父节点权限名称：</td>
                    <td style="width:32%;">
                        <input name="pid" id="pid" value="" class="mini-textbox" style="width:200px;" maxlength="10" />
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">链接地址：</td>
                    <td style="width:32%;">
                        <input name="url" id="url" class="mini-textbox" style="width:200px;"  required="true" maxlength="100" requiredErrorText="链接地址不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">显示图标：</td>
                    <td style="width:32%;">
                        <%--<input name="icon" id="icon" class="mini-textbox" style="width:200px;"  required="true" maxlength="100" requiredErrorText="显示图标不能为空"/>--%>
                            <input type="button" id="btn1" value="选择图标">
                            <div id="mask"></div>
                            <div id="chooseWindow">
                                <div class="chooseTitle">
                                    <p>选择图标</p><a id="closeIcon"><img src="img/CLOSE.png"></a>
                                </div>
                                <div class="chooseIcon">
                                    <ul>
                                        <li id="icon1"><img src="img/ARROW.png"><p>用户管理</p></li>
                                        <li id="icon2"><img src="img/BOOK.png"><p>角色管理</p></li>
                                        <li id="icon3"><img src="img/BOOK-2.png"><p>权限管理</p></li>
                                        <li id="icon4"><img src="img/CHART.png"><p>商品管理</p></li>
                                        <li id="icon5"><img src="img/COG.png"><p>采购订单管理</p></li>
                                        <li id="icon6"><img src="img/DOCUMENTS.png"><p>出货订单管理</p></li>
                                        <li id="icon7"><img src="img/FOLDER.png"><p>代码项管理</p></li>
                                        <li id="icon8"><img src="img/NOTEPAD.png"><p>字典管理</p></li>
                                        <li id="icon9"><img src="img/PEN.png"><p>厂商管理</p></li>
                                        <li id="icon10"><img src="img/TAGS.png"><p>业务类型管理</p></li>
                                    </ul>
                                </div>
                            </div>
                    </td>
                </tr>
            </table>
         </div>
            
        </div>
        <div  region="south" showSplit="false" showSplitIcon="false" height="30" showHeader="false"  style="border: none">
	        <div class="mini-toolbar" style="position: fixed;left:0;bottom: 0;right:0;text-align: center;border-width: 1px 0 0 0" >
	            <a class="mini-button cursor" onclick="PagePowerAdd.funSave()" iconCls="icon-save">保存</a>
	            <a class="mini-button cursor" onclick="PagePowerAdd.funCancel()" iconCls="icon-cancel" style="margin:0 20px;">取消</a>
	        </div>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#btn1").click(function(){
                $("#chooseWindow").fadeIn(600);
                $("#mask").addClass("mask").fadeIn(600);
            });
            $("#closeIcon").click(function(){
                $("#chooseWindow").fadeOut(600);
                $("#mask").fadeOut(600);
            });
            $(".chooseIcon ul>li").click(function () {
                $("#chooseWindow").fadeOut(600);
                $("#mask").fadeOut(600);
            })
        })
    	mini.parse();
    </script>
</body>
</html>
