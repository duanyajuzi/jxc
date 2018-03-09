<%--
  Created by IntelliJ IDEA.
  User: cx
  Date: 2018/3/9
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/orderinfo/js/inoutStockItem_add.js'/>"></script>
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
        <div id="InoutStockItemForm">
            <input id="id" name="id"  class="mini-hidden" />
            <table class="form-table" border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;margin: 15px auto">
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">客户订单号：</td>
                    <td style="width:32%;">
                        <input name="orderName" id="orderName" class="mini-textbox" style="width:200px;"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">商品名称：</td>
                    <td style="width:32%;">
                        <input name="goodsName" id="goodsName" class="mini-textbox" style="width:200px;"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">原厂料号：</td>
                    <td style="width:32%;">
                        <input name="ycmaterialNum" id="ycmaterialNum" class="mini-textbox" style="width:200px;"/>
                    </td>
                </tr>
                <tr id="khlhhidden">
                    <td class="form-label" style="text-align: right;width:16%;">客户料号：</td>
                    <td style="width:32%;">
                        <input name="materialNum" id="materialNum" class="mini-textbox" style="width:200px;"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">出库数量：</td>
                    <td style="width:32%;">
                        <input name="goodNum" id="goodNum" class="mini-textbox" style="width:200px;" />
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">单价：</td>
                    <td style="width:32%;">
                        <input name="price" id="price" class="mini-spinner" minValue="0" maxValue="100000000"  style="width:200px;" required="true"/>
                    </td>
                </tr>
            </table>
        </div>

    </div>
    <div  region="south" showSplit="false" showSplitIcon="false" height="30" showHeader="false"  style="border: none">
        <div class="mini-toolbar" style="position: fixed;left:0;bottom: 0;right:0;text-align: center;border-width: 1px 0 0 0" >
            <a class="mini-button cursor" onclick="PageInoutStockItemAdd.funSave()" iconCls="icon-save">保存</a>
            <a class="mini-button cursor" onclick="PageInoutStockItemAdd.funCancel()" iconCls="icon-cancel" style="margin:0 20px;">取消</a>
        </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
</script>
</body>
</html>

