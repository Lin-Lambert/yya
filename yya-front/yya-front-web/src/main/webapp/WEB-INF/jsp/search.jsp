<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!-- saved from url=(0047)http://list.jd.com/list.html?cat=1315,1343,1355 -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="utf-8" http-equiv="charset">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resource'/>/base.css" media="all">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resource'/>/plist20131112.css" media="all">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resource'/>/list-page-20141009.css" media="all">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resource'/>/pop_compare.css" media="all">
<script type="text/javascript"
	src="<c:url value='/resource'/>/jquery-1.2.6.pack.js"></script>
<style id="style-1-cropbar-clipper">/* Copyright 2014 Evernote Corporation. All rights reserved. */
.en-markup-crop-options {
    top: 18px !important;
    left: 50% !important;
    margin-left: -100px !important;
    width: 200px !important;
    border: 2px rgba(255,255,255,.38) solid !important;
    border-radius: 4px !important;
}

.en-markup-crop-options div div:first-of-type {
    margin-left: 0px !important;
}
</style>
<script type="text/javascript">
	function query() {
		//执行关键词查询时清空过滤条件
		document.getElementById("catalog_name").value="";
		document.getElementById("price").value="";
		document.getElementById("page").value="";
		//执行查询
		queryList();
	}
	function queryList() {
		//提交表单
		document.getElementById("actionForm").submit();
	}
	function filter(key, value) {
		document.getElementById(key).value=value;
		queryList();
	}
	function sort() {
		var s = document.getElementById("sort").value;
		s++;
		document.getElementById("sort").value = s;
		queryList();
	}
	function changePage(p) {
		var curpage = Number(document.getElementById("page").value);
		curpage = curpage + p;
		document.getElementById("page").value = curpage;
		queryList();
	}
</script>
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
                            var html = "<span>" + _data.username+"，欢迎来到商品城！<a href=\"http://www.youyijia.com/user/logout.html\" class=\"link-logout\">[退出]</a>" + "</span>";
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
</head>
<body class="root61">
<div id="shortcut-2013">
	<div class="w">
		<ul class="fr lh">
			<li class="fore1 youyijia-login" id="loginbar"><span><a href="/user/login.html">您好，请登录</a> <a href="/user/reg.html" class="link-regist" style="color: red">免费注册</a></span></li>
			<li class="fore2 ld">
				<s></s>
				<a href="/order/orderinfo.html" rel="nofollow">我的订单</a>
			</li>
			<li class="fore2-1 ld" ><i></i>
				<i></i>
				<s></s>
				<a  href="#">我的商城</a>
			</li>
			<li class="fore2-2 ld" >        <i></i><s></s>        <a href="#" target="_blank" rel="nofollow">商城会员</a>    </li>
			<li class="fore3 ld menu" data-widget="dropdown" clstag="homepage|keycount|home2013|01d"><s></s>
				<i></i>
				<span class="outline"></span>
				<span class="blank"></span>
				<a href="#" target="_blank">企业采购</a>
				<b></b>
			</li>
			<li class="fore4 ld menu" id="biz-service" data-widget="dropdown">
				<s></s>
				<span class="outline"></span>
				<span class="blank"></span>
				客户服务
				<b></b>
			</li>
			<li class="fore5 ld menu" id="site-nav" data-widget="dropdown">
				<s></s>
				<span class="outline"></span>
				<span class="blank"></span>
				网站导航
				<b></b>
			</li>
		</ul>
		<span class="clr"></span>
	</div>
</div><!--shortcut end-->
<div id="o-header-2013">
	<div class="w" id="header-2013">
		<div id="logo-2013" class="ld"><a href="/" hidefocus="true"><b></b><img src="<c:url value='/resource'/>/reglogo.png" width="238" height="67" alt="京东"></a></div>
		<!--logo end-->
		<div id="search-2013">
			<div class="i-search ld">
				<ul id="shelper" class="hide"></ul>
				<form id="actionForm" action="search.html" method="POST">
				<div class="form">
					<input type="text" class="text" accesskey="s" name="queryString" id="key" value="${queryString }"
						autocomplete="off" onkeydown="javascript:if(event.keyCode==13) {query()}">
					<input type="button" value="搜索" class="button" onclick="query()">
				</div>
				<input type="hidden" name="cid" id="cid" value="${cid }"/>
				<input type="hidden" name="price" id="price" value="${price }"/> 
				<input type="hidden" name="page" id="page" value="${result.curPage }"/> 
				<input type="hidden" name="sort" id="sort" value="${sort }"/> 
				</form>
			</div>
			<div id="hotwords"></div>
		</div>
		<div id="settleup-2013">
			<dl>
				<dt class="ld"><s></s><span class="shopping"></span><a href="/cart/show.html" id="settleup-url">去购物车结算</a> <b></b> </dt>
				<dd>
					<div class="prompt">
						<div class="loading-style1"><b></b>加载中，请稍候...</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--settleup end-->
	</div>

	<!--header end-->
	<div class="w">
		<div id="nav-2013">
			<div id="categorys-2013" class="categorys-2014">
				<div class="mt ld">
					<h2><a href="#">全部商品分类<b></b></a></h2>
				</div>
			</div>
			<div id="treasure"></div>
			<ul id="navitems-2013">
				<li class="fore1" id="nav-home"><a href="${pageContext.request.contextPath}/">首页</a></li>
				<li class="fore2" id="nav-fashion"><a href="#">服装城</a></li>
				<li class="fore3" id="nav-chaoshi"><a href="#">食品</a></li>
				<li class="fore4" id="nav-tuan"><a href="#">团购</a></li>
				<li class="fore5" id="nav-auction"><a href="#">夺宝岛</a></li>
				<li class="fore6" id="nav-shan"><a href="#">闪购</a></li>
				<li class="fore7" id="nav-jinrong"><a href="#" target="_blank">金融</a></li>
			</ul>
		</div>
	</div>
</div>
<div class="w">
	<div class="breadcrumb">
		<strong><a href="${pageContext.request.contextPath}/">首页</a></strong><span>&nbsp;&gt;&nbsp;<a
			href="#">搜索</a></span>
	</div>
</div>
<div class="w main">
<div class="right-extra">
<div id="select" clstag="thirdtype|keycount|thirdtype|select" class="m">
	<div class="mt">
		<h1>
			<strong>&nbsp;商品筛选</strong>
		</h1>
	</div>
	<div class="mc attrs">
		<div data-id="100001" class="brand-attr">
			<div class="attr">
				<div class="a-key">商品类别：</div>
				<div class="a-values">
					<div class="v-tabs">
						<div class="tabcon">
							<div>
								<c:choose>
									<c:when test="${cid == 34}">
										<a href="javascript:filter('cid', '34')" style="color: red;">手机</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:filter('cid', '34')" >手机</a>
									</c:otherwise>
								</c:choose>
							</div>
							<div>
								<a href="javascript:filter('cid', '76')">时尚卫浴</a>
							</div>
							<div>
								<a href="javascript:filter('cid', '560')">另类文体</a>
							</div>
							<div>
								<a href="javascript:filter('cid', '76')">创意相架</a>
							</div>
							<div>
								<a href="javascript:filter('cid', '560')">巧妙收纳</a>
							</div>
							<div>
								<a href="javascript:filter('cid', '76')">与钟不同</a>
							</div>
							<div>
								<a href="javascript:filter('cid', '560')">个性男人</a>
							</div>
							<div>
								<a href="javascript:filter('cid', '76')">电脑周边</a>
							</div>
							<div>
								<a href="javascript:filter('cid', '560')">品质家电</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '76')">品味茶杯</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '560')">四季用品</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '76')">健康宝宝</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '560')">新潮美容</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '76')">产品配件</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '560')">雅致灯饰</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '76')">阳光车饰</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '560')">趣味纸抽</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '76')">布艺毛绒</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '560')">益智手工</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '76')">环保餐具</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '560')">闪亮匙扣</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '76')">手机饰品</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '560')">精品数码</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '76')">理财钱罐</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '560')">美味厨房</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '560')">保健按摩</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('cid', '76')">魅力女人</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div data-id="100002" class="prop-attrs">
			<div class="attr">
				<div class="a-key">价格：单位（元）</div>
				<div class="a-values">
					<div class="v-fold">
						<ul class="f-list">
							<c:choose>
								<c:when test="${pricePage >= 50000 && pricePage <= 100000}">
									<li><a href="javascript:filter('price','50000-100000')" style="color: red;">500-1000</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="javascript:filter('price','50000-100000')">500-1000</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${pricePage >= 100000 && pricePage <= 150000}">
									<li><a href="javascript:filter('price','100000-150000')" style="color: red;">1000-1500</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="javascript:filter('price','100000-150000')">1000-1500</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${pricePage >= 150000 && pricePage <= 200000}">
									<li><a href="javascript:filter('price','150000-200000')" style="color: red;">1500-2000</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="javascript:filter('price','150000-200000')">1500-2000</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${pricePage >= 200000 && pricePage <= 250000}">
									<li><a href="javascript:filter('price','200000-250000')" style="color: red;">2000-2500</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="javascript:filter('price','200000-250000')">2000-2500</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${pricePage >= 250000 && pricePage <= 300000}">
									<li><a href="javascript:filter('price','250000-300000')" style="color: red;">2500-3000</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="javascript:filter('price','250000-300000')">2500-3000</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${pricePage >= 300000}">
									<li><a href="javascript:filter('price','300000-*')" style="color: red;">3000以上</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="javascript:filter('price','300000-*')">3000以上</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${empty pricePage}">
									<li><a href="javascript:filter('price','*-*')" style="color: red;">全部</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="javascript:filter('price','*-*')">全部</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="filter">
	<div class="cls"></div>
	<div class="fore1">
		<dl class="order">
			<dt>排序：</dt>
			<dd>
				<a href="javascript:sort()">价格</a><b></b>
			</dd>
		</dl>
		<dl class="activity">
			<dd></dd>
		</dl>
		<div class="pagin pagin-m">
			<span class="text"><i>${result.curPage }</i>/${result.pageCount }</span>
			<a href="javascript:changePage(-1)" class="prev">上一页<b></b></a>
			<a href="javascript:changePage(1)" class="next">下一页<b></b></a>
		</div>
		<div class="total">
			<span>共<strong>${result.recordCount }</strong>个商品
			</span>
		</div>
		<span class="clr"></span>
	</div>
</div>
<!--商品列表开始-->
<div id="plist" class="m plist-n7 plist-n8 prebuy">
	<ul class="list-h">
		<c:forEach var="product" items="${result.productList }">
		<li pid="${product.id }">
			<div class="lh-wrap">
				<div class="p-img">
					<a target="_blank" href="http://www.youyijia.com/product/${product.id }.html">
						<img width="220" height="282" class="err-product" src="${product.image}" onerror="javascript:this.src='${pageContext.request.contextPath}/images/404.jpg'">
					</a>
				</div>
				<div class="p-name">
					<a target="_blank" href="/product/${product.id}.html">${product.title }</a>
				</div>
				<div class="p-price">
					<strong>￥<fmt:formatNumber value="${product.price/100}" maxFractionDigits="2"/></strong><span id="p1269191543"></span>
				</div>
			</div>
		</li>
		</c:forEach>
	</ul>
</div>
<!--商品列表结束-->
</div>
<div class="left">
	<div id="sortlist" clstag="thirdtype|keycount|thirdtype|sortlist"
		class="m">
		<div class="mt">
			<h2>服饰内衣</h2>
		</div>
		<div class="mc">
			<div class="item current">
				<h3>
					<b></b>导航栏
				</h3>
				<ul>
					<li><a href="#">图书、音像、电子书</a></li>
					<li><a href="#">手机、数码、通信</a></li>
					<li><a href="#">电脑、办公</a></li>
					<li><a href="#">家具、家具、家装、厨具</a></li>
					<li><a href="#">男装、女装、内衣</a></li>
					<li><a href="#">鞋靴、箱包、钟表、奢侈品</a></li>
					<li><a href="#">母婴、玩具乐器</a></li>
					<li><a href="#">食品、酒类、生鲜、特产</a></li>
					<li><a href="#">家用电器</a></li>
					<li><a href="#">腕表、珠宝配饰、眼镜</a></li>
					<li><a href="#">营养保健</a></li>
					<li><a href="#">个人化妆、清洁用品</a></li>
					<li><a href="#">汽车、汽车用品</a></li>
					<li><a href="#">保健滋补、医药、成人</a></li>
					<li><a href="#">休闲、运动、户外健身</a></li>
					<li><a href="#">礼品、卡、旅游、充值</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="limitBuy">
		<div id="limitbuy9199"
			clstag="thirdtype|keycount|thirdtype|limitbuy536"
			class="m limitbuy hide">
			<div class="mt">
				<h2>服饰鞋帽</h2>
			</div>
			<div class="mc">
				<div id="clock9199" class="clock">正在加载…</div>
				<div id="limit9199"></div>
			</div>
		</div>
	</div>
	<div id="ad_left" reco_id="6" class="m m0 hide"></div>
</div><!--<div class="left">-->

<span class="clr"></span>
<div id="Collect_Tip" class="Tip360 w260"></div>

</div><!--<div class="w main">-->


</body>
</html>