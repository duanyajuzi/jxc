<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/blueprint_add.js'/>"></script>
    <title></title>
    <style>
        .mini-grid-cell-inner, .mini-grid-headerCell-inner {
            padding: 3px 0 !important;
        }
        .mini-textbox-border{
            height: 21px;
        }

        .mini-grid-cell-nowrap {
            text-align: center;
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
        .mini-grid-rows-view {
            height: 118px;
        }
    </style>
</head>
<body>
<div class="mini-layout" style="width: 100%;height: 100%;" allowResize="false">
        <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;margin-top: 15px">
        <div id="blueprintFormAdd">
            <input id="id" name="id"  class="mini-hidden" />
            <table class="form-table" border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;">
                <tr>
                    <td class="form-label" style="text-align: right;width:20%;">客户名称：</td>
                    <td>
                        <input name="pname" id="pname" class="mini-combobox" style="width:84%"
                               allowInput="true" idField="id" textField="customerName" valueFromSelect="true"
                               url="${pageContext.request.contextPath}/customer/queryCustomerList" onvalidation="PageBlueprintAdd.onPnameValidation"
                               required="true" maxlength="10" requiredErrorText="客户方案名称不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:20%;">客户料号：</td>
                    <td>
                        <input name="materialNum" id="materialNum" class="mini-textbox" style="width:84%"
                               onvalidation="PageBlueprintAdd.onSimilarValidation" required="true" requiredErrorText="客户料号不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="goodsId" id="goodsId" class="mini-textbox" style="width:84%" allowInput="true"
                               required="true"  requiredErrorText="商品名称不能为空"  visible="false"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:20%;">销售价(未税)：</td>
                    <td>
                        <input name="price" id="price" class="mini-textbox" style="width:68%;"  required="true" maxlength="12" requiredErrorText="单价不能为空"/>
                        <input name="unit" id="unit" class="mini-combobox" style="width:15%;"
                               textField="msgVal"  valueField="msgKey"  emptyText="请选择"
                               required="true" vtype="float;maxlength:10" requiredErrorText="单位不能为空"/>
                    </td>
                </tr>
               <%-- <tr>
                    <td class="form-label" style="text-align: right;width:20%;">数量：</td>
                    <td>
                        <input name="goodsNum" id="goodsNum" class="mini-textbox" style="width:84%"
                               required="true" vtype="float;maxlength:19" requiredErrorText="数量不能为空"/>
                    </td>
                </tr>--%>
                <tr>
                    <td class="form-label" style="text-align: right;">备注：</td>
                    <td>
                        <input name="memo" id="memo" class="mini-textArea" style="width:84%"  maxlength="300" requiredErrorText="备注不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;">是否有阶梯价：</td>
                    <td>
                        <input id="checkbox" name="checkbox" class="mini-checkbox" text="是"
                               onvaluechanged="PageBlueprintAdd.onValueChanged" trueValue="1" falseValue="0"/>
                    </td>
                </tr>
            </table>
            <div style="width:80%;margin-left: 10%;display: none" id="toolbar" name="toolbar">
                <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
                    <table style="width:100%;">
                        <tr>
                            <td style="width:100%;">
                                <a class="mini-button" iconCls="icon-add" onclick="PageBlueprintAdd.addRow()" plain="true" tooltip="增加...">增加</a>
                                <a class="mini-button" iconCls="icon-remove" onclick="PageBlueprintAdd.removeRow()" plain="true">删除</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="datagrid1" class="mini-datagrid" style="width:80%;height:150px;margin-left: 10%;display: none"
                 url="${pageContext.request.contextPath}/ladderPrice/getList" idField="id" ajaxType="get"
                 allowResize="fasle" pageSize="20"
                 allowCellEdit="true" allowCellSelect="true" multiSelect="true"
                 editNextOnEnterKey="true"  editNextRowCell="true" showPager="false">
                <div property="columns">
                    <div name="num"  field="num" headerAlign="center" width="100">数量
                        <input property="editor" class="mini-spinner"  value="1" minValue="1" maxValue="100000000"  maxlength="20" style="width:100%;" />
                    </div>
                    <div field="price" width="100" headerAlign="center" >价格
                        <input property="editor" id="danJia" class="mini-spinner"  minValue="0" maxValue="100000000" style="width:100%;"/>
                    </div>
                </div>
            </div>
         </div>
        </div>
        <div region="south" showSplit="false" showSplitIcon="false" height="30" showHeader="false"  style="border: none">
	        <div class="mini-toolbar" style="position: fixed;left:0;bottom: 0;right:0;text-align: center;border-width: 1px 0 0 0" >
	            <a class="mini-button cursor" onclick="PageBlueprintAdd.funSave()" iconCls="icon-save">保存</a>
	            <a class="mini-button cursor" onclick="PageBlueprintAdd.funCancel()" iconCls="icon-cancel" style="margin:0 20px;">取消</a>
	        </div>
        </div>
    </div>
    <script type="text/javascript">
    	mini.parse();
    </script>
</body>
</html>
