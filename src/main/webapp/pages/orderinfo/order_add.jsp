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
<div class="mini-layout" style="width: 100%;height: 490px" allowResize="false">
        <div title="center" region="center" allowResize="false" showSplit="true" showSplitIcon="false" allowUnselect="false" style="border:0 none;">
        <div id="orderFormAdd">
                	<input id="id" name="id"  class="mini-hidden" />
            <table class="form-table" border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;">
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">订单编号：</td>
                    <td style="width:32%;">
                        <input name="orderNo" id="orderNo" readonly class="mini-textbox" style="width:200px;"  required="true" maxlength="20" requiredErrorText="订单编号不能为空"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">客户订单号：</td>
                    <td style="width:32%;">
                        <input name="orderName" id="orderName" class="mini-textbox" style="width:200px;"  required="true" maxlength="100" requiredErrorText="客户订单号不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">业务类型：</td>
                    <td style="width:32%;">
                        <input name="businessId" id="businessId" class="mini-combobox" style="width:200px; "
                               idField="id" textField="business" url="${pageContext.request.contextPath}/business/queryBusinessList"
                               required="true" maxlength="15" requiredErrorText="业务类型不能为空"
                                emptyText="请输入或选择" allowInput="true"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">交货时间：</td>
                    <td style="width:32%;">
                        <input name="deliveryTime" id="deliveryTime" class="mini-datepicker" style="width:200px; "
                               format="yyyy-MM-dd" showTime="true" required="true" showOkButton="true" showClearButton="false"
                               nullValue="null"  emptyText="交货时间" allowInput="false"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">交货地址：</td>
                    <td colspan="3">
                        <input name="deliveryAddress" id="deliveryAddress" class="mini-textbox" style="width:573px;"  required="true" maxlength="200" requiredErrorText="交货地址不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">工厂：</td>
                    <td style="width:32%;">
                        <input name="customerId" id="customerId" class="mini-combobox" style="width:200px;"  required="true" maxlength="10"
                               requiredErrorText="工厂不能为空" onvaluechanged="PageOrderAdd.onDeptChanged()" allowInput="true"
                             allowput="true"  idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList" />
                    </td>
                     <td class="form-label" style="text-align: right;width:16%;">客户：</td>
                    <td style="width:32%;">
                    <input name="pcustomerId" id="pcustomerId" class="mini-combobox" style="width:200px;"
                           onvaluechanged="PageOrderAdd.onDeptChanged()" allowInput="true"
                           idField="id" textField="customerName" url="${pageContext.request.contextPath}/customer/queryCustomerList"
                           required="true" maxlength="10" requiredErrorText="客户不能为空"/>
                    </td>
                </tr>
                <tr hidden>
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
                <tr hidden>
                    <td class="form-label" style="text-align: right;width:16%;">厂商联系地址：</td>
                    <td style="width:32%;">
                        <input name="caddress" id="caddress" class="mini-textbox" style="width:200px;"  required="true" maxlength="200" requiredErrorText="厂商联系地址不能为空"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">采购商联系地址：</td>
                    <td style="width:32%;">
                        <input name="paddress" id="paddress" class="mini-textbox" style="width:200px;"  required="true" maxlength="200" requiredErrorText="联系地址不能为空"/>
                    </td>
                </tr>
                <tr hidden>
                    <td class="form-label" style="text-align: right;width:16%;">厂商联系电话：</td>
                    <td style="width:32%;">
                        <input name="ctel" id="ctel" class="mini-textbox" style="width:200px;"  required="true" maxlength="40" requiredErrorText="厂商联系电话不能为空"/>
                    </td>
                    <td class="form-label" style="text-align: right;width:16%;">采购商联系电话：</td>
                    <td style="width:32%;">
                        <input name="ptel" id="ptel" class="mini-textbox" style="width:200px;"  required="true" maxlength="40" requiredErrorText="联系电话不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">备注：</td>
                    <td style="width:32%;">
                        <input name="memo" id="memo" class="mini-textArea" style="width:573px;" maxlength="400" />
                    </td>
                </tr>
                <tr hidden>
                    <td class="form-label" colspan="2" style="padding-left: 44px;">
                        是否直接生成销售订单：
                        <div id="zdsc" name="zdsc" class="mini-checkbox" readOnly="false" text="" onvaluechanged="onValueChanged"></div>
                        是否控货：
                        <div id="iskh" name="iskh" class="mini-checkbox" readOnly="false" text="" onvaluechanged="onValueChanged"></div>
                    </td>
                </tr>
            </table>

            <div style="width:100%;">
                <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
                    <table style="width:100%;">
                        <tr>
                            <td style="width:100%;">
                                <a class="mini-button" iconCls="icon-add" onclick="PageOrderAdd.addRow()" plain="true" tooltip="增加...">增加</a>
                                <a class="mini-button" iconCls="icon-remove" onclick="PageOrderAdd.removeRow()" plain="true">删除</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div id="orderItemGrid" class="mini-datagrid" style="width:100%;height:270px;"
                 url="${pageContext.request.contextPath}/orderItem/getList" idField="id"
                 allowResize="false" ajaxType="get" showPager="false" oncellbeginedit="PageOrderAdd.OnCellBeginEdit"
                 allowCellEdit="true" allowCellSelect="true" multiSelect="true" oncellcommitedit="PageOrderAdd.onMaterialNumChanged"
                 editNextOnEnterKey="true" allowSortColumn="fasle"  editNextRowCell="true">
                <div property="columns">
                    <div type="indexcolumn"></div>
                    <div type="checkcolumn"></div>
                    <div field="materialNum" name="materialNum"  headerAlign="center" width="100" allowSort="true" >客户料号
                        <input property="editor" class="mini-combobox" style="width:100%;"
                               allowInput="true"  valueField="id" textField="materialNum"
                               required="true" maxlength="19" />
                    </div>
                    <div field="esgouNum" width="100"  headerAlign="center" allowSort="true">数量
                        <input property="editor" class="mini-spinner"  value="1" minValue="1" maxValue="100000000"  maxlength="20" style="width:100%;"/>
                    </div>
                    <div  field="goodsName" headerAlign="center" allowSort="true" width="150" >商品名称
                        <input property="editor" class="mini-textbox" style="width:100%;" minWidth="200" />
                    </div>
                    <div field="price" headerAlign="center" width="100" allowSort="true" >单价（未税）
                        <input property="editor" class="mini-textbox" style="width:100%;"/>
                    </div>
                    <div field="dictName" headerAlign="center" width="100" allowSort="true">单位
                        <input property="editor" class="mini-textbox" style="width:100%;"/>
                    </div>
                    <div field="totalMoney" headerAlign="center"  width="100" allowSort="true" >总价（未税）
                        <input property="editor" class="mini-spinner" style="width:100%;"/>
                    </div>
                    <div field="customerGoodId" headerAlign="center" width="0" allowSort="true" >
                        <input property="editor" class="mini-textbox" style="width:100%;"/>
                    </div>
                    <div field="isHasLadder" headerAlign="center" width="0" allowSort="true" >
                        <input property="editor" class="mini-textbox" style="width:100%;"/>
                    </div>
                    <div field="oneprice" headerAlign="center" width="0" allowSort="true" >
                        <input property="editor" class="mini-textbox" style="width:100%;"/>
                    </div>
                </div>
            </div>
         </div>
        </div>
</div>


        <div  region="south" showSplit="false" showSplitIcon="false" height="30" showHeader="false"  style="border: none">
	        <div class="mini-toolbar" style="position: fixed;left:0;bottom: 0;right:0;text-align: center;border-width: 1px 0 0 0" >
	            <a class="mini-button cursor" onclick="PageOrderAdd.funSave()" iconCls="icon-save">保存</a>
	            <a class="mini-button cursor" onclick="PageOrderAdd.funCancel()" iconCls="icon-cancel" style="margin:0 20px;">取消</a>
	        </div>
        </div>

    <script type="text/javascript">
    	mini.parse();
    </script>
</body>
</html>
