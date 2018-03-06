<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String type = request.getParameter("orderType").trim() ;
%>
<html>
<head>

    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/order.js'/>"></script>
    <title></title>
    <style>
        #detailGrid_Form .mini-grid-headerCell-outer{
            background-color: #cad7e0;
        }

        #detailGrid_Form .mini-grid-cell {
            background-color: #e3edf3;;
        }
    </style>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div title="north" region="north" showSplit="false" showSplitIcon="false" height="110px" showHeader="false"
         style="border: none">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;height:100%"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="orderForm">
                <lable class="form-label">业务类型：</lable>
                <input name="businessId" id="businessId" class="mini-combobox" allowInput="true"
                       idField="id" textField="business" url="${pageContext.request.contextPath}/business/queryBusinessList"
                       style="width:120px;" emptyText="请输入或选择" valueFromSelect="true" popupHeight="200"/>
                <lable class="form-label">订单编号：</lable>
                <input name="orderNo" id="orderNo" class="mini-textbox" emptyText="订单编号"  style="width:120px;"/>
                <lable class="form-label">客户订单号：</lable>
                <input name="orderName" id="orderName" class="mini-textbox" emptyText="客户订单号"  style="width:120px;"/>
                <lable class="form-label">客户：</lable>
                <input name="pcustomerId" id="pcustomerId" class="mini-combobox" style="width:150px;"allowInput="true"
                       idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList"
                       maxlength="50" emptyText="请输入或选择" valueFromSelect="true"/>
                <lable class="form-label">客户料号：</lable>
                <input name="materialNum" id="materialNum" class="mini-textbox" emptyText="客户料号"  style="width:120px;"/>
                <br/>
                <lable class="form-label"  style="padding-left: 23px;" name="stimeSearch" id="stimeSearch">订单时间：</lable>
                <input name="orderTimeBegin" id="orderTimeBegin" class="mini-datepicker" style="width:120px;"
                       format="yyyy-MM-dd" emptyText="开始时间" allowInput="false"  />至
                <input name="orderTimeEnd" id="orderTimeEnd" class="mini-datepicker" style="width:120px;"
                       format="yyyy-MM-dd"   emptyText="结束时间" allowInput="false"  />
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
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="orderGrid" class="mini-datagrid" idField="id" allowResize="false"  onshowrowdetail="onShowRowDetail"
                 pagesize="10" sizeList="[10,30,50,100]" ajaxType="get" allowAlternating="true"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="expandcolumn">#</div>
                    <div type="indexcolumn" headerAlign="center"  width="30">序号</div>
                    <div field="orderNo" width="130" headerAlign="center" allowSort="true" align="center">订单编号</div>
                    <div field="orderName" width="120" headerAlign="center" allowSort="true" align="center">客户订单号</div>
                    <div field="name2" width="120" headerAlign="center" allowSort="true" align="center">客户</div>
                    <div field="orderTime" width="100" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" align="center">订单时间</div>
                    <div field="orderStatus" width="100" headerAlign="center" renderer="onStatusRenderer" allowSort="true" align="center">订单状态</div>
                    <div field="deliveryTime" width="100" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" align="center">交货时间</div>
                    <div field="business" width="120" headerAlign="center" allowSort="true" align="center">业务类型</div>
                    <div field="button" width="70" headerAlign="center" align="center" renderer="funSetButton" >操作</div>
                </div>
            </div>
        </div>

        <%--内嵌出货细项表格--%>
        <div id="detailGrid_Form" style="display: none;">
            <div id="orderInfoGrid" class="mini-datagrid" style="width: 100%;" showPager="false"  ajaxType="get"
                 url="${pageContext.request.contextPath}/orderItem/getList">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="20">序号</div>
                    <div field="materialNum" width="100" allowSort="true" headerAlign="center" align="center">客户料号</div>
                    <div field="esgouNum" width="100" allowSort="true" headerAlign="center" align="center">订单数量</div>
                    <div field="tmpNum" width="100" allowSort="true" headerAlign="center" align="center">已出库数量</div>
                    <div field="goodsName" width="100" allowSort="true" headerAlign="center" align="center">商品名称</div>
                    <div field="price" width="100" allowSort="true" headerAlign="center" align="center">单价（未税）</div>
                    <div field="dictName" width="100" allowSort="true" headerAlign="center" align="center">单位</div>
                    <div field="totalMoney" width="100" allowSort="true" headerAlign="center" align="center">总价（未税）</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    mini.parse();
    var type = <%=type%>;
    var orderGrid=mini.get("orderGrid");
    orderGrid.setUrl("${pageContext.request.contextPath}/order/querySell?orderType="+type);
    function funSetButton(e) {
        var button;
        button= '<a class="mini-button mini-button-plain" href="javascript:void(0)">' +
            '<span class="mini-button-text  mini-button-icon icon-expand" style="height: auto" ' +
            'onclick="PageOrder.funDetail()">详情</span></a>';
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


    var detailGrid_Form = document.getElementById("detailGrid_Form");
    var orderInfoGrid=mini.get("orderInfoGrid");
    function onShowRowDetail(e) {
        var grid = e.sender;
        var row = e.record;
        var td = grid.getRowDetailCellEl(row);
        td.appendChild(detailGrid_Form);
        detailGrid_Form.style.display = "block";
        orderInfoGrid.load({ orderId: row.id });
    }
</script>
</body>
</html>
