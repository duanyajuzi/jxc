<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/setBill.js'/>"></script>
    <title></title>
</head>
<body>

<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div region="west" showSplit="false" showSplitIcon="false" width="650"  showHeader="false"
         style="border:1px solid #cccccc;">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="searchForm">
                <lable class="form-label">业务类型：</lable>
                <input name="businessId" id="businessId" class="mini-combobox" allowInput="true"
                       idField="id" textField="business" url="${pageContext.request.contextPath}/business/queryBusinessList"
                       style="width:150px;"   maxlength="20"
                       onvaluechanged="PageSetBill.funSearch()"/>
            </div>
        </div>
        <div id="inoutGrid" class="mini-datagrid" sortMode="client" style="height: 480px"
             idField="id" allowResize="false" multiSelect="true" sizeList="[10,30,50,100]" allowAlternating="true">
            <div property="columns">
                <div type="checkcolumn" ></div>
                <div field="id" visible="false">出入库存Id</div>
                <div fiels="billItemId" visible="billItemId">billItemId</div>
                <div field="business" width="100" headerAlign="center" allowSort="true">业务类型</div>
                <div field="orderId" visible="false">orderId</div>
                <div field="orderName" width="100" headerAlign="center" allowSort="true">订单名称</div>
                <div field="orderTime" width="100"  visible="false"
                     dateFormat="yyyy-MM-dd HH:mm:ss" headerAlign="center" allowSort="true">订单时间</div>
                <div field="stime" name="stime" width="120"  headerAlign="center"
                     dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">入\出库时间</div>
                <div field="goodId"  visible="false">goodId</div>
                <div field="goodsName"  width="80" headerAlign="center" allowSort="true">商品名称</div>
                <div field="goodNum" name="goodNum" width="80" headerAlign="center" allowSort="true">入/出库数量</div>
                <div field="pcustomerId"  visible="false">pcustomerId</div>
                <div field="pcustomerName"  visible="false" name="pcustomerName"  width="100" headerAlign="center" allowSort="true">所属公司</div>
                <div field="pconsigneeName" width="120" visible="false" headerAlign="center" allowSort="true">收货人姓名</div>
                <div field="pconsigneeTel" width="120" visible="false" headerAlign="center" allowSort="true">收货人联系方式</div>
                <div field="consigneeName" width="120" visible="false" headerAlign="center" allowSort="true">发货人姓名</div>
                <div field="consigneeTel" width="120" visible="false" headerAlign="center" allowSort="true">发货人联系方式</div>
                <div field="deliveryAddress" width="120"  visible="false" headerAlign="center" allowSort="true">发/收货地址</div>
            </div>
        </div>
    </div>

    <div region="center" align="center">
        <input type="button" value=">>" onclick="PageSetBill.setDetailGrid()" style="margin-top: 230px;text-align: center"/>
    </div>

    <div region="east" allowResize="false" showSplit="false" showSplitIcon="false" allowUnselect="false" showHeader="false"
    width="430" style="border:1px solid #cccccc;">
        <div id="detailGrid" class="mini-datagrid" idField="id"  sortMode="client" style="height: 425px"
             showColumns="true" showPager="false" width="410" region="east" multiSelect="true"
             allowCellEdit="true" allowCellSelect="true" editNextOnEnterKey="true"  editNextRowCell="true" navEditMode="true">
                <div property="columns">
                    <%--<div type="checkcolumn" ></div>--%>
                    <div field="id" visible="false">出入库存Id</div>
                    <div fiels="billItemId" visible="billItemId">billItemId</div>
                    <div field="businessId" visible="false">businessId</div>
                    <div field="business" width="90" headerAlign="center" allowSort="true">业务类型</div>
                    <div field="orderId" visible="false">orderId</div>
                    <div field="orderName" width="100"  visible="false">订单名称</div>
                    <div field="pcustomerId"  visible="false">pcustomerId</div>
                    <div field="pcustomerName" name="pcustomerName" visible="false"width="100"  headerAlign="center" allowSort="true">采购公司
                        <input property="editor" class="mini-textbox" style="width:100%;"/></div>
                    <div field="pconsigneeName" width="120" visible="false" headerAlign="center" allowSort="true">收货人姓名</div>
                    <div field="pconsigneeTel" width="120" visible="false" headerAlign="center" allowSort="true">收货人联系方式</div>
                    <div field="consigneeName" width="120" visible="false" headerAlign="center" allowSort="true">发货人姓名</div>
                    <div field="consigneeTel" width="120" visible="false" headerAlign="center" allowSort="true">发货人联系方式</div>
                    <div field="deliveryAddress" width="120" visible="false" headerAlign="center" allowSort="true">发/收货地址</div>
                    <div field="goodId"  visible="false">goodId</div>
                    <div field="goodsName"  width="80"  visible="false">商品名称</div>
                    <div field="goodNum" name="goodNum" width="80"  visible="false">入/出库数量</div>
                    <div field="stime"  name="stime" width="120" dateFormat="yyyy-MM-dd HH:mm:ss"  headerAlign="center">入\出库时间</div>
                    <div field="orderTime" width="120" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss"
                          allowSort="true">订单时间</div>
                    <div field="action" width="50" headerAlign="center" allowSort="true">操作</div>
                </div>
            </div>

        <div id="billAddForm">
            <input id="id" name="id"  class="mini-hidden" />
            <table class="form-table" border="0" cellpadding="2" cellspacing="3" style="width:100%;table-layout:fixed;">
                <tr>
                    <td class="form-label"  style="text-align: right;width:16%;">所属公司：</td>
                    <td style="width:32%;">
                        <input name="customerId" id="customerId" class="mini-combobox"
                               onvaluechanged="PageSetBill.onDeptChanged()"
                               idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList"
                               required="true"style="width:125px;"/>
                    </td>
                    <td style="width:20%;" align="right"><label class="form-label" id="consigneeNameLabel">发/收货人姓名：</label></td>
                    <td style="width:32%;">
                        <input name="consigneeName" id="consigneeName" class="mini-textbox"  required="true" style="width:125px;"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:20%;">联系方式：</td>
                    <td style="width:16%;">
                        <input name="consigneeTel" id="consigneeTel" class="mini-textbox"  required="true"  style="width:125px;"/>
                    </td>
                    <td style="width:20%;" align="right"><label class="form-label" id="deliveryAddressLabel">发/收货人姓名：</label></td>
                    <td style="width:16%;">
                        <input name="deliveryAddress" id="deliveryAddress" class="mini-textbox" required="true" style="width:125px;"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">开票时间：</td>
                    <td style="width:32%;">
                        <input name="billTime" id="billTime" class="mini-datepicker" style="width:125px;"
                               onvaluechanged="PageSetBill.funPrePayTimeChanged()"
                               format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss" showTime="true"  showOkButton="true" showClearButton="false"
                               nullValue="null"  emptyText="开票时间" allowInput="false"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:20%;">预付款时间：</td>
                    <td style="width:32%;">
                        <input name="prePayTime" id="prePayTime" class="mini-datepicker" style="width:125px;"
                               format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss" showTime="true"  showOkButton="true" showClearButton="false"
                               nullValue="null"  emptyText="预付款时间" allowInput="false"/>
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<td style="width:32%;" >--%>
                        <%--<a  class="mini-button block-button" iconCls="icon-save" onclick="PageSetBill.funSave()">确定</a>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            </table>
        </div>
        <div align="center" style="margin-top: 8px">
            <a  class="mini-button block-button" iconCls="icon-save" onclick="PageSetBill.funSave()">确定</a>
        </div>
    </div>

</div>
<script>
    mini.parse();
</script>
</body>
</html>