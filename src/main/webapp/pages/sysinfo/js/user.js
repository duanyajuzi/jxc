
var PageUser = function(){
    return {
        defaultOption: {
            basePath:"",
            userGrid : null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.userGrid = mini.get("userGrid");
            this.funSearch();
        },
        funSearch : function()
        {
        	var userForm = new mini.Form("userForm");
        	this.userGrid.load(userForm.getData());
        },
        funReset : function()
        {
        	var userForm = new mini.Form("userForm");
        	userForm.setData();
            this.userGrid.load();

        },
        funAdd : function()
        {
        	var paramData = {action: "add", row:{}, title:"新增数据"};
            this.funOpenInfo(paramData);
        },
        funModify : function()
        {
        	var row = this.userGrid.getSelected();
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
                url: this.basePath + "/pages/sysinfo/user_add.jsp",
                title: paramData.title,
                width: 450,
                height: 400,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageUserAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                	me.userGrid.reload();
                }
            })
        },
        funDelete : function()
        {
            var row = this.userGrid.getSelected();
            var me = this;
            if(row)
            {
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok") 
                    {
                        $.ajax({
                            url : me.basePath + "/user/del",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data)
                            {
                            	mini.alert(data.msg, "提醒", function(){
                            		if(data.success)
                                    {
                            			 me.userGrid.reload();
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
	PageUser.init();
});