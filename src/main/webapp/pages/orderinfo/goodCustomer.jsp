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
				<lable class="form-label">所属工厂：</lable>
                <input name="customerId" id="customerId" class="mini-combobox" allowInput="true" emptyText="请输入或选择"
                       idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList"
                       style="width:100px;"  maxlength="20" />
				<lable class="form-label">原厂料号：</lable>
                <input name="materialNum" id="materialNum" class="mini-textbox" emptyText="原厂料号"  style="width:100px;"/>
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
                        <a  class="mini-button block-button" iconCls="icon-add" onclick="PageGoodCustomer.funAdd()">新增商品</a>
                        <a  class="mini-button block-button hide" iconCls="icon-edit" id="edit" onclick="PageGoodCustomer.funModify()" style="display: none">修改商品</a>
                        <a  class="mini-button block-button hide" iconCls="icon-remove" id="remove" onclick="PageGoodCustomer.funDelete()" style="display: none">删除商品</a>
                        <a  class="mini-button block-button" iconCls="icon-add" id="addFangAn" onclick="PageGoodCustomer.addFangAn()" style="display: none">新增客户方案</a>
                        <a  class="mini-button block-button" iconCls="icon-edit" id="editFangAn" onclick="PageGoodCustomer.modifyFangAn()" style="display: none">修改客户方案</a>
                        <a  class="mini-button block-button" iconCls="icon-remove" id="removeFangAn" onclick="PageGoodCustomer.deleteFangAn()" style="display: none">删除客户方案</a>
                        <%--<a  class="mini-button block-button hide" iconCls="icon-collapse" id="expand" onclick="PageGoodCustomer.funManageSchemes()">客户管理</a>--%>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="goodCustomerGrid" class="mini-datagrid" idField="id" allowResize="false"
                 url="${pageContext.request.contextPath}/goodCustomer/query" onshowrowdetail="onShowRowDetail" onhiderowdetail="onhiderowdetail"
                 pagesize="50" sizeList="[10,30,50,100]" allowAlternating="true"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="expandcolumn" >#</div>
                    <div field="business" width="120" headerAlign="center" align="center" allowSort="true">业务类型</div>
                    <div field="goodsName" width="120" headerAlign="center" align="center" allowSort="true">所属商品</div>
                    <div field="customerName" width="120" headerAlign="center" align="center" allowSort="true">所属工厂</div>
                    <div field="materialNum" width="120" headerAlign="center" align="center" allowSort="true">原厂料号</div>
                    <div field="unitPrice" width="120" headerAlign="center" align="center" allowSort="true">采购价(未税)</div>
                    <div field="dictName" width="120" headerAlign="center" align="center" allowSort="true">单位</div>
                    <div field="storage" width="120" headerAlign="center" align="center" allowSort="true">库存量</div>
                </div>
            </div>
        </div>
        <%--内嵌出货细项表格--%>
        <div id="detailGrid_Form" style="display: none;">
            <div id="employee_grid" class="mini-datagrid" style="width: 100%;" showPager="false"
                 url="${pageContext.request.contextPath}/blueprint/query" pagesize="50" sizeList="[10,30,50,100]"
                 allowAlternating="true"  sortMode="client">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="60">序号</div>
                    <div field="pname" width="120" headerAlign="center" align="center" allowSort="true">客户方案名称</div>
                    <div field="goodsNum" width="120" headerAlign="center" align="center" allowSort="true">数量</div>
                    <div field="materialNum" width="120" headerAlign="center" align="center" allowSort="true">客户料号</div>
                    <div field="price" width="120" headerAlign="center" align="center" allowSort="true">销售价(未税)</div>
                    <div field="dictName" width="120" headerAlign="center" align="center" allowSort="true">单位</div>
                    <div field="memo" width="120" headerAlign="center" align="center" allowSort="true">备注</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    mini.parse();
    var detailGrid_Form = document.getElementById("detailGrid_Form");
    var employee_grid = mini.get("employee_grid");
    var goodCustomerGrid = mini.get("goodCustomerGrid");
    function onShowRowDetail(e){
        var grid = e.sender;
        var row = e.record;
        var td = grid.getRowDetailCellEl(row);
        td.appendChild(detailGrid_Form);
        detailGrid_Form.style.display = "block";
        employee_grid.load({ goodsId: row.id });
    }


    function onhiderowdetail() {
        $("#editFangAn,#removeFangAn").hide();
    }

    //    行选中时显示编辑和删除按钮
    employee_grid.on("select", function () {
        $("#editFangAn,#removeFangAn").show();
    });

    employee_grid.on("deselect", function () {
        $("#editFangAn,#removeFangAn").hide();
    });

    goodCustomerGrid.on("select", function () {
        $("#edit,#remove,#addFangAn").show();
    });

    goodCustomerGrid.on("deselect", function () {
        $("#edit,#remove,#addFangAn").hide();
    });

</script>
</body>
</html>
