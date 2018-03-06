
var PageGoodCustomerAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            goodCustomerForm : null,
            grid: null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.goodCustomerForm = new mini.Form("goodCustomerFormAdd");
            this.grid = mini.get("datagrid1");
            PageMain.funDictInfo("unit", true, "rmb", "danwei");

        },
        funBusValuechanged : function(e)
        {
            PageMain.funLoadGoodsByBussinessId("goodId", false, "", e.value)
        },



        funSetData : function(data) {
        	var row = data.row;
        	this.action = data.action;
           /* var storage=mini.get("storage");*/
            this.goodCustomerForm.setData(row);
            if(this.action == "modify") {
                var data = new Object();
                data.good_customer_id = row.id;
                this.grid.load(data);
                //PageMain.funLoadGoodsByBussinessId("goodId", false, "", row.businessId);
                //storage.disable();
            }else if(this.action == "view"){
                var data = new Object();
                data.good_customer_id = row.id;
                this.grid.load(data);
                PageGoodCustomerAdd.labelModel();
                this.grid.setEnabled(false);
                $("#toolbar").hide();
                $(".mini-toolbar").css("display", "none");

            }
        },
        funSave : function() {
        	this.goodCustomerForm.validate();
            if (!this.goodCustomerForm.isValid()) {
                 var errorTexts = form.getErrorTexts();
                 for (var i in errorTexts) {
                     mini.alert(errorTexts[i]);
                     return;
                 }
            }

            var me = this;
            var obj = this.goodCustomerForm.getData(true);
            var data = this.grid.getChanges();
            var json = mini.encode(data);
            obj.data = json;
            $.ajax({
               url : me.basePath + "/goodCustomer/" + me.action + "?a="+Math.random(),
               type : 'POST',
               data : obj,
               dataType: 'json',
               success: function (data) {
            	   mini.alert(data.msg, "提醒", function(){
	               		if(data.success) {
	               			PageMain.funCloseWindow("save");
	                    }
                   });
               },
               error: function (jqXHR, textStatus, errorThrown) {
            	   PageMain.funShowMessageBox("操作出现异常");
               }
           });
        },
        funCancel : function() {
        	PageMain.funCloseWindow("cancel");
        },

        onSimilarValidation : function(e) {
            var obj={
                materialNum:e.value
            };
            $.ajax({
                url : PageMain.basePath+"/goodCustomer/query",
                type : 'POST',
                data : obj,
                dataType: 'json',
                async: false,
                success: function (data) {
                    if(data.total==0){
                        e.isValid = true;
                    }else if(data.total>0){
                        e.isValid = false;
                        e.errorText = "原厂料号不能为空，且不能重复";
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }
            });
        },



        addRow : function() {
            var newRow = { name: "New Row" };
            this.grid.addRow(newRow, 0);

            this.grid.beginEditCell(newRow, "LoginName");
        },
        removeRow : function() {
            var rows = this.grid.getSelecteds();
            if (rows.length > 0) {
                this.grid.removeRows(rows, true);
            }
        },
        saveData : function() {
            var me = this;
            var data = this.grid.getChanges();
            obj.data = mini.encode(data);

            this.grid.loading("保存中，请稍后......");
            $.ajax({
                url : me.basePath + "/goodCustomer/add" ,
                data: obj,
                type: "post",
                dataType: 'json',
                success: function (text) {
                    //this.grid.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
        },
        //设置只读模式
        labelModel : function() {
            var fields = this.goodCustomerForm.getFields();
            for (var i = 0; l = fields.length, i < l; i++) {
                var c = fields[i];
                if (c.setReadOnly) c.setReadOnly(true);
                if (c.setIsValid) c.setIsValid(true);
            }
        }

    }
}();

$(function(){
	PageGoodCustomerAdd.init();
});