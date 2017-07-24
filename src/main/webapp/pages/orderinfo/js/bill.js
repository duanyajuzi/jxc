
var PageBill = function(){
    return {
        defaultOption: {
            basePath:"",
            billGrid : null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.billGrid = mini.get("billGrid");
            this.funSearch();
        },
        funSearch : function()
        {
        	var billForm = new mini.Form("billForm");
        	this.billGrid.load(billForm.getData());
        },
        funReset : function()
        {
        	var billForm = new mini.Form("billForm");
        	billForm.setData();
            this.billGrid.load();

        },
        funAdd : function()
        {
        	var paramData = {action: "add", row:{}, title:"新增数据"};
            this.funOpenInfo(paramData);
        },
        funModify : function()
        {
        	var row = this.billGrid.getSelected();
            if(row)
            {
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
                url: this.basePath + "/pages/orderinfo/bill_add.jsp",
                title: paramData.title,
                width: 400,
                height: 30 *  12 + 65,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageBillAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                	me.billGrid.reload();
                }
            })
        },
        funDelete : function()
        {
            var row = this.billGrid.getSelected();
            var me = this;
            if(row)
            {
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok") 
                    {
                        $.ajax({
                            url : me.basePath + "/bill/del",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data)
                            {
                            	mini.alert(data.msg, "提醒", function(){
                            		if(data.success)
                                    {
                            			 me.billGrid.reload();
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
	PageBill.init();
});