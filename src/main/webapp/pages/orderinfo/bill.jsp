<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/bill.js'/>"></script>
    <title></title>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div title="north" region="north" showSplit="false" showSplitIcon="false" height="70" showHeader="false"
         style="border: none">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;height:100%"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="billForm">
                
				<lable class="form-label">票据编号：</lable>
                <input name="billNo" id="billNo" class="mini-textbox" emptyText="票据编号"  style="width:100px;"/>
				<lable class="form-label">所属企业：</lable>
                <input name="customerId" id="customerId" class="mini-textbox" emptyText="所属企业"  style="width:100px;"/>
				<lable class="form-label">开票时间：</lable>
                <input name="billTime" id="billTime" class="mini-textbox" emptyText="开票时间"  style="width:100px;"/>
				<lable class="form-label">预付款时间(从企业的默认帐期中取，从而计算天数)：</lable>
                <input name="prePayTime" id="prePayTime" class="mini-textbox" emptyText="预付款时间(从企业的默认帐期中取，从而计算天数)"  style="width:100px;"/>
				<lable class="form-label">业务类型：</lable>
                <input name="businessId" id="businessId" class="mini-textbox" emptyText="业务类型"  style="width:100px;"/>
				<lable class="form-label">票据类型：（0采购订单；1：出货订单）：</lable>
                <input name="billType" id="billType" class="mini-textbox" emptyText="票据类型：（0采购订单；1：出货订单）"  style="width:100px;"/>
				<lable class="form-label">付款状态；0未付；1已付：</lable>
                <input name="payState" id="payState" class="mini-textbox" emptyText="付款状态；0未付；1已付"  style="width:100px;"/>
				<lable class="form-label">付款时间：</lable>
                <input name="payTime" id="payTime" class="mini-textbox" emptyText="付款时间"  style="width:100px;"/>
				<lable class="form-label">付款确认人：</lable>
                <input name="payConfirmUser" id="payConfirmUser" class="mini-textbox" emptyText="付款确认人"  style="width:100px;"/>
				<lable class="form-label">创建用户ID：</lable>
                <input name="cuserId" id="cuserId" class="mini-textbox" emptyText="创建用户ID"  style="width:100px;"/>
				<lable class="form-label">创建时间：</lable>
                <input name="ctime" id="ctime" class="mini-textbox" emptyText="创建时间"  style="width:100px;"/>
                <a class="mini-button" iconCls="icon-search" onclick="PageBill.funSearch()" plain="true"><label>查询</label></a>
                <a class="mini-button" iconCls="icon-reset" onclick="PageBill.funReset()" plain="true"><label>重置</label></a>
            </div>
        </div>
    </div>
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:30%;">
                        <a  class="mini-button block-button" iconCls="icon-add" onclick="PageBill.funAdd()">新增</a>
                        <a  class="mini-button block-button hide" iconCls="icon-edit" id="edit" onclick="PageBill.funModify()">修改</a>
                        <a  class="mini-button block-button hide" iconCls="icon-remove" id="remove" onclick="PageBill.funDelete()">删除</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="billGrid" class="mini-datagrid"
                  idField="id" allowResize="false"
                 url="${pageContext.request.contextPath}/bill/query"
                 pagesize="50" sizeList="[10,30,50,100]" allowAlternating="true"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="5%">序号</div>
					 <div field="billNo" width="120" headerAlign="center" allowSort="true">票据编号</div>
					 <div field="customerId" width="120" headerAlign="center" allowSort="true">所属企业</div>
					 <div field="billTime" width="120" headerAlign="center" allowSort="true">开票时间</div>
					 <div field="prePayTime" width="120" headerAlign="center" allowSort="true">预付款时间(从企业的默认帐期中取，从而计算天数)</div>
					 <div field="businessId" width="120" headerAlign="center" allowSort="true">业务类型</div>
					 <div field="billType" width="120" headerAlign="center" allowSort="true">票据类型：（0采购订单；1：出货订单）</div>
					 <div field="payState" width="120" headerAlign="center" allowSort="true">付款状态；0未付；1已付</div>
					 <div field="payTime" width="120" headerAlign="center" allowSort="true">付款时间</div>
					 <div field="payConfirmUser" width="120" headerAlign="center" allowSort="true">付款确认人</div>
					 <div field="cuserId" width="120" headerAlign="center" allowSort="true">创建用户ID</div>
					 <div field="ctime" width="120" headerAlign="center" allowSort="true">创建时间</div>
                    <div field="show_plan" width="10%" headerAlign="center" allowSort="false"
                         align="left">操作
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
