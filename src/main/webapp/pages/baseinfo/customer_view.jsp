<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/baseinfo/js/customer_view.js'/>"></script>
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

        .mini-textbox-input[readonly], .mini-buttonedit-readOnly>.mini-buttonedit-border, .mini-buttonedit-readOnly .mini-buttonedit-input[readonly] {
            background-color: white;
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
                        <input name="customerName" id="customerName" class="mini-textbox"  readonly style="width:80%"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:15%;">联系人：</td>
                    <td>
                        <input name="contacts" id="contacts" class="mini-textbox"  readonly style="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;">联系地址：</td>
                    <td >
                        <input name="address" id="address" class="mini-textbox"  readonly style="width:80%"/>
                    </td>
                    <td class="form-label" style="text-align: right;">联系人电话：</td>
                    <td>
                        <input name="tel" id="tel" class="mini-textbox"  readonly style="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;">收货人姓名：</td>
                    <td>
                        <input name="consigneeName" id="consigneeName" class="mini-textbox"  readonly style="width:80%"/>
                    </td>
                    <td class="form-label" style="text-align: right;">收货人电话：</td>
                    <td >
                        <input name="consigneeTel" id="consigneeTel" class="mini-textbox"  readonly style="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;">社会信用代码：</td>
                    <td>
                        <input name="creditCode" id="creditCode" class="mini-textbox"  readonly style="width:80%"/>
                    </td>
                    <td class="form-label" style="text-align: right;">开票电话：</td>
                    <td>
                        <input name="openTel" id="openTel" class="mini-textbox"  readonly style="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;">开户行：</td>
                    <td>
                        <input name="openBank" id="openBank" class="mini-textbox"  readonly style="width:80%"/>
                    </td>
                    <td class="form-label" style="text-align: right;">银行帐号：</td>
                    <td>
                        <input name="bankAccount" id="bankAccount" class="mini-textbox" readonly style="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;">开票地址：</td>
                    <td colspan="3">
                        <input name="billingAddress" id="billingAddress" class="mini-textbox" style="width:92%;"  readonly/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;">备注：</td>
                    <td colspan="3">
                        <input name="memo" id="memo" class="mini-textArea" style="width:92%;" readonly/>
                    </td>
                </tr>
            </table>
         </div>
            
        </div>
    </div>
    <script type="text/javascript">
    	mini.parse();
    </script>
</body>
</html>
