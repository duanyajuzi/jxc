(function($){
	//节点对象
	var NodeX = function(p, idx,flowObjs)
	{
		if (idx == -1)
		{
			this.name = '开始';
			this.id = 'start';
			this.objIdx = 0;
		}
		else
		{
			this.name = flowObjs[idx][0];
			this.id = flowObjs[idx][1];
			this.objIdx = idx;
		}
		this.parent = p;
		this.type = 't';//普通节点
		if (this.name == '开始') this.type = 's';
		else if (flowObjs[idx][3] == '顺序') this.type = 'f';
		else if (flowObjs[idx][3] == '分支') this.type = 'b';
		else if (flowObjs[idx][3] == '并行') this.type = 'p';
		else if (flowObjs[idx][3] == 'NULL') this.type = 'n';
		else if (flowObjs[idx][3] == 'STOP') this.type = 'e';
		this.nodes = [];
		this._initChildNodes(flowObjs);
		if (this.type == 'b' || this.type == 'p')
		{
			if (this.nodes.length<2) this.type = 't';//无子节点的分支（并行），调整为普通节点
			if (this.nodes.length==1){
				this.name = this.nodes[0].name;
				this.id = this.nodes[0].id;
				this.nodes = [];
			}
		}
	};
	//递归生成子节点
	NodeX.prototype._initChildNodes = function(flowObjs)
	{
		if (this.name == '(开始)')
		{
			this.nodes.push(new NodeX(this, -1, []));//虚拟开始节点
		}
		for(var i=this.objIdx + 1;i<flowObjs.length;i++)
		{
			if (flowObjs[i][2] == this.id)
			{
				var node = new NodeX(this, i, flowObjs);
				this.nodes.push(node);
			}
		}
		if (this.type == 'f' && this.nodes.length<1 ) this.type = 't';
	};
	//节点测试串
	NodeX.prototype.toString = function()
	{
		var strs = [];
		var cstrs = [];
		strs.push('{id:"'+this.id+'",name:"'+this.name+'",type:"'+this.type+'",parent.id="'+(this.parent ? this.parent.id:'')+'",nodes:[');
		for(var n=0;n<this.nodes.length;n++)
		{
			cstrs.push(this.nodes[n].toString());
		}
		strs.push(cstrs.join(','));
		strs.push(']}');
		return strs.join('');
	}
	//
	var WfviewX = function(p)
	{
        this.ops = $.extend({
			zoom: 1,
			direction: '-',
			flowObjs:[]
        }, p);
		this.flow = new NodeX(false, 0, this.ops.flowObjs);
		this.flow.top = 0;
		this.flow.left = 0;
		//
		this.or = this.ops.direction == '-' ? 0 : 1; //显示方向 0-横，1-竖
		this.nH = 36*this.ops.zoom; //nodeHeight
		this.nW = 96*this.ops.zoom; //nodeWidth
		this.snW = 40*this.ops.zoom; //startNodeWidth
		this.npW = 48*this.ops.zoom; //左右导宽度之和 nodePadWidth
		this.bnlW = 36*this.ops.zoom; //branchNodeLeftWidth ;
		this.bnrW = this.npW - this.bnlW;//branchNodeRightWidth
		this.bnvG = 16*this.ops.zoom;//分支并行的垂直间隙 branchNodeVerGap
		this.bnTip = "分支选择执行";//branchTip
		this.pnTip = "分支并行执行";//parallelTip
		this.wnlW = 24*this.ops.zoom;//普通节点的左导,workNodeLeftWidth
		this.wnrW = this.npW -  this.wnlW;//workNodeRightWidth
		this.wnblW = 7;//workNodeBodyLeft
		this.wntT = 8*this.ops.zoom;//workNodeTitleTop
		this.wntH = 18*this.ops.zoom;//workNodeTitleHeight
		//
	};
	//
	//
	WfviewX.prototype._init_position = function(node)
	{
		node.height = this.nH;
		node.width = this.nW;
		node.rightPad = 0;
		if (this._is_start_end_node(node)) node.width = this.snW;
		var cnode;
		var pnode;
		var curLeft = 0;
		var curTop = 0;
		if (node.nodes) 
		{
			if (node.type == "f")
			{
				for(var i=0;i<node.nodes.length;i++)
				{
					cnode = node.nodes[i];
					this._init_position(cnode);
					if (this.or) //垂直方向
					{
						cnode.left = curLeft;
						cnode.top = curTop;
						if (node.width < cnode.width){//子节点宽度大
							node.width = cnode.width;
							//调整前面的节点水平居中
							for(var j=0;j<i;j++)
							{
								pnode = node.nodes[j];
								pnode.left = (node.width - pnode.width)/2;
							}
						}
						else if (node.width > cnode.width)
						{
							cnode.left = (node.width - cnode.width)/2;//调整本节点水平居中
						}
						curTop += cnode.height;
						node.height = curTop;
					}
					else //水平方向显示
					{
						cnode.left = curLeft;
						cnode.top = curTop;
						if (node.height < cnode.height){//子节点高度大
							node.height = cnode.height;
							//调整前面的节点垂直居中
							for(var j=0;j<i;j++)
							{
								pnode = node.nodes[j];
								pnode.top = (node.height - pnode.height)/2;
							}
						}
						else if (node.height > cnode.height)
						{
							cnode.top = (node.height - cnode.height)/2;//调整当前节点垂直居中
						}
						curLeft += cnode.width;
						node.width = curLeft;
					}
				}
			}
			else if (this._is_branch_node(node))
			{
				for(var i=0;i<node.nodes.length;i++)
				{
					cnode = node.nodes[i];
					this._init_position(cnode);
					if (this.or) //垂直方向
					{
						cnode.top = curTop + this.bnlW + (this.npW + this.nH);
						cnode.left = curLeft + this.bnvG/2; //左右空隙
						if (node.height < cnode.height ){//子节点更高
							node.height = cnode.height;
							//调整前面的节点下导线拉长
							for(var j=0;j<i;j++)
							{
								pnode = node.nodes[j];
								pnode.rightPad = node.height - pnode.height; 
							}
						}
						else
						{
							cnode.rightPad = node.height - cnode.height; //调整当前节点下导线拉长
						}
						curLeft += cnode.width + this.bnvG/2; //左右空隙
						node.width = curLeft  + this.bnvG/2;
					}
					else//水平方向显示
					{
						cnode.left = curLeft + this.bnlW + (this.npW + this.nW);
						cnode.top = curTop + this.bnvG/2;
						if (node.width < cnode.width){//子节点宽
							node.width = cnode.width;
							//调整前面的节点右导线拉长
							for(var j=0;j<i;j++)
							{
								pnode = node.nodes[j];
								pnode.rightPad = node.width - pnode.width;
							}
						}
						else
						{
							cnode.rightPad = node.width - cnode.width; //调整当前节点下导线拉长
						}
						curTop += cnode.height + this.bnvG/2 ; //上下空隙
						node.height = curTop  + this.bnvG/2;
					}
				}
			}
		}
		if (node.type != "f")
		{
			if (this.or) //垂直方向
			{
				node.height += this.npW;//由节点上导和下导组成
				if (this._is_branch_node(node)) node.height += (this.npW + this.nH);
			}
			else{
				node.width += this.npW;//由节点左导和右导组成
				if (this._is_branch_node(node)) node.width += (this.npW + this.nW);
			}
		}
	};
	//
	WfviewX.prototype._is_body_node = function(node)
	{
		if (node.type == "s" || node.type=="t" || node.type=="e" || node.type == "n" )
		{
			return true;
		}
		return false;
	};
	WfviewX.prototype._is_branch_node = function(node)
	{
		if (node.type == "b" || node.type == "p")
		{
			return true;
		}
		return false;
	};
	WfviewX.prototype._is_start_end_node = function(node)
	{
		if (node.type == "s" || node.type == "e")
		{
			return true;
		}
		return false;
	};
	WfviewX.prototype._branch_left_line = function(node)
	{
		var idx = node.nodes.length-1;
		if (idx<0) return '';
		var rt = this._pos_style(this.bnlW-1,node.nodes[0].height/2 + this.bnvG/2,//最上面元素的中间+上分支结节的top
								2,node.height - node.nodes[0].height/2 - node.nodes[idx].height/2 - this.bnvG);
		return '<div style="position:absolute;border-left:1px solid #000;'+rt+'"></div>';
	};
	WfviewX.prototype._branch_right_line = function(node)
	{
		var idx = node.nodes.length-1;
		if (idx<0) return '';
		var rt = this._pos_style(0,node.nodes[0].height/2 + this.bnvG/2,//最上面元素的中间+上分支结节的top
								2,node.height - node.nodes[0].height/2 - node.nodes[idx].height/2 - this.bnvG);
		return '<div style="position:absolute;border-left:1px solid #000;'+rt+'"></div>';
	};
	WfviewX.prototype._branch_top_line = function(node)
	{
		var idx = node.nodes.length-1;
		if (idx<0) return '';
		var rt = this._pos_style(node.nodes[0].width/2 + this.bnvG/2,//最上面元素的中间+左分支结节的top
								this.bnlW-1, node.width - node.nodes[0].width/2 - node.nodes[idx].width/2 - this.bnvG,2);
		return '<div style="position:absolute;border-top:1px solid #000;'+rt+'"></div>';
	};
	WfviewX.prototype._branch_bottom_line = function(node)
	{
		var idx = node.nodes.length-1;
		if (idx<0) return '';
		var rt = this._pos_style(node.nodes[0].width/2 + this.bnvG/2,//最上面元素的中间+左分支结节的top
								0, node.width - node.nodes[0].width/2 - node.nodes[idx].width/2 - this.bnvG,2);
		return '<div style="position:absolute;border-top:1px solid #000;'+rt+'"></div>';
	};
	WfviewX.prototype._pos_style = function(left,top,width,height)
	{
		return 'top:'+top+'px;left:'+left+'px;width:'+width+'px;height:'+height+'px;'
	};
	//
	WfviewX.prototype._node_div = function(node)
	{
		var html = [];
		var subHtml = [];
		if (node.nodes)
		{
			for(var i=0;i<node.nodes.length;i++)
			{
				subHtml.push(this._node_div(node.nodes[i]));
			}
		}
		html.push('<div class="pbc_n_'+node.type+'" id="n'+node.id+'" style="'+this._pos_style(node.left,node.top,node.width,node.height)+'">');
		if (this._is_body_node(node))
		{
			if (this.or) //垂直方向
			{
				//输入箭头,上
				if (node.type != "s") html.push('<div class="pbc_np_lt" style="'+this._pos_style(0,0,node.width,this.wnlW)+'"></div>');
				//标题,中
				html.push('<div class="pbc_np_b'+(node.type == 'n' ? ' node_style_gray':'')+'" id="nb'+node.id+'" style="'+this._pos_style(0,this.wnlW,node.width,node.height-this.npW)+'"');
				if (this._is_start_end_node(node))
				{	
					html.push(' title="'+node.name+'">');
				}
				else
				{
					html.push('><div class="pbc_np_bl"  style="'+this._pos_style(0,0,node.width,node.height-this.npW)+'">');
					html.push('<div class="pbc_np_br"  style="'+this._pos_style(this.wnblW,0,node.width-this.wnblW,node.height-this.npW)+'">');
					html.push('<div class="pbc_np_btt" style="'+this._pos_style(1-this.wnblW,this.wntT,node.width-2,this.wntH)+'"  title="'+node.name+'">');
					html.push(node.name+'</div></div></div>');
				}
				html.push('</div>');
				//输出线,下
				if (node.type != "e") html.push('<div class="pbc_np_lb" style="'+this._pos_style(0,node.height-this.wnrW,node.width,this.wnrW+node.rightPad)+'"></div>');
				//
			}
			else //水平方向
			{
				//输入箭头,左
				if (node.type != "s") html.push('<div class="pbc_np_ll" style="'+this._pos_style(0,0,this.wnlW,node.height)+'"></div>');
				//标题,中
				html.push('<div class="pbc_np_b'+(node.type == 'n' ? ' node_style_gray':'')+'" id="nb'+node.id+'"  style="'+this._pos_style(this.wnlW,0,node.width-this.npW,node.height)+'"');
				if (this._is_start_end_node(node))
				{		
					html.push(' title="'+node.name+'">');
				}
				else
				{
					html.push('><div class="pbc_np_bl"  style="'+this._pos_style(0,0,node.width-this.npW,node.height)+'">');
					html.push('<div class="pbc_np_br"  style="'+this._pos_style(this.wnblW,0,node.width-this.npW-this.wnblW,node.height)+'">');
					html.push('<div class="pbc_np_btt" style="'+this._pos_style(1-this.wnblW,this.wntT,node.width-this.npW-2,this.wntH)+'"  title="'+node.name+'">');
					html.push(node.name+'</div></div></div>');
				}
				html.push('</div>');
				//输出线,右
				if (node.type != "e") html.push('<div class="pbc_np_lr" style="'+this._pos_style(node.width-this.wnrW,0,this.wnrW+node.rightPad,node.height)+'"></div>');
				//
			}
		}
		else if (this._is_branch_node(node))
		{
			var btitle = node.name+' ('+(node.type=="branch" ? this.bnTip : this.pnTip)+')';
			if (this.or) //垂直方向
			{
				//节点,加上导32，和下导16
				html.push('<div class="pbc_np_lt" style="'+this._pos_style((node.width-this.nW)/2,0,this.nW,this.wnlW)+'"></div>');
				html.push('<div class="pbc_np_b" id="nb'+node.id+'" style="'+this._pos_style((node.width-this.nW)/2,this.wnlW,this.nW,this.nH)+'">');
				html.push('<div class="pbc_np_bl"  style="'+this._pos_style(0,0,this.nW,this.nH)+'">');
				html.push('<div class="pbc_np_br"  style="'+this._pos_style(this.wnblW,0,this.nW,this.nH)+'">');
				html.push('<div class="pbc_np_btt" style="'+this._pos_style(1-this.wnblW,this.wntT,this.nW-2,this.wntH)+'"  title="'+node.name+'">');
				html.push(node.name+'</div></div></div></div>');
				html.push('<div class="pbc_np_lb" style="'+this._pos_style(0,this.wnlW+this.nH,node.width,this.wnrW)+'"></div>');
				//
				html.push('<div class="pbc_np_'+node.type+'rt" style="'+this._pos_style(0,this.wnlW+this.nH+this.wnrW,node.width,this.bnlW)+'"  title="'+btitle+'">');
				html.push(this._branch_top_line(node));
				html.push('</div>');
				//
				html.push(subHtml.join(''));
				html.push('<div class="pbc_np_'+node.type+'rb" style="'+this._pos_style(0,node.height-this.bnrW,node.width,this.bnrW+node.rightPad)+'">');
				html.push(this._branch_bottom_line(node));
				html.push('</div>');
			}
			else
			{
				//加左导32，和右导16
				html.push('<div class="pbc_np_ll" style="'+this._pos_style(0,(node.height - this.nH)/2,this.wnlW,this.nH)+'"></div>');
				html.push('<div class="pbc_np_b" id="nb'+node.id+'" style="'+this._pos_style(this.wnlW,(node.height - this.nH)/2,this.nW,this.nH)+'">');
				html.push('<div class="pbc_np_bl"  style="'+this._pos_style(0,0,this.nW,this.nH)+'">');
				html.push('<div class="pbc_np_br"  style="'+this._pos_style(this.wnblW,0,this.nW,this.nH)+'">');
				html.push('<div class="pbc_np_btt" style="'+this._pos_style(1-this.wnblW,this.wntT,this.nW-2,this.wntH)+'"  title="'+node.name+'">');
				html.push(node.name+'</div></div></div></div>');
				html.push('<div class="pbc_np_lr" style="'+this._pos_style(this.wnlW+this.nW+this.wnblW,0,this.wnrW,node.height)+'"></div>');
				//
				html.push('<div class="pbc_np_'+node.type+'rl" style="'+this._pos_style(this.wnlW+this.nW+this.wnrW,0,this.bnlW,node.height)+'" title="'+btitle+'">');
				html.push(this._branch_left_line(node));
				html.push('</div>');
				//
				html.push(subHtml.join(''));
				html.push('<div class="pbc_np_'+node.type+'rr" style="'+this._pos_style(node.width-this.bnrW,0,this.bnrW+node.rightPad,node.height)+'">');
				html.push(this._branch_right_line(node));
				html.push('</div>');
			}
		}
		else //顺序
		{
			//顺序不加左右导，更直观
			if (this.or) //垂直方向
			{
				html.push(subHtml.join(''));
				if (node.rightPad>0){ //顺序下边拉长
					html.push('<div class="pbc_np_lb" style="'+this._pos_style(0,node.height,node.width,node.rightPad)+'"></div>');
				}
			}
			else
			{
				html.push(subHtml.join(''));
				if (node.rightPad>0){ //顺序右边拉长
					html.push('<div class="pbc_np_lr" style="'+this._pos_style(node.width,0,node.rightPad,node.height)+'"></div>');
				}
			}
		}
		html.push('</div>');
		
		return html.join('');
	};
	WfviewX.prototype.getHtml = function(id)
	{
		this._init_position(this.flow);
		var html = [];
		html.push('<div id="'+id+'" style="position:relative;'+this._pos_style(this.flow.left,this.flow.top,this.flow.width,this.flow.height)+'">');
		html.push(this._node_div(this.flow) + '</div>');
		return html.join('');
	};
	
	WfviewX.prototype.setNodeStyle = function(nodeId, style)
	{
		var s = style;
		if (s.substr(0,11) != 'node_style_') s = 'node_style_'+style;
		if (style) $('#nb'+nodeId).addClass(s);
	};
	WfviewX.prototype.clearNodeStyle = function(nodeId, style)
	{
		var s = style;
		if (s.substr(0,11) != 'node_style_') s = 'node_style_'+style;
		if (style) $('#nb'+nodeId).removeClass(s);
	};

    //插件函数
    $.WorkFlowView = function(vid, p){
        obj = new WfviewX(vid, p);
        return obj;
    };
    
    //
    var pageid = 0;
	//
	//
})(window.$);