
var PageInoutStockItemAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            InoutStockItemForm:null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.InoutStockItemForm = new mini.Form("InoutStockItemForm");
            this.InoutStockItemForm.setEnabled(false);
            mini.get("price").setEnabled(true)
        },
        funSetData : function(data)
        {
            var row = data.row;
            if(row.orderType==0){
                $("#khlhhidden").hide();
            }
            this.action = data.action;
            this.InoutStockItemForm.setData(row);
        },
        funSave : function()
        {
            this.InoutStockItemForm.validate();
            if (!this.InoutStockItemForm.isValid())
            {
                var errorTexts = form.getErrorTexts();
                for (var i in errorTexts)
                {
                    mini.alert(errorTexts[i]);
                    return;
                }
            }

            var me = this;
            var obj = this.InoutStockItemForm.getData(true);
            $.ajax({
                url : me.basePath + "/orderItem/" + me.action + "?a="+Math.random(),
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
        }
    }
}();

$(function(){
    PageInoutStockItemAdd.init();
});
