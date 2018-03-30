<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/queryinfo/js/statement.js'/>"></script>
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
                <lable class="form-label" name="stimeSearch" id="stimeSearch">出库时间：</lable>
                <input name="stimeBegin" id="stimeBegin" class="mini-datepicker" style="witdth:150px;"
                       format="yyyy-MM-dd" emptyText="开始时间" allowInput="false"  />至
                <input name="stimeEnd" id="stimeEnd" class="mini-datepicker" style="width:150px;"
                       format="yyyy-MM-dd" emptyText="结束时间" allowInput="false"  />
                <a class="mini-button" iconCls="icon-search" onclick="PageStatement.funSearch()" plain="true"><label>查询</label></a>
                <a class="mini-button" iconCls="icon-reset" onclick="PageStatement.funReset()" plain="true"><label>重置</label></a>
                </div>
            </div>
        </div>
    </div>
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:30%;">
                        <a  class="mini-button block-button hide" iconCls="icon-redo" id="export" onclick="PageOrder.PageStatement()">导出订单</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="inoutItemGrid" class="mini-datagrid" idField="id" allowResize="false" showPager="false"
                 url="${pageContext.request.contextPath}/orderItem/queryCList" ondrawsummarycell="PageStatement.onDrawSummaryCell"
                 allowAlternating="true"  ajaxType="get"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="30" align="center">序号</div>
                    <div field="orderName" width="120" allowSort="true" headerAlign="center" align="center">客户订单号</div>
                    <div field="ycmaterialNum" width="120" allowSort="true" headerAlign="center" align="center">原厂料号</div>
                    <div field="materialNum" width="120" allowSort="true" headerAlign="center" align="center">客户料号</div>
                    <div field="goodsName" width="120" allowSort="true" headerAlign="center" align="center">商品名称</div>
                    <div field="goodNum" name="goodNum" width="120" allowSort="true" headerAlign="right" align="right">数量</div>
                    <div field="inprice" name="inprice" width="120" allowSort="true" headerAlign="right" align="right">采购单价</div>
                    <div field="outprice" name="outprice" width="120" allowSort="true" headerAlign="right" align="right">销售单价</div>
                    <div field="intotalMoney" name="intotalMoney" width="0" allowSort="true" headerAlign="right" align="right" summaryType="sum">采购总价</div>
                    <div field="outtotalMoney" name="outtotalMoney" width="0" allowSort="true" headerAlign="right" align="right" summaryType="sum">销售总价</div>
                    <div field="deliveryTime" name="deliveryTime" width="120" allowSort="true" headerAlign="center" dateFormat="yyyy-MM-dd" align="center">交期</div>
                    <div field="createTime" name="createTime" width="120" headerAlign="center" align="center" dateFormat="yyyy-MM-dd" allowSort="true">出库时间</div>
                </div>
            </div>
        </div>
        <div style="height: 45px;float:right">
            <table style="font-size: 14px;" >
                <tr>
                    <td style="text-align: right">采购总价：</td>
                    <td id="inzj" style="padding-right: 20px;"></td>
                    <td style="text-align: right">销售总价：</td>
                    <td id="outzj" style="padding-right: 20px;"></td>
                </tr>
                <tr>
                    <td>采购含税总价：</td>
                    <td id="inhszj"></td>
                    <td>销售含税总价：</td>
                    <td id="outhszj"></td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
