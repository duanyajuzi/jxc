
var PageBlueprintAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            blueprintForm : null,
            grid : null,
            priceTrue : false,

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
            var t = mini.get("checkbox");
            if(row.isHasLadder=="1"){
                t.setChecked(true);
                $("#toolbar").css("display", "block");
                $("#datagrid1").css("display", "block");
            }else{
                t.setChecked(false);
                $("#toolbar").css("display", "none");
                $("#datagrid1").css("display", "none");
            }
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
            if( PageBlueprintAdd.defaultOption.priceTrue==false){
                mini.alert("请输入正确的价格");
                return;
            }
            var me = this;
            var obj = this.blueprintForm.getData(true);
            var data = this.grid.getChanges();
            var json = mini.encode(data);
            var t = mini.get("checkbox");
            if(t.checked){
                obj.isHasLadder=1;
            }else{
                obj.isHasLadder=0;
            }
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
            if(e.value==""){
                mini.alert("原厂料号不能为空");
            }else {
                var obj={
                    fmaterialNum:e.value
                };
                var fid=mini.get("id").getValue();
                if(fid){
                    obj.fid=fid;
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
                            mini.alert("客户料号不能重复");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                    }
                });
            }
        },

        addRow : function() {
            var newRow = { name: "New Row" };
            var index = $(".mini-grid-row").length;
            this.grid.addRow(newRow, index);
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
        },


        onValueChanged : function(e) {
            var checked = this.getChecked();
            if(checked){
                $("#toolbar").css("display", "block");
                $("#datagrid1").css("display", "block");
            }else {
                $("#toolbar").css("display", "none");
                $("#datagrid1").css("display", "none");
            }
        },
        onPriceValidation : function(e) {
            if (e.isValid) {
                if (PageBlueprintAdd.isPrice(e.value) == false) {
                    PageBlueprintAdd.defaultOption.priceTrue=false;
                    e.isValid = false;
                    e.errorText = "请输入正确的价格";
                    mini.alert("请输入正确的价格");
                }else if(PageBlueprintAdd.isPrice(e.value) == true){
                    PageBlueprintAdd.defaultOption.priceTrue=true;
                }
            }
        },
        isPrice : function(v) {
            var re = new RegExp("^([1-9][\\d]{0,7}|0)(\\.[\\d]{1,2})?$");
            if (re.test(v)) return true;
            return false;
        },
    }
}();

$(function(){
    PageBlueprintAdd.init();
});