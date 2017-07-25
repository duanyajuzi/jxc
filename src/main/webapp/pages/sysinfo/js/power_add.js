
var PagePowerAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            powerForm : null,
            pidValue:null
            
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.powerForm = new mini.Form("powerFormAdd");
        },
        funSetData : function(data)
        {
            var pid=mini.get("pid");
        	var row= data.row;
            this.action = data.action;
            if(row.pid==1) {
                row.name2 = "所有权限";
            }
            if(this.action=="add"){
                $("#pid").attr("attr-pid",row.pid);
                 this.pidValue =$("#pid").attr("attr-pid");
                pid.setValue(row.powerName);
                pid.disable();
            }else if(this.action=="modify") {
                this.pidValue = row.pid;
                this.powerForm.setData(row);
                pid.setValue(row.name2);
                pid.disable();
            }
        },
        
        funSave : function()
        {
        	this.powerForm.validate();
            if (!this.powerForm.isValid()) 
            {
                 var errorTexts = form.getErrorTexts();
                 for (var i in errorTexts) 
                 {
                     mini.alert(errorTexts[i]);
                     return;
                 }
            }
            var me = this;
            var obj = this.powerForm.getData(true);
            obj.pid=this.pidValue;
            $.ajax({
                url:me.basePath+"/power/queryPowerNoNum",
                type:'post',
                data:{"powerNo":obj.powerNo,"id":obj.id},
                dataType: 'json',
                success:function (result) {
                    if(result==0){
                        $.ajax({
                            url : me.basePath + "/power/" + me.action + "?a="+Math.random(),
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
                    }
                    else if(result >= 1){
                        mini.alert("权限编号已经存在，请修改")
                    }
                },
                error:function () {
                    mini.alert("quey error");
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
	PagePowerAdd.init();
});