
var PageGoodsAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            goodsForm : null
            
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.goodsForm = new mini.Form("goodsFormAdd");
        },
        funSetData : function(data)
        {
        	var row = data.row;
            PageMain.funDictInfo("unit", false, "", "danwei");
            PageMain.funDictInfo("specUnit", false, "", "ggdw");
            var storage=mini.get("storage");
        	this.action = data.action;
            if(this.action=="add"){
                this.goodsForm.setData(row);
            }else if(this.action=="modify") {
                this.goodsForm.setData(row);
                storage.disable();
            }
        },
        //验证物料号，只能输入英文字母和汉字
        onCheckmaterialNum:function () {
          var materialNum=mini.get("materialNum");
          if(materialNum.isValid()){
              var pattern=/^[A-Za-z0-9]+$/;
              if(!pattern.test(materialNum.value)){
                  materialNum.setIsValid(false);
                  materialNum.errorText="只能输入英文字母和数字";
                  // return false;
              }
          }
        },
        funSave : function()
        {
        	this.goodsForm.validate();
            if (!this.goodsForm.isValid())
            {
                 var errorTexts = form.getErrorTexts();
                 for (var i in errorTexts) 
                 {
                     mini.alert(errorTexts[i]);
                     return;
                 }
            }
            
            var me = this;
            var obj = this.goodsForm.getData(true);
            var messageBox = mini.loading("保存中......","提示");
            $.ajax({
               url : me.basePath + "/goods/" + me.action + "?a="+Math.random(),
               type : 'POST',
               data : obj,
               dataType: 'json',
               success: function (data) 
               {
                   mini.hideMessageBox(messageBox);
            	   mini.alert(data.msg, "提醒", function(){
	               		if(data.success)
	                    {
	               			PageMain.funCloseWindow("save");
	                    }
                   });
               },
               error: function (jqXHR, textStatus, errorThrown) 
               {
                   mini.hideMessageBox(messageBox);
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
	PageGoodsAdd.init();
});