
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
        	var businessId= mini.get("businessId").getValue().trim();
        	var orderNo = mini.get("orderNo").getValue().trim();
        	var orderName=mini.get("orderName").getValue().trim();
        	var materialNum=mini.get("materialNum").getValue().trim();
            var orderTimeBegin=mini.get("orderTimeBegin");
            var orderTimeEnd=mini.get("orderTimeEnd");
            var paramData={"businessId":businessId,"orderNo":orderNo,"orderName":orderName,"materialNum":materialNum,
                "orderTimeBegin":orderTimeBegin.text,"orderTimeEnd":orderTimeEnd.text};
        	this.orderGrid.load(paramData, function(res){
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
                // row.orderType=this.type;
            	var paramData = {action: "modify", row: row, title:"编辑数据"};
                // if(row.orderStatus==0) {
                    this.funOpenInfo(paramData);
                // }else {
                //     PageMain.funShowMessageBox("订单状态已改变，不可更改");
                // }
            }
            else
            {
            	PageMain.funShowMessageBox("请选择一条记录");
            }
        },
        funOpenInfo : function(paramData)
        {
        	var me = this;
        	var url;
        	if(paramData.row.orderType == 0){
        	    url = this.basePath + "/pages/orderinfo/buy_order_add.jsp";
            }else{
                url = this.basePath + "/pages/orderinfo/order_add.jsp";
            }

        	mini.open({
                url: url,
                title: paramData.title,
                width: 750,
                height: 550 ,
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
                if(row.orderStatus==0) {
                    mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                        if (action == "ok") {
                            $.ajax({
                                url: me.basePath + "/order/del",
                                type: 'POST',
                                data: {"id": row.id},
                                dataType: 'json',
                                success: function (data) {
                                    mini.alert(data.msg, "提醒", function () {
                                        if (data.success) {
                                            me.orderGrid.reload();
                                        }
                                    });
                                },
                                error: function () {
                                    mini.alert("删除记录失败");
                                }
                            });
                        }
                    });
                }else {
                    mini.alert("订单状态已改变，不可删除");
                }
            }
            else
            {
                mini.alert("请先选择要删除的记录");
            }
        },
        //设置订单-商品详情
        funSetOrder:function () {
            var row = this.orderGrid.getSelected();
            if(row){
                var paramData = {orderId: row.id,businessId:row.businessId,orderType:row.orderType};
                if(row.orderStatus==0) {
                    mini.open({
                        url: this.basePath + "/pages/orderinfo/orderItem.jsp",
                        title: row.orderName,
                        width: 670,
                        height: 500,
                        onload: function () {
                            var iframe = this.getIFrameEl();
                            iframe.contentWindow.PageOrderItem.funSearch(paramData);
                            iframe.contentWindow.PageOrderItem.funGetData(paramData);
                        },
                        ondestroy: function (action) {
                        }
                    });
                }else {
                    mini.alert("订单状态已改变，不可设置订单内容");
                }
            }else{
                mini.alert("请先选择订单");
            }
        },
        //订单详情页
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
        //下发订单时更改订单状态
        funUpdateOrderStatus:function () {
            var row=this.orderGrid.getSelected();
            var orderGrid=mini.get("orderGrid");
            var me=this;
            mini.confirm("确定下发订单么?", "提醒", function (action) {
                if (action == "ok") {
                    $.ajax({
                        url: me.basePath + "/order/updateOrderStatus",
                        data: {"id": row.id},
                        type: "post",
                        dataType: "json",
                        success: function () {
                            orderGrid.load();
                        },
                        error: function () {
                            mini.alert("updateOrderStatus error");
                        }
                    });
                }
            });
        }
        //设置订单内容
        // funOpenInout:function () {
        //     var row = this.orderGrid.getSelected();
        //     if (row) {
        //         var data={orderId:row.id};
        //         mini.open({
        //             url: this.basePath + "/pages/orderinfo/inoutStock.jsp",
        //             width: 800,
        //             height: 500,
        //             onload: function () {
        //                 var iframe = this.getIFrameEl();
        //                 iframe.contentWindow.PageInoutStock.funSearch(data);
        //             },
        //             ondestroy: function () {
        //             }
        //         });
        //     }
        // }
    }
    
}();

$(function(){
	PageOrder.init();
});