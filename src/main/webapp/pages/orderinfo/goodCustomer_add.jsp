<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/goodCustomer_add.js'/>"></script>
    <title></title>
    <style>
        .mini-grid-cell-inner, .mini-grid-headerCell-inner {
            padding: 3px 0 !important;
        }
        .mini-textbox-border{
            height: 20px;
        }
        .mini-textbox-input{
            height: 24px;
            line-height: 24px;
        }
        .mini-textbox-input[readonly], .mini-buttonedit-readOnly>.mini-buttonedit-border, .mini-buttonedit-readOnly .mini-buttonedit-input[readonly] {
            background-color: white;
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
        <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;margin-top:10px">
            <div id="goodCustomerFormAdd">
                <input id="id" name="id"  class="mini-hidden" />
                <table class="form-table" border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;">
                    <tr>
                        <td class="form-label" style="text-align: right;width:20%">业务类型：</td>
                        <td>
                            <input name="businessId" id="businessId" class="mini-combobox" allowInput="true" emptyText="请输入或选择"
                                   idField="id" textField="business" url="${pageContext.request.contextPath}/business/queryBusinessList"
                                   style="width:83%;" onvaluechanged="PageGoodCustomerAdd.funBusValuechanged"  required="true" maxlength="10" requiredErrorText="业务类型不能为空"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-label" style="text-align: right;width:20%">所属商品：</td>
                        <td>
                            <input name="goodId" id="goodId" class="mini-combobox" style="width:83%;"  required="true" allowInput="true"
                                   textField="msgVal"  valueField="msgKey" emptyText="请选择"
                                   maxlength="50" requiredErrorText="所属商品不能为空"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-label" style="text-align: right;width:20%">所属工厂：</td>
                        <td>
                            <input name="customerId" id="customerId" class="mini-combobox" allowInput="true"  emptyText="请输入或选择"
                                   idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList"
                                   style="width:83%;"  required="true" maxlength="10" requiredErrorText="所属客户不能为空"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-label" style="text-align: right;width:20%">原厂料号：</td>
                        <td>
                            <input name="materialNum" id="materialNum" class="mini-textbox" style="width:83%;"  required="true" maxlength="100"
                                   <%--onvalidation="PageGoodCustomerAdd.onSimilarValidation"--%> requiredErrorText="物料号不能为空"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-label" style="text-align: right;width:20%">采购价(未税)：</td>
                        <td>
                            <input name="unitPrice" id="unitPrice" class="mini-textbox" style="width:70%;" vtype="float;maxlength:12"  required="true"  requiredErrorText="单价不能为空"/>
                            <input name="unit" id="unit" class="mini-combobox" style="width:12%;"  required="true"
                                   textField="msgVal"  valueField="msgKey"  emptyText="请选择"
                                   maxlength="50" requiredErrorText="单位不能为空"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-label" style="text-align: right;width:20%">是否有阶梯价：</td>
                        <td>
                            <input id="checkbox" name="checkbox" class="mini-checkbox" text="是" trueValue="1" falseValue="0"/>
                        </td>
                    </tr>
                </table>
                <div style="width:80%;margin-left: 10%" id="toolbar" name="toolbar">
                    <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
                        <table style="width:100%;">
                            <tr>
                                <td style="width:100%;">
                                    <a class="mini-button" iconCls="icon-add" onclick="PageGoodCustomerAdd.addRow()" plain="true" tooltip="增加...">增加</a>
                                    <a class="mini-button" iconCls="icon-remove" onclick="PageGoodCustomerAdd.removeRow()" plain="true">删除</a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div id="datagrid1" class="mini-datagrid" style="width:80%;height:200px;margin-left: 10%"
                     url="${pageContext.request.contextPath}/customerPrice/getList" idField="id" ajaxType="get"
                     allowResize="fasle" pageSize="20"
                     allowCellEdit="true" allowCellSelect="true" multiSelect="true"
                     editNextOnEnterKey="true"  editNextRowCell="true" showPager="false">
                    <div property="columns">
                        <div name="num"  field="num" headerAlign="center" allowSort="true" width="100">数量
                            <input property="editor" class="mini-textbox" style="width:100%;" />
                        </div>
                        <div field="price" width="100" allowSort="true" headerAlign="center" >价格
                            <input property="editor" class="mini-textbox" style="width:100%;"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div region="south" showSplit="false" showSplitIcon="false" height="30" showHeader="false"  style="border: none">
	        <div class="mini-toolbar" style="position: fixed;left:0;bottom: 0;right:0;text-align: center;border-width: 1px 0 0 0" >
	            <a class="mini-button cursor" onclick="PageGoodCustomerAdd.funSave()" iconCls="icon-save">保存</a>
	            <a class="mini-button cursor" onclick="PageGoodCustomerAdd.funCancel()" iconCls="icon-cancel" style="margin:0 20px;">取消</a>
	        </div>
        </div>
    </div>
</body>
</html>
