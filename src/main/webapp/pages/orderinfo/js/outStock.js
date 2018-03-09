
var PageOutStock=function () {
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
            this.inoutItemGrid.load();
            PageOutStock.defaultOption.orderType=PageOutStock.getUrlParam("orderType");
        },
        getUrlParam:function(name){
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return decodeURIComponent(r[2]); return null;
        },
        funSearch: function () {
            var businessId= mini.get("businessId").getValue().trim();
            var orderNo = mini.get("orderNo").getValue().trim();
            var orderName=mini.get("orderName").getValue().trim();
            var materialNum=mini.get("materialNum").getValue().trim();
            var ycmaterialNum=mini.get("ycmaterialNum").getValue().trim();
            var stimeBegin=mini.get("stimeBegin");
            var stimeEnd=mini.get("stimeEnd");
            var paramData={"businessId":businessId,"orderNo":orderNo,"orderName":orderName,"materialNum":materialNum,
                "ycmaterialNum":ycmaterialNum,"stimeBegin":stimeBegin.text,"stimeEnd":stimeEnd.text};
            this.inoutItemGrid.load(paramData, function(res){
                console.log(res);
            });
        },
        funReset:function () {
            var inoutStockForm=new mini.Form("inoutStockForm");
            inoutStockForm.setData();
            this.inoutItemGrid.load();
        },
        funModify : function()
        {
            var row = this.inoutItemGrid.getSelected();
            if(row)
            {
                row.orderType=PageOutStock.defaultOption.orderType;
                var paramData = {action: "updateInoutStockItem", row: row, title:"修改价格"};
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
                url: this.basePath + "/pages/orderinfo/inoutStockItem_add.jsp",
                title: paramData.title,
                width: 400,
                height: 280,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageInoutStockItemAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                    me.inoutItemGrid.reload();
                }
            })
        },
        //商品出库数量
        funSetInoutStockNum: function () {
            var data={orderType:PageOutStock.defaultOption.orderType};
            mini.open({
                url:this.basePath+"/pages/orderinfo/setOutStock.jsp",
                height:600,
                width:1100,
                onload:function () {
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageSetOutStock.funGetData(data);
                },
                ondestroy:function () {
                    mini.get("inoutItemGrid").load();
                }
            });
        }
    }
} ();

$(function(){
    PageOutStock.init();
});
