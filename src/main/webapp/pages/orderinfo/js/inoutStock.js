
var PageInoutStock=function () {
    return {
        defaultOption: {
            basePath: "",
            orderItemInfoGrid: null
        },
        init: function () {
            mini.parse();
            this.orderItemInfoGrid = mini.get("orderItemInfoGrid");
        },
        funSearch: function (data) {
            this.orderItemInfoGrid.load(data);
        },
        //商品出库
        funInoutStock:function () {
            var inoutNum=mini.get("inoutNum");
            var inoutNumValue=inoutNum.getValue();
            var row=this.orderItemInfoGrid.getSelected(); 
            var esgouNumvalue=row.esgouNum;
            if(parseFloat(inoutNumValue)>parseFloat(esgouNumvalue)){
                mini.alert("超出最大采购数量，请重新输入");
            }
            if(row){
                $.ajax({
                    url:this.basePath+"/orderItem/updateOrderItem",
                    data:{orderItem:inoutNumValue},
                    type:"post",
                    dataType:"json",
                    success:function () {
                        
                    },
                    error:function () {

                    }
                });
                var paramData={};
                $.ajax({
                    url:this.basePath+"/orderItem/insertTabInoutStock",
                    data:paramData,
                    type:'post',
                    dataType:"json",
                    success:function () {
                        
                    },
                    error:function () {
                        
                    }
                });
            }else{
                mini.alert("请选择一条商品记录")
            }
        }
    }
} ();

$(function(){
    PageInoutStock.init();
});
