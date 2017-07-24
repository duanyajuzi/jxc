
var PageBlueprint = function(){
    return {
        defaultOption: {
            basePath:"",
            blueprintGrid : null,
            goodsData:null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.blueprintGrid = mini.get("blueprintGrid");
            this.funSearch();
        },
        funGetData:function (data) {
            this.goodsData=data;
        },
        funSearch : function(data)
        {
            this.blueprintGrid.load(data);
        },
        funSearchInfo:function () {
            var blueprintForm = new mini.Form("blueprintForm");
            var planData=blueprintForm.getData();
            var totalData={"pname":planData.pname,"goodsId":this.goodsData.goodsId};
            this.blueprintGrid.load(totalData);
        },
        funReset : function()
        {
            var blueprintForm = new mini.Form("blueprintForm");
            blueprintForm.setData();
            this.blueprintGrid.load(this.goodsData);

        },
        funAdd : function()
        {
            var paramData = {action: "add", row:{goodsId:this.goodsData.goodsId}, title:"新增数据"};
            this.funOpenInfo(paramData);
        },
        funModify : function()
        {
            var row = this.blueprintGrid.getSelected();
            if(row)
            {
                row.goodsId=this.goodsData.goodsId;
                var paramData = {action: "modify", row: row, title:"编辑数据"};
                this.funOpenInfo(paramData);
            }
            else
            {
                PageMain.funShowMessageBox("请选择一条记录");
            }
        },
        funOpenInfo : function(paramData)
        {
            var me = this;
            mini.open({
                url: this.basePath + "/pages/orderinfo/blueprint_add.jsp",
                title: paramData.title,
                width: 400,
                height: 30 *  7 + 65,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageBlueprintAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                    me.blueprintGrid.reload();
                }
            })
        },
        funDelete : function()
        {
            var row = this.blueprintGrid.getSelected();
            var me = this;
            if(row)
            {
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok")
                    {
                        $.ajax({
                            url : me.basePath + "/blueprint/del",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data)
                            {
                                mini.alert(data.msg, "提醒", function(){
                                    if(data.success)
                                    {
                                        me.blueprintGrid.reload();
                                    }
                                });

                            },
                            error: function ()
                            {
                                mini.alert("删除记录失败");
                            }
                        });
                    }
                })
            }
            else
            {
                mini.alert("请先选择要删除的记录");
            }
        }
    }
}();
$(function(){
	PageBlueprint.init();
});