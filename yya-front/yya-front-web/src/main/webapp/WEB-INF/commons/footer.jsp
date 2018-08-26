<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="w" clstag="homepage|keycount|home2013|38a">
	<jsp:include page="footer-links.jsp"></jsp:include>
</div>
<script type="text/javascript" src="/mycart/js/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="/mycart/js/jquery-extend.js"></script>
<script type="text/javascript" src="/mycart/js/lib-v1.js" charset="utf-8"></script>
<script type="text/javascript" src="/mycart/js/atguigu.js" charset="utf-8"></script>
<script type="text/javascript"> (function(){
var A="";
var B=[];
B=pageConfig.FN_GetRandomData(B);
$("#hotwords").html(A);
var _searchValue = "${query}";
if(_searchValue.length == 0){
	_searchValue = B;
}
$("#key").val(_searchValue).bind("focus",function(){if (this.value==B){this.value="";this.style.color="#333"}}).bind("blur",function(){if (!this.value){this.value=B;this.style.color="#999"}});
})();
</script>