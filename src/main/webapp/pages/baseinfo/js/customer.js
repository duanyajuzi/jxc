
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
            this.customerGrid.on("drawcell", function (e) {
                var record = e.record;
                column = e.column;
                field = e.field;
                if (column.field == "show_plan") {
                    e.cellHtml = "<a href='javascript:void(0);' onclick='PageCustomer.chaKan(\"" + record.id + "\")'>查看</a>";
                }
            });
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
                width: 900,
                height: 380,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageCustomerAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                    if(action=="save"){
                        PageCustomer.showSuccess();
                        me.customerGrid.reload();
                    }
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
        },
        chaKan : function () {
            var row = this.customerGrid.getSelected();
            var paramData = {action: "view", row: row, title:"客户详情"};
            var me = this;
            mini.open({
                url: this.basePath + "/pages/baseinfo/customer_view.jsp",
                title: paramData.title,
                width: 900,
                height: 360,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageCustomerAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                    me.customerGrid.reload();
                }
            })
        },

        //操作成功提示
        showSuccess : function() {
            mini.showMessageBox({
                showModal: false,
                width: 250,
                title: '提示',
                iconCls: 'mini-messagebox-success',
                message: '操作成功',
                timeout: 1000
            });
        }
    }
}();

$(function(){
	PageCustomer.init();
});