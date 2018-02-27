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
        <div id="blueprintFormAdd">
       
                	<input id="id" name="id"  class="mini-hidden" />
            <table class="form-table" border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;">
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">客户名称：</td>
                    <td style="width:32%;">
                        <input name="pname" id="pname" class="mini-textbox" style="width:200px;"  required="true" maxlength="100" requiredErrorText="方案名称不能为空"/>
                    </td>
                </tr>
                <tr>
                    <%--<td class="form-label" style="text-align: right;width:16%;">商品名称：</td>--%>
                    <td style="width:32%;">
                        <input name="goodsId" id="goodsId" class="mini-textbox" style="width:200px;" allowInput="true"
                               <%--url="${pageContext.request.contextPath}/goods/queryGoodsList" idField="id" textField="goodsName"--%>
                               required="true" maxlength="19" requiredErrorText="商品名称不能为空"  visible="false"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">数量：</td>
                    <td style="width:32%;">
                        <input name="goodsNum" id="goodsNum" class="mini-textbox" style="width:200px;"
                               required="true" vtype="float;maxlength:19" requiredErrorText="数量不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">客户料号：</td>
                    <td style="width:32%;">
                        <input name="materialNum" id="materialNum" class="mini-textbox" style="width:200px;"
                               required="true" requiredErrorText="客户料号不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">销售未税价：</td>
                    <td style="width:32%;">
                        <input name="price" id="price" class="mini-textbox" style="width:120px;"  required="true" maxlength="12" requiredErrorText="单价不能为空"/>
                        <input name="unit" id="unit" class="mini-combobox" style="width:75px;"
                               textField="msgVal"  valueField="msgKey"  emptyText="请选择"
                               required="true" vtype="float;maxlength:10" requiredErrorText="单位不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">备注：</td>
                    <td style="width:32%;">
                        <input name="memo" id="memo" class="mini-textArea" style="width:200px;"  required="true" maxlength="300" requiredErrorText="备注不能为空"/>
                    </td>
                </tr>
            </table>
         </div>
            
        </div>
        <div  region="south" showSplit="false" showSplitIcon="false" height="30" showHeader="false"  style="border: none">
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
