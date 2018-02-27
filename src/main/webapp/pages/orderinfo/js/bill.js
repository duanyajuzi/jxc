
var PageBill = function(){
    return {
        defaultOption: {
            basePath:"",
            billGrid : null,
            type:null,
            billItemGrid:null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.billGrid = mini.get("billGrid");
            this.billItemGrid=mini.get("billItemGrid");
            // this.billGrid.load();
            this.type=PageBill.getUrlParam("billType");
            if(this.type==0){
                this.billItemGrid.updateColumn("stime", {header: "入库时间"});
                this.billItemGrid.updateColumn("goodNum", {header: "入库数量"});
            }else if(this.type==1){
                this.billItemGrid.updateColumn("stime", {header: "出库时间"});
                this.billItemGrid.updateColumn("goodNum", {header: "出库数量"});
            }
        },
        getUrlParam:function(name){
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return decodeURIComponent(r[2]); return null;
        },
        funSearch : function()
        {
        	var billForm = new mini.Form("billForm");
        	this.billGrid.load(billForm.getData(),function (res) {
                
            });
        },
        funReset : function()
        {
        	var billForm = new mini.Form("billForm");
        	billForm.setData();
            this.billGrid.load();

        },
        funAdd : function()
        {
        	var paramData = {action: "add",row:{billType:this.type}, title:"新增数据"};
            this.funOpenInfo(paramData);
        },
        funModify : function()
        {
        	var row = this.billGrid.getSelected();
            if(row)
            {
                row.billType=this.type;
            	var paramData = {action: "modify", row: row, title:"编辑数据"};
                if(row.payTime == null || row.payTime == ""){
                    this.funOpenInfo(paramData);
                }else {
                    PageMain.funShowMessageBox("已付款，不可修改");
                }
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
                url: this.basePath + "/pages/orderinfo/bill_add.jsp",
                title: paramData.title,
                width: 400,
                height: 400,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageBillAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                	me.billGrid.reload();
                }
            })
        },
        funOpenSetBill:function () {
          var paramData={"orderType":this.type};
          mini.open({
              url:this.basePath+"/pages/orderinfo/setBill.jsp",
              title:"开票",
              width:1150,
              height:600,
              onload:function () {
                  var iframe=this.getIFrameEl();
                  iframe.contentWindow.PageSetBill.funGetData(paramData);
              },
              ondestroy:function () {
              }
          });  
        },
        funView:function () {
          var row=this.billGrid.getSelected();
          if(row){
              mini.open({
                  url:this.basePath+"/pages/orderinfo/bill_add.jsp",
                  title:"查看详情",
                  width: 400,
                  height: 380,
                  onload:function () {
                      var iframe=this.getIFrameEl();
                      var data={"row":row,"action":"view"};
                      iframe.contentWindow.PageBillAdd.funSetData(data);
                  },
                  ondestroy:function () {
                  }
              });
          }
        },
        funModifySetBill:function () {
            var row=this.billGrid.getSelected();
            var paramData={"orderType":this.type,"row":row,"action":"modify"};
            if(row){
                // if(row.payTime==null || row.payTime=="") {
                    mini.open({
                        url: this.basePath + "/pages/orderinfo/setBill.jsp",
                        title: "开票",
                        width: 1150,
                        height: 600,
                        onload: function () {
                            var iframe = this.getIFrameEl();
                            iframe.contentWindow.PageSetBill.funGetData(paramData);
                            iframe.contentWindow.PageSetBill.funSetBillRight(paramData);
                        },
                        ondestroy: function () {
                            // this.billGrid.reload();
                        }
                        // }
                    });

                // }else{
                //     mini.alert("订单已付款，不可修改票据内容");
                // }
            }else{
                mini.alert("请先选择所需修改的票据记录");
            }
        },
        funDelete : function()
        {
            var row = this.billGrid.getSelected();
            var me = this;
            if(row)
            {
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok") 
                    {
                        $.ajax({
                            url : me.basePath + "/bill/del",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data)
                            {
                            	mini.alert(data.msg, "提醒", function(){
                            		if(data.success)
                                    {
                            			 me.billGrid.reload();
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
        }
    }
}();

$(function(){
	PageBill.init();
});