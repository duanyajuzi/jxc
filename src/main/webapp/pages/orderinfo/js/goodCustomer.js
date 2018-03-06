
var PageGoodCustomer = function(){
    return {
        defaultOption: {
            basePath:"",
            goodCustomerGrid : null,
            employee_grid : null
        },
        init :function () {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.goodCustomerGrid = mini.get("goodCustomerGrid");
            this.employee_grid = mini.get("employee_grid");
            PageMain.funLoadGoodsByBussinessId("goodId", false, "", -1)
            this.funSearch();
        },
        funBusValuechanged : function(e) {
            PageMain.funLoadGoodsByBussinessId("goodId", false, "", e.value)
        },
        funSearch : function() {
        	var goodCustomerForm = new mini.Form("goodCustomerForm");
        	this.goodCustomerGrid.load(goodCustomerForm.getData());
        },
        funReset : function() {
        	var goodCustomerForm = new mini.Form("goodCustomerForm");
        	goodCustomerForm.setData();
            this.goodCustomerGrid.load();
        },
        funAdd : function() {
        	var paramData = {action: "add", row:{}, title:"新增数据"};
            this.funOpenInfo(paramData);
        },
        funModify : function() {
        	var row = this.goodCustomerGrid.getSelected();
            if(row) {
            	var paramData = {action: "modify", row: row, title:"编辑数据"};
                this.funOpenInfo(paramData);
            } else {
            	PageMain.funShowMessageBox("请选择一条记录");
            }
        },
        funOpenInfo : function(paramData) {

        	var me = this;
        	mini.open({
                url: this.basePath + "/pages/orderinfo/goodCustomer_add.jsp",
                title: paramData.title,
                width: 700,
                height: 500,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageGoodCustomerAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                	me.goodCustomerGrid.reload();
                }
            })
        },
        funDelete : function()
        {
            var row = this.goodCustomerGrid.getSelected();
            var me = this;
            if(row)
            {
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok") 
                    {
                        $.ajax({
                            url : me.basePath + "/goodCustomer/del",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data)
                            {
                            	mini.alert(data.msg, "提醒", function(){
                            		if(data.success)
                                    {
                            			 me.goodCustomerGrid.reload();
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
        funManageSchemes:function(){
            var row = this.goodCustomerGrid.getSelected();
            if(row){
                var data=JSON.stringify(row.id);
                var paramData = {goodsId: data};
                mini.open({
                    url:this.basePath+"/pages/orderinfo/blueprint.jsp",
                    title:row.goodsName+"的客户管理",
                    width:850,
                    height:500,
                    onload:function () {
                        var iframe=this.getIFrameEl();
                        iframe.contentWindow.PageBlueprint.funSearch(paramData);
                        iframe.contentWindow.PageBlueprint.funGetData(paramData);
                    },
                    ondestroy:function () {

                    }
                });
            }else
            {
                PageMain.funShowMessageBox("请先选择商品");
            }
        },

        addFangAn : function() {
            var row = this.goodCustomerGrid.getSelected();
            var paramData = {action: "add", row:{goodsId:row.id}, title:"新增客户方案"};
            console.log(paramData)
            this.fangAnInfo(paramData);
        },

        modifyFangAn : function() {
            var row = this.employee_grid.getSelected();
            var paramData = {action: "modify", row:row, title:"修改客户方案"};
            this.fangAnInfo(paramData);
        },

        deleteFangAn : function(){
            var row = this.employee_grid.getSelected();
            var me = this;
            if(row){
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok") {
                        $.ajax({
                            url : me.basePath + "/blueprint/del",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data) {
                                mini.alert(data.msg, "提醒", function(){
                                    if(data.success) {
                                        me.employee_grid.reload();
                                    }
                                });

                            },
                            error: function(){
                                mini.alert("删除记录失败");
                            }
                        });
                    }
                })
            }else{
                mini.alert("请先选择要删除的记录");
            }
        },


        fangAnInfo : function(paramData) {
            var me = this;
            mini.open({
                url: this.basePath + "/pages/orderinfo/blueprint_add.jsp",
                title: paramData.title,
                width: 700,
                height: 500,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageBlueprintAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                    me.employee_grid.reload();
                }
            })
        },




        viewXiangqing : function(e){
            var button;
            button= '<a class="mini-button mini-button-plain" href="javascript:void(0)">' +
                '<span class="mini-button-text  mini-button-icon icon-expand" style="height: auto" ' +
                'onclick="PageGoodCustomer.funOpenInfo()">详情</span></a>';
            return button;
        } ,

        //商品数据详情页
        funOpenInfo:function () {
            var row = this.goodCustomerGrid.getSelected();
            var paramData = {'action': "view", 'row': row};
            mini.open({
                url: this.basePath + "/pages/orderinfo/goodCustomer_add.jsp",
                title: "商品详细信息",
                width: 850,
                height:500,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    iframe.contentWindow.PageGoodCustomerAdd.funSetData(paramData);
                },
                ondestroy: function (action) {
                }
            });
        },

        viewFangAn : function(e){
            var button;
            button= '<a class="mini-button mini-button-plain" href="javascript:void(0)">' +
                '<span class="mini-button-text  mini-button-icon icon-expand" style="height: auto" ' +
                'onclick="PageGoodCustomer.fangAnInfo();">详情</span></a>';
            return button;
        } ,

        //客户方案详情页
        fangAnInfo:function () {
            var row = this.employee_grid.getSelected();
            var paramData = {'action': "view", 'row': row};
            mini.open({
                url: this.basePath + "/pages/orderinfo/blueprint_add.jsp",
                title: "客户方案详细信息",
                width: 850,
                height:500,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    iframe.contentWindow.PageBlueprintAdd.funSetData(paramData);
                },
                ondestroy: function (action) {
                }
            });
        },


    }
}();

$(function(){
	PageGoodCustomer.init();
});