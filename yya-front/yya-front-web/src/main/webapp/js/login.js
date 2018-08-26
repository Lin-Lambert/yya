function checkLogin(){
	var _ticket= get_cookie("YOUYIJIA_TICKET");
	if(!_ticket){
		return ;
	}
	$.ajax({
		url : "http://www.youyijia.com/service/user/" + _ticket,
		dataType : "json",
		type : "GET",
		success : function(data){
			if(data){
				var _data = data;
				var html =_data.username+"，欢迎来到商品城！<a href=\"http://www.youyijia.com/user/logout.html\" class=\"link-logout\">[退出]</a>";
				$("#ttbar-login").html(html);
			}
		},
	});
};

function get_cookie(Name) {
	   var search = Name + "="//查询检索的值
	   var returnvalue = "";//返回值
	   if (document.cookie.length > 0) {
	     sd = document.cookie.indexOf(search);
	     if (sd!= -1) {
	        sd += search.length;
	        end = document.cookie.indexOf(";", sd);
	        if (end == -1)
	         end = document.cookie.length;
	         //unescape() 函数可对通过 escape() 编码的字符串进行解码。
	        returnvalue=unescape(document.cookie.substring(sd, end))
	      }
	   } 
	   return returnvalue;
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	checkLogin();
});