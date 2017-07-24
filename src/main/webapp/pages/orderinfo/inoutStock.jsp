<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/order.js'/>"></script>
    <title></title>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div title="north" region="north" showSplit="false" showSplitIcon="false" height="300" showHeader="false"
         style="border: none">
        <div class="mini-fit">
            <div id="orderItemGrid" class="mini-datagrid" showSummaryRow="true" ondrawsummarycell="onDrawSummaryCell"
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
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false"
         allowUnselect="false" style="border:1px solid #cccccc;">
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
