
var PageDict = function(){
    return {
        defaultOption: {
            basePath:"",
            dictGrid : null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.dictGrid = mini.get("dictGrid");
            this.funSearch();
        },
        funSearch : function()
        {
        	var dictForm = new mini.Form("dictForm");
            var nr=mini.get("nr");
            var i=dictForm.getData();
            i.nr=nr.text;
        	this.dictGrid.load(i);
        },
        funReset : function()
        {
        	var dictForm = new mini.Form("dictForm");
        	dictForm.setData();
            this.dictGrid.load();

        },
        funAdd : function()
        {
        	var paramData = {action: "add", row:{}, title:"新增数据"};
            this.funOpenInfo(paramData);
        },
        funModify : function()
        {
        	var row = this.dictGrid.getSelected();
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
                url: this.basePath + "/pages/baseinfo/dict_add.jsp",
                title: paramData.title,
                width: 400,
                height: 30 *  5 + 65,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageDictAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                	me.dictGrid.reload();
                }
            })
        },
        funDelete : function()
        {
            var row = this.dictGrid.getSelected();
            var me = this;
            if(row)
            {
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok") 
                    {
                        $.ajax({
                            url : me.basePath + "/dict/del",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data)
                            {
                            	mini.alert(data.msg, "提醒", function(){
                            		if(data.success)
                                    {
                            			 me.dictGrid.reload();
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
	PageDict.init();
});