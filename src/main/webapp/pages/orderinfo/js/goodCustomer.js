
var PageGoodCustomer = function(){
    return {
        defaultOption: {
            basePath:"",
            goodCustomerGrid : null,
            employee_grid : null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.goodCustomerGrid = mini.get("goodCustomerGrid");
            this.employee_grid = mini.get("employee_grid");
            PageMain.funLoadGoodsByBussinessId("goodId", false, "", -1)
            this.funSearch();
        },
        funBusValuechanged : function(e)
        {
            PageMain.funLoadGoodsByBussinessId("goodId", false, "", e.value)
        },
        funSearch : function()
        {
        	var goodCustomerForm = new mini.Form("goodCustomerForm");
        	this.goodCustomerGrid.load(goodCustomerForm.getData());
        },
        funReset : function()
        {
        	var goodCustomerForm = new mini.Form("goodCustomerForm");
        	goodCustomerForm.setData();
            this.goodCustomerGrid.load();
        },
        funAdd : function()
        {
        	var paramData = {action: "add", row:{}, title:"新增数据"};
            this.funOpenInfo(paramData);
        },
        funModify : function()
        {
        	var row = this.goodCustomerGrid.getSelected();
            if(row)
            {
            	var paramData = {action: "modify", row: row, title:"编辑数据"};
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
                url: this.basePath + "/pages/orderinfo/goodCustomer_add.jsp",
                title: paramData.title,
                width: 400,
                height: 30 * 5 + 85,
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
                    height:450,
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
            var paramData = {action: "add", row:{goodsId:row.id}, title:"新增数据"};
            this.fangAnInfo(paramData);
        },
        fangAnInfo : function(paramData) {
            var me = this;
            mini.open({
                url: this.basePath + "/pages/orderinfo/blueprint_add.jsp",
                title: paramData.title,
                width: 400,
                height: 30 *  7 + 65,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageBlueprintAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                    me.employee_grid.reload();
                }
            })
        },




       /* onShowRowDetail : function(e) {
            var grid = e.sender;
            var row = e.record;
            var detailGrid_Form = document.getElementById("detailGrid_Form");
            var employee_grid = mini.get("employee_grid");
            var td = grid.getRowDetailCellEl(row);


            console.log(td)
            td.appendChild(detailGrid_Form);
            detailGrid_Form.style.display = "block";
            employee_grid.load({ goodsId: row.id });
        }*/
    }
}();

$(function(){
	PageGoodCustomer.init();
});