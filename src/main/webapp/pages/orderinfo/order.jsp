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
				<%--<lable class="form-label">订单编号：</lable>--%>
                <%--<input name="orderNo" id="orderNo" class="mini-textbox" emptyText="订单编号"  style="width:100px;"/>--%>
				<lable class="form-label">订单名称：</lable>
                <input name="orderName" id="orderName" class="mini-textbox" emptyText="订单名称"  style="width:100px;"/>
				<%--<lable class="form-label">订单时间：</lable>--%>
                <%--<input name="orderTime" id="orderTime" class="mini-textbox" emptyText="订单时间"  style="width:100px;"/>--%>
				<%--<lable class="form-label">订单类型：</lable>--%>
                <%--<input name="orderType" id="orderType" class="mini-textbox" emptyText="订单类型："  style="width:100px;"/>--%>
				<lable class="form-label">订单状态：</lable>
                <input name="orderStatus" id="orderStatus" class="mini-textbox" emptyText="订单状态"  style="width:100px;"/>
				<%--<lable class="form-label">交货时间：</lable>--%>
                <%--<input name="deliveryTime" id="deliveryTime" class="mini-textbox" emptyText="交货时间"  style="width:100px;"/>--%>
				<%--<lable class="form-label">交货地址：</lable>--%>
                <%--<input name="deliveryAddress" id="deliveryAddress" class="mini-textbox" emptyText="交货地址"  style="width:100px;"/>--%>
				<lable class="form-label">厂商：</lable>
                <input name="customerName" id="customerName" class="mini-textbox" emptyText="厂商"  style="width:100px;"/>
				<%--<lable class="form-label">厂商联系人：</lable>--%>
                <%--<input name="ccontacts" id="ccontacts" class="mini-textbox" emptyText="厂商联系人"  style="width:100px;"/>--%>
				<%--<lable class="form-label">厂商联系地址：</lable>--%>
                <%--<input name="caddress" id="caddress" class="mini-textbox" emptyText="厂商联系地址"  style="width:100px;"/>--%>
				<%--<lable class="form-label">厂商联系电话：</lable>--%>
                <%--<input name="ctel" id="ctel" class="mini-textbox" emptyText="厂商联系电话"  style="width:100px;"/>--%>
				<lable class="form-label">采购公司：</lable>
                <input name="pcustomerName" id="pcustomerName" class="mini-textbox" emptyText="采购公司"  style="width:100px;"/>
				<%--<lable class="form-label">采购人：</lable>--%>
                <%--<input name="pcontacts" id="pcontacts" class="mini-textbox" emptyText="采购人"  style="width:100px;"/>--%>
				<%--<lable class="form-label">联系地址：</lable>--%>
                <%--<input name="paddress" id="paddress" class="mini-textbox" emptyText="联系地址"  style="width:100px;"/>--%>
				<%--<lable class="form-label">联系电话：</lable>--%>
                <%--<input name="ptel" id="ptel" class="mini-textbox" emptyText="联系电话"  style="width:100px;"/>--%>
				<%--<lable class="form-label">创建时间：</lable>--%>
                <%--<input name="ctime" id="ctime" class="mini-textbox" emptyText="创建时间"  style="width:100px;"/>--%>
				<%--<lable class="form-label">创建人：</lable>--%>
                <%--<input name="cuserid" id="cuserid" class="mini-textbox" emptyText="创建人"  style="width:100px;"/>--%>
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
                 <%--onshowrowdetail="onShowRowDetail"--%>
                 pagesize="50" sizeList="[10,30,50,100]" allowAlternating="true"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <%--<div type="expandcolumn" >#</div>--%>
                    <div type="indexcolumn" headerAlign="center"  width="5%">序号</div>
					 <div field="orderNo" width="130" headerAlign="center" allowSort="true">订单编号</div>
					 <div field="orderName" width="120" headerAlign="center" allowSort="true">订单名称</div>
					 <div field="orderTime" width="120" headerAlign="center" allowSort="true"
                          dateFormat="yyyy-MM-dd HH:mm:ss">订单时间</div>
					 <%--<div field="orderType" width="120" headerAlign="center" allowSort="true">订单类型：（0采购订单；1：出货订单）</div>--%>
					 <div field="orderStatus" width="120" headerAlign="center"
                          renderer="onStatusRenderer" allowSort="true">订单状态</div>
					 <div field="deliveryTime" width="120" headerAlign="center" allowSort="true"
                          dateFormat="yyyy-MM-dd HH:mm:ss">交货时间</div>
                     <div field="business" width="120" headerAlign="center" allowSort="true">业务类型</div>
					 <%--<div field="deliveryAddress" width="120" headerAlign="center" allowSort="true">交货地址</div>--%>
					 <%--<div field="name1" width="120" headerAlign="center" allowSort="true">厂商</div>--%>
					 <%--<div field="ccontacts" width="120" headerAlign="center" allowSort="true">厂商联系人</div>--%>
					 <%--<div field="caddress" width="120" headerAlign="center" allowSort="true">厂商联系地址</div>--%>
					 <%--<div field="ctel" width="120" headerAlign="center" allowSort="true">厂商联系电话</div>--%>
					 <%--<div field="name2" width="120" headerAlign="center" allowSort="true">采购公司</div>--%>
					 <%--<div field="pcontacts" width="120" headerAlign="center" allowSort="true">采购人</div>--%>
					 <%--<div field="paddress" width="120" headerAlign="center" allowSort="true">联系地址</div>--%>
					 <%--<div field="ptel" width="120" headerAlign="center" allowSort="true">联系电话</div>--%>
					 <%--<div field="ctime" width="120" headerAlign="center" allowSort="true">创建时间</div>--%>
					 <%--<div field="cuserid" width="120" headerAlign="center" allowSort="true">创建人</div>--%>
					 <%--<div field="memo" width="120" headerAlign="center" allowSort="true">备注</div>--%>
                    <div field="button" width="10%" headerAlign="center"
                         renderer="funSetButton">操作</div>
                </div>
            </div>
            <%--内嵌form表单--%>
            <div id="detailForm" style="display: none;padding: 5px">
                <input class="mini-hidden" name="id" id="id"/>
                <table  style="width:100%;">
                    <tr>
                        <td class="form-label" style="text-align: right;width:16%;">订单编号：</td>
                        <td style="width:32%;">
                            <input name="orderNo" id="orderNo" class="mini-textbox" style="width:200px;"  required="true" maxlength="20" requiredErrorText="订单编号不能为空"/>
                        </td>
                        <td class="form-label" style="text-align: right;width:16%;">订单名称：</td>
                        <td style="width:32%;">
                            <input name="orderName"  class="mini-textbox" style="width:200px;"  required="true" maxlength="100" requiredErrorText="订单名称不能为空"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-label" style="text-align: right;width:16%;">订单时间：</td>
                        <td style="width:32%;">
                            <input name="orderTime" id="orderTime" class="mini-datepicker" style="width:200px;"
                                   format="yyyy-MM-dd H:mm:ss" timeFormat="H:mm:ss" showTime="true"  showOkButton="true" showClearButton="false"
                                   nullValue="null"  emptyText="开始时间" allowInput="false"  />
                        </td>
                        <td class="form-label" style="text-align: right;width:16%;">交货时间：</td>
                        <td style="width:32%;">
                            <input name="deliveryTime" id="deliveryTime" class="mini-datepicker" style="width:200px;"
                              format="yyyy-MM-dd H:mm:ss" timeFormat="H:mm:ss" showTime="true"  showOkButton="true" showClearButton="false"
                            nullValue="null"  emptyText="开始时间" allowInput="false"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-label" style="text-align: right;width:16%;">订单状态：</td>
                        <td style="width:32%;">
                            <input name="orderStatus"  class="mini-textbox" style="width:200px;"  required="true" maxlength="5" requiredErrorText="订单状态不能为空"/>
                        </td>
                        <td class="form-label" style="text-align: right;width:16%;">交货地址：</td>
                        <td style="width:32%;">
                            <input name="deliveryAddress" id="deliveryAddress" class="mini-textbox" style="width:200px;"  required="true" maxlength="200" requiredErrorText="交货地址不能为空"/>
                        </td>
                    </tr>
                    <td class="form-label" style="text-align: right;width:16%;">厂商：</td>
                    <td style="width:32%;">
                        <input name="customerId" id="customerId" class="mini-combobox" style="width:200px;"  required="true" maxlength="10"
                               requiredErrorText="厂商不能为空" onvaluechanged="PageOrderAdd.onDeptChanged()" allowInput="true"
                               allowInput="true"  idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList" />
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">采购公司：</td>
                    <td style="width:32%;">
                        <input name="pcustomerId" id="pcustomerId" class="mini-combobox" style="width:200px;"
                               onvaluechanged="PageOrderAdd.onDeptChanged()" allowInput="true"
                               idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList"
                               required="true" maxlength="10" requiredErrorText="采购公司不能为空"/>
                    </td>
                    </tr>
                    <tr>
                        <td class="form-label" style="text-align: right;width:16%;">厂商联系人：</td>
                        <td style="width:32%;">
                            <input name="ccontacts" id="ccontacts" class="mini-textbox" style="width:200px;"  required="true" maxlength="40"
                                   requiredErr orText="厂商联系人不能为空"/>
                        </td>
                        <td class="form-label" style="text-align: right;width:16%;">采购人：</td>
                        <td style="width:32%;">
                            <input name="pcontacts" id="pcontacts" class="mini-textbox" style="width:200px;"  required="true" maxlength="40" requiredErrorText="采购人不能为空"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-label" style="text-align: right;width:16%;">厂商联系地址：</td>
                        <td style="width:32%;">
                            <input name="caddress" id="caddress" class="mini-textbox" style="width:200px;"  required="true" maxlength="200" requiredErrorText="厂商联系地址不能为空"/>
                        </td>
                        <td class="form-label" style="text-align: right;width:16%;">采购商联系地址：</td>
                        <td style="width:32%;">
                            <input name="paddress" id="paddress" class="mini-textbox" style="width:200px;"  required="true" maxlength="200" requiredErrorText="联系地址不能为空"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-label" style="text-align: right;width:16%;">厂商联系电话：</td>
                        <td style="width:32%;">
                            <input name="ctel" id="ctel" class="mini-textbox" style="width:200px;"  required="true" maxlength="40" requiredErrorText="厂商联系电话不能为空"/>
                        </td>
                        <td class="form-label" style="text-align: right;width:16%;">采购商联系电话：</td>
                        <td style="width:32%;">
                            <input name="ptel" id="ptel" class="mini-textbox" style="width:200px;"  required="true" maxlength="40" requiredErrorText="联系电话不能为空"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-label" style="text-align: right;width:16%;">备注：</td>
                        <td style="width:32%;">
                            <input name="memo" id="memo" class="mini-textArea" style="width:200px;"  required="true" maxlength="400" requiredErrorText="备注不能为空"/>
                        </td>
                    </tr>
                </table>
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
                    '<a class="mini-button mini-button " href="javascript:void(0)">' +
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
