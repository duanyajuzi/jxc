<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/baseinfo/js/customer_add.js'/>"></script>
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
        <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none; margin-top:30px">
        <div id="customerFormAdd">
            <input id="id" name="id"  class="mini-hidden" />
            <table class="form-table" border="0" cellpadding="1" cellspacing="2" style="width:100%;">
                <tr>
                    <td class="form-label" style="text-align: right;width:15%;">客户名称：</td>
                    <td>
                        <input name="customerName" id="customerName" class="mini-textbox"  required="true" maxlength="100" requiredErrorText="客户名称不能为空" style="width:80%"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:15%;">联系人：</td>
                    <td>
                        <input name="contacts" id="contacts" class="mini-textbox"  required="true" maxlength="50" requiredErrorText="联系人不能为空" style="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;">联系地址：</td>
                    <td >
                        <input name="address" id="address" class="mini-textbox"  required="true" maxlength="300" requiredErrorText="联系地址不能为空" style="width:80%"/>
                    </td>
                    <td class="form-label" style="text-align: right;">联系人电话：</td>
                    <td>
                        <input name="tel" id="tel" class="mini-textbox"  required="true" requiredErrorText="联系人电话不能为空"style="width:80%"
                               vtype="float;maxlength:20"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;">收货人姓名：</td>
                    <td>
                        <input name="consigneeName" id="consigneeName" class="mini-textbox"  required="true" maxlength="50" requiredErrorText="收货人姓名不能为空"
                               style="width:80%"/>
                    </td>
                    <td class="form-label" style="text-align: right;">收货人电话：</td>
                    <td >
                        <input name="consigneeTel" id="consigneeTel" class="mini-textbox"  required="true"
                               style="width:80%"
                               vtype="float;maxlength:20"  requiredErrorText="收货人电话不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;">社会信用代码：</td>
                    <td>
                        <input name="creditCode" id="creditCode" class="mini-textbox" maxlength="50" style="width:80%"/>
                    </td>
                    <td class="form-label" style="text-align: right;">开票电话：</td>
                    <td>
                        <input name="openTel" id="openTel" class="mini-textbox"  maxlength="50" style="width:80%" />
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;">开户行：</td>
                    <td>
                        <input name="openBank" id="openBank" class="mini-textbox" maxlength="300" style="width:80%"/>
                    </td>
                    <td class="form-label" style="text-align: right;">银行帐号：</td>
                    <td>
                        <input name="bankAccount" id="bankAccount" class="mini-textbox" vtype="float;maxLength:20" style="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;">开票地址：</td>
                    <td colspan="3">
                        <input name="billingAddress" id="billingAddress" class="mini-textbox" style="width:92%;" maxlength="300" />
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;">备注：</td>
                    <td colspan="3">
                        <input name="memo" id="memo" class="mini-textArea" style="width:92%;" maxlength="500"/>
                    </td>
                </tr>
            </table>
         </div>
            
        </div>
        <div  region="south" showSplit="false" showSplitIcon="false" height="30" showHeader="false"  style="border: none">
	        <div class="mini-toolbar" style="position: fixed;left:0;bottom: 0;right:0;text-align: center;border-width: 1px 0 0 0" >
	            <a class="mini-button cursor" onclick="PageCustomerAdd.funSave()" iconCls="icon-save">保存</a>
	            <a class="mini-button cursor" onclick="PageCustomerAdd.funCancel()" iconCls="icon-cancel" style="margin:0 20px;">取消</a>
	        </div>
        </div>
    </div>
    <script type="text/javascript">
    	mini.parse();
        //验证手机
        function onPhoneValidation(number) {
            if(number.isValid) {
//               var reg=/^[0-9]{11}$/;
                var reg = /^0?(13|14|15|17|18)[0-9]{9}$/;
                if (!reg.test(number.value)) {
                    number.isValid = false;
                    number.errorText = "请输入正确的手机号码";
                }
            }
        }
        //验证银行卡号
        function onBankValidation(bankAccount) {
            var bankno = bankAccount.value;
            var strBin = "10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";
            if (bankno == "") {
                 bankAccount.isValid = false;
                bankAccount.errorText = "请填写银行卡号";
                return false;
            }
            else if (bankno.length !=16 && bankno.length != 19) {
                 bankAccount.isValid = false;
                bankAccount.errorText = "银行卡号长度必须为16位或19位";
                return false;
            }
            //开头6位
            else if (strBin.indexOf(bankno.substring(0, 2)) == -1) {
                 bankAccount.isValid = false;
                bankAccount.errorText = "银行卡号开头6位不符合规范";
                return false;
            }
        }
    </script>
</body>
</html>
