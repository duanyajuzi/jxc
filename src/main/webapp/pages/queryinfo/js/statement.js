
var PageStatement=function () {
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
            PageStatement.drawcell();
        },

        exportExcel : function () {
            var customerId = mini.get("customerId").getValue();
            var pname = mini.get("customerId").getText();
            var stimeBegin = mini.get("stimeBegin").getFormValue();
            var stimeEnd = mini.get("stimeEnd").getFormValue();
            window.location.href = this.basePath + "/orderItem/exportStatement?customerId="+customerId + "&pname="+pname+"&stimeBegin="+stimeBegin + "&stimeEnd="+stimeEnd;

        },

        drawcell : function(){
            this.inoutItemGrid.on("drawcell", function (e) {
                var record = e.record;
                column = e.column;
                if (column.field == "goodNum") {
                    e.cellHtml = record.goodNum.toLocaleString();
                }
                if (column.field == "inprice") {
                    e.cellHtml = record.inprice.toFixed(4).toLocaleString();
                }
                if (column.field == "outprice") {
                    e.cellHtml = record.outprice.toFixed(4).toLocaleString();
                }
            });
        },

        funSearch: function () {
            var customerId=mini.get("customerId").getValue().trim();
            var stimeBegin=mini.get("stimeBegin");
            var stimeEnd=mini.get("stimeEnd");
            var paramData={"customerId":customerId,"stimeBegin":stimeBegin.text,"stimeEnd":stimeEnd.text};
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
        onDrawSummaryCell : function (e) {
            var result = e.result;
            var grid = e.sender;
            //客户端汇总计算
            if (e.field == "intotalMoney") {
                $("#inzj").text(e.value.toFixed(2))
                $("#inhszj").text((e.value * 1.17).toFixed(2))
            }
            if (e.field == "outtotalMoney") {
                $("#outzj").text(e.value.toFixed(2))
                $("#outhszj").text((e.value * 1.17).toFixed(2))
            }
        }
    }
} ();

$(function(){
    PageStatement.init();
});
