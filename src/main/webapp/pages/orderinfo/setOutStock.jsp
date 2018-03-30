<%--
  Created by IntelliJ IDEA.
  User: cx
  Date: 2018/3/1
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/setOutStock.js'/>"></script>
    <title></title>
</head>
<body>

<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div region="west" showSplit="false" showSplitIcon="false" width="410" showHeader="false"
         style="border:1px solid #cccccc;">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;" showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="searchForm">
                <table id="search_table">
                    <tr>
                        <td style="width: 75px"><lable class="form-label">业务类型：</lable></td>
                        <td><input name="businessId" id="businessId" class="mini-combobox" allowInput="true"
                               idField="id" textField="business" url="${pageContext.request.contextPath}/business/queryBusinessList"
                               style="width:110px;"   maxlength="10" emptyText="请输入或选择" valueFromSelect="true" popupHeight="150"/></td>
                        <td style="width: 70px"> <lable class="form-label">订单号：</lable></td>
                        <td> <input name="orderNo" id="orderNo" class="mini-textbox" emptyText="订单号"  style="width:110px;"/></td>
                    </tr>
                    <tr>
                        <td> <lable class="form-label">原厂料号：</lable></td>
                        <td> <input name="ycmaterialNum" id="ycmaterialNum" class="mini-textbox" emptyText="原厂料号"  style="width:110px;"/></td>
                        <td > <lable class="form-label">客户订单号：</lable></td>
                        <td> <input name="orderName" id="orderName" class="mini-textbox" emptyText="客户订单号"  style="width:110px;"/></td>
                    </tr>
                   <tr>
                       <td> <lable class="form-label">客户料号：</lable></td>
                       <td> <input name="materialNum" id="materialNum" class="mini-textbox" emptyText="客户料号"  style="width:110px;"/></td>
                        <td><a class="mini-button" iconCls="icon-search" onclick="PageSetOutStock.funSearch()" plain="true"><label>查询</label></a></td>
                        <td><a class="mini-button" iconCls="icon-reset" onclick="PageSetOutStock.funReset()" plain="true"><label>重置</label></a></td>
                   </tr>
                </table>
            </div>
        </div>
        <div id="orderTree" class="mini-tree"  style="width:100%;height:450px"
             showTreeIcon="true" textField="text" idField="treeId" nodesField="children" resultAsTree="true"
             showCheckBox="true" checkOnTextClick="true"
             expandOnLoad="true" contextMenu="#treeMenu">
        </div>
        <ul id="treeMenu" class="mini-contextmenu"  onbeforeopen="onBeforeOpen">
            <li name="outEdit" id="outEdit" iconCls="icon-edit" onclick="PageSetOutStock.onEditNum()">出库数量</li>
        </ul>
    </div>
    <div region="center" align="center">
        <input type="button" value=">>" onclick="PageSetOutStock.setInoutTableAll()" style="margin-top: 250px;text-align: center"/>
    </div>
    <div title="商品出库详情" region="east" width="640" style="border:1px solid #cccccc;">
        <div id="datagrid" class="mini-datagrid" idField="id"  sortMode="client" style="height: 510px"
             allowResize="false" showSplit="false" allowCellSelect="true"  allowCellEdit="true"
             showSplitIcon="false" allowUnselect="false" showHeader="false" showModified="false"
             showColumns="true" showPager="false"  region="east">
            <div property="columns">
                <div field="key" visible="false"  headerAlign="center" allowSort="true">key</div>
                <div field="orderNo" visible="false">订单ID</div>
                <div field="orderName" width="100" headerAlign="center" allowSort="true">客户订单号</div>
                <div field="id" visible="false">orderItemId</div>
                <div field="customerGoodId" visible="false">customerGoodId</div>
                <div field="goodId" visible="false">goodId</div>
                <div field="goodsName" width="80" headerAlign="center" allowSort="true">商品名称</div>
                <div field="materialNum" width="120" headerAlign="center" allowSort="true">客户料号</div>
                <div field="tmpNum" name="tmpNum" width="70" headerAlign="center" allowSort="true">出库数量</div>
                <div field="inprice" width="90" headerAlign="center" >采购价
                    <input property="editor" id="inprice" class="mini-spinner" minValue="0" maxValue="100000000" style="width:100%;"/>
                </div>
                <div field="outprice" width="90" headerAlign="center" >销售价
                    <input property="editor" id="outprice" class="mini-spinner" minValue="0" maxValue="100000000" style="width:100%;"/>
                </div>
                <div field="action" width="80" headerAlign="center" allowSort="true">操作</div>
            </div>
        </div>
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:160px;font-size: 14px" align="right"> <lable class="form-label" id="stimeLabel"></lable>出库时间：</td>
                    <td style="width:210px;">
                        <input name="stime" id="stime" class="mini-datepicker" style="width:200px;" required="true" allowInput="false"  />
                    </td>
                    <td style="width:30%;" align="left">
                        <a  class="mini-button block-button" iconCls="icon-save" onclick="PageSetOutStock.updateOrderItemTmpNum()">确定</a>
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


