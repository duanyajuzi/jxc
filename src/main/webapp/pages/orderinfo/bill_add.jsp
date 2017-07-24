<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/bill_add.js'/>"></script>
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
        <div id="billFormAdd">
       
                	<input id="id" name="id"  class="mini-hidden" />
            <table class="form-table" border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;">
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">票据编号：</td>
                    <td style="width:32%;">
                        <input name="billNo" id="billNo" class="mini-textbox" style="width:200px;"  required="true" maxlength="255" requiredErrorText="票据编号不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">所属企业：</td>
                    <td style="width:32%;">
                        <input name="customerId" id="customerId" class="mini-textbox" style="width:200px;"  required="true" maxlength="19" requiredErrorText="所属企业不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">开票时间：</td>
                    <td style="width:32%;">
                    	<input name="billTime" id="billTime" class="mini-datepicker" style="width:200px;" format="yyyy-MM-dd"  nullValue="null"  emptyText="开始时间" allowInput="false"/>
                    </td>
                    
                    
                    
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">预付款时间(从企业的默认帐期中取，从而计算天数)：</td>
                    <td style="width:32%;">
                    	<input name="prePayTime" id="prePayTime" class="mini-datepicker" style="width:200px;" format="yyyy-MM-dd"  nullValue="null"  emptyText="开始时间" allowInput="false"/>
                    </td>
                    
                    
                    
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">业务类型：</td>
                    <td style="width:32%;">
                        <input name="businessId" id="businessId" class="mini-textbox" style="width:200px;"  required="true" maxlength="19" requiredErrorText="业务类型不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">票据类型：（0采购订单；1：出货订单）：</td>
                    <td style="width:32%;">
                        <input name="billType" id="billType" class="mini-textbox" style="width:200px;"  required="true" maxlength="5" requiredErrorText="票据类型：（0采购订单；1：出货订单）不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">付款状态；0未付；1已付：</td>
                    <td style="width:32%;">
                        <input name="payState" id="payState" class="mini-textbox" style="width:200px;"  required="true" maxlength="5" requiredErrorText="付款状态；0未付；1已付不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">付款时间：</td>
                    <td style="width:32%;">
                    	<input name="payTime" id="payTime" class="mini-datepicker" style="width:200px;" format="yyyy-MM-dd"  nullValue="null"  emptyText="开始时间" allowInput="false"/>
                    </td>
                    
                    
                    
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">付款确认人：</td>
                    <td style="width:32%;">
                        <input name="payConfirmUser" id="payConfirmUser" class="mini-textbox" style="width:200px;"  required="true" maxlength="19" requiredErrorText="付款确认人不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">创建用户ID：</td>
                    <td style="width:32%;">
                        <input name="cuserId" id="cuserId" class="mini-textbox" style="width:200px;"  required="true" maxlength="19" requiredErrorText="创建用户ID不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">创建时间：</td>
                    <td style="width:32%;">
                    	<input name="ctime" id="ctime" class="mini-datepicker" style="width:200px;" format="yyyy-MM-dd"  nullValue="null"  emptyText="开始时间" allowInput="false"/>
                    </td>
                    
                    
                    
                </tr>
            </table>
         </div>
            
        </div>
        <div  region="south" showSplit="false" showSplitIcon="false" height="30" showHeader="false"  style="border: none">
	        <div class="mini-toolbar" style="position: fixed;left:0;bottom: 0;right:0;text-align: center;border-width: 1px 0 0 0" >
	            <a class="mini-button cursor" onclick="PageBillAdd.funSave()" iconCls="icon-save">保存</a>
	            <a class="mini-button cursor" onclick="PageBillAdd.funCancel()" iconCls="icon-cancel" style="margin:0 20px;">取消</a>
	        </div>
        </div>
    </div>
    <script type="text/javascript">
    	mini.parse();
    </script>
</body>
</html>
