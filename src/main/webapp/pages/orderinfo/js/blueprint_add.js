
var PageBlueprintAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            blueprintForm : null,
            grid : null,

        },
        init :function (){
            mini.parse();
            this.basePath = PageMain.basePath;
            this.grid = mini.get("datagrid1");
            this.blueprintForm = new mini.Form("blueprintFormAdd");
        },
        funSetData : function(data){
            var row = data.row;
            PageMain.funDictInfo("unit", true, "rmb", "danwei");
            this.action = data.action;
            this.blueprintForm.setData(row);
            if(this.action == "modify") {
                var data = new Object();
                data.blueprint_id = row.id;
                this.grid.load(data);
            }else if(this.action == "view"){
                var data = new Object();
                data.blueprint_id = row.id;
                this.grid.load(data);
                PageBlueprintAdd.labelModel();
                this.grid.setEnabled(false);
                $("#toolbar").hide();
                $(".mini-toolbar").css("display", "none");
            }
        },
        funSave : function() {
            this.blueprintForm.validate();
            if (!this.blueprintForm.isValid()) {
                var errorTexts = this.blueprintForm.getErrorTexts();
                for (var i in errorTexts) {
                    mini.alert(errorTexts[i]);
                    return;
                }
            }

            var me = this;
            var obj = this.blueprintForm.getData(true);
            var data = this.grid.getChanges();
            var json = mini.encode(data);
            obj.data = json;
            $.ajax({
                url : me.basePath + "/blueprint/" + me.action + "?a="+Math.random(),
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
        funCancel : function()
        {
            PageMain.funCloseWindow("cancel");
        },

        onSimilarValidation : function(e) {
            var obj={
                materialNum:e.value,
            }
            $.ajax({
                url : PageMain.basePath+"/blueprint/query",
                type : 'POST',
                data : obj,
                dataType: 'json',
                async: false,
                success: function (data) {
                    if(data.total==0){
                        e.isValid = true;
                    }else if(data.total>0){
                        e.isValid = false;
                        e.errorText = "客户料号不能为空，且不能重复";
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

        //设置只读模式
        labelModel : function() {
            var fields = this.blueprintForm.getFields();
            for (var i = 0; l = fields.length, i < l; i++) {
                var c = fields[i];
                if (c.setReadOnly) c.setReadOnly(true);
                if (c.setIsValid) c.setIsValid(true);
            }
        }
    }
}();

$(function(){
    PageBlueprintAdd.init();
});