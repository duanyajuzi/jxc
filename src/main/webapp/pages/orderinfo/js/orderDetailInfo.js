/**
 * Created by admin on 2017-07-20.
 */
var PageOrderDetailInfo=function () {
    return {
        defaultOption: {
            basePath:""
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.funSearch();
        },
        funSearch : function(row)
        {
            var orderDetailForm=new mini.Form("orderDetailForm");
            var orderDetailGrid=mini.get("orderDetailGrid");
            var orderStatus=mini.get("orderStatus");
            if(row) {
                $.ajax({
                    url: this.basePath+"/order/query",
                    data: {id: row.id,orderType:row.orderType},
                    type: "get",
                    dataType: "json",
                    success: function (text) {
                        var o = mini.decode(text.data[0]);
                        o.orderStatus=PageOrderDetailInfo.onStatusChanged(o.orderStatus);
                        orderDetailForm.setData(o);
                        orderDetailForm.setEnabled(false);
                    },
                    error: function () {
                    }
                });
                orderDetailGrid.load({orderId: row.id});
            }
        },
        onStatusChanged:function (e) {
            var Genders = [{ id: -1, text: '订单已删除' },
                { id: 0, text: '订单申请'},
                { id: 1, text: '订单下发'},
                { id: 2, text: '订单出库'},
                { id: 3, text: '订单入库'},
                { id: 4, text: '订单开票'},
                { id: 5, text: '订单完成'}];
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e) return g.text;
             }
            return "";
        }
    }
}();
$(function(){
    PageOrderDetailInfo.init();
});