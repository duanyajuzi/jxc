var PageSetBill = function () {
    return {
        defaultOption: {
            basePath: "",
            detailGrid: null,
            inoutGrid:null,
            orderType:null,
            billAddForm:null,
            searchForm:null,
            billId:null,
            action:null
        },
        init: function () {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.inoutGrid = mini.get("inoutGrid");
            this.detailGrid = mini.get("detailGrid");
            this.billAddForm=new mini.Form("billAddForm");
            this.searchForm=new mini.Form("searchForm");
        },
        funGetData:function (data) {
            this.orderType=data.orderType;
            var inoutGrid = mini.get("inoutGrid");
            var detailGrid = mini.get("detailGrid");
            if(this.orderType==0){
                inoutGrid.updateColumn("stime",{header:"入库时间"});
                inoutGrid.updateColumn("goodNum", {header: "入库数量"});
                detailGrid.updateColumn("stime",{header:"入库时间"});
                detailGrid.updateColumn("goodNum", {header: "入库数量"});
                $("#consigneeNameLabel").html("发货人姓名：");
                $("#deliveryAddressLabel").html("发货地址：");
            }else if(this.orderType==1){
                inoutGrid.updateColumn("stime",{header:"出库时间"});
                inoutGrid.updateColumn("goodNum", {header: "出库数量"});
                detailGrid.updateColumn("stime",{header:"出库时间"});
                detailGrid.updateColumn("goodNum", {header: "出库数量"});
                $("#consigneeNameLabel").html("收货人姓名：");
                $("#deliveryAddressLabel").html("收货地址：");
            }
        },
        funSearch:function () {
            var param=this.searchForm.getData();
            var inoutGrid=mini.get("inoutGrid");
            inoutGrid.setUrl(this.basePath+"/bill/queryInoutList?orderType="+this.orderType+"&businessId="+param.businessId);
            inoutGrid.load();
        },
        funSetBillRight:function (data) {
            var row=data.row;
            this.action=data.action;
            var type=data.orderType;
            this.billId=row.id;
            var inoutGrid=mini.get("inoutGrid");
            var detailGrid=mini.get("detailGrid");
            var billAddForm=new mini.Form("billAddForm");
            var businessIdValue=null;
            var me=this;
            $.ajax({
                url:this.basePath+"/bill/queryInoutItemList",
                data:{"billId": row.id},
                type:"post",
                dataType:"json",
                success:function (paramData) {
                    var result=paramData.data;
                    if(result.length!=0) {
                        var data = [];
                        for (var i = 0; i < result.length; i++) {
                            data.push(PageSetBill.funGetOutDetailData(result[i]));
                        }
                            var change=PageSetBill.funChangeInfo(type,result[0]);
                            billAddForm.setData(change);
                            detailGrid.setData(data);
                            businessIdValue = result[0].businessId;
                            inoutGrid.setUrl(me.basePath + "/bill/queryInoutList?orderType=" + type + "&businessId=" + businessIdValue);
                            inoutGrid.load();
                    }
                },
                error:function () {
                }
            });
        },
        funGetOutDetailData:function (data) {
            var paramData = {
                "id":data.id,
                "billItemId":data.billItemId,
                "orderId":data.orderId,
                "orderName":data.orderName,
                "goodId":data.goodId,
                "goodsName":data.goodsName,
                "goodNum":data.goodNum,
                "businessId":data.businessId,
                "business": data.business,
                "stime":data.stime,
                "pcustomerId": data.pcustomerId,
                "pcustomerName": data.pcustomerName,
                "pconsigneeName": data.pconsigneeName,
                "consigneeName": data.consigneeName,
                "deliveryAddress": data.deliveryAddress,
                "pconsigneeTel": data.pconsigneeTel,
                "consigneeTel": data.consigneeTel,
                "orderTime": data.orderTime,
                "action": "<a href='javascript:void(0);' onclick='PageSetBill.delRight(this)'>删除</a>"
            };
            return paramData;
        },
        setDetailGrid: function () {
            var items=this.inoutGrid.getSelecteds();
            var beforeData=this.detailGrid.getData();
            var length=items.length;
            if(length!=0){
                for(var i=0;i<items.length;i++){
                    var paramData=PageSetBill.funGetOutDetailData(items[i]);
                    beforeData.push(paramData);
                }
                this.detailGrid.setData(beforeData);
               var data=PageSetBill.funChangeInfo(this.orderType,items[0]);
                this.billAddForm.setData(data);
                this.inoutGrid.removeRows(items);
            }
        },
        funChangeInfo:function (type,data) {
            if(type==0){
                data.customerId = data.customerId;
                data.customerName = data.customerName;
                data.consigneeName = data.consigneeName;
                data.consigneeTel = data.consigneeTel;
            }else if(this.orderType==1){
                data.customerId = data.pcustomerId;
                data.customerName = data.pcustomerName;
                data.consigneeName = data.pconsigneeName;
                data.consigneeTel = data.pconsigneeTel;
            }
            return data;
        },
        onDeptChanged:function () {
            var customerId = mini.get("customerId");
            var consigneeName=mini.get("consigneeName");
            var consigneeTel=mini.get("consigneeTel");
            var id=customerId.getValue();
            if(id!="") {
                $.ajax({
                    url: this.basePath + "/customer/queryOtherInfoList",
                    type: "post",
                    data: {id: id},
                    dataType: "json",
                    success: function (result) {
                        consigneeName.setValue(result[0].consigneeName);
                        consigneeTel.setValue(result[0].consigneeTel);
                    },
                    error: function () {
                        mini.alert("id2 error");
                    }
                });
            }
        },
        funPrePayTimeChanged:function () {
            var billTime=mini.get("billTime");
            var prePayTime=mini.get("prePayTime");
            var billTimeValue=billTime.getValue();
            var prePayTimeValue=PageSetBill.DateAdd("d ", 60, billTimeValue);
            console.log(prePayTimeValue);
            prePayTime.setValue(prePayTimeValue);
        },
        //给指定日期增加天数
        DateAdd:function(interval, number, date) {
        switch (interval) {
            case "y ": {
                date.setFullYear(date.getFullYear() + number);
                return date;
                break;
            }
            case "q ": {
                date.setMonth(date.getMonth() + number * 3);
                return date;
                break;
            }
            case "m ": {
                date.setMonth(date.getMonth() + number);
                return date;
                break;
            }
            case "w ": {
                date.setDate(date.getDate() + number * 7);
                return date;
                break;
            }
            case "d ": {
                date.setDate(date.getDate() + number);
                return date;
                break;
            }
            case "h ": {
                date.setHours(date.getHours() + number);
                return date;
                break;
            }
            case "m ": {
                date.setMinutes(date.getMinutes() + number);
                return date;
                break;
            }
            case "s ": {
                date.setSeconds(date.getSeconds() + number);
                return date;
                break;
            }
            default: {
                date.setDate(d.getDate() + number);
                return date;
                break;
            }
        }
    },
        //删除某行商品信息
        delRight: function (obj) {
            var row=this.detailGrid.getSelected();
            if(row){
                this.detailGrid.removeRow(row);
                this.inoutGrid.addRow(row);
            }
        },
        funSave:function () {
            var data=this.detailGrid.getData();
            var leftData=this.inoutGrid.getData();
            var param=this.billAddForm.getData();
            var prePayTime=mini.get("prePayTime");
            var billTime=mini.get("billTime");
            var value=billTime.text;
            param.prePayTime=prePayTime.text;
            param.billType=this.orderType;
            param.billTime=value;
            if(this.action=="modify"){
                param.id=this.billId;
            }else {
                param.id=null;
            }
            param.businessId=data[0].businessId;
            param.data=JSON.stringify(data);
            param.leftData=JSON.stringify(leftData);
            $.ajax({
                url: this.basePath + "/bill/add",
                data: param,
                type: "post",
                ataType: "json",
                success: function () {
                PageMain.funCloseWindow("save");
                 },
                 error: function () {
                 }
             });
        }
    }
}();

$(function () {
    PageSetBill.init();
});
