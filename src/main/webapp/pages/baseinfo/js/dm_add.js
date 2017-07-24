
var PageDmAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            dmForm : null
            
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.dmForm = new mini.Form("dmFormAdd");
        },
        funSetData : function(data)
        {  
            var bm=mini.get("bm"); 
        	var row = data.row;
        	this.action = data.action;
            if(this.action=="add"){
                
            }else if(this.action=="modify"){
                bm.disable();
            }
        	this.dmForm.setData(row);
        },
        funSave : function()
        {
        	this.dmForm.validate();
            if (!this.dmForm.isValid()) 
            {
                 var errorTexts = form.getErrorTexts();
                 for (var i in errorTexts) 
                 {
                     mini.alert(errorTexts[i]);
                     return;
                 }
            }
            
            var me = this;
            var obj = this.dmForm.getData(true);
            $.ajax({
               url : me.basePath + "/dm/" + me.action + "?a="+Math.random(),
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
	PageDmAdd.init();
});