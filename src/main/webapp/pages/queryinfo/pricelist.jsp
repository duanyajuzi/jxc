<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/queryinfo/js/pricelist.js'/>"></script>
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
    <div title="north" region="north" showSplit="false" showSplitIcon="false" height="75" showHeader="false"
         style="border: none">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;height:100%"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="inoutStockForm">
                <div style="margin: 0 10px">
                    <lable class="form-label">客户：</lable>
                    <input name="customerId" id="customerId" class="mini-combobox" style="width:200px;"allowInput="true"
                           idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList"
                           maxlength="50" emptyText="请输入或选择" valueFromSelect="true"/>
                <a class="mini-button" iconCls="icon-search" onclick="PagePricelist.funSearch()" plain="true"><label>查询</label></a>
                <a class="mini-button" iconCls="icon-reset" onclick="PagePricelist.funReset()" plain="true"><label>重置</label></a>
                </div>
            </div>
        </div>
    </div>
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:30%;">
                        <a  class="mini-button block-button hide" iconCls="icon-redo" id="export" onclick="PagePricelist.exportExcel()">导出订单</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="inoutItemGrid" class="mini-datagrid" idField="id" allowResize="false" showPager="false"
                 url="${pageContext.request.contextPath}/orderItem/queryPriceList"
                 allowAlternating="true"  ajaxType="get"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="30" align="center">序号</div>
                    <div field="ycmaterialNum" width="120" allowSort="true" headerAlign="center" align="center">原厂料号</div>
                    <div field="materialNum" width="120" allowSort="true" headerAlign="center" align="center">客户料号</div>
                    <div field="goodsName" width="120" allowSort="true" headerAlign="center" align="center">商品名称</div>
                    <div field="unitPrice" name="unitPrice" width="120" allowSort="true" headerAlign="right" align="right">采购单价</div>
                    <div field="price" name="price" width="120" allowSort="true" headerAlign="right" align="right">销售单价</div>
                    <div field="dictName" width="120" allowSort="true" headerAlign="center" align="center">单位</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
