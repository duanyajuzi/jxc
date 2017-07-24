;
$(function(){
	/*日期输入控件的调整*/
	if ($.gCalendar){
	 	var cal = $.gCalendar();
	 	cal.hide();
	 	cal.$hc.find('.ym input').attr('readOnly', true);
	 	$(window).on('resize', function(e){
	 		var $hc = $.gCalendar().$hc;
	 		if ($hc.position().top<0) $hc.css({top:0});
	 	});
	}
	//
	/*textarea输入框自动增高*/
	$(document).on('blur', 'textarea', function(){
		var $this = $(this);
		//???
	});
});
