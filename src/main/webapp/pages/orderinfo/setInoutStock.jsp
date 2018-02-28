<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/setInoutStock.js'/>"></script>
    <title></title>
</head>
<body>

<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div region="west" showSplit="false" showSplitIcon="false" width="250" showHeader="false"
         style="border:1px solid #cccccc;">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="searchForm">
                <%--<lable class="form-label">业务类型：</lable>--%>
                <%--<input name="businessId" id="businessId" class="mini-combobox" allowInput="true"--%>
                       <%--idField="id" textField="business" url="${pageContext.request.contextPath}/business/queryBusinessList"--%>
                       <%--style="width:100px;"   maxlength="10"--%>
                       <%--onvaluechanged="PageSetInoutStock.funSearch()"/>--%>
                <lable class="form-label">订单号：</lable>
                <input name="orderNo" id="orderNo" class="mini-textbox" emptyText="订单号"  style="width:100px;"/>
                <lable class="form-label">客户订单号：</lable>
                <input name="orderName" id="orderName" class="mini-textbox" emptyText="客户订单号"  style="width:100px;"/>
                <%--<lable class="form-label">物料号：</lable>--%>
                <%--<input name="materialNum" id="materialNum" class="mini-textbox" emptyText="物料号"  style="width:100px;"/>--%>
                <lable class="form-label">客户物料号：</lable>
                <input name="materialNum" id="materialNum" class="mini-textbox" emptyText="客户物料号"  style="width:100px;"/>
                <a class="mini-button" iconCls="icon-search" onclick="PageSetInoutStock.funSearch()" plain="true"><label>查询</label></a>
                <a class="mini-button" iconCls="icon-reset" onclick="PageSetInoutStock.funReset()" plain="true"><label>重置</label></a>
            </div>
        </div>
        <div id="orderTree" class="mini-tree"  style="width:100%;height: 380px"
            showTreeIcon="true" textField="text" idField="id" nodesField="children" resultAsTree="true"
             showCheckBox="true" checkRecursive="false" autoCheckParent="true" expandOnLoad="true"
             contextMenu="#treeMenu">
        </div>
        <ul id="treeMenu" class="mini-contextmenu"  onbeforeopen="onBeforeOpen">
            <li name="inEdit"  id="inEdit" iconCls="icon-edit" onclick="PageSetInoutStock.onEditNum()">入库数量</li>
            <li name="outEdit" id="outEdit" iconCls="icon-edit" onclick="PageSetInoutStock.onEditNum()">出库数量</li>
        </ul>
    </div>
    <div region="center" align="center">
        <input type="button" value=">>" onclick="PageSetInoutStock.setInoutTableAll()" style="margin-top: 230px;text-align: center"/>
    </div>
    <div title="商品入/出库详情" region="east" allowResize="false" showSplit="false"
         showSplitIcon="false" allowUnselect="false" showHeader="false" showModified="false"
         width="580" style="border:1px solid #cccccc;">
        <div id="datagrid" class="mini-datagrid" idField="id"  sortMode="client" style="height: 420px"
             showColumns="true" showPager="false"  region="east">
            <div property="columns">
                <div field="key" visible="false"  headerAlign="center" allowSort="true">key</div>
                <div field="orderId" visible="false">订单ID</div>
                <div field="orderName" width="100" headerAlign="center" allowSort="true">订单名称</div>
                <div field="id" visible="false">orderItemId</div>
                <div field="customerGoodId" visible="false">customerGoodId</div>
                <div field="goodId" visible="false">goodId</div>
                <div field="goodsName" width="80" headerAlign="center" allowSort="true">商品名称</div>
                <div field="materialNum" width="120" headerAlign="center" allowSort="true">商品物料号</div>
                <div field="tmpNum" name="tmpNum" width="100" headerAlign="center" allowSort="true">入\出库数量</div>
                <div field="action" width="120" headerAlign="center" allowSort="true">操作</div>
            </div>
        </div>
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:90px;" align="right"> <lable class="form-label" id="stimeLabel"></lable>： </td>
                    <td style="width:210px;">
                        <input name="stime" id="stime" class="mini-datepicker" style="width:200px;"
                               format="yyyy-MM-dd H:mm:ss" timeFormat="H:mm:ss" showTime="true"  showOkButton="true" showClearButton="false"
                               nullValue="null"  emptyText="" allowInput="false"  />
                    </td>
                    <td style="width:30%;" align="left">
                        <a  class="mini-button block-button" iconCls="icon-save" onclick="PageSetInoutStock.updateOrderItemTmpNum()">确定</a>
                    </td>
                    <td></td>
                </tr>
            </table>
        </div>
    </div>
</div>
</div>
<script>
    mini.parse();
    function onBeforeOpen(e) {
        var menu = e.sender;
        var tree = mini.get("orderTree");
        var node = tree.getSelectedNode();
        if (!node) {
            e.cancel = true;
            return;
        }
        if (node.orderName==null) {
            e.cancel = true;
            //阻止浏览器默认右键菜单
            e.htmlEvent.preventDefault();
            return;
        }
    }


</script>
</body>
</html>

