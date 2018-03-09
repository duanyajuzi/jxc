
var PageInoutStock=function () {
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
            PageInoutStock.defaultOption.orderType=PageInoutStock.getUrlParam("orderType");
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
            var stimeBegin=mini.get("stimeBegin");
            var stimeEnd=mini.get("stimeEnd");
            var paramData={"businessId":businessId,"orderNo":orderNo,"orderName":orderName,"materialNum":materialNum,"stimeBegin":stimeBegin.text,"stimeEnd":stimeEnd.text};
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
                row.ycmaterialNum=row.materialNum;
                row.orderType=PageInoutStock.defaultOption.orderType;
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
        //商品入库数量
        funSetInoutStockNum: function () {
            var data={orderType:PageInoutStock.defaultOption.orderType}
           mini.open({
               url:this.basePath+"/pages/orderinfo/setInoutStock.jsp",
               height:600,
               width:1100,
               onload:function () {
                   var iframe=this.getIFrameEl();
                   iframe.contentWindow.PageSetInoutStock.funGetData(data);
               },
               ondestroy:function () {
                   mini.get("inoutItemGrid").load();
               }
           });
        }
    }
} ();

$(function(){
    PageInoutStock.init();
});
