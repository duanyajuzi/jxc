
var PageCustomer = function(){
    return {
        defaultOption: {
            basePath:"",
            customerGrid : null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.customerGrid = mini.get("customerGrid");
            this.funSearch();
        },
        funSearch : function()
        {
        	var customerForm = new mini.Form("customerForm");
        	this.customerGrid.load(customerForm.getData());
        },
        funReset : function()
        {
        	var customerForm = new mini.Form("customerForm");
        	customerForm.setData();
            this.customerGrid.load();

        },
        funAdd : function()
        {
        	var paramData = {action: "add", row:{}, title:"新增数据"};
            this.funOpenInfo(paramData);
        },
        funModify : function()
        {
        	var row = this.customerGrid.getSelected();
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
                url: this.basePath + "/pages/baseinfo/customer_add.jsp",
                title: paramData.title,
                width: 400,
                height: 30 *  13 + 80,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageCustomerAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                	me.customerGrid.reload();
                }
            })
        },
        funDelete : function()
        {
            var row = this.customerGrid.getSelected();
            var me = this;
            if(row)
            {
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok") 
                    {
                        $.ajax({
                            url : me.basePath + "/customer/del",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data)
                            {
                            	mini.alert(data.msg, "提醒", function(){
                            		if(data.success)
                                    {
                            			 me.customerGrid.reload();
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
	PageCustomer.init();
});