<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
   <meta http-equiv="pragma" content="no-cache">
   <meta http-equiv="cache-control" content="no-cache">
   <meta http-equiv="expires" content="0"> 
   <meta name="format-detection" content="telephone=no">  
   <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
   <meta name="format-detection" content="telephone=no">
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link rel="stylesheet" href="/mycart/css/base.css">
   <link href="/mycart/css/purchase.2012.css?v=201410141639" rel="stylesheet" type="text/css">
   <title>我的订单 - 商品商城</title>
   <script>
   	var pageConfig  = {};
   </script>
    <script type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
<body>
<!--shortcut start-->
<jsp:include page="../commons/shortcut.jsp" />
<!--shortcut end-->
<div class="w w1 header clearfix">
	<div id="logo"><a href="http://www.youyijia.com/"><img clstag="clickcart|keycount|xincart|logo" src="/mycart/images/reglogo.png" title="返回商品商城首页" alt="返回商品商城首页"></a></div>
    <div class="language"><a href="javascript:void(0);" onclick="toEnCart()"></a></div>
	<div class="progress clearfix">
	</div>
	</div>
</div>
<div class="w cart">
	<div class="cart-hd group">
		<h1>我的订单</h1>
		<span id="show2" class="fore"> 	</div>
	<div id="show">
	
<div class="cart-frame">
    <div class="tl"></div>
    <div class="tr"></div>
</div>
<c:forEach items="${pageResult.data}" var="order">
    <c:set var="total" value="${pageResult.totle}"></c:set>
    <c:if test="${order.status != 1}">
        <c:set var="num" value="${ num + 1 }"></c:set>
    </c:if>
    <c:if test="${order.status == 1}">
    <div class="cart-inner" style="margin-bottom: 30px;overflow:auto    ">
        <div class="cart-thead clearfix" style="border-bottom: 1px solid #ddd;">
            <div class="column t-goods" style="width: 170px;"><strong>订单号:${order.orderId}</strong></div>
            <%--<div class="column t-inventory" style="float: right;"><strong>总额:￥<fmt:formatNumber value="${totalPrice/100}" groupingUsed="false" maxFractionDigits="2"  minFractionDigits="2"></fmt:formatNumber></strong></div>--%>
        </div>
        <div id="product-list" class="cart-tbody">
            <!-- ************************商品开始********************* -->
            <c:forEach items="${order.orderproducts}" var="orderproduct">
                <c:set var="totalPrice" value="${totalPrice + orderproduct.price * orderproduct.num}"/>
                <table style="width: 100%;margin-top: 10px;border-bottom:1px solid #ddd;">
                    <tr>
                        <td style="width: 25%;text-align: center;padding-bottom:10px;">
                            <a href="http://www.youyijia.com/product/${orderproduct.productId }.html" target="_blank">
                                <img clstag="clickcart|keycount|xincart|p-imglistcart" src="${orderproduct.imagePath}" alt="${orderproduct.title}" width="52" height="52">
                            </a>
                        </td>
                        <td style="width: 25%;padding-bottom:10px;">
                            <a href="http://product.youyijia.com/product/${orderproduct.productId }.html" clstag="clickcart|keycount|xincart|productnamelink" target="_blank">${orderproduct.title}</a>
                        </td>
                        <td style="width: 25%;text-align: center;padding-bottom:10px;">
                            x${orderproduct.num }
                        </td>
                        <td style="width: 25%;text-align: center;padding-bottom:10px;">
                            <strong>￥<fmt:formatNumber value="${orderproduct.price/100}" groupingUsed="false" maxFractionDigits="2"  minFractionDigits="2"></fmt:formatNumber></strong>
                        </td>
                    </tr>
                </table>
            </c:forEach>
            <table style="width: 100%;height: 29px;background-color: #F7F7F7;line-height: 29px;">
                <tr>
                    <td style="width: 25%;text-align: center;padding-bottom:10px;"></td>
                    <td style="width: 25%;text-align: center;padding-bottom:10px;"><strong>支付方式:货到付款</strong></td>
                    <td style="width: 25%;text-align: center;padding-bottom:10px;"><strong>收件人:${order.buyerNick}</strong></td>
                    <td style="width: 25%;text-align: center;padding-bottom:10px;"><strong>总额:￥<fmt:formatNumber value="${totalPrice/100}" groupingUsed="false" maxFractionDigits="2"  minFractionDigits="2"></fmt:formatNumber></td>
                </tr>
            </table>
        </div><!-- product-list结束 -->
    </div>
    </c:if>
</c:forEach>
<c:if test="${total == num}">
    <div class="cart-inner" style="margin-bottom: 30px;">
        <div class="cart-thead clearfix" style="border-bottom: 1px solid #ddd;">
        </div>
        <div id="product-list" class="cart-tbody">
            <div style="text-align: center;font-size: 20px;margin: 40px 0;"><a href="/">您还没有提交的订单，赶紧去剁手吧！！！</a></div>
        </div>
    </div>
</c:if>
</div>
</div>
<!--推荐位html修改处-->


<script type="text/javascript" src="/mycart/js/base-v1.js"></script>
<!-- footer start -->
<jsp:include page="../commons/footer.jsp" />
<!-- footer end -->

<!-- 购物车相关业务 -->
<script type="text/javascript" src="/mycart/js/cart.js"></script>
<script type="text/javascript" src="/mycart/js/jquery.price_format.2.0.min.js"></script>

</html>