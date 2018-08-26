<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
<script>
	$(function () {
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
                        $(".youyijia-login").html("");
						$(".youyijia-login").html(html);
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
		checkLogin();
    })
</script>

<div id="shortcut-2013">
	<div class="w">
		<ul class="fr lh">
			<li class="fore1 youyijia-login" id="loginbar" clstag="homepage|keycount|home2013|01b"><a href="/user/login.html">您好,请登录</a>&nbsp;<a href="#" style="color: red">[免费注册]</a></li>
			<li class="fore2 ld" clstag="homepage|keycount|home2013|01c">
				<s></s>
				<a href="/order/orderinfo.html" rel="nofollow">我的订单</a>
			</li>
			<li class="fore2-1 ld" id="jd-vip" style="padding-left: 11px;">
				<s></s>
				<a href="#">我的商城</a>
			</li>
			<li class="fore3 ld menu" id="app-jd" data-widget="dropdown" clstag="homepage|keycount|home2013|01d" style="padding-left: 11px">
				<s></s>
				<a href="http://app.jd.com/" target="_blank">商城会员</a><b></b>
			</li>
			<li class="fore4 ld menu" id="biz-service" data-widget="dropdown" clstag="homepage|keycount|home2013|01e">
				<s></s>
				<span class="outline"></span>
				<span class="blank"></span>
				企业采购
				<b></b>
				<div class="dd">
					<div><a href="http://help.jd.com/index.html" target="_blank">帮助中心</a></div>
					<div><a href="http://myjd.jd.com/repair/orderlist.action" target="_blank" rel="nofollow">售后服务</a></div>
					<div><a href="http://chat.jd.com/jdchat/custom.action" target="_blank" rel="nofollow">在线客服</a></div>
					<div><a href="http://myjd.jd.com/opinion/list.action" target="_blank" rel="nofollow">投诉中心</a></div>
					<div><a href="http://www.jd.com/contact/service.html" target="_blank">客服邮箱</a></div>
				</div>
			</li>
			<li class="fore5 ld menu" id="site-nav" data-widget="dropdown" clstag="homepage|keycount|home2013|01f">
				<s></s>
				<span class="outline"></span>
				<span class="blank"></span>
				网站导航
				<b></b>
				<div class="dd lh">
					<dl class="item fore1">
						<dt>特色栏目</dt>
						<dd>
							<div><a target="_blank" href="http://mobile.jd.com/index.do">商品通信</a></div>
							<div><a target="_blank" href="http://jdstar.jd.com/">校园之星</a></div>
							<div><a target="_blank" href="http://my.jd.com/personal/guess.html">为我推荐</a></div>
							<div><a target="_blank" href="http://shipingou.jd.com/">视频购物</a></div>
							<div><a target="_blank" href="http://club.jd.com/">商品社区</a></div>
							<div><a target="_blank" href="http://read.jd.com/">在线读书</a></div>
							<div><a target="_blank" href="http://diy.jd.com/">装机大师</a></div>
							<div><a target="_blank" href="http://giftcard.jd.com/market/index.action">商品E卡</a></div>
							<div><a target="_blank" href="http://channel.jd.com/jiazhuang.html">家装城</a></div>
							<div><a target="_blank" href="http://dapeigou.jd.com/">搭配购</a></div>
							<div><a target="_blank" href="http://xihuan.jd.com/">我喜欢</a></div>
						</dd>
					</dl>
					<dl class="item fore2">
						<dt>企业服务</dt>
						<dd>
							<div><a target="_blank" href="http://giftcard.jd.com/company/index">企业客户</a></div>
							<div><a target="_blank" href="http://sale.jd.com/p10997.html">办公直通车</a></div>
						</dd>
					</dl>
					<dl class="item fore3">
						<dt>旗下网站</dt>
						<dd>
							<div><a target="_blank" href="http://en.jd.com/">English Site</a></div>
						</dd>
					</dl>
				</div>
			</li>
		</ul>
		<span class="clr"></span>
	</div>
</div>