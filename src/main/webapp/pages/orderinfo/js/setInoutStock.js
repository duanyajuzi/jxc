var PageSetInoutStock = function () {
    return {
        defaultOption: {
            basePath: "",
            orderTree: null,
            datagrid: null,
            beforeTmpNum: null,
            searchForm:null,
            orderType:null
        },
        init: function () {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.orderTree = mini.get("orderTree");
            this.datagrid = mini.get("datagrid");
            this.searchForm=new mini.Form("searchForm");
        },
        funGetData:function (data) {
            if(data!=null && data!="") {
                this.orderType = data.orderType;
                var inEdit = mini.get("inEdit");
                var outEdit = mini.get("outEdit");
                // var tmpNum=mini.get()
                if (this.orderType == 1) {
                    inEdit.hide();
                    this.datagrid.updateColumn("tmpNum", {header: "出库数量"});
                    $("#stimeLabel").html("出库时间");

                } else if (this.orderType == 0) {
                    outEdit.hide();
                    this.datagrid.updateColumn("tmpNum", {header: "入库数量"});
                    $("#stimeLabel").html("入库时间");
                }
            }
        },
        funHideButton:function () {

        },
        funSearch:function () {
            var param=this.searchForm.getData();
            this.orderTree.load(this.basePath+"/orderItem/queryOrderTreeList?orderType="+this.orderType+"&businessId="+param.businessId);
        },
        setInoutTableAll: function () {
            var valueList = new Array();
            var data = new Array();
            var values = this.orderTree.getValue(false);
            valueList = values.split(",");
            var valueLength = valueList.length;
            for (var i = 0; i < valueLength; i++) {
                data[i] = this.orderTree.getNode(valueList[i]);
            }
            for(var i = 0; i < data.length; i++){
                data[i].tmpNum = data[i].afterNum;
                PageSetInoutStock.funSetTable(data[i]);
            }
            PageSetInoutStock.funUncheckTree();
        },
        onEditNum: function () {
            var node = this.orderTree.getSelectedNode();
            this.beforeTmpNum = node.tmpNum;
            var unique = node.orderId + "-" + node.id;
            mini.prompt("请输入出货数量", "出库数量",
                function (action, value) {
                    if (action == "ok") {
                        var pattern=/^[0-9]*$/;
                        if(pattern.test(value)){
                            if (value > node.afterNum) {
                                PageMain.funShowMessageBox("出货数量大于商品订购数量,请重新输入");
                            } else {
                                node.tmpNum = value;
                                PageSetInoutStock.funSetTable(node);
                                PageSetInoutStock.funUncheckTree();
                            }
                        }else {
                            mini.alert("请输入数字");
                        }
                    }
                }
            );
        },
        //删除某行商品信息
        delRight: function (obj) {
            var row=this.datagrid.getSelected();
            var unique = row.key;
            var value = unique.split("-")[1];
            var node = this.orderTree.getNode(value);
            node.tmpNum = row.tmpNum;
            PageSetInoutStock.funModifyTree(node);
            this.datagrid.removeRow(row);
        },
        //删除商品信息时修改tree
        funModifyTree: function (data) {
            data.afterNum = parseFloat(data.afterNum) + parseFloat(data.tmpNum);
            this.orderTree.updateNode(data, {afterNum: data.afterNum});
            this.orderTree.updateNode(data, {text: data.goodsName + "-" + data.materialNum + "(" + parseFloat(data.afterNum) + ")"});
        },
        //添加出库商品数量修改tree
        funUpdateTree: function (data) {
            data.afterNum = parseFloat(data.afterNum) - parseFloat(data.tmpNum);
            this.orderTree.updateNode(data, {afterNum: data.afterNum});
            this.orderTree.updateNode(data, {text: data.goodsName + "-" + data.materialNum + "(" + parseFloat(data.afterNum) + ")"});
        },
        //取消树的选中
        funUncheckTree: function () {
            var nodes = this.orderTree.getAllChildNodes(this.orderTree.getRootNode());
            this.orderTree.uncheckNodes(nodes);
        },
        //设置table内容
        funSetTable: function (data) {
            var beforeData = this.datagrid.getData();
            if (data != null && data != "") {
                var unique=data.orderId + "-" + data.id;
                var row=this.datagrid.findRows(function (row) {
                    if(row.key==unique) return true;
                });
                if(row.length!=0 && row[0].tmpNum != data.esgouNum){
                    var rowData=row[0];
                    this.funUpdateTree(data);
                    data.tmpNum = parseFloat(data.tmpNum) + parseFloat(this.beforeTmpNum);
                    this.datagrid.updateRow(rowData,{"tmpNum":data.tmpNum});
                }else if(row.length!=0 && row[0].tmpNum==data.esgouNum){
                    mini.alert("已全部入/出库");
                }else {
                    var paramData = {
                        "key": unique,
                        "orderId":data.orderId,
                        "orderName": data.orderName,
                        "id":data.id,
                        "goodsName": data.goodsName,
                        "materialNum": data.materialNum,
                        "tmpNum": data.tmpNum,
                        "action": "<a href='javascript:void(0);' onclick='PageSetInoutStock.delRight(this)'>删除</a>"
                    };
                    beforeData.push(paramData);
                    this.funUpdateTree(data);
                    this.datagrid.setData(beforeData);
                }
            }
        },
        updateOrderItemTmpNum: function () {
            var data = this.datagrid.getData();
            var stime=mini.get("stime");
            var value=stime.text;
            var param=this.searchForm.getData();
            var paramData={"stime":value,"orderType":this.orderType,"businessId":param.businessId,"data":JSON.stringify(data)};
            $.ajax({
                url: this.basePath + "/orderItem/updateOrderItemTmpNum",
                data: paramData,
                type: "post",
                dataType: "json",
                success: function () {
                    PageMain.funCloseWindow("save");
                },
                error: function () {
                }
            });
            $.ajax({
                url: this.basePath + "/orderItem/insertTabInoutStock",
                data: paramData,
                type: "post",
                dataType: "json",
                success: function () {
                },
                error: function () {

                }
            });
        }
    }
}();

$(function () {
    PageSetInoutStock.init();
});