<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String type = request.getParameter("orderType").trim() ;
%>
<html>
<head>

    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/order.js'/>"></script>
    <title></title>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div title="north" region="north" showSplit="false" showSplitIcon="false" height="70" showHeader="false"
         style="border: none">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;height:100%"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="orderForm">
				<lable class="form-label">客户订单号：</lable>
                <input name="orderName" id="orderName" class="mini-textbox" emptyText="客户订单号"  style="width:100px;"/>
				<lable class="form-label">订单状态：</lable>
                <input name="orderStatus" id="orderStatus" class="mini-textbox" emptyText="订单状态"  style="width:100px;"/>
				<lable class="form-label">工厂：</lable>
                <input name="customerName" id="customerName" class="mini-textbox" emptyText="工厂"  style="width:100px;"/>
				<lable class="form-label">客户：</lable>
                <input name="pcustomerName" id="pcustomerName" class="mini-textbox" emptyText="客户"  style="width:100px;"/>
                <a class="mini-button" iconCls="icon-search" onclick="PageOrder.funSearch()" plain="true"><label>查询</label></a>
                <a class="mini-button" iconCls="icon-reset" onclick="PageOrder.funReset()" plain="true"><label>重置</label></a>
            </div>
        </div>
    </div>
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:30%;">
                        <a  class="mini-button block-button" iconCls="icon-add" onclick="PageOrder.funAdd()">新增</a>
                        <a  class="mini-button block-button hide" iconCls="icon-edit" id="edit" onclick="PageOrder.funModify()">修改</a>
                        <a  class="mini-button block-button hide" iconCls="icon-remove" id="remove" onclick="PageOrder.funDelete()">删除</a>
                        <a  class="mini-button block-button hide" iconCls="icon-node" id="set" onclick="PageOrder.funSetOrder()">设置订单内容</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="orderGrid" class="mini-datagrid" idField="id" allowResize="false"
                 pagesize="50" sizeList="[10,30,50,100]" ajaxType="get" allowAlternating="true"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="5%">序号</div>
					 <div field="orderNo" width="130" headerAlign="center" allowSort="true">订单编号</div>
					 <div field="orderName" width="120" headerAlign="center" allowSort="true">客户订单号</div>
					 <div field="orderTime" width="120" headerAlign="center" allowSort="true"
                          dateFormat="yyyy-MM-dd">订单时间</div>
					 <div field="orderStatus" width="120" headerAlign="center"
                          renderer="onStatusRenderer" allowSort="true">订单状态</div>
					 <div field="deliveryTime" width="120" headerAlign="center" allowSort="true"
                          dateFormat="yyyy-MM-dd">交货时间</div>
                     <div field="business" width="120" headerAlign="center" allowSort="true">业务类型</div>
                    <div field="button" width="12%" headerAlign="center" align="center"
                         renderer="funSetButton" >操作</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    mini.parse();
    var type = <%=type%>;
    var orderGrid=mini.get("orderGrid");
    orderGrid.setUrl("${pageContext.request.contextPath}/order/query?orderType="+type);
    function funSetButton(e) {
        var button;
        var orderStatus = e.row.orderStatus;
        if(orderStatus==0){
            button= '<a class="mini-button mini-button-plain" href="javascript:void(0)">' +
                    '<span class="mini-button-text  mini-button-icon icon-expand" style="height: auto" ' +
                    'onclick="PageOrder.funOpenOderDetailInfo(type)">详情</span></a>' +
                    '<a class="mini-button mini-button-plain " href="javascript:void(0)">' +
                    '<span class="mini-button-text  mini-button-icon icon-filter" style="height: auto" ' +
                    'onclick="PageOrder.funUpdateOrderStatus()">下发订单</span></a>';
        }else {
            button= '<a class="mini-button mini-button-plain" href="javascript:void(0)">' +
                    '<span class="mini-button-text  mini-button-icon icon-expand" style="height: auto" ' +
                    'onclick="PageOrder.funOpenOderDetailInfo(type)">详情</span></a>';
        }
        return button;
    }

    var Genders = [{ id: -1, text: '订单已删除' },
                              { id: 0, text: '订单申请'},
                              { id: 1, text: '订单下发'},
                              { id: 2, text: '订单出库'},
                              { id: 3, text: '订单入库'},
                              { id: 4, text: '订单开票'},
                              { id: 5, text: '订单完成'}];
    function onStatusRenderer(e) {
        for (var i = 0, l = Genders.length; i < l; i++) {
            var g = Genders[i];
            if (g.id == e.value) return g.text;
        }
        return "";
    }

</script>
</body>
</html>
