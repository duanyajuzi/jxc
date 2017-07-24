
var PageBusiness = function(){
    return {
        defaultOption: {
            basePath:"",
            businessGrid : null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.businessGrid = mini.get("businessGrid");
            this.funSearch();
        },
        funSearch : function()
        {
        	var businessForm = new mini.Form("businessForm");
        	this.businessGrid.load(businessForm.getData());
        },
        funReset : function()
        {
        	var businessForm = new mini.Form("businessForm");
        	businessForm.setData();
            this.businessGrid.load();

        },
        funAdd : function()
        {
        	var paramData = {action: "add", row:{}, title:"新增数据"};
            this.funOpenInfo(paramData);
        },
        funModify : function()
        {
        	var row = this.businessGrid.getSelected();
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
                url: this.basePath + "/pages/baseinfo/business_add.jsp",
                title: paramData.title,
                width: 400,
                height: 30 *  3 + 80,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageBusinessAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                	me.businessGrid.reload();
                }
            })
        },
        funDelete : function()
        {
            var row = this.businessGrid.getSelected();
            var me = this;
            if(row)
            {
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok") 
                    {
                        $.ajax({
                            url : me.basePath + "/business/del",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data)
                            {
                            	mini.alert(data.msg, "提醒", function(){
                            		if(data.success)
                                    {
                            			 me.businessGrid.reload();
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
	PageBusiness.init();
});