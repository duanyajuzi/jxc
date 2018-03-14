var PageSetOutStock = function () {
    return {
        defaultOption: {
            basePath: "",
            orderTree: null,
            datagrid: null,
            beforeTmpNum: null,
            searchForm:null,
            orderType:null,
            realStorageMap:new Map(),
            tempStorageMap:new Map()
        },
        init: function () {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.orderTree = mini.get("orderTree");
            this.datagrid = mini.get("datagrid");
            this.searchForm=new mini.Form("searchForm");
            var date=new Date();
            mini.get("stime").setValue(date);

        },
        funGetData:function (data) {
            if(data!=null && data!="") {
                this.orderType = data.orderType;
                PageSetOutStock.funSearch();
            }
        },
        funSearch:function () {
            var businessId=mini.get("businessId").getValue().trim("");
            var orderNo=mini.get("orderNo").getValue().trim("");
            var orderName=mini.get("orderName").getValue().trim("");
            var materialNum=mini.get("materialNum").getValue().trim("");
            var ycmaterialNum=mini.get("ycmaterialNum").getValue().trim("");
            var paramData={'orderType':this.orderType,'orderNo':orderNo,'orderName':orderName,'materialNum':materialNum,
                'ycmaterialNum':ycmaterialNum,'businessId':businessId};
            $.ajax({
                url: this.basePath + "/orderItem/queryOrderTreeList",
                data: paramData,
                type: "post",
                dataType: "json",
                success: function (data) {
                    mini.get("orderTree").loadData(data);
                    if(data.length>0){
                        for(var i=0;i<data.length;i++) {
                            if (data[i].children.length > 0) {
                                for (var j = 0; j < data[i].children.length; j++) {
                                    PageSetOutStock.defaultOption.realStorageMap.set(data[i].children[j].customerGoodId, data[i].children[j].storage)
                                }
                            }
                        }
                    }
                },
                error: function () {
                }
            });
        },
        funReset:function(){
            var searchForm = new mini.Form("searchForm");
            searchForm.setData();
            this.orderTree.load(this.basePath+"/orderItem/queryOrderTreeList?orderType="+this.orderType);
        },
        setInoutTableAll: function () {
            var valueList = new Array();
            var data = new Array();
            var values = this.orderTree.getValue(false);
            var node = this.orderTree.getSelectedNode();
            if(node.checked==true || (values!=null && values!="")) {
                if (values.length > 0) {
                    valueList = values.split(",");
                    var valueLength = valueList.length;
                    for (var i = 0; i < valueLength; i++) {
                        data[i] = this.orderTree.getNode(valueList[i]);
                    }
                    for (var j = 0; j < data.length; j++) {
                        if(PageSetOutStock.setTempMap(data[j].customerGoodId,data[j].afterNum)) {
                            data[j].beforeTmpNum = data[j].tmpNum;
                            data[j].tmpNum = data[j].afterNum;
                            PageSetOutStock.funSetTable(data[j]);
                        }else{
                            mini.alert(data[j].orderName+"的"+data[j].materialNum+"库存不足");
                        }
                    }
                }else {
                    mini.alert("请选择所要入库的商品");
                }
            }
            PageSetOutStock.funUncheckTree();

        },
        onEditNum: function () {
            var node = this.orderTree.getSelectedNode();
            node.beforeTmpNum = node.tmpNum;
            mini.prompt("请输数量", "数量",
                function (action, value) {
                    if (action == "ok") {
                        var pattern=/^[0-9]*$/;
                        if(pattern.test(value)){
                            if (value > node.afterNum) {
                                PageMain.funShowMessageBox("数量大于商品订购数量,请重新输入");
                            } else if(value=="" || value==null){
                                PageMain.funShowMessageBox("请输入数量");
                            }else if(PageSetOutStock.setTempMap(node.customerGoodId,value)){
                                node.tmpNum = value;
                                PageSetOutStock.funSetTable(node);
                                PageSetOutStock.funUncheckTree();
                            }else{
                                mini.alert(node.orderName+"的"+node.materialNum+"库存不足");
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
            // var node = this.orderTree.getNode(value);
            var node=this.orderTree.findNodes(function(node){
                if(node.treeId==value) return true;
            })[0];
            node.tmpNum = row.tmpNum;
            PageSetOutStock.funModifyTree(node);
            PageSetOutStock.removeTempMap(row.customerGoodId,row.tmpNum);
            this.datagrid.removeRow(row);
        },
        //删除商品信息时修改tree
        funModifyTree: function (data) {
            data.afterNum = parseFloat(data.afterNum) + parseFloat(data.tmpNum);
            this.orderTree.updateNode(data, {afterNum: data.afterNum});
            this.orderTree.updateNode(data, {text: data.goodsName + "-" + data.ycmaterialNum+ data.materialNum + "(" + parseFloat(data.afterNum) + ")"});
        },
        //添加出库商品数量修改tree
        funUpdateTree: function (data) {
            data.afterNum = parseFloat(data.afterNum) - parseFloat(data.tmpNum);
            this.orderTree.updateNode(data, {afterNum: data.afterNum});
            this.orderTree.updateNode(data, {text: data.goodsName + "-" + data.ycmaterialNum + data.materialNum + "(" + parseFloat(data.afterNum) + ")"});
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
                var unique=data.orderId + "-" + data.treeId;
                var row=this.datagrid.findRows(function (row) {
                    if(row.key==unique) return true;
                });
                if(row.length!=0 && row[0].tmpNum != data.esgouNum){
                    var rowData=row[0];
                    this.funUpdateTree(data);
                    data.tmpNum = parseFloat(data.tmpNum) + parseFloat(data.beforeTmpNum);
                    this.datagrid.updateRow(rowData,{"tmpNum":data.tmpNum});
                }else if(row.length!=0 && row[0].tmpNum==data.esgouNum){
                    mini.alert(row[0].orderName+"的"+row[0].materialNum+"已全部出库");
                }else {
                    var paramData = {
                        "key": unique,
                        "orderId":data.orderId,
                        "orderName": data.orderName,
                        "id":data.treeId,
                        "customerGoodId":data.customerGoodId,
                        "goodId":data.goodId,
                        "goodsName": data.goodsName,
                        "materialNum": data.materialNum,
                        "tmpNum": data.tmpNum,
                        "unitPrice": data.unitPrice,
                        "action": "<a href='javascript:void(0);' onclick='PageSetOutStock.delRight(this)'>删除</a>"
                    };
                    beforeData.push(paramData);
                    this.funUpdateTree(data);
                    this.datagrid.setData(beforeData);
                }
            }
        },
        updateOrderItemTmpNum: function () {
            var data = this.datagrid.getData();
            if(data.length>0) {
                var stime = mini.get("stime");
                var value = stime.text;
                var param = this.searchForm.getData();
                if (this.orderType == 1) {
                    data.tmpNum = 0 - parseFloat(data.tmpNum);
                }
                var paramData = {
                    "stime": value,
                    "orderType": this.orderType,
                    "businessId": param.businessId,
                    "data": JSON.stringify(data)
                };
                var messageBox = mini.loading("保存中......","提示");
                $.ajax({
                    url: this.basePath + "/orderItem/insertTabInoutStock",
                    data: paramData,
                    type: "post",
                    dataType: "json",
                    async:false,
                    success: function (data) {
                        mini.hideMessageBox(messageBox);
                        mini.alert(data.msg, "提醒", function(){
                            if(data.success)
                            {
                                PageMain.funCloseWindow("save");
                            }
                        });
                    },
                    error: function () {
                    }
                });
                // $.ajax({
                //     url: this.basePath + "/orderItem/updateOrderItemTmpNumOut",
                //     data: paramData,
                //     type: "post",
                //     dataType: "json",
                //     async:false,
                //     success: function () {
                //
                //     },
                //     error: function () {
                //     }
                // });
            }
        },
        setTempMap:function (goodsId,num) {
            if(PageSetOutStock.defaultOption.tempStorageMap.has(goodsId)){
                var tempnum = PageSetOutStock.defaultOption.tempStorageMap.get(goodsId);
                PageSetOutStock.defaultOption.tempStorageMap.set(goodsId,parseFloat(tempnum)+parseFloat(num));
            }else{
                PageSetOutStock.defaultOption.tempStorageMap.set(goodsId,num);
            }
            if(parseFloat(PageSetOutStock.defaultOption.tempStorageMap.get(goodsId)) > parseFloat(PageSetOutStock.defaultOption.realStorageMap.get(goodsId))){
                PageSetOutStock.removeTempMap(goodsId,num);
                return false;
            }else {
                return true;
            }
        },
        removeTempMap:function (goodsId,num) {
            var tempnum = PageSetOutStock.defaultOption.tempStorageMap.get(goodsId);
            PageSetOutStock.defaultOption.tempStorageMap.set(goodsId,parseFloat(tempnum)-parseFloat(num));
        }
    }
}();

$(function () {
    PageSetOutStock.init();
});
