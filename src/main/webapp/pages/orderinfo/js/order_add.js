
var PageOrderAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            orderForm : null,
            pOrderType:null,
            
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.orderForm = new mini.Form("orderFormAdd");
        },
        funSetData : function(data)
        {
            var row = data.row;
            this.pOrderType=row.orderType;
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
            }
        },
        //获取当前时间
        // getNowFormatDate:function(){
        //     var date = new Date();
        //     var seperator1 = "-";
        //     var seperator2 = ":";
        //     var month = date.getMonth() + 1;
        //     var strDate = date.getDate();
        //     if (month >= 1 && month <= 9) {
        //         month = "0" + month;
        //     }
        //     if (strDate >= 0 && strDate <= 9) {
        //         strDate = "0" + strDate;
        //     }
        //     var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        //         + " " + date.getHours() + seperator2 + date.getMinutes()
        //         + seperator2 + date.getSeconds();
        //     return currentdate;
        // },
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
                 var errorTexts = form.getErrorTexts();
                 for (var i in errorTexts) 
                 {
                     mini.alert(errorTexts[i]);
                     return;
                 }
            }
            var me = this;
            var obj = this.orderForm.getData(true);
            obj.orderType=this.pOrderType;
            $.ajax({
               url : me.basePath + "/order/" + me.action + "?a="+Math.random(),
               type : 'POST',
               data : obj,
               dataType: 'json',
               success: function (data) 
               {
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
        }
    }
}();

$(function(){
	PageOrderAdd.init();
});