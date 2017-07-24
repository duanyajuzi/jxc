
var PageGoodCustomerAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            goodCustomerForm : null
            
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.goodCustomerForm = new mini.Form("goodCustomerFormAdd");
            PageMain.funDictInfo("unit", false, "", "danwei");

        },
        funBusValuechanged : function(e)
        {
            PageMain.funLoadGoodsByBussinessId("goodId", false, "", e.value)
        },
        funSetData : function(data)
        {
        	var row = data.row;
        	this.action = data.action;
            if(this.action == "modify")
            {
                PageMain.funLoadGoodsByBussinessId("goodId", false, "", row.businessId);
            }
        	this.goodCustomerForm.setData(row);
        },
        funSave : function()
        {
        	this.goodCustomerForm.validate();
            if (!this.goodCustomerForm.isValid()) 
            {
                 var errorTexts = form.getErrorTexts();
                 for (var i in errorTexts) 
                 {
                     mini.alert(errorTexts[i]);
                     return;
                 }
            }
            
            var me = this;
            var obj = this.goodCustomerForm.getData(true);
            $.ajax({
               url : me.basePath + "/goodCustomer/" + me.action + "?a="+Math.random(),
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
	PageGoodCustomerAdd.init();
});