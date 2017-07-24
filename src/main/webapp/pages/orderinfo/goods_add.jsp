<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/goods_add.js'/>"></script>
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
        <div id="goodsFormAdd">
       
                	<input id="id" name="id"  class="mini-hidden" />
            <table class="form-table" border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;">
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">物料号：</td>
                    <td style="width:32%;">
                        <input name="materialNum" id="materialNum" class="mini-textbox" style="width:200px;"
                               required="true" maxlength="30" requiredErrorText="物料号不能为空"
                               onvalidation="onCheckmaterialNum"
                        />
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">商品名称：</td>
                    <td style="width:32%;">
                        <input name="goodsName" id="goodsName" class="mini-textbox" style="width:200px;"  required="true" maxlength="100" requiredErrorText="商品名称不能为空"/>
                    </td>
                </tr>
                <tr >
                    <td class="form-label" style="text-align: right;width:16%;">单价：</td>
                    <td style="width:32%;">
                        <input name="unitPrice" id="unitPrice" class="mini-textbox" style="width:120px;" vtype="float;maxlength:12"  required="true"  requiredErrorText="单价不能为空"/>
                        <input name="unit" id="unit" class="mini-combobox" style="width:75px;"  required="true"
                               textField="msgVal"  valueField="msgKey"  emptyText="请选择"
                               maxlength="50" requiredErrorText="单位不能为空"/>
                    </td>
                </tr>

                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">规格：</td>
                    <td style="width:32%;">
                        <input name="spec" id="spec" class="mini-textbox" style="width:120px;"  vtype="float;maxlength:12" required="true"  requiredErrorText="规格不能为空"/>
                        <input name="specUnit" id="specUnit" class="mini-combobox" style="width:75px;"  required="true"
                               textField="msgVal"  valueField="msgKey"  emptyText="请选择" maxlength="30" />
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">所属客户：</td>
                    <td style="width:32%;">
                        <input name="customerId" id="customerId" class="mini-combobox" allowInput="true"
                               idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList"
                               style="width:200px;"  required="true" maxlength="10" requiredErrorText="所属客户不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">库存量：</td>
                    <td style="width:32%;">
                        <input name="storage" id="storage" class="mini-textbox" style="width:200px;"  required="true" vtype="float;maxlength:12" requiredErrorText="库存量（只显示，界面上不做修改，同时）不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">业务类型：</td>
                    <td style="width:32%;">
                        <input name="businessId" id="businessId" class="mini-combobox" allowInput="true"
                               idField="id" textField="business" url="${pageContext.request.contextPath}/business/queryBusinessList"
                               style="width:200px;"  required="true" maxlength="10" requiredErrorText="业务类型不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">备注：</td>
                    <td style="width:32%;">
                        <input name="memo" id="memo" class="mini-textArea" style="width:200px;"  required="true" maxlength="100" requiredErrorText="备注不能为空"/>
                    </td>
                </tr>
            </table>
         </div>
            
        </div>
        <div  region="south" showSplit="false" showSplitIcon="false" height="30" showHeader="false"  style="border: none">
	        <div class="mini-toolbar" style="position: fixed;left:0;bottom: 0;right:0;text-align: center;border-width: 1px 0 0 0" >
	            <a class="mini-button cursor" onclick="PageGoodsAdd.funSave()" iconCls="icon-save">保存</a>
	            <a class="mini-button cursor" onclick="PageGoodsAdd.funCancel()" iconCls="icon-cancel" style="margin:0 20px;">取消</a>
	        </div>
        </div>
    </div>
    <script type="text/javascript">
    	mini.parse();
        //验证物料号，只能输入英文字母和汉字
        function onCheckmaterialNum(materialNum) {
            if(materialNum.isValid){
                var pattern=/^[A-Za-z0-9]+$/;
                if(!pattern.test(materialNum.value)){
                    materialNum.isValid=false;
                    materialNum.errorText="只能输入英文字母和数字";
//                    return false;
                }
            }
        }
    </script>
</body>
</html>
