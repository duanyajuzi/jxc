
var PageOrderItem = function(){
    return {
        defaultOption: {
            basePath:"",
            orderItemGrid : null,
            orderItemAddForm:null,
            orderData:null,
            action:null,
            orderItemSave:null,
            orderItemReset:null,
            //厂商商品Id
            goodsNumId:null,
            //修改之前的商品采购数量
            goodsNumBefore:null,
            //
            businessId:null,
            orderType:null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.orderItemGrid = mini.get("orderItemGrid");
            this.orderItemAddForm=new mini.Form("orderItemAddForm");
            this.orderItemSave=mini.get("orderItemSave");
            this.orderItemReset=mini.get("orderItemReset");
            PageMain.funLoadGoodsByBussinessId("goodsId", false, "", -1)
            this.funSearch();
            this.funLabelModel();
        },
        funGetData:function (data) {
            this.orderData=data;
            this.businessId=data.businessId;
            this.orderType=data.orderType;
        },
        funBusValuechanged : function(e)
        {
            PageMain.funLoadGoodsByBussinessId("goodsId", false, "", e.value)
        },
        funSetData:function (data) {
            this.action=data.action;
            var paramData={"businessId":this.businessId};
            if(this.action=="add"){
                this.orderItemAddForm.setData(paramData);
                PageMain.funLoadGoodsByBussinessId("goodsId", false, "", this.businessId);
                this.funInputModel();
            }else if(this.action=="modify") {
                var row = this.orderItemGrid.getSelected();
                if (row) {
                    this.goodsNumId=row.customerGoodId;
                    this.goodsNumBefore=row.esgouNum;
                    PageMain.funLoadGoodsByBussinessId("goodsId", false, "", row.businessId);
                    this.orderItemAddForm.setData(row);
                    this.funInputModel();
                } else {
                    mini.alert("请选择一条所购商品信息");
                }
            }
        },
        funSearch:function(data)
        {
            this.orderItemGrid.load(data);
            var businessId=mini.get("businessId");
            if(data!=null){
                businessId.setValue(data.businessId);
            }
        },
        funReset:function()
        {
            var orderItemAddForm = new mini.Form("orderItemAddForm");
            orderItemAddForm.setData();
            // this.orderItemGrid.load();
        },
        funAdd : function()
        {
            var paramData = {action: "add"};
            this.funSetData(paramData);
        },
        funModify:function () {
            var paramData = {action: "modify"};
            this.funSetData(paramData);
        },
        funDelete : function()
        {
            var row = this.orderItemGrid.getSelected();
            var me = this;
            if(row)
            {
                var goodsData={esgouNum:-row.esgouNum,id:row.customerGoodId};
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok")
                    {
                        $.ajax({
                            url : me.basePath + "/orderItem/del",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data)
                            {
                                mini.alert(data.msg, "提醒", function(){
                                    if(data.success)
                                    {
                                        me.funReset();
                                        me.orderItemGrid.reload();
                                        if(me.orderType==1) {
                                            me.funUpdateStorage(goodsData);
                                        }
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
                console.log("请先选择要删除的记录")
                mini.alert("请先选择要删除的记录");
            }
        },
        //form不可用
        funLabelModel:function () {
            var form=new mini.Form("orderItemAddForm");
            var fields=form.getFields();
            for (var i = 0, l = fields.length; i < l; i++) {
                var c = fields[i];
                if (c.setReadOnly) c.setReadOnly(true);     //只读
                if (c.setIsValid) c.setIsValid(true);      //去除错误提示
            }
            this.orderItemSave.disable();
            this.orderItemReset.disable();
        },
        //form可用
        funInputModel:function () {
            var form=new mini.Form("orderItemAddForm");
            var unitPrice=mini.get("unitPrice");
            var storage=mini.get("storage");
            var businessId=mini.get("businessId");
            var fields = form.getFields();
            for (var i = 0, l = fields.length; i < l; i++) {
                var c = fields[i];
                if (c.setReadOnly) c.setReadOnly(false);
            }
            businessId.disable();
            unitPrice.disable();
            storage.disable();
            mini.repaint(document.body);
            this.orderItemSave.enable();
            this.orderItemReset.enable();
        },
        //根据业务类型设置商品
        onGoodsNameChanged:function () {
            var businessId=mini.get("businessId");
            var goodsId=mini.get("goodsId");
            var value=businessId.getValue();
            $.ajax({
                url: this.basePath + "/goods/queryGoodsList",
                type: "post",
                data: {businessId:value},
                dataType: "json",
                success: function (result) {
                    goodsId.setData(result);
                },
                error: function () {
                    mini.alert("queryGoodsList error");
                }
            });
        },
        //根据商品设置物料号
        onMaterialNumChanged:function () {
            var goodsId=mini.get("goodsId");
            var materialNum=mini.get("materialNum");
            var value=goodsId.getValue();
            materialNum.setValue("");
            $.ajax({
                url: this.basePath + "/order/queryMaterialNum",
                type: "post",
                data: {goodId:value},
                dataType: "json",
                success: function (result) {
                    materialNum.setData(result);
                },
                error: function () {
                    mini.alert("queryMaterialNum error");
                }
            });
        },
        //g
        //根据物料号设置price
        onPriceChanged:function () {
            var unitPrice=mini.get("unitPrice");
            var materialNum=mini.get("materialNum");
            var storage=mini.get("storage");
            var value=materialNum.getValue();
            if(value!="") {
                $.ajax({
                    url: this.basePath + "/orderItem/queryNumInfoList",
                    type: "post",
                    data: {id:value},
                    dataType: "json",
                    success: function (result) {
                        // goodsName.setValue(result[0].goodsName);
                        unitPrice.setValue(result[0].unitPrice);
                        storage.setValue(result[0].storage);
                    },
                    error: function () {
                        mini.alert("queryNumInfoList error");
                    }
                });
            }
        },
        //根据物料号设置price后，再根据采购数量设置price
        onPriceChangedByNum:function () {
            var materialNum=mini.get("materialNum");
            var esgouNum=mini.get("esgouNum");
            var unitPrice=mini.get("unitPrice");
            var storage=mini.get("storage");
            var materialNumValue=materialNum.getValue();
            var esgouNumValue=esgouNum.getValue();
            var storageValue=storage.getValue();
            if(this.orderType==1) {
                if (parseFloat(esgouNumValue) > parseFloat(storageValue)) {
                    mini.alert("货存量不足，请修改");
                    this.orderItemSave.disable();
                } else {
                    this.orderItemSave.enable();
                }
            }
            if (this.action == "modify") {
                    materialNumValue = this.goodsNumId;
            }
                $.ajax({
                url: this.basePath + "/orderItem/queryPlanPrice",
                type: "post",
                data:{goodsId:materialNumValue},
                // data: {goodsId:materialNumValue,id:materialNumValue,esgouNum:esgouNumValue},
                dataType: "json",
                success: function (result) {
                    var length=result.length;
                    if(length>0) {
                        for (var i = 0; i < length; i++) {
                            if(esgouNumValue>result[i].goodsNum){
                                unitPrice.setValue(result[i].price);
                            }
                        }
                    }
                },
                error: function () {
                    mini.alert("queryNumInfoList error");
                }
            });
        },
        funUpdateStorage:function (data) {
            $.ajax({
                url:this.basePath+"/goods/updateStorage",
                type:"post",
                data:data,
                dataType:"json",
                success:function () {
                    
                },
                error:function () {
                    mini.alert("updateStorage error");
                }
            });
        },
        funSave:function () {
            this.orderItemAddForm.validate();
            if (!this.orderItemAddForm.isValid())
            {
                var errorTexts = form.getErrorTexts();
                for (var i in errorTexts)
                {
                    mini.alert(errorTexts[i]);
                    return;
                }
            }
            var me = this;
            var obj = this.orderItemAddForm.getData(true);
            obj.orderId=this.orderData.orderId;
            var esgouNumFinal=obj.esgouNum;
            if(obj!="") {
                obj.customerGoodId = obj.materialNum;
                if (me.action == "modify") {
                    // obj.customerGoodId = this.goodsNumId;
                    esgouNumFinal=obj.esgouNum - this.goodsNumBefore;
                }
            }
            var goodsData={esgouNum:esgouNumFinal,id:obj.customerGoodId};
            $.ajax({
                url : me.basePath + "/orderItem/" + me.action + "?a="+Math.random(),
                type : 'POST',
                data : obj,
                dataType: 'json',
                success: function (data)
                {
                    mini.showMessageBox({
                        showModal:false,
                        width:200,
                        title:"提示",
                        iconCls:"mini-messagebox-warning",
                        message:"操作成功",
                        timeout:1500
                    });
                    me.funReset();
                    if(me.action=="modify"){
                        me.funLabelModel();
                    }
                    if(me.orderType==1) {
                        me.funUpdateStorage(goodsData);
                    }
                        me.orderItemGrid.reload();
                },
                error: function (jqXHR, textStatus, errorThrown)
                {
                    PageMain.funShowMessageBox("操作出现异常");
                }
            });
        }
    }
}();

$(function(){
    PageOrderItem.init();
});
