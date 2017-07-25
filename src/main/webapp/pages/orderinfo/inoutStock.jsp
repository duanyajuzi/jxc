<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/inoutStock.js'/>"></script>
    <title></title>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div title="north" region="north" showSplit="false" showSplitIcon="false" height="420" showHeader="false"
         style="border: none">
        <div class="mini-fit">
            <div id="orderItemInfoGrid" class="mini-datagrid" showSummaryRow="true" ondrawsummarycell="onDrawSummaryCell"
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
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">出库数量：</td>
                    <td style="width:32%;">
                        <input name="inoutNum" id="inoutNum" class="mini-textbox" style="width:150px;"
                               required="true" vtype="float;maxlength:19" requiredErrorText="数量不能为空"/>
                    </td>
                    <td style="width:30%;">
                        <a name="inoutButton" class="mini-button block-button" iconCls="icon-save" onclick="">出\入库</a>
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">是否开票：</td>
                    <td style="width:32%;">
                        <input name="isBill" id="isBill" class="mini-textbox" style="width:150px;"
                               required="true" vtype="float;maxlength:1" />
                    </td>
                </tr>
            </table>
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
