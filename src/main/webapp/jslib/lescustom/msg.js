/**
 * 
 */

;(function($){
	
	var basePath = $.globalConfig('basePath');
	
	var Msg = function(socketPath, functionid){
		this.$socketPath = socketPath;
		this.$functionid = functionid;
	}

    //查询当前用户 当前页面的开关
	Msg.prototype.queryMsgOn = function(){
		var msgon;
		var functionid = this.$functionid;
		$.ajax({
			url: basePath + 'sysmanage/msg/queryMsgOn',
			data:{'functionid':functionid},
			dataType:'json',
			type:'post',
			async:false,
			success:function(data){
				msgon = data.msgon;
			},
			error:function(e){
				
			}
		});
		return msgon;
	}
    
	//
	Msg.prototype.queryMsgs = function(){
		var msgs = [];
		var functionid = this.$functionid;
		var socketPath = this.$socketPath;
		$.ajax({
    		url:basePath + 'sysmanage/msg/queryMsgs',
    		data:{'functionid':functionid},
    		dataType:'json',
    		type:'post',
    		async:false,
    		success:function(data){
    			msgs = data;
    		},
    		error:function(){
    			
    		}
    	});
		
		if(msgs.length > 0){
			for(var i=0; i < msgs.length; i++){
				var msg = msgs[i];
				var nsp = msg.msgnsp;
				var pnsp = msg.msgpnsp;
				if(nsp){
					var bcsocket = io.connect(socketPath+nsp);
				}
				if(pnsp){
					var psocket = io.connect(socketPath+pnsp);
				}

				var events = msg.events;
				if(events.length > 0){
					for(var i = 0; i < events.length; i++){
						var event = events[i];
						var evt = event.msgevt;
						var state = event.msgstate;
						var bcCallback = event.msgbccallback;
		                var pCallback = event.msgpcallback;
		                if(state == '1'){
		    				if(bcCallback){
		    					bcsocket.on(evt, function(rt){
									var data = rt._data;
		    						eval(""+bcCallback+"("+JSON.stringify(data)+")");
									recordReceive(bcsocket, rt);
		    					});
		    				}
		    				if(pCallback){
		    					psocket.on(evt, function(rt){
									var data = rt._data;
		    						eval(""+pCallback+"("+JSON.stringify(data)+")");
									recordReceive(psocket, rt);
		    					});
		    				}
		    			}
					}
				}
			}
		}

		function recordReceive(socket, data){

			var logSocket = io.connect(socketPath+'/les_websocket');
			if(data._persistentEnable == true){
				data.sessionId = socket.id;
				data.nsp = socket.nsp;
				logSocket.emit('receive', JSON.stringify(data));
				//TODO 只记录一条
				if(f == 0){
					data.userid = UserSession.getUserid();
					logSocket.emit('login', JSON.stringify(data));
					f = 1;
				}
			}
		}
	}

	//持久化
	var f = 0;
	Msg.prototype.recordReceive = function(socket, data){

		var logSocket = io.connect(this.$socketPath+'/les_websocket');
		if(data._persistentEnable == true){
			data.sessionId = socket.id;
			data.nsp = socket.nsp;
			logSocket.emit('receive', JSON.stringify(data));
			//TODO 只记录一条
			if(f == 0){
				data.userid = UserSession.getUserid();
				logSocket.emit('login', JSON.stringify(data));
				f = 1;
			}
		}
	}


	//
	$.Msg = function(socketPath, functionid){
		var msg = new Msg(socketPath, functionid);
		return msg;
	}
	
})(jQuery);