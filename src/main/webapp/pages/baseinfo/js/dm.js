
var PageDm = function(){
    return {
        defaultOption: {
            basePath:"",
            dmGrid : null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.dmGrid = mini.get("dmGrid");
            this.funSearch();
        },
        funSearch : function()
        {
        	var dmForm = new mini.Form("dmForm");
        	this.dmGrid.load(dmForm.getData());
        },
        funReset : function()
        {
        	var dmForm = new mini.Form("dmForm");
        	dmForm.setData();
            this.dmGrid.load();

        },
        funAdd : function()
        {
        	var paramData = {action: "add", row:{}, title:"新增数据"};
            this.funOpenInfo(paramData);
        },
        funModify : function()
        {
        	var row = this.dmGrid.getSelected();
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
                url: this.basePath + "/pages/baseinfo/dm_add.jsp",
                title: paramData.title,
                width: 400,
                height: 30 *  2 + 95,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageDmAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                	me.dmGrid.reload();
                }
            })
        },
        funDelete : function()
        {
            var row = this.dmGrid.getSelected();
            var me = this;
            if(row)
            {
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok") 
                    {
                        $.ajax({
                            url : me.basePath + "/dm/del",
                            type: 'POST',
                            data: {"bm": row.bm},
                            dataType: 'json',
                            success: function (data)
                            {
                            	mini.alert(data.msg, "提醒", function(){
                            		if(data.success)
                                    {
                            			 me.dmGrid.reload();
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
	PageDm.init();
});