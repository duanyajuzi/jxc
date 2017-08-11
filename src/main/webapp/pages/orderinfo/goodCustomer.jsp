<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/goodCustomer.js'/>"></script>
    <title></title>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div title="north" region="north" showSplit="false" showSplitIcon="false" height="70" showHeader="false"
         style="border: none">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;height:100%"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="goodCustomerForm">
				<lable class="form-label">业务类型：</lable>
                <input name="businessId" id="businessId" class="mini-combobox" allowInput="true"emptyText="请输入或选择"
                       idField="id" textField="business" url="${pageContext.request.contextPath}/business/queryBusinessList"
                       style="width:100px;" onvaluechanged="PageGoodCustomer.funBusValuechanged"   maxlength="20" />
                <lable class="form-label">所属商品：</lable>
                <input name="goodId" id="goodId" class="mini-combobox" style="width:100px;"  allowInput="true"
                       textField="msgVal"  valueField="msgKey"  emptyText="请输入或选择" maxlength="50"/>
				<lable class="form-label">所属客户：</lable>
                <input name="customerId" id="customerId" class="mini-combobox" allowInput="true" emptyText="请输入或选择"
                       idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList"
                       style="width:100px;"  maxlength="20" />
				<lable class="form-label">物料号：</lable>
                <input name="materialNum" id="materialNum" class="mini-textbox" emptyText="物料号"  style="width:100px;"/>
                <a class="mini-button" iconCls="icon-search" onclick="PageGoodCustomer.funSearch()" plain="true"><label>查询</label></a>
                <a class="mini-button" iconCls="icon-reset" onclick="PageGoodCustomer.funReset()" plain="true"><label>重置</label></a>
            </div>
        </div>
    </div>
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:30%;">
                        <a  class="mini-button block-button" iconCls="icon-add" onclick="PageGoodCustomer.funAdd()">新增</a>
                        <a  class="mini-button block-button hide" iconCls="icon-edit" id="edit" onclick="PageGoodCustomer.funModify()">修改</a>
                        <a  class="mini-button block-button hide" iconCls="icon-remove" id="remove" onclick="PageGoodCustomer.funDelete()">删除</a>
                        <a  class="mini-button block-button hide" iconCls="icon-collapse" id="expand" onclick="PageGoodCustomer.funManageSchemes()">方案管理</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="goodCustomerGrid" class="mini-datagrid"
                  idField="id" allowResize="false"
                 url="${pageContext.request.contextPath}/goodCustomer/query"
                 pagesize="10" sizeList="[10,20,30,50,100]" allowAlternating="true"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="5%">序号</div>
                    <div field="business" width="120" headerAlign="center" allowSort="true">业务类型</div>
					 <div field="goodsName" width="120" headerAlign="center" allowSort="true">所属商品</div>
					 <div field="customerName" width="120" headerAlign="center" allowSort="true">所属客户</div>
					 <div field="materialNum" width="120" headerAlign="center" allowSort="true">物料号</div>
					 <div field="unitPrice" width="120" headerAlign="center" allowSort="true">单价</div>
					 <div field="dictName" width="120" headerAlign="center" allowSort="true">单位</div>
					 <%--<div field="storage" width="120" headerAlign="center" allowSort="true">库存量</div>--%>
                    <%--<div field="acion" width="10%" headerAlign="center" allowSort="false"
                         align="left">操作
                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
