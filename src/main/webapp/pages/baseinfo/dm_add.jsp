<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/top-include.jsp" %>
    <script type="text/javascript" src="<c:url value='/pages/baseinfo/js/dm_add.js'/>"></script>
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
        <div id="dmFormAdd">
       
                	<%--<input id="bm" name="bm"  class="mini-hidden" />--%>
            <table class="form-table" border="0" cellpadding="1" cellspacing="2" style="width:100%;">
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">字典编码：</td>
                    <td style="width:32%;">
                        <input name="bm" id="bm" class="mini-textbox" style="width:200px;"  required="true" maxlength="500" requiredErrorText="字典编码不能为空"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label" style="text-align: right;width:16%;">字典名称：</td>
                    <td style="width:32%;">
                        <input name="nr" id="nr" class="mini-textbox" style="width:200px;"  required="true" maxlength="500" requiredErrorText="字典名称不能为空"/>
                    </td>
                </tr>
            </table>
         </div>
            
        </div>
        <div  region="south" showSplit="false" showSplitIcon="false" height="30" showHeader="false"  style="border: none">
	        <div class="mini-toolbar" style="position: fixed;left:0;bottom: 0;right:0;text-align: center;border-width: 1px 0 0 0" >
	            <a class="mini-button cursor" onclick="PageDmAdd.funSave()" iconCls="icon-save">保存</a>
	            <a class="mini-button cursor" onclick="PageDmAdd.funCancel()" iconCls="icon-cancel" style="margin:0 20px;">取消</a>
	        </div>
        </div>
    </div>
    <script type="text/javascript">
    	mini.parse();
    </script>
</body>
</html>
