<%@ include file="/WEB-INF/pages/common/taglibs-include.jsp" %>

<script src="<c:url value='/jslib/miniui/boot.js'/>"></script>
<script src="<c:url value='/jslib/lesui2/les.ui2.global.js'/>"></script>
<script src="<c:url value='/jslib/echarts.min.js'/>"></script>
<script src="<c:url value='/jslib/jquery.gesoft-1.0.js'/>"></script>

<link href="<c:url value='/jslib/css/common.css'/>" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/loadDwr.js'/>"></script>

<script type="text/javascript">
<!--
	var _ctx_ = "${ctx}";
	var _loginUserType_ = "${loginusertype}";
	PageMain.basePath = "${pageContext.request.contextPath}";
//-->
</script>   

<script type="text/javascript">
<!--
	if (typeof window['DWRUtil'] == 'undefined')    window.DWRUtil = dwr.util;
	dwr.engine._errorHandler= function(message ,ex){
	};
	 dwr.engine.setErrorHandler(function(){});
//-->
</script>   