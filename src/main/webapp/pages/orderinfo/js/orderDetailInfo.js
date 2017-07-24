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
            if(row) {
                $.ajax({
                    url: this.basePath+"/order/query",
                    data: {id: row.id,orderType:row.orderType},
                    type: "post",
                    dataType: "json",
                    success: function (text) {
                        var o = mini.decode(text.data[0]);
                        orderDetailForm.setData(o);
                    },
                    error: function () {
                    }
                });
                orderDetailGrid.load({orderId: row.id});
            }
        }
    }
}();
$(function(){
    PageOrderDetailInfo.init();
});