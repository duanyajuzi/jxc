
var PageBusinessAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            businessForm : null
            
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.businessForm = new mini.Form("businessFormAdd");
        },
        funSetData : function(data)
        {
        	var row = data.row;
        	this.action = data.action;
        	this.businessForm.setData(row);
        },
        funSave : function()
        {
        	this.businessForm.validate();
            if (!this.businessForm.isValid()) 
            {
                 var errorTexts = form.getErrorTexts();
                 for (var i in errorTexts) 
                 {
                     mini.alert(errorTexts[i]);
                     return;
                 }
            }
            
            var me = this;
            var obj = this.businessForm.getData(true);
            $.ajax({
               url : me.basePath + "/business/" + me.action + "?a="+Math.random(),
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
	PageBusinessAdd.init();
});