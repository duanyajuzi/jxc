
var PageCustomerAdd = function(){
    return {
        defaultOption: {
            basePath:"",
            action : "",
            customerForm : null
            
        },
        init :function ()
        {
            mini.parse();
            this.basePath = PageMain.basePath;
            this.customerForm = new mini.Form("customerFormAdd");
        },
        funSetData : function(data)
        {
        	var row = data.row;
        	this.action = data.action;
        	this.customerForm.setData(row);
        },
        funSave : function()
        {  
        	this.customerForm.validate();
            console.log(this.customerForm.validate());
            if (!this.customerForm.isValid()) 
            {
                 var errorTexts = form.getErrorTexts();
                 for (var i in errorTexts) 
                 {
                     mini.alert(errorTexts[i]);
                     return;
                 }
            }
            
            var me = this;
            var obj = this.customerForm.getData(true);
            $.ajax({
               url : me.basePath + "/customer/" + me.action + "?a="+Math.random(),
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
        },
        //验证中文
        onChineseValidation:function(name) {
            if(name.isValid){
                var pattern=/^[\u4e00-\u9fa5]{2,5}$/;
                if(!pattern.test(name.value)){
                    name.isValid=false;
                    name.errorText="只能输入2~5位中文";
                }
            }
        },
//         //验证手机
//         onPhoneValidation: function (number) {
//             if(number.isValid) {
// //               var reg=/^[0-9]{11}$/;
//                 var reg = /^0?(13|14|15|17|18)[0-9]{9}$/;
//                 if (!reg.test(number.value)) {
//                     number.isValid = false;
//                     number.errorText = "请输入正确的手机号码";
//                 }
//             }
//         },
        //验证银行卡号
        onBankValidation:function() {
            var bankAccount=mini.get("bankAccount");
            var bankno = bankAccount.value;
            var strBin = "10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";
            if (bankno == "") {
                // bankAccount.isValid = false;
                bankAccount.errorText = "请填写银行卡号";
                return false;
            }
            else if (bankno.length !=16 && bankno.length != 19) {
                // bankAccount.isValid = false;
                bankAccount.errorText = "银行卡号长度必须为16位或19位";
                return false;
            }
            //开头6位
            else if (strBin.indexOf(bankno.substring(0, 2)) == -1) {
                // bankAccount.isValid = false;
                bankAccount.errorText = "银行卡号开头6位不符合规范";
                return false;
            }
        },
    }
}();

$(function(){
	PageCustomerAdd.init();
});