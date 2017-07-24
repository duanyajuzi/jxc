<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/sysinfo/js/power.js'/>"></script>
    <title></title>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div title="north" region="north" showSplit="false" showSplitIcon="false" height="70" showHeader="false"
         style="border: none">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;height:100%"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="powerForm">
				<lable class="form-label">权限名称：</lable>
                <input name="powerName" id="powerName" class="mini-textbox" emptyText="权限名称"  style="width:100px;"/>
				<lable class="form-label">权限编号：</lable>
                <input name="powerNo" id="powerNo" class="mini-textbox" emptyText="权限编号"  style="width:100px;"/>
                <a class="mini-button" iconCls="icon-search" onclick="PagePower.funSearch()" plain="true"><label>查询</label></a>
                <a class="mini-button" iconCls="icon-reset" onclick="PagePower.funReset()" plain="true"><label>重置</label></a>
            </div>
        </div>
    </div>
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:30%;">
                        <a  class="mini-button block-button" iconCls="icon-add" onclick="PagePower.funAdd()">新增</a>
                        <a  class="mini-button block-button hide" iconCls="icon-edit" id="edit" onclick="PagePower.funModify()">修改</a>
                        <a  class="mini-button block-button hide" iconCls="icon-remove" id="remove" onclick="PagePower.funDelete()">删除</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="powerGrid" class="mini-treegrid"
                  idField="id" allowResize="false" treeColumn="taskname" showTreeIcon="true" expandOnLoad="true"
                 parentField="pid" resultAsTree="false" expandOnNodeClick="true"  url="${pageContext.request.contextPath}/power/queryList"
                  <%--nodesField="children"--%>
                 sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="5%">序号</div>
                    <div name="taskname" field="name1" width="120">权限名称</div>
					 <div field="powerNo" width="120" headerAlign="center" >权限编号</div>
					 <%--<div field="name2" width="120" headerAlign="center" >父节点权限名称</div>--%>
					 <div field="url" width="120" headerAlign="center" >链接地址</div>
					 <div field="icon" width="120" headerAlign="center" >显示图标</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
