var PageMain = function(){
    return {
        defaultOption: {
            basePath:""
        },
        init :function (basePath){
            this.basePath = basePath;
        },
        funShowMessageBox : function(msg)
        {
            mini.showMessageBox({
                showModal: false,
                width: 250,
                title: "提醒",
                iconCls: "mini-messagebox-warning",
                message: msg,
                timeout: 2000
            });
        },
        funCloseWindow : function(action)
        {
            if(window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        },
        //第一个参数是，下拉框的ID，第二个是是否设计默认值，第三个参数是默认值，第四个参数是代码编码
        funDictInfo : function(paramId, flag, defaultVal, dmbm)
        {
            loadDwr.queryDictInfo(dmbm, {"callback":function(data)
            {
                var obj = mini.get(paramId);
                obj.setData(data);
                if (flag)
                {
                    obj.setValue(defaultVal);
                }
            }});
        },
        funLoadGoodsByBussinessId : function(paramId, flag, defaultVal, bussinessId)
        {
            loadDwr.queryGoodsInfo(bussinessId, {"callback":function(data)
            {
                var obj = mini.get(paramId);
                obj.setData(data);
                if (flag)
                {
                    obj.setValue(defaultVal);
                }
            }});
        }

    }
}();