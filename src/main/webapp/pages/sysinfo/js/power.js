
var PagePower = function(){
    return {
        defaultOption: {
            basePath:"",
            powerGrid : null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.powerGrid = mini.get("powerGrid");
            this.funSearch();
        },
        funSearch : function()
        {
        	var powerForm = new mini.Form("powerForm");
        	this.powerGrid.load(powerForm.getData());
        },
        funReset : function()
        {
        	var powerForm = new mini.Form("powerForm");
        	powerForm.setData();
            this.powerGrid.load();

        },
        funAdd : function()
        {  var row=this.powerGrid.getSelected();
            if(row) {
                var paramData = {action: "add", row: {"pid":row.id,"powerName":row.name1}, title: "新增数据"};
                this.funOpenInfo(paramData);
            }else{
                PageMain.funShowMessageBox("请先选择您所增加权限的父权限");
        }
        },
        funModify : function()
        {
        	var row = this.powerGrid.getSelected();
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
                url: this.basePath + "/pages/sysinfo/power_add.jsp",
                title: paramData.title,
                width: 400,
                height: 30 *  7 + 65,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PagePowerAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                	me.powerGrid.reload();
                }
            })
        },
        funDelete : function()
        {
            var row = this.powerGrid.getSelected();
            var me = this;
            if(row)
            {
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok") 
                    {
                        $.ajax({
                            url : me.basePath + "/power/del",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data)
                            {
                            	mini.alert(data.msg, "提醒", function(){
                            		if(data.success)
                                    {
                            			 me.powerGrid.reload();
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
	PagePower.init();
});