
var PageUserAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            userForm : null
            
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.userForm = new mini.Form("userFormAdd");
        },
        funSetData : function(data)
        {
        	var row = data.row;
        	this.action = data.action;
        	this.userForm.setData(row);
        },
        funSave : function()
        {
        	this.userForm.validate();
            if (!this.userForm.isValid()) 
            {
                 var errorTexts = form.getErrorTexts();
                 for (var i in errorTexts) 
                 {
                     mini.alert(errorTexts[i]);
                     return;
                 }
            }
            var me = this;
            var obj = this.userForm.getData(true);
            $.ajax({
               url : me.basePath + "/user/" + me.action + "?a="+Math.random(),
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
        //渲染角色
       onGetRole:function () {
           
        }
    }
}();

$(function(){
	PageUserAdd.init();
});