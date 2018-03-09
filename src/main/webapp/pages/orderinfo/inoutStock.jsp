<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String orderType = request.getParameter("orderType").trim() ;
%>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/inoutStock.js'/>"></script>
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
    <div title="north" region="north" showSplit="false" showSplitIcon="false" height="105" showHeader="false"
         style="border: none">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;height:100%"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="inoutStockForm">
                <div style="width: 900px;margin: 0 auto">
                    <div style="width:100%;">
                <lable class="form-label">业务类型：</lable>
                <input name="businessId" id="businessId" class="mini-combobox" allowInput="true"
                       idField="id" textField="business" url="${pageContext.request.contextPath}/business/queryBusinessList"
                       style="width:150px;" emptyText="请输入或选择" valueFromSelect="true" popupHeight="200"/>
                <lable class="form-label">订单编号：</lable>
                <input name="orderNo" id="orderNo" class="mini-textbox" emptyText="订单编号"  style="width:150px;"/>
                <lable class="form-label">客户订单号：</lable>
                <input name="orderName" id="orderName" class="mini-textbox" emptyText="客户订单号"  style="width:150px;"/>
                <lable class="form-label">原厂料号：</lable>
                <input name="materialNum" id="materialNum" class="mini-textbox" emptyText="原厂料号"  style="width:150px;"/>
                </div>
                <div style="margin: 0 200px">
                <lable class="form-label" name="stimeSearch" id="stimeSearch">入库时间：</lable>
                <input name="stimeBegin" id="stimeBegin" class="mini-datepicker" style="witdth:150px;"
                       format="yyyy-MM-dd" emptyText="开始时间" allowInput="false"  />至
                <input name="stimeEnd" id="stimeEnd" class="mini-datepicker" style="width:150px;"
                       format="yyyy-MM-dd" emptyText="结束时间" allowInput="false"  />
                <a class="mini-button" iconCls="icon-search" onclick="PageInoutStock.funSearch()" plain="true"><label>查询</label></a>
                <a class="mini-button" iconCls="icon-reset" onclick="PageInoutStock.funReset()" plain="true"><label>重置</label></a>
                </div>
                </div>
            </div>
        </div>
    </div>
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:30%;">
                        <%--<a name="add" class="mini-button block-button" iconCls="icon-add" onclick="PageInoutStock.funAdd()">增加</a>--%>
                        <%--<a name="modify" class="mini-button block-button" iconCls="icon-edit" onclick="PageInoutStock.funModify()">修改</a>--%>
                        <%--<a name="delete" class="mini-button block-button" iconCls="icon-remove" onclick="PageInoutStock.funDelete()">删除</a>--%>
                        <a name="setInStockButton" id="setInStockButton" class="mini-button" iconCls="icon-node" onclick="PageInoutStock.funSetInoutStockNum()">入库</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="inoutItemGrid" class="mini-datagrid" idField="id" allowResize="false"
                 url="${pageContext.request.contextPath}/orderItem/queryInoutStockItem"
                 pagesize="30" sizeList="[10,30,50,100]" allowAlternating="true"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="30" align="center">序号</div>
                    <div field="orderNo" width="120" allowSort="true" headerAlign="center" align="center">订单编号</div>
                    <div field="orderName" width="120" allowSort="true" headerAlign="center" align="center">客户订单号</div>
                    <div field="goodsName" width="120" allowSort="true" headerAlign="center" align="center">商品名称</div>
                    <div field="materialNum" width="120" allowSort="true" headerAlign="center" align="center">原厂料号</div>
                    <div field="goodNum" name="goodNum" width="120" allowSort="true" headerAlign="center" align="center">入库商品数量</div>
                    <div field="price" name="price" width="120" allowSort="true" headerAlign="center" align="center">单价</div>
                    <div field="stime" name="stime" width="150" headerAlign="center" align="center"
                         dateFormat="yyyy-MM-dd" allowSort="true">入库时间</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
