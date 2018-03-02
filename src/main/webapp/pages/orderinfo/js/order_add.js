
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
            this.orderForm.setData(row);
            if(this.action=="add") {
                var orderNo = mini.get("orderNo");
                $.ajax({
                    url: this.basePath + "/order/queryOrderNo",
                    type: "post",
                    data: {},
                    dataType: "json",
                    success: function (result) {
                        orderNo.setValue("DD" + result);
                    },
                    error: function () {
                    }
                });
            }else if(this.action == 'modify'){
                var data = new Object();
                data.orderId = row.id;
                this.orderItemGrid.load(data);
                PageOrderAdd.defaultOption.orderId = row.id;
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
            var newRow = { name: "New Row" };
            var index = $(".mini-grid-row").length;
            this.orderItemGrid.addRow(newRow, index);
            this.orderItemGrid.beginEditCell(newRow, "LoginName");
        },
        removeRow : function () {
            var rows = this.orderItemGrid.getSelecteds();
            if (rows.length > 0) {
                this.orderItemGrid.removeRows(rows, true);
            }
        },

        OnCellBeginEdit : function (e) {
            var record = e.record, field = e.field;
            if (field == "dictName" || field == "goodsName" || field == "price" || field == "totalMoney" ) {
                e.cancel = true;
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
                    var url;
                    if(PageOrderAdd.defaultOption.pOrderType == 0){
                        url =  PageOrderAdd.defaultOption.basePath + "/orderItem/queryNumInfoList2";
                    }else{
                        url =  PageOrderAdd.defaultOption.basePath + "/orderItem/queryNumInfoList";
                    }
                    $.ajax({
                        url:url,
                        type: "post",
                        data: {id:value},
                        dataType: "json",
                        success: function (result) {
                            var obj = new Object();
                            obj.materialNum = result[0].materialNum;
                            obj.goodsName = result[0].goodsName;
                            obj.price = result[0].price;
                            obj.dictName = result[0].dictName;
                            obj.customerGoodId = result[0].id;
                            if(record.esgouNum > 0){
                                obj.totalMoney = (obj.price * record.esgouNum).toFixed(2)
                            }
                            grid.updateRow(record, obj);
                        },
                        error: function () {
                            mini.alert("queryNumInfoList error");
                        }
                    });
                }
            }else if(field == "esgouNum"){
                if(record.price > 0){
                    record.totalMoney = (value * record.price).toFixed(2);
                    grid.updateRow(record, record);
                }
            }

        },

    }
}();

$(function(){
	PageOrderAdd.init();
});