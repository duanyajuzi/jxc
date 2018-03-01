
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
    }
}();

$(function(){
	PageCustomerAdd.init();
});