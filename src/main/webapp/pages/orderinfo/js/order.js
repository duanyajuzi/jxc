
var PageOrder = function(){
    return {
        defaultOption: {
            basePath:"",
            orderGrid : null,
            type:null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.orderGrid = mini.get("orderGrid");
            this.orderGrid.load();
            this.type=PageOrder.getUrlParam("orderType");
        },
       getUrlParam:function(name){
       var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
       var r = window.location.search.substr(1).match(reg);
       if (r != null) return decodeURIComponent(r[2]); return null;
         },
        funSearch : function()
        {
        	var orderForm = new mini.Form("orderForm");
            var data=orderForm.getData();
        	this.orderGrid.load(data, function(res){
                console.log(res);
            });
        },
        funReset : function()
        {
        	var orderForm = new mini.Form("orderForm");
        	orderForm.setData();
            this.orderGrid.load();
        },
        funAdd : function()
        {
        	var paramData = {action: "add", row:{orderType:this.type}, title:"新增数据"};
            this.funOpenInfo(paramData);
        },
        funModify : function()
        {
        	var row = this.orderGrid.getSelected();
            if(row)
            {
                row.orderType=this.type;
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
                url: this.basePath + "/pages/orderinfo/order_add.jsp",
                title: paramData.title,
                width: 650,
                height: 30 *  12 + 65,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageOrderAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                	me.orderGrid.reload();
                }
            })
        },
        funDelete : function()
        {
            var row = this.orderGrid.getSelected();
            var me = this;
            if(row)
            {
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok") 
                    {
                        $.ajax({
                            url : me.basePath + "/order/del",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data)
                            {
                            	mini.alert(data.msg, "提醒", function(){
                            		if(data.success)
                                    {
                            			 me.orderGrid.reload();
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
        //设置订单-商品详情
        funSetOrder:function () {
            var row = this.orderGrid.getSelected();
            if (row) {
                var paramData = {orderId: row.id};
                mini.open({
                    url: this.basePath + "/pages/orderinfo/orderItem.jsp",
                    title:row.orderName,
                    width: 670,
                    height: 30 * 12 + 65,
                    onload: function () {
                        var iframe=this.getIFrameEl();
                        iframe.contentWindow.PageOrderItem.funSearch(paramData);
                        iframe.contentWindow.PageOrderItem.funGetData(paramData);
                    },
                    ondestroy: function (action) {
                    }
                })
            }else{
                mini.alert("请先选择订单");
            }
        },
        funOpenOderDetailInfo:function (data) {
            var row = this.orderGrid.getSelected();
            mini.open({
                url: this.basePath + "/pages/orderinfo/orderDetailInfo.jsp",
                title: row.orderName + "订单详情",
                width: 670,
                height: 30 * 12 + 65,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    iframe.contentWindow.PageOrderDetailInfo.funSearch(row);
                },
                ondestroy: function (action) {
                }
            });
        },
        funOpenInout:function () {
            mini.open({
                url:this.basePath+"pages/orderinfo/inoutStock.jsp",
                width:400,
                height:400,
                onload:function () {
                    
                },
                ondestroy:function () {
                    
                }
            });
        }
    }
    
}();

$(function(){
	PageOrder.init();
});