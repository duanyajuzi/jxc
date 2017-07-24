<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/baseinfo/js/customer.js'/>"></script>
    <title></title>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
    <div title="north" region="north" showSplit="false" showSplitIcon="false" height="70" showHeader="false"
         style="border: none">
        <div id="p1" class="mini-panel" title="检索条件" style="width: 100%;height:100%"
             showCloseButton="false">
            <div style="margin-left: 2%;display:inline;line-height: 32px;" class="searchBar" id="customerForm">
                
				<lable class="form-label">客户名称：</lable>
                <input name="customerName" id="customerName" class="mini-textbox" emptyText="客户名称"  style="width:100px;"/>
				<lable class="form-label">联系人：</lable>
                <input name="contacts" id="contacts" class="mini-textbox" emptyText="联系人"  style="width:100px;"/>
				<%--<lable class="form-label">联系地址：</lable>--%>
                <%--<input name="address" id="address" class="mini-textbox" emptyText="联系地址"  style="width:100px;"/>--%>
				<lable class="form-label">联系人电话：</lable>
                <input name="tel" id="tel" class="mini-textbox" emptyText="联系人电话"  style="width:100px;"/>
				<lable class="form-label">收货人姓名：</lable>
                <input name="consigneeName" id="consigneeName" class="mini-textbox" emptyText="收货人姓名"  style="width:100px;"/>
				<lable class="form-label">收货人电话：</lable>
                <input name="consigneeTel" id="consigneeTel" class="mini-textbox" emptyText="收货人电话"  style="width:100px;"/>
				<%--<lable class="form-label">社会信用代码：</lable>--%>
                <%--<input name="creditCode" id="creditCode" class="mini-textbox" emptyText="社会信用代码"  style="width:100px;"/>--%>
				<%--<lable class="form-label">开票地址：</lable>--%>
                <%--<input name="billingAddress" id="billingAddress" class="mini-textbox" emptyText="开票地址"  style="width:100px;"/>--%>
				<%--<lable class="form-label">开户行：</lable>--%>
                <%--<input name="openBank" id="openBank" class="mini-textbox" emptyText="开户行"  style="width:100px;"/>--%>
				<%--<lable class="form-label">银行帐号：</lable>--%>
                <%--<input name="bankAccount" id="bankAccount" class="mini-textbox" emptyText="银行帐号"  style="width:100px;"/>--%>
				<%--<lable class="form-label">开票电话：</lable>--%>
                <%--<input name="openTel" id="openTel" class="mini-textbox" emptyText="开票电话"  style="width:100px;"/>--%>
				<%--<lable class="form-label">备注：</lable>--%>
                <%--<input name="memo" id="memo" class="mini-textbox" emptyText="备注"  style="width:100px;"/>--%>
                <a class="mini-button" iconCls="icon-search" onclick="PageCustomer.funSearch()" plain="true"><label>查询</label></a>
                <a class="mini-button" iconCls="icon-reset" onclick="PageCustomer.funReset()" plain="true"><label>重置</label></a>
            </div>
        </div>
    </div>
    <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:30%;">
                        <a  class="mini-button block-button" iconCls="icon-add" onclick="PageCustomer.funAdd()">新增</a>
                        <a  class="mini-button block-button hide" iconCls="icon-edit" id="edit" onclick="PageCustomer.funModify()">修改</a>
                        <a  class="mini-button block-button hide" iconCls="icon-remove" id="remove" onclick="PageCustomer.funDelete()">删除</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="customerGrid" class="mini-datagrid"
                  idField="id" allowResize="false"
                 url="${pageContext.request.contextPath}/customer/query"
                 pagesize="50" sizeList="[10,30,50,100]" allowAlternating="true"  sortMode="client" style="height: 100%;">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center"  width="5%">序号</div>
					 <div field="customerName" width="120" headerAlign="center" allowSort="true">客户名称</div>
					 <div field="contacts" width="120" headerAlign="center" allowSort="true">联系人</div>
					 <div field="address" width="120" headerAlign="center" allowSort="true">联系地址</div>
					 <div field="tel" width="120" headerAlign="center" allowSort="true">联系人电话</div>
					 <div field="consigneeName" width="120" headerAlign="center" allowSort="true">收货人姓名</div>
					 <div field="consigneeTel" width="120" headerAlign="center" allowSort="true">收货人电话</div>
					 <div field="creditCode" width="120" headerAlign="center" allowSort="true">社会信用代码</div>
					 <div field="billingAddress" width="120" headerAlign="center" allowSort="true">开票地址</div>
					 <div field="openBank" width="120" headerAlign="center" allowSort="true">开户行</div>
					 <div field="bankAccount" width="120" headerAlign="center" allowSort="true">银行帐号</div>
					 <div field="openTel" width="120" headerAlign="center" allowSort="true">开票电话</div>
					 <div field="memo" width="120" headerAlign="center" allowSort="true">备注</div>
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
