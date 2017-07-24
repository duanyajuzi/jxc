
var PageDictAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            dictForm : null
            
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.dictForm = new mini.Form("dictFormAdd");
        },
        funSetData : function(data)
        {
            var row = data.row;
            var dmbm=mini.get("dmbm");
        	this.action = data.action;
        	this.dictForm.setData(row);
            dmbm.setValue(row.nr);
        },
        funSave : function()
        {
        	this.dictForm.validate();
            if (!this.dictForm.isValid()) 
            {
                 var errorTexts = form.getErrorTexts();
                 for (var i in errorTexts) 
                 {
                     mini.alert(errorTexts[i]);
                     return;
                 }
            }
            
            var me = this;
            var obj = this.dictForm.getData(true);
            $.ajax({
               url : me.basePath + "/dict/" + me.action + "?a="+Math.random(),
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
	PageDictAdd.init();
});