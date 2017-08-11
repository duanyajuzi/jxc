<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/order_add.js'/>"></script>
    <title></title>
    <style>
        .mini-grid-cell-inner, .mini-grid-headerCell-inner {
            padding: 3px 0 !important;
        }
        .mini-textbox-border{
            height: 24px;
        }
        .mini-textbox-input{
            height: 24px;
            line-height: 24px;
        }
        .mini-textbox{
            height: 27px;
        }

        html, body {
            margin: 0px;
            padding: 0;
            border: 0;
            width: 100%;
            height: 100%;
            overflow: hidden;
        }
    </style>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
        <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div id="orderFormAdd">
       
                	<input id="id" name="id"  class="mini-hidden" />
            <table class="form-table" border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;">
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">订单编号：</td>
                    <td style="width:32%;">
                        <input name="orderNo" id="orderNo" class="mini-textbox" style="width:200px;"  required="true" maxlength="20" requiredErrorText="订单编号不能为空"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">订单名称：</td>
                    <td style="width:32%;">
                        <input name="orderName" id="orderName" class="mini-textbox" style="width:200px;"  required="true" maxlength="100" requiredErrorText="订单名称不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">订单时间：</td>
                    <td style="width:32%;">
                    	<input name="orderTime" id="orderTime" class="mini-datepicker" style="width:200px;"
                               format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss" showTime="true"  showOkButton="true" showClearButton="false"
                               nullValue="null"  emptyText="订单时间" allowInput="false"  />
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">交货时间：</td>
                    <td style="width:32%;">
                        <input name="deliveryTime" id="deliveryTime" class="mini-datepicker" style="width:200px; "
                               format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss" showTime="true"  showOkButton="true" showClearButton="false"
                               nullValue="null"  emptyText="交货时间" allowInput="false"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">交货地址：</td>
                    <td style="width:32%;">
                        <input name="deliveryAddress" id="deliveryAddress" class="mini-textbox" style="width:520px;"  required="true" maxlength="200" requiredErrorText="交货地址不能为空"/>
                    </td>
                </tr>
                    <td class="form-label" style="text-align: right;width:16%;">厂商：</td>
                    <td style="width:32%;">
                        <input name="customerId" id="customerId" class="mini-combobox" style="width:200px;"  required="true" maxlength="10"
                               requiredErrorText="厂商不能为空" onvaluechanged="PageOrderAdd.onDeptChanged()" allowInput="true"
                             allowput="true"  idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList" />
                    </td>
                     <td class="form-label" style="text-align: right;width:16%;">采购公司：</td>
                    <td style="width:32%;">
                    <input name="pcustomerId" id="pcustomerId" class="mini-combobox" style="width:200px;"
                           onvaluechanged="PageOrderAdd.onDeptChanged()" allowInput="true"
                           idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList"
                           required="true" maxlength="10" requiredErrorText="采购公司不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">厂商联系人：</td>
                    <td style="width:32%;">
                        <input name="ccontacts" id="ccontacts" class="mini-textbox" style="width:200px;"  required="true" maxlength="40"
                               requiredErr orText="厂商联系人不能为空"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">采购人：</td>
                    <td style="width:32%;">
                        <input name="pcontacts" id="pcontacts" class="mini-textbox" style="width:200px;"  required="true" maxlength="40" requiredErrorText="采购人不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">厂商联系地址：</td>
                    <td style="width:32%;">
                        <input name="caddress" id="caddress" class="mini-textbox" style="width:200px;"  required="true" maxlength="200" requiredErrorText="厂商联系地址不能为空"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">采购商联系地址：</td>
                    <td style="width:32%;">
                        <input name="paddress" id="paddress" class="mini-textbox" style="width:200px;"  required="true" maxlength="200" requiredErrorText="联系地址不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">厂商联系电话：</td>
                    <td style="width:32%;">
                        <input name="ctel" id="ctel" class="mini-textbox" style="width:200px;"  required="true" maxlength="40" requiredErrorText="厂商联系电话不能为空"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">采购商联系电话：</td>
                    <td style="width:32%;">
                        <input name="ptel" id="ptel" class="mini-textbox" style="width:200px;"  required="true" maxlength="40" requiredErrorText="联系电话不能为空"/>
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<td class="form-label" style="text-align: right;width:16%;">创建时间：</td>--%>
                    <%--<td style="width:32%;">--%>
                    	<%--<input name="ctime" id="ctime" class="mini-datepicker" style="width:200px;--%>
                    	<%--" format="yyyy-MM-dd"  nullValue="null"  emptyText="开始时间" allowInput="false"/>--%>
                    <%--</td>--%>
                    <%--<td class="form-label" style="text-align: right;width:16%;">创建人：</td>--%>
                    <%--<td style="width:32%;">--%>
                        <%--<input name="cuserid" id="cuserid" class="mini-textbox" style="width:200px;"  required="true" maxlength="10" requiredErrorText="创建人不能为空"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">备注：</td>
                    <td style="width:32%;">
                        <input name="memo" id="memo" class="mini-textArea" style="width:520px;"  required="true" maxlength="400" requiredErrorText="备注不能为空"/>
                    </td>
                </tr>
            </table>
         </div>
            
        </div>
        <div  region="south" showSplit="false" showSplitIcon="false" height="30" showHeader="false"  style="border: none">
	        <div class="mini-toolbar" style="position: fixed;left:0;bottom: 0;right:0;text-align: center;border-width: 1px 0 0 0" >
	            <a class="mini-button cursor" onclick="PageOrderAdd.funSave()" iconCls="icon-save">保存</a>
	            <a class="mini-button cursor" onclick="PageOrderAdd.funCancel()" iconCls="icon-cancel" style="margin:0 20px;">取消</a>
	        </div>
        </div>
    </div>
    <script type="text/javascript">
    	mini.parse();
    </script>
</body>
</html>
