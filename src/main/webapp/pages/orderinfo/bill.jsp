<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String billType = request.getParameter("billType").trim() ;
%>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/bill.js'/>"></script>
    <title></title>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div title="north" region="north" showSplit="false" showSplitIcon="false" height="70" showHeader="false"
         style="border: none">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;height:100%"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="billForm">
				<lable class="form-label">票据编号：</lable>
                <input name="billNo" id="billNo" class="mini-textbox" emptyText="票据编号"  style="width:100px;"/>
				<lable class="form-label">所属企业：</lable>
                <input name="customerId" id="customerId" class="mini-combobox" emptyText="所属企业" allowInput="true"
                       idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList" style="width:100px;"/>
				<lable class="form-label">业务类型：</lable>
                <input name="businessId" id="businessId" class="mini-combobox" emptyText="业务类型" alloweInput="true"
                       idField="id" textField="business" url="${pageContext.request.contextPath}/business/queryBusinessList" style="width:100px;"/>
				<%--<lable class="form-label">付款状态：</lable>--%>
                <%--<input name="payState" id="payState" class="mini-textbox" emptyText="付款状态"  style="width:100px;"/>--%>
                <a class="mini-button" iconCls="icon-search" onclick="PageBill.funSearch()" plain="true"><label>查询</label></a>
                <a class="mini-button" iconCls="icon-reset" onclick="PageBill.funReset()" plain="true"><label>重置</label></a>
            </div>
        </div>
    </div>
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:30%;">
                        <a  class="mini-button block-button" iconCls="icon-add" onclick="PageBill.funOpenSetBill()">开票</a>
                        <%--<a  class="mini-button block-button" iconCls="icon-add" onclick="PageBill.funAdd()">新增</a>--%>
                        <a  class="mini-button block-button hide" iconCls="icon-edit" id="edit" onclick="PageBill.funModify()">付款</a>
                        <%--<a  class="mini-button block-button hide" iconCls="icon-remove" id="remove" onclick="PageBill.funDelete()">删除</a>--%>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="billGrid" class="mini-datagrid"
                  idField="id" allowResize="false" onshowrowdetail="onShowRowDetail"
                 pagesize="50" sizeList="[10,30,50,100]" allowAlternating="true"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="expandcolumn" >详情</div>
                    <div type="indexcolumn" headerAlign="center"  width="5%">序号</div>
					 <div field="billNo" width="120" headerAlign="center" allowSort="true">票据编号</div>
					 <%--<div field="customerName" width="120" headerAlign="center" allowSort="true">所属企业</div>--%>
                    <%--<div type="consigneeName" headerAlign="center"  width="120">收货人姓名</div>--%>
                    <%--<div field="consigneeTel" width="120" headerAlign="center" allowSort="true">收货人电话</div>--%>
                    <%--<div field="deliveryAddress" width="120" headerAlign="center" allowSort="true">收货地址</div>--%>
					 <div field="billTime" width="120" headerAlign="center"
                          dateFormat="yyyy-MM-dd HH:mm:ss"allowSort="true">开票时间</div>
					 <div field="prePayTime" width="120" headerAlign="center"
                          dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">预付款时间</div>
					 <div field="business" width="100" headerAlign="center" allowSort="true">业务类型</div>
					 <div field="payState" width="50" headerAlign="center"
                          renderer="onStatusRenderer" allowSort="true">付款状态</div>
					 <div field="payTime" width="120" headerAlign="center"
                          dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">付款时间</div>
                    <div field="action" width="140"headerAlign="center"
                         renderer="funSetButton">操作</div>
                </div>
            </div>
        </div>
        <%--内嵌开票细项表格--%>
        <div id="detailGrid_Form" style="display: none;">
            <div id="billItemGrid" class="mini-datagrid" style="width: 100%;"
                 url="${pageContext.request.contextPath}/bill/queryInoutItemList">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="5%">序号</div>
                    <div field="business" width="120" allowSort="true" headerAlign="center">业务类型</div>
                    <div field="goodsName" width="120" allowSort="true" headerAlign="center">商品名称</div>
                    <div field="goodNum" name="goodNum" width="120" allowSort="true" headerAlign="center">入\出商品数量</div>
                    <div field="stime" name="stime" width="120"dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">入\出货时间</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    mini.parse();
    var billType = <%=billType%>;
    var Genders=[{id:0,text:"未付款"},
        {id:1,text:"已付款"}];
    function onStatusRenderer(e) {
        for (var i = 0, l = Genders.length; i < l; i++) {
            var g = Genders[i];
            if (g.id == e.value) return g.text;
        }
        return "";
    }
    var billGrid=mini.get("billGrid");
    billGrid.setUrl("${pageContext.request.contextPath}/bill/query?billType="+billType);
    billGrid.load();
    var detailGrid_Form = document.getElementById("detailGrid_Form");
    var billItemGrid=mini.get("billItemGrid");
    function onShowRowDetail(e) {
        var grid = e.sender;
        var row = e.record;
        var td = grid.getRowDetailCellEl(row);
        td.appendChild(detailGrid_Form);
        detailGrid_Form.style.display = "block";
        billItemGrid.load({ "billId": row.id});
    }
    function funSetButton() {
        var button= '<a class="mini-button mini-button-plain" href="javascript:void(0)">' +
        '<span class="mini-button-text  mini-button-icon icon-expand" style="height: auto" ' +
        'onclick="PageBill.funView()">详情</span></a>' +
                '<a class="mini-button mini-button-plain" href="javascript:void(0)">' +
                '<span class="mini-button-text  mini-button-icon icon-expand" style="height: auto" ' +
                'onclick="PageBill.funModifySetBill()">修改票据内容</span></a>' ;
        return button;
    }
</script>
</body>
</html>
