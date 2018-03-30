
var PagePricelist=function () {
    return {
        defaultOption: {
            basePath: "",
            inoutItemGrid:null,
            orderType:null
        },
        init: function () {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.inoutItemGrid=mini.get("inoutItemGrid");
            // this.inoutItemGrid.load();
            PagePricelist.drawcell();
        },
        drawcell : function(){
            this.inoutItemGrid.on("drawcell", function (e) {
                var record = e.record;
                column = e.column;
                if (column.field == "price") {
                    e.cellHtml = record.price.toFixed(4).toLocaleString();
                }
                if (column.field == "unitPrice") {
                    e.cellHtml = record.totalMoney.toFixed(4).toLocaleString();
                }
            });
        },

        funSearch: function () {
            var customerId=mini.get("customerId").getValue().trim();
            var paramData={"customerId":customerId};
            if (customerId != ""){
                this.inoutItemGrid.load(paramData, function(res){
                    console.log(res);
                });
            }else{
                mini.alert("请选择客户");
            }

        },
        funReset:function () {
            var inoutStockForm=new mini.Form("inoutStockForm");
            inoutStockForm.setData();
            this.inoutItemGrid.load();
        },
    }
} ();

$(function(){
    PagePricelist.init();
});
