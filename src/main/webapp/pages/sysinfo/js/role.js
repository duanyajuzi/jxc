
var PageRole = function(){
    return {
        defaultOption: {
            basePath:"",
            roleGrid : null
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.roleGrid = mini.get("roleGrid");
            this.tree = mini.get("powerTree");
            this.funSearch();
        },
        funSearch:function () {
            var roleForm = new mini.Form("roleForm");
            this.roleGrid.load(roleForm.getData());
            /*
             根据表单中roleName获取roleId，
             有问题（roleName模糊查询，可能查到的ID不止一个，待改进。）
             */
            if(roleForm.getData().roleName!="") {
                $.ajax({
                    url: this.basePath + "/role/queryRoleId",
                    type: "post",
                    data: {"roleName": roleForm.getData().roleName},
                    async: false,
                    dateType: 'json',
                    success: function (result) {
                        PageRole.queryPower(result);
                    },
                    error: function () {
                    }
                });
            }
        },
        funReset : function()
        {
        	var roleForm = new mini.Form("roleForm");
        	roleForm.setData();
            this.roleGrid.load();
            var nodes=this.tree.getAllChildNodes(this.tree.getRootNode());
            this.tree.uncheckNodes(nodes);
        },
        funAdd : function()
        {
        	var paramData = {action: "add", row:{}, title:"新增数据"};
            this.funOpenInfo(paramData);
        },
        funModify : function()
        {
        	var row = this.roleGrid.getSelected();
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

        //保存角色权限
        funSave:function () {
            var me=this;
            var row=this.roleGrid.getSelected();
            if(row){
                var value=me.tree.getValue(false);
                $.ajax({
                    url:me.basePath+"/role/saveRolePower",
                    type:"post",
                    data:{"id":row.id,"ids":value},
                    async:false,
                    dateType:'json',
                    success:function () {
                         mini.alert("设置权限成功");
                    },
                     error:function () {
                         mini.alert("操作失败");
                     }
                });
            }
        },
        //根据grid角色查询角色权限
        onSelectionChanged:function () {
            var record=this.roleGrid.getSelected();
            if(record){
                PageRole.queryPower(record.id);
            }
        },
        //查询角色权限
        queryPower:function (data) {
            var powerTree = mini.get("powerTree");
            var me=this;
            $.ajax({
                url : me.basePath + "/role/queryRolePower",
                type: 'POST',
                data: {"id": data},
                dataType: 'json',
                success: function (result){
                    // var nodes=powerTree.getAllChildNodes(powerTree.getRootNode());
                    // powerTree.uncheckNodes(nodes);
                    var value=new Array();
                    for(var i=0;i<result.length;i++) {
                        value[i]=result[i].pid;
                        // 写法一,未取消之前选中节点
                        // var node= powerTree.getNode(result[i].pid);
                        // powerTree.checkNode(result[i]);
                    }
                    //写法二
                    var str=value.join(',');
                    powerTree.setValue(str);
                },
                error:function () {
                    mini.alert("查询失败");
                }
            });
        },
        funOpenInfo : function(paramData)
        {
        	var me = this;
        	mini.open({
                url: this.basePath + "/pages/sysinfo/role_add.jsp",
                title: paramData.title,
                width: 400,
                height: 30 *  3 + 80,
                onload:function(){
                    var iframe=this.getIFrameEl();
                    iframe.contentWindow.PageRoleAdd.funSetData(paramData);
                },
                ondestroy:function(action){
                	me.roleGrid.reload();
                }
            })
        },
        funDelete : function()
        {
            var row = this.roleGrid.getSelected();
            var me = this;
            if(row)
            {
                mini.confirm("确定要删除这条记录?", "提醒", function (action) {
                    if (action == "ok")
                    {
                        $.ajax({
                            url : me.basePath + "/role/del",
                            type: 'POST',
                            data: {"id": row.id},
                            dataType: 'json',
                            success: function (data)
                            {
                            	mini.alert(data.msg, "提醒", function(){
                            		if(data.success)
                                    {
                            			 me.roleGrid.reload();
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
	PageRole.init();
});