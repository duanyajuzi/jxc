
var PageGoods = function(){
    return {
        defaultOption: {
            basePath:"",
            goodsGrid : null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.goodsGrid = mini.get("goodsGrid");
            this.funSearch();
        },
        funSearch : function()
        {
        	var goodsForm = new mini.Form("goodsForm");
            var data=goodsForm.getData();
            this.goodsGrid.load(data);
        },
        funReset : function()
        {
        	var goodsForm = new mini.Form("goodsForm");
        	goodsForm.setData();
            this.goodsGrid.load();

        },
        funAdd : function()
        {
        	var paramData = {action: "add", row:{}, title:"新增数据"};
            this.funOpenInfo(paramData);
        },
        funModify : function()
        {
        	var row = this.goodsGrid.getSelected();
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
                url: this.basePath + "/pages/orderinfo/goods_add.jsp",
                title: paramData.title,
                width: 400,
                height: 220,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageGoodsAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                	me.goodsGrid.reload();
                }
            })
        },
        funDelete : function()
        {
            var row = this.goodsGrid.getSelected();
            var me = this;
            if(row)
            {
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok") 
                    {
                        $.ajax({
                            url : me.basePath + "/goods/del",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data)
                            {
                            	mini.alert(data.msg, "提醒", function(){
                            		if(data.success)
                                    {
                            			 me.goodsGrid.reload();
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
	PageGoods.init();
});