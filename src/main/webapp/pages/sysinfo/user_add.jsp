<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/sysinfo/js/user_add.js'/>"></script>
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
    </style>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
        <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div id="userFormAdd">
       
                	<input id="id" name="id"  class="mini-hidden" />
            <table class="form-table" border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;">
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">登录帐号：</td>
                    <td style="width:32%;">
                        <input name="userName" id="userName" class="mini-textbox" style="width:200px;"  required="true" maxlength="50" requiredErrorText="登录帐号不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">登录密码：</td>
                    <td style="width:32%;">
                        <input name="userPwd" id="userPwd" class="mini-password" style="width:200px;"  required="true" maxlength="50" requiredErrorText="登录密码不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">所属角色：</td>
                    <td style="width:32%;">
                        <input name="roleId" id="roleId" class="mini-combobox" style="width:200px;" url="${pageContext.request.contextPath}/role/queryRoleList"
                               textField="roleName" idField="id" emptyText="请选择" required="true"
                               allowInput="true" maxlength="10" requiredErrorText="所属角色不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">姓名：</td>
                    <td style="width:32%;">
                        <input name="realName" id="realName" class="mini-textbox" style="width:200px;"  required="true" maxlength="50" requiredErrorText="姓名不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">联系电话：</td>
                    <td style="width:32%;">
                        <input name="tel" id="tel" class="mini-textbox" style="width:200px;"  required="true" maxlength="30" requiredErrorText="联系电话不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">地址：</td>
                    <td style="width:32%;">
                        <input name="position" id="position" class="mini-textbox"
                               style="width:200px;"  required="true" maxlength="100" requiredErrorText="地址不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">公司抬头：</td>
                    <td style="width:32%;">
                        <input name="company" id="company" class="mini-textbox"
                               style="width:200px;"  required="true" maxlength="50" requiredErrorText="公司抬头不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">订单号开头：</td>
                    <td style="width:32%;">
                        <input name="orderTitle" id="orderTitle" class="mini-textbox"
                               style="width:200px;"  required="true" maxlength="10" requiredErrorText="订单号开头不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">备注：</td>
                    <td style="width:32%;">
                        <input name="memo" id="memo" class="mini-textArea" style="width:200px;" maxlength="300" requiredErrorText="备注不能为空"/>
                    </td>
                </tr>
            </table>
         </div>
            
        </div>
        <div  region="south" showSplit="false" showSplitIcon="false" height="30" showHeader="false"  style="border: none">
	        <div class="mini-toolbar" style="position: fixed;left:0;bottom: 0;right:0;text-align: center;border-width: 1px 0 0 0" >
	            <a class="mini-button cursor" onclick="PageUserAdd.funSave()" iconCls="icon-save">保存</a>
	            <a class="mini-button cursor" onclick="PageUserAdd.funCancel()" iconCls="icon-cancel" style="margin:0 20px;">取消</a>
	        </div>
        </div>
    </div>
    <script type="text/javascript">
    	mini.parse();
    </script>
</body>
</html>
