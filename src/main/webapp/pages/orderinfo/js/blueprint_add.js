
var PageBlueprintAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            blueprintForm : null

        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.blueprintForm = new mini.Form("blueprintFormAdd");
        },
        funSetData : function(data)
        {
            var row = data.row;
            PageMain.funDictInfo("unit", false, "", "danwei");
            this.action = data.action;
            this.blueprintForm.setData(row);
        },
        funSave : function()
        {
            this.blueprintForm.validate();
            if (!this.blueprintForm.isValid())
            {
                var errorTexts = form.getErrorTexts();
                for (var i in errorTexts)
                {
                    mini.alert(errorTexts[i]);
                    return;
                }
            }

            var me = this;
            var obj = this.blueprintForm.getData(true);
            $.ajax({
                url : me.basePath + "/blueprint/" + me.action + "?a="+Math.random(),
                type : 'POST',
                data : obj,
                dataType: 'json',
                success: function (data)
                {
                    mini.alert(data.msg, "提醒", function(){
                        if(data.success)
                        {
                            PageMain.funCloseWindow("save");
                        }
                    });
                },
                error: function (jqXHR, textStatus, errorThrown)
                {
                    PageMain.funShowMessageBox("操作出现异常");
                }
            });
        },
        funCancel : function()
        {
            PageMain.funCloseWindow("cancel");
        },

        onSimilarValidation : function(e) {
            var obj={
                materialNum:e.value,
            }
            $.ajax({
                url : PageMain.basePath+"/blueprint/query",
                type : 'POST',
                data : obj,
                dataType: 'json',
                async: false,
                success: function (data) {
                    if(data.total==0){
                        e.isValid = true;
                    }else if(data.total>0){
                        e.isValid = false;
                        e.errorText = "客户料号不能为空，且不能重复";
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }
            });
        }
    }
}();

$(function(){
    PageBlueprintAdd.init();
});