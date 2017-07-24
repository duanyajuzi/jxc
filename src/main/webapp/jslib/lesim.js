/**
 * 
 */

;(function($){

    //
    Cross.on('addUser', function(data){
        if ($('#lesim_talk').css('visibility') == 'hidden') {

            $('#lesim_talk').css('visibility', 'inherit');
            //show的时候进入房间 hide的时候退出房间
            //document.getElementById("talk").contentWindow.socket.emit('join', userid)
            Cross.call(document.getElementById("talk").contentWindow, LESIM_DOMAIN,'join');
        }

        //document.getElementById("talk").contentWindow.addUser(data);
        Cross.call(document.getElementById("talk").contentWindow, LESIM_DOMAIN,'addUser',data);
    });

    //
    Cross.on('hideTalk', function(){
        $('#lesim_talk').css('visibility', "hidden");
    });

    //
    Cross.on('showUnread', function(){
        if($('#lesim_main').css('display') == 'none'){
            $('.num').show();
        }
    });

    //
    Cross.on("loadGroupList", function(){
        Cross.call(document.getElementById("main").contentWindow, LESIM_DOMAIN, 'loadGroupList');
    });

    //
    Cross.on("hideMain", function(){
        $('#lesim_main').hide();
    });

    //
    Cross.on("openMainS", function(options){
        layer.open(options);
    });

    //
    Cross.on("listenOnline", function(data){
        Cross.call(document.getElementById("talk").contentWindow, LESIM_DOMAIN, 'listenOnline', data);
    });

    //
    Cross.on("closeMainS", function(action){
        var index = layer.getFrameIndex(action); //先得到当前iframe层的索引
        layer.close(index); //再执行关闭
    });

    //
    Cross.on("talkStatus",function(){
        var status=$("#lesim_talk").css("visibility");
        if(status=="visible"){
            Cross.call(document.getElementById("talk").contentWindow, LESIM_DOMAIN, 'hideTalkWindow');
        }
    })
	

})(jQuery);