
var PageInoutStockAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            InoutStockFormAdd:null,
            pOrderType:null

        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.InoutStockFormAdd = new mini.Form("InoutStockFormAdd");
        },
        funSetData : function(data)
        {
            var row = data.row;
            this.pOrderType=row.orderType;
            this.action = data.action;
            this.InoutStockFormAdd.setData(row);
        },
        funSave : function()
        {
            this.InoutStockFormAdd.validate();
            if (!this.InoutStockFormAdd.isValid())
            {
                var errorTexts = form.getErrorTexts();
                for (var i in errorTexts)
                {
                    mini.alert(errorTexts[i]);
                    return;
                }
            }

            var me = this;
            var obj = this.InoutStockFormAdd.getData(true);
            obj.orderType=this.pOrderType;
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
    PageInoutStockAdd.init();
});
