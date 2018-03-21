
var PageOrderAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            orderForm : null,
            pOrderType:null,
            orderId:''

        },
        init :function ()
        {
            mini.parse();
            PageOrderAdd.defaultOption.basePath = PageMain.basePath;
            this.basePath = PageMain.basePath;
            this.orderForm = new mini.Form("orderFormAdd");
            this.orderItemGrid = mini.get("orderItemGrid");
        },
        funSetData : function(data)
        {
            var row = data.row;
            PageOrderAdd.defaultOption.pOrderType=row.orderType;
            this.action = data.action;
            row.zdsc == 1? row.zdsc=true : row.zdsc=false;
            row.iskh == 1? row.iskh=true : row.iskh=false;
            this.orderForm.setData(row);
            if(this.action=="add") {
                var orderNo = mini.get("orderNo");
                $.ajax({
                    url: this.basePath + "/order/queryOrderNo",
                    type: "post",
                    data: {},
                    dataType: "json",
                    success: function (result) {
                        var data = result.data;
                        orderNo.setValue(data[0]);
                    },
                    error: function () {
                    }
                });
            }else if(this.action == 'modify'){
                var data = new Object();
                data.orderId = row.id;
                this.orderItemGrid.load(data);
                PageOrderAdd.updateTotalPrice();
                PageOrderAdd.defaultOption.orderId = row.id;
                mini.get("zdsc").setEnabled(false);
            }else if(this.action == 'view'){
                var data = new Object();
                data.orderId = row.id;
                this.orderItemGrid.load(data);
                PageOrderAdd.updateTotalPrice();
                PageOrderAdd.defaultOption.orderId = row.id;
                this.orderForm.setEnabled(false);
                this.orderItemGrid.setEnabled(false);
                $(".mini-toolbar").hide()
            }
        },
        //选择厂商和采购公司时联动填充联系人、电话、地址
        onDeptChanged:function () {
            var customerId = mini.get("customerId");
            var ccontacts=mini.get("ccontacts");
            var caddress=mini.get("caddress");
            var ctel=mini.get("ctel");
            var pcustomerId = mini.get("pcustomerId");
            var pcontacts=mini.get("pcontacts");
            var paddress=mini.get("paddress");
            var ptel=mini.get("ptel");
            var id1 = customerId.getValue();
            var id2=pcustomerId.getValue();
            if(id1!="") {
                $.ajax({
                    url: this.basePath + "/customer/queryOtherInfoList",
                    type: "post",
                    data: {id: id1},
                    dataType: "json",
                    success: function (result) {
                        ccontacts.setValue(result[0].contacts);
                        caddress.setValue(result[0].address);
                        ctel.setValue(result[0].tel);
                    },
                    error: function () {
                        mini.alert("id1 error");
                    }
                });
            }
            if(id2!="") {
                $.ajax({
                    url: this.basePath + "/customer/queryOtherInfoList",
                    type: "post",
                    data: {id: id2},
                    dataType: "json",
                    success: function (result) {
                        pcontacts.setValue(result[0].contacts);
                        paddress.setValue(result[0].address);
                        ptel.setValue(result[0].tel);
                    },
                    error: function () {
                        mini.alert("id2 error");
                    }
                });
            }
        },
        funSave : function()
        {
        	this.orderForm.validate();
            if (!this.orderForm.isValid()) 
            {
                 return;
            }
            var me = this;
            var orderinfo = this.orderItemGrid.getChanges();
            var json = mini.encode(orderinfo);
            var obj = this.orderForm.getData(true);
            obj.data = json;
            obj.orderType=PageOrderAdd.defaultOption.pOrderType;
            obj.iskh = obj.iskh == 'true' ? 1 : 0;
            obj.zdsc = obj.zdsc == 'true' ? 1 : 0;
            var messageBox = mini.loading("保存中......","提示");
            $.ajax({
               url : me.basePath + "/order/" + me.action + "?a="+Math.random(),
               type : 'POST',
               data : obj,
               dataType: 'json',
               success: function (data) 
               {
                   mini.hideMessageBox(messageBox);
                   mini.alert(data.msg, "提醒", function(){
                       if(data.success)
                       {
                           PageMain.funCloseWindow("save");
                       }
                   });

               },
               error: function (jqXHR, textStatus, errorThrown) 
               {
            	   PageMain.funShowMessageBox("操作出现异常");
               }
           });
        },

        funCancel : function()
        {
        	PageMain.funCloseWindow("cancel");
        },

        addRow : function () {
            var customerId = mini.get("customerId").getValue();
            var businessId = mini.get("businessId").getValue();
            var pcustomerId = mini.get("pcustomerId").getValue();
            if(customerId && businessId && pcustomerId){
                var newRow = { name: "New Row" };
                var index = $(".mini-grid-row").length;
                this.orderItemGrid.addRow(newRow, index);
                this.orderItemGrid.beginEditCell(newRow, "LoginName");
            }else{
                mini.alert("请先选择业务类型、工厂和客户")
            }

        },
        removeRow : function () {
            var rows = this.orderItemGrid.getSelecteds();
            if (rows.length > 0) {
                this.orderItemGrid.removeRows(rows, true);
            }
            var index = $(".mini-grid-row").length;
            if(index == 0){
                mini.get("customerId").setEnabled(true);
                mini.get("businessId").setEnabled(true);
                mini.get("pcustomerId").setEnabled(true);
            }
            PageOrderAdd.updateTotalPrice();
        },

        OnCellBeginEdit : function (e) {
            var record = e.record, field = e.field;

            if (field == "dictName" || field == "goodsName"  || field == "totalMoney" ) {
                e.cancel = true;
            }

            var editor = e.editor;
            if (field == "materialNum") {
                var customerId = mini.get("customerId").getValue();
                var businessId = mini.get("businessId").getValue();
                var pcustomerId = mini.get("pcustomerId").getValue();
                if (customerId && businessId && pcustomerId) {
                    var url;
                    if(PageOrderAdd.defaultOption.pOrderType == 0){
                        url = PageOrderAdd.defaultOption.basePath + "/order/queryMaterialNum2?customerId=" + customerId+"&businessId="+businessId;
                    }else{
                        url = PageOrderAdd.defaultOption.basePath + "/order/queryMaterialNum?customerId=" + customerId+"&businessId="+businessId +"&pcustomerId="+pcustomerId;
                    }

                    editor.setUrl(url);
                } else {
                    e.cancel = true;
                }
            }

        },

        //根据物料号设置price
        onMaterialNumChanged:function (e) {
            var grid = e.sender;
            var record = e.record;
            var field = e.field
            var value = e.value;
            if (field == "materialNum") {
                if(value!="") {

                    //禁用工厂
                    mini.get("customerId").setEnabled(false);
                    mini.get("businessId").setEnabled(false);
                    mini.get("pcustomerId").setEnabled(false);

                    var url;
                    if(PageOrderAdd.defaultOption.pOrderType == 0){//采购
                        url =  PageOrderAdd.defaultOption.basePath + "/orderItem/queryNumInfoList2";
                    }else{//销售
                        url =  PageOrderAdd.defaultOption.basePath + "/orderItem/queryNumInfoList";
                    }
                    $.ajax({
                        url:url,
                        type: "post",
                        data: {blueprintId:value},
                        dataType: "json",
                        success: function (result) {
                            var obj = new Object();
                            obj.materialNum = result[0].materialNum;
                            obj.goodsName = result[0].goodsName;
                            obj.price = result[0].price;
                            obj.oneprice = result[0].price;
                            obj.dictName = result[0].dictName;
                            obj.isHasLadder = result[0].isHasLadder;
                            obj.customerGoodId = result[0].blueprintId;
                            obj.goodsId = result[0].goodsId;
                            if(record.esgouNum > 0){
                                obj.totalMoney = (obj.price * record.esgouNum).toFixed(2)
                            }
                            grid.updateRow(record, obj);
                            PageOrderAdd.updateTotalPrice();
                        },
                        error: function () {
                            mini.alert("queryNumInfoList error");
                        }
                    });
                }
            }else if(field == "esgouNum"){
                if(record.price > 0){
                    if(record.isHasLadder == '1'){
                        //阶梯价格处理
                        var url;
                        if(PageOrderAdd.defaultOption.pOrderType == 0){//采购
                            url =  PageOrderAdd.defaultOption.basePath + "/order/getLadderPrice";
                        }else{//销售
                            url =  PageOrderAdd.defaultOption.basePath + "/order/getBluePrintLadderPrice";
                        }

                        //获取阶梯价格
                        $.ajax({
                            url : url,
                            type : 'GET',
                            data : {customerGoodId:record.customerGoodId,num:value},
                            dataType: 'json',
                            success: function (data)
                            {
                                var priceArr = data.data;
                                if(priceArr.length > 0){
                                    var price = priceArr[0].price;
                                    record.totalMoney = (value * price).toFixed(2);
                                    record.price = price;
                                    grid.updateRow(record, record);
                                }else{
                                    record.price = record.oneprice;
                                    record.totalMoney = (value * record.price).toFixed(2);
                                    grid.updateRow(record, record);
                                }
                            },
                        });

                    }else{
                        record.totalMoney = (value * record.price).toFixed(2);
                        grid.updateRow(record, record);
                    }
                    PageOrderAdd.updateTotalPrice();

                }
            }

        },

        /**
         * 更新总价
         */
        updateTotalPrice : function(){
            var totalPrice = 0;
            var tmp = 0;
            setTimeout(function() {
                $(".mini-grid-row").each(function(){
                    var price = parseFloat($(this).find(".mini-grid-cell:eq(7)").text());
                    if(price > 0){
                        totalPrice += price;
                    }
                    tmp ++;
                    //单位
                    var dict = $(this).find(".mini-grid-cell:eq(6)").text()
                    if(tmp == $(".mini-grid-row").length){
                        $("#zj").text(totalPrice.toFixed(2) + dict)
                        $("#hszj").text((totalPrice * 1.17).toFixed(2) + dict)
                    }
                });
                if($(".mini-grid-row").length == 0){
                    $("#zj").text(0)
                    $("#hszj").text(0)
                }
            }, 500);

        },

    }
}();

$(function(){
	PageOrderAdd.init();
});