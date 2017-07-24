<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/sysinfo/js/role.js'/>"></script>
    <title></title>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div title="north" region="north" showSplit="false" showSplitIcon="false"  height="70" showHeader="false"
         style="border: none">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;height:100%"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="roleForm">
				<lable class="form-label">角色名称：</lable>
                <input name="roleName" id="roleName" class="mini-textbox" emptyText="角色名称"  style="width:100px;"/>
                <a class="mini-button" iconCls="icon-search" onclick="PageRole.funSearch()" plain="true"><label>查询</label></a>
                <a class="mini-button" iconCls="icon-reset" onclick="PageRole.funReset()" plain="true"><label>重置</label></a>
            </div>
        </div>
    </div>
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false"
         style="border:0 none;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:30%;">
                        <a  class="mini-button block-button" iconCls="icon-add" onclick="PageRole.funAdd()">新增</a>
                        <a  class="mini-button block-button hide" iconCls="icon-edit" id="edit" onclick="PageRole.funModify()">修改</a>
                        <a  class="mini-button block-button hide" iconCls="icon-remove" id="remove" onclick="PageRole.funDelete()">删除</a>
                        <a  class="mini-button block-button hide" iconCls="icon-save" id="save" onclick="PageRole.funSave()">设置权限</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="roleGrid" class="mini-datagrid" idField="id" allowResize="false"
                 url="${pageContext.request.contextPath}/role/query"
                 onselectionchanged="PageRole.onSelectionChanged()"
                 pagesize="50" sizeList="[10,30,50,100]" allowAlternating="true"  sortMode="client" style="height: 100%">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="5%">序号</div>
					 <div field="roleName" width="120" headerAlign="center" allowSort="true">角色名称</div>
					 <div field="roleMemo" width="120" headerAlign="center" allowSort="true">备注</div>
                </div>
            </div>
        </div>
    </div>
    <div title="east" region="east"  width="200" showSplit="false" showSplitIcon="false"  showHeader="false" style="border:1px solid #ccc;" >
        <div id="powerTree" class="mini-tree"  showTreeIcon="true" expandOnLoad="true" parentField="pid" resultAsTree="false"
             url="${pageContext.request.contextPath}/power/queryList"  textField="name1" idField="id"
              showCheckBox="true" checkRecursive="false" autoCheckParent="true" ></div>
    </div>
</div>

</body>
</html>
