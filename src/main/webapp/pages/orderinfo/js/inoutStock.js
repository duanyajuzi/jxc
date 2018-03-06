
var PageInoutStock=function () {
    return {
        defaultOption: {
            basePath: "",
            inoutGrid:null,
            orderType:null,
            inoutItemGrid:null
        },
        init: function () {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.inoutGrid=mini.get("inoutGrid");
            this.inoutItemGrid=mini.get("inoutItemGrid");
            PageInoutStock.defaultOption.orderType=PageInoutStock.getUrlParam("orderType");
        },
        getUrlParam:function(name){
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return decodeURIComponent(r[2]); return null;
        },
        funSearch: function () {
            var stimeBegin=mini.get("stimeBegin");
            var stimeEnd=mini.get("stimeEnd");
            var paramData={"stimeBegin":stimeBegin.text,"stimeEnd":stimeEnd.text};
            this.inoutGrid.load(paramData, function(res){
                console.log(res);
            });
        },
        funReset:function () {
            var inoutStockForm=new mini.Form("inoutStockForm");
            inoutStockForm.setData();
            this.inoutGrid.load();
        },
        funAdd : function()
        {
            var paramData = {action: "insertTabInoutStock",row:{orderType:PageInoutStock.defaultOption.orderType}, title:"新增数据"};
            this.funOpenInfo(paramData);
        },
        funModify : function()
        {
            var row = this.inoutGrid.getSelected();
            if(row)
            {
                row.orderType=PageInoutStock.defaultOption.orderType;
                var paramData = {action: "updateTabInoutStock", row: row, title:"编辑数据"};
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
                url: this.basePath + "/pages/orderinfo/inoutStock_add.jsp",
                title: paramData.title,
                width: 320,
                height: 120,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageInoutStockAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                    me.inoutGrid.reload();
                }
            })
        },
        funDelete:function () {
            var row = this.inoutGrid.getSelected();
            var me = this;
            if(row)
            {
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok")
                    {
                        $.ajax({
                            url : me.basePath + "/orderItem/deleteTabInoutStock",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data)
                            {
                                mini.alert(data.msg, "提醒", function(){
                                    if(data.success)
                                    {
                                        me.inoutGrid.reload();
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
        //商品入库数量
        funSetInoutStockNum: function () {
            var data={orderType:PageInoutStock.defaultOption.orderType}
           mini.open({
               url:this.basePath+"/pages/orderinfo/setInoutStock.jsp",
               height:600,
               width:1000,
               onload:function () {
                   var iframe=this.getIFrameEl();
                   iframe.contentWindow.PageSetInoutStock.funGetData(data);
               },
               ondestroy:function () {
                   mini.get("inoutGrid").load();
               }
           });
        }
    }
} ();

$(function(){
    PageInoutStock.init();
});
