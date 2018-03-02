<%--
  Created by IntelliJ IDEA.
  User: cx
  Date: 2018/3/1
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String orderType = request.getParameter("orderType").trim() ;
%>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/outStock.js'/>"></script>
    <title></title>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div title="north" region="north" showSplit="false" showSplitIcon="false" height="70" showHeader="false"
         style="border: none">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;height:100%"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="inoutStockForm">
                <lable class="form-label" name="stimeSearch" id="stimeSearch"><c:if test="${param.orderType==0}">入</c:if><c:if test="${param.orderType==1}">出</c:if>库时间：</lable>
                <input name="stimeBegin" id="stimeBegin" class="mini-datepicker" style="witdth:150px;"
                       format="yyyy-MM-dd H:mm:ss" timeFormat="H:mm:ss" showTime="true"  showOkButton="true" showClearButton="false"
                       nullValue="null"  emptyText="开始时间" allowInput="false"  />至
                <input name="stimeEnd" id="stimeEnd" class="mini-datepicker" style="width:150px;"
                       format="yyyy-MM-dd H:mm:ss" timeFormat="H:mm:ss" showTime="true"  showOkButton="true" showClearButton="false"
                       nullValue="null"  emptyText="结束时间" allowInput="false"  />
                <a class="mini-button" iconCls="icon-search" onclick="PageOutStock.funSearch()" plain="true"><label>查询</label></a>
                <a class="mini-button" iconCls="icon-reset" onclick="PageOutStock.funReset()" plain="true"><label>重置</label></a>
            </div>
        </div>
    </div>
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:30%;">
                        <%--<a name="add" class="mini-button block-button" iconCls="icon-add" onclick="PageOutStock.funAdd()">增加</a>--%>
                        <%--<a name="modify" class="mini-button block-button" iconCls="icon-edit" onclick="PageOutStock.funModify()">修改</a>--%>
                        <%--<a name="delete" class="mini-button block-button" iconCls="icon-remove" onclick="PageOutStock.funDelete()">删除</a>--%>
                        <a name="setInStockButton" id="setInStockButton" class="mini-button" iconCls="icon-node" onclick="PageOutStock.funSetInoutStockNum()">入库</a>
                        <a name="setOutStockButton" id="setOutStockButton" class="mini-button" iconCls="icon-node" onclick="PageOutStock.funSetInoutStockNum()">出库</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="inoutGrid" class="mini-datagrid"
                 idField="id" allowResize="false" onshowrowdetail="onShowRowDetail"
                 pagesize="50" sizeList="[10,30,50,100]" allowAlternating="true"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="expandcolumn" >详情</div>
                    <div type="indexcolumn" headerAlign="center"  width="5%">序号</div>
                    <div field="business" width="120" headerAlign="center" allowSort="true">业务类型</div>
                    <div field="stime" name="stime" width="120" headerAlign="center"
                         dateFormat="yyyy-MM-dd" allowSort="true">入/出库时间</div>
                    <div field="isBIll" width="50"  renderer="onStatusRenderer"
                         headerAlign="center" allowSort="true">是否开票</div>
                </div>
            </div>
        </div>
        <%--内嵌出货细项表格--%>
        <div id="detailGrid_Form" style="display: none;">
            <div id="inoutItemGrid" class="mini-datagrid" style="width: 100%;" showPager="false"
                 url="${pageContext.request.contextPath}/orderItem/queryOutStockItem">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="5%">序号</div>
                    <div field="goodsName" width="120" allowSort="true" headerAlign="center">商品名称</div>
                    <div field="materialNum" width="120" allowSort="true" headerAlign="center">物料号</div>
                    <div field="goodNum" name="goodNum" width="120" allowSort="true">入/出库商品数量</div>
                </div>
            </div>
        </div>

    </div>

</div>
<script>
    mini.parse();
    var orderType=<%=orderType%>;
    var setOutStockButton=mini.get("setOutStockButton");
    var setInStockButton=mini.get("setInStockButton");
    if(orderType==0){
        setOutStockButton.hide();
    }else if(orderType==1){
        setInStockButton.hide();
    }

    var Genders=[{id:0,text:"未开票"},
        {id:1,text:"已开票"}];
    function onStatusRenderer(e) {
        for (var i = 0, l = Genders.length; i < l; i++) {
            var g = Genders[i];
            if (g.id == e.value) return g.text;
        }
        return "";
    }

    var inoutGrid=mini.get("inoutGrid");
    inoutGrid.setUrl("${pageContext.request.contextPath}/orderItem/queryInoutStock?orderType=1");
    inoutGrid.load();
    var detailGrid_Form = document.getElementById("detailGrid_Form");
    var inoutItemGrid=mini.get("inoutItemGrid");
    function onShowRowDetail(e) {
        var grid = e.sender;
        var row = e.record;
        var td = grid.getRowDetailCellEl(row);
        td.appendChild(detailGrid_Form);
        detailGrid_Form.style.display = "block";
        inoutItemGrid.load({ inout_stock_id: row.id });
    }

</script>
</body>
</html>
