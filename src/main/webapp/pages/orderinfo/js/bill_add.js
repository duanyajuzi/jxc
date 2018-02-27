
var PageBillAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            billForm : null,
            pbillType:null,
            orderId:null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.billForm = new mini.Form("billFormAdd");
        },
        funSetData : function(data)
        {
        	var row = data.row;
            this.pbillType=row.billType;
            this.orderId=row.orderId;
        	this.action = data.action;
            row.payState=PageBillAdd.onPayState(row.payState);
        	this.billForm.setData(row);
            PageBillAdd.labelModel(this.action);
        },
        onPayState:function(e){
        var Genders=[{id:0,text:"未付款"}, {id:1,text:"已付款"}];
        for (var i = 0, l = Genders.length; i < l; i++) {
            var g = Genders[i];
            if (g.id == e) return g.text;
        }
        return "";
        },
        //文本只读
        labelModel:function(action){
            var form = new mini.Form("billFormAdd");
            var payTime=mini.get("payTime");
            var fields = form.getFields();
            for (var i = 0, l = fields.length; i < l; i++) {
                var c = fields[i];
                if (c.setReadOnly) c.setReadOnly(true);     //只读
                if (c.setIsValid) c.setIsValid(true);      //去除错误提示
            }
            if(action=="modify"){
                payTime.setReadOnly(false);
            }
        },
        funSave : function()
        {
        	this.billForm.validate();
            if (!this.billForm.isValid()) 
            {
                 var errorTexts = form.getErrorTexts();
                 for (var i in errorTexts) 
                 {
                     mini.alert(errorTexts[i]);
                     return;
                 }
            }
            var me = this;
            var obj = this.billForm.getData(true);
            obj.billType=this.pbillType;
            obj.orderId=this.orderId;
            $.ajax({
               url : me.basePath + "/bill/" + me.action + "?a="+Math.random(),
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
	PageBillAdd.init();
});