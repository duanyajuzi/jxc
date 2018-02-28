<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/sysinfo/js/user.js'/>"></script>
    <title></title>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div title="north" region="north" showSplit="false" showSplitIcon="false" height="70" showHeader="false"
         style="border: none">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;height:100%"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="userForm">
                
				<lable class="form-label">登录帐号：</lable>
                <input name="userName" id="userName" class="mini-textbox" emptyText="登录帐号"  style="width:100px;"/>
                <lable class="form-label">所属角色：</lable>
                <input name="roleId" id="roleId" class="mini-combobox" emptyText="所属角色"
                       url="${pageContext.request.contextPath}/role/queryRoleList" textField="roleName" idField="id" style="width: 100px"/>
				<lable class="form-label">姓名：</lable>
                <input name="realName" id="realName" class="mini-textbox" emptyText="姓名"  style="width:100px;"/>
				<lable class="form-label">联系电话：</lable>
                <input name="tel" id="tel" class="mini-textbox" emptyText="联系电话"  style="width:100px;"/>
                <a class="mini-button" iconCls="icon-search" onclick="PageUser.funSearch()" plain="true"><label>查询</label></a>
                <a class="mini-button" iconCls="icon-reset" onclick="PageUser.funReset()" plain="true"><label>重置</label></a>
            </div>
        </div>
    </div>
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:30%;">
                        <a  class="mini-button block-button" iconCls="icon-add" onclick="PageUser.funAdd()">新增</a>
                        <a  class="mini-button block-button hide" iconCls="icon-edit" id="edit" onclick="PageUser.funModify()">修改</a>
                        <a  class="mini-button block-button hide" iconCls="icon-remove" id="remove" onclick="PageUser.funDelete()">删除</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="userGrid" class="mini-datagrid"
                  idField="id" allowResize="false"
                 url="${pageContext.request.contextPath}/user/query"
                 pagesize="50" sizeList="[10,30,50,100]" allowAlternating="true"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="60">序号</div>
					 <div field="userName" width="120" headerAlign="center" align="center" allowSort="true">登录帐号</div>
					 <div field="userPwd" width="120" headerAlign="center" align="center" allowSort="true">登录密码</div>
					 <div field="realName" width="120" headerAlign="center" align="center" allowSort="true">姓名</div>
					 <div field="roleName" width="120" headerAlign="center" align="center" allowSort="true">所属角色</div>
					 <div field="position" width="120" headerAlign="center" align="center" allowSort="true">职位</div>
					 <div field="tel" width="120" headerAlign="center" align="center" allowSort="true">联系电话</div>
					 <div field="memo" width="120" headerAlign="center" align="center" allowSort="true">备注</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
