<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/blueprint.js'/>"></script>
    <title></title>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div title="north" region="north" showSplit="false" showSplitIcon="false" height="70" showHeader="false"
         style="border: none">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;height:100%"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="blueprintForm">
                <lable class="form-label">方案名称：</lable>
                <input name="pname" id="pname" class="mini-textbox" emptyText="方案名称"  style="width:100px;"/>
                <a class="mini-button" iconCls="icon-search" onclick="PageBlueprint.funSearchInfo()" plain="true"><label>查询</label></a>
                <a class="mini-button" iconCls="icon-reset" onclick="PageBlueprint.funReset()" plain="true"><label>重置</label></a>
            </div>
        </div>
    </div>
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:30%;">
                        <a  class="mini-button block-button" iconCls="icon-add" onclick="PageBlueprint.funAdd()">新增</a>
                        <a  class="mini-button block-button hide" iconCls="icon-edit" id="edit" onclick="PageBlueprint.funModify()">修改</a>
                        <a  class="mini-button block-button hide" iconCls="icon-remove" id="remove" onclick="PageBlueprint.funDelete()">删除</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="blueprintGrid" class="mini-datagrid"
                  idField="id" allowResize="false"
                 url="${pageContext.request.contextPath}/blueprint/query"
                 pagesize="50" sizeList="[10,30,50,100]" allowAlternating="true"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="5%">序号</div>
                    <div field="pname" width="120" headerAlign="center" allowSort="true">方案名称</div>
					 <%--<div field="goodsName" width="120" headerAlign="center" allowSort="true">商品名称</div>--%>
					 <div field="goodsNum" width="120" headerAlign="center" allowSort="true">数量</div>
					 <div field="price" width="120" headerAlign="center" allowSort="true">单价</div>
					 <div field="dictName" width="120" headerAlign="center" allowSort="true">单位</div>
					 <div field="memo" width="120" headerAlign="center" allowSort="true">备注</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
