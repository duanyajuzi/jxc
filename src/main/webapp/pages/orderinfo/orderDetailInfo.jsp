<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017-07-20
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/orderDetailInfo.js'/>"></script>
    <title></title>
</head>
<body>
<div  style="width: 100%;height: 100%;" allowResize="false">
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false"
        style="border:0 none;height: 300px;">
        <div id="orderDetailForm">
            <input id="id" name="id"  class="mini-hidden" />
            <table class="form-table" border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;">
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">订单编号：</td>
                    <td style="width:32%;">
                        <input name="orderNo" id="orderNo" class="mini-textbox" style="width:200px;"  required="true" maxlength="20" requiredErrorText="订单编号不能为空"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">客户订单号：</td>
                    <td style="width:32%;">
                        <input name="orderName" id="orderName" class="mini-textbox" style="width:200px;"  required="true" maxlength="100" requiredErrorText="客户订单号不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">订单时间：</td>
                    <td style="width:32%;">
                        <input name="orderTime" id="orderTime" class="mini-datepicker" style="width:200px;"
                               format="yyyy-MM-dd" showTime="true"  showOkButton="true" showClearButton="false"
                               nullValue="null"  emptyText="订单时间" allowInput="false"  />
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">交货时间：</td>
                    <td style="width:32%;">
                        <input name="deliveryTime" id="deliveryTime" class="mini-datepicker" style="width:200px; "
                               format="yyyy-MM-dd" showTime="true"  showOkButton="true" showClearButton="false"
                               nullValue="null"  emptyText="交货时间" allowInput="false"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">订单状态：</td>
                    <td style="width:32%;">
                        <input name="orderStatus" id="orderStatus" class="mini-textbox" style="width:200px;"
                               required="true" maxlength="5" requiredErrorText="订单状态不能为空"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">业务类型：</td>
                    <td style="width:32%;">
                        <input name="businessId" id="businessId" class="mini-combobox" style="width:200px; "
                               idField="id" textField="business" url="${pageContext.request.contextPath}/business/queryBusinessList"
                               required="true" maxlength="15" requiredErrorText="业务类型不能为空"
                               emptyText="请输入或选择" allowInput="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">交货地址：</td>
                    <td colspan="3">
                        <input name="deliveryAddress" id="deliveryAddress" class="mini-textbox" style="width:520px;"
                               required="true" maxlength="200" requiredErrorText="交货地址不能为空"/>
                    </td>
                </tr>
                <tr>
                <td class="form-label" style="text-align: right;width:16%;">工厂：</td>
                <td style="width:32%;">
                    <input name="customerId" id="customerId" class="mini-combobox" style="width:200px;"  required="true" maxlength="10"
                           requiredErrorText="厂商不能为空" onvaluechanged="PageOrderAdd.onDeptChanged()" allowInput="true"
                           allowput="true"  idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList" />
                </td>
                <td class="form-label" style="text-align: right;width:16%;">客户：</td>
                <td style="width:32%;">
                    <input name="pcustomerId" id="pcustomerId" class="mini-combobox" style="width:200px;"
                           onvaluechanged="PageOrderAdd.onDeptChanged()" allowInput="true"
                           idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList"
                           required="true" maxlength="10" requiredErrorText="采购公司不能为空"/>
                </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">工厂联系人：</td>
                    <td style="width:32%;">
                        <input name="ccontacts" id="ccontacts" class="mini-textbox" style="width:200px;"  required="true" maxlength="40"
                               requiredErr orText="厂商联系人不能为空"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">客户联系人：</td>
                    <td style="width:32%;">
                        <input name="pcontacts" id="pcontacts" class="mini-textbox" style="width:200px;"  required="true" maxlength="40" requiredErrorText="采购人不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">工厂联系地址：</td>
                    <td style="width:32%;">
                        <input name="caddress" id="caddress" class="mini-textbox" style="width:200px;"  required="true" maxlength="200" requiredErrorText="厂商联系地址不能为空"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">客户联系地址：</td>
                    <td style="width:32%;">
                        <input name="paddress" id="paddress" class="mini-textbox" style="width:200px;"  required="true" maxlength="200" requiredErrorText="联系地址不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">工厂联系电话：</td>
                    <td style="width:32%;">
                        <input name="ctel" id="ctel" class="mini-textbox" style="width:200px;"  required="true" maxlength="40" requiredErrorText="厂商联系电话不能为空"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">客户联系电话：</td>
                    <td style="width:32%;">
                        <input name="ptel" id="ptel" class="mini-textbox" style="width:200px;"  required="true" maxlength="40" requiredErrorText="联系电话不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">备注：</td>
                    <td style="width:32%;">
                        <input name="memo" id="memo" class="mini-textArea" style="width:520px;" maxlength="400"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div title="south" region="south" allowResize="false" showSplit="true" showSplitIcon="false"
         allowUnselect="false" style="border:1px solid #cccccc;height: 282px">
        <div class="mini-fit">
            <div id="orderDetailGrid" class="mini-datagrid" showSummaryRow="true" ondrawsummarycell="onDrawSummaryCell"
                 url="${pageContext.request.contextPath}/orderItem/query"
                 idField="id" allowResize="false"
                 pagesize="10" sizeList="[10,30,50,100]" allowAlternating="true"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="5%">序号</div>
                    <div field="goodsName" headerAlign="center" allowSort="true">商品名称</div>
                    <div field="materialNum"  headerAlign="center" allowSort="true">物料号</div>
                    <div field="esgouNum" headerAlign="center" allowSort="true">数量</div>
                    <div field="dictName" headerAlign="center" allowSort="true">单位</div>
                    <div field="unitPrice"  headerAlign="center" allowSort="true">单价</div>
                    <div field="totalMoney"  headerAlign="center" allowSort="true" summaryType="sum">总价</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    mini.parse();
    function onDrawSummaryCell(e) {
        //客户端汇总计算
        if (e.field == "totalMoney") {
            e.cellHtml = "总价: " + e.value;
        }
    }

</script>
</body>
</html>
