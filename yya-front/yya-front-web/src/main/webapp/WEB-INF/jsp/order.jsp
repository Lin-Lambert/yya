<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>订单结算页 -商品商城</title>
    <!--结算页面样式-->
    <link type="text/css" rel="stylesheet" href="/mycart/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="/mycart/css/purchase.checkout.css"/>
    <script type="text/javascript" src="/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="/mycart/js/jquery.checkout.js"></script>
    <script type="text/javascript" src="/mycart/js/base-v1.js"></script>
    <script type="text/javascript" src="/mycart/js/order.common.js"></script>
    <script type="text/javascript" src="/mycart/js/youyijia.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/layer/2.4/layer.js"></script>
    <script>
        $(function () {
            $(".deleteBtn").click(function () {
                var userShippingId = $(this).attr("userShippingId");
                layer.confirm('这个收货地址',function() {
                    window.location = "http://www.youyijia.com/order/doDeleteShipping.html?pids=${param.pids}&userShippingId=" + userShippingId;
                }, function() {
                    return false;
                });
            });
        });
    </script>
</head>
<body id="mainframe">
<!--shortcut start-->
<jsp:include page="../commons/shortcut.jsp"/>
<!--shortcut end-->
<form id="orderForm" class="hide">
    <input type="hidden" name="paymentType" value="1"/>
    <c:forEach items="${products}" var="product" varStatus="i">
        <c:set var="totalPrice"  value="${ totalPrice + (product.productPrice * product.num)}"/>
        <c:set var="totalNum"  value="${ totalNum + product.num}"/>
        <input type="hidden" name="orderproducts[${i.index}].productId" value="${product.productId}"/>
        <input type="hidden" name="orderproducts[${i.index}].num" value="${product.num}"/>
        <input type="hidden" name="orderproducts[${i.index}].price" value="${product.productPrice}"/>
        <input type="hidden" name="orderproducts[${i.index}].totalFee" value="${product.productPrice}"/>
        <input type="hidden" name="orderproducts[${i.index}].title" value="${product.productTitle}"/>
        <input type="hidden" name="orderproducts[${i.index}].imagePath" value="${product.productImage}"/>
    </c:forEach>
    <c:set var="totalPrice" value="${totalPrice}"/>
    <input type="hidden" name="payment"
           value="<fmt:formatNumber groupingUsed="false" maxFractionDigits="2" minFractionDigits="2" value="${totalPrice/100 }"/>"/>
</form>
<div class="w w1 header clearfix">
    <div id="logo"><a href="http://www.youyijia.com/"><img src="/mycart/images/reglogo.png" alt="商品商城"/></a></div>
    <div class="progress clearfix">
        <ul class="progress-2">
            <li class="s1"><b></b>1.我的购物车</li>
            <li class="s2"><b></b>2.填写核对订单信息</li>
            <li class="s3">3.成功提交订单</li>
        </ul>
    </div>
</div>
<div class="w m2"><a name="consigneeFocus"></a>
    <div id="checkout">
        <div class="mt">
            <h2>填写并核对订单信息</h2>
        </div>
        <div id="wizard" class="checkout-steps">
            <div id="step-1" class="step step-complete">
                <div class="step-title">
                    <div id="save-consignee-tip" class="step-right">
                    </div>
                    <strong id="consigneeTitleDiv">收货人信息</strong>
                    <span class="step-action" id="consignee_edit_action"><a href="/order/toAddShipping.html?pids=${param.pids}">[新增收货地址]</a></span>
                </div>
                <div class="step-content">
                    <div id="consignee" class="sbox-wrap">
                        <div class="sbox">
                            <div class="s-content">
                                <table style="width: 100%;">
                                    <c:if test="${empty shippings}">
                                        <tr>
                                            <td><div style="text-align: center;font-size: 20px;"><a href="/order/toAddShipping.html?pids=${param.pids}">请添加收货地址</a></div></td>
                                        </tr>
                                    </c:if>
                                    <c:forEach items="${shippings}" var="shipping">
                                        <tr>
                                            <td><input type="radio" name="shippingRadio" userShippingId="${shipping.id}"/></td>
                                            <td style="margin-top: 10px">${shipping.name}</td>
                                            <td style="margin-top: 10px">${shipping.phone}</td>
                                            <td style="margin-top: 10px">${shipping.state}</td>
                                            <td style="margin-top: 10px">${shipping.city}</td>
                                            <td style="margin-top: 10px">${shipping.district}</td>
                                            <td style="margin-top: 10px">${shipping.address}</td>
                                            <td><a href="/order/toEditShipping.html?pids=${param.pids}&userShippingId=${shipping.id}" style="color:#005ea7">编辑</a></td>
                                            <td><a href="javascipt:void(0);" class="deleteBtn" userShippingId="${shipping.id}" style="color:#005ea7">删除</a></td>
                                            <input type="hidden" name="ordershipping.receiverName" value="${shipping.name}"/>
                                            <input type="hidden" name="ordershipping.receiverPhone" value="${shipping.phone}"/>
                                            <input type="hidden" name="ordershipping.receiverMobile" value="${shipping.mobile}"/>
                                            <input type="hidden" name="ordershipping.receiverState" value="${shipping.state}"/>
                                            <input type="hidden" name="ordershipping.receiverCity" value="${shipping.city}"/>
                                            <input type="hidden" name="ordershipping.receiverDistrict" value="${shipping.district}"/>
                                            <input type="hidden" name="ordershipping.receiverAddress" value="${shipping.address}"/>
                                            <input type="hidden" name="ordershipping.receiverZip" value="${shipping.zip}"/>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div><!--@end div#consignee-->
                </div>
            </div>

            <div id="step-4" class="step step-complete">
                <div class="step-title">
                    <div id="save-consignee-tip" class="step-right">
                    </div>
                    <strong id="consigneeTitleDiv">商品 商品价 优惠 数量 库存状态</strong>
                </div>
                <div class="step-title hide"><a href="http://cart.jd.com/cart/cart.html" id="cartRetureUrl"
                                                class="return-edit">返回修改购物车</a><strong>商品清单</strong></div>
                <div class="step-content">
                    <div id="part-order" class="sbox-wrap">
                        <div class="sbox">
                            <div id="order-cart">
                                <div class="order-review">
                                    <!--商品清单展示-->
                                    <span id="span-skulist">
                                        <!--**********商品清单内容列表开始************-->
<div class="review-body">
	 		<div class="review-block review-present">
		 <div class="block-header">
		    <table class="order-table">
			  <tbody>
			    <tr class="hide">
				  <td class="fore1"><b></b>
					<strong>
					   					      						             已购满100.00元
						  						  						  	     ，您可以返回购物车领取赠品
						  					   					</strong>
				  </td>
				  <td class="p-price">
					<strong>
						￥<fmt:formatNumber groupingUsed="false" maxFractionDigits="2" minFractionDigits="2"
                                           value="${product.price/100 }"/>
					</strong>
				  </td>
				  <td class="p-promotion">&nbsp;</td>
				  <td class="fore2"></td>
				  <td class="fore2">&nbsp;</td>
				</tr>
			   </tbody>
		    </table>
	     </div>
		       </div>
    <!-- 此处置空是必须的  -->
        <div class="review-tbody">
            <c:forEach items="${products}" var="product">


		        <table class="order-table" style="width: 100%;border-bottom: 1px solid #ddd;">
                    <tbody>
                          <tr>
                              <td class="fore1" style="width: 12%;">
                               <div class="p-goods">
                                  <div class="p-img"><a href="http://www.youyijia.com/product/${product.productId}.html"
                                                        target='_blank'><img width="52" height="52" src="${product.productImage}"></a></div>
                                   </div>
                            </td>
                              <td style="width: 20%;">
                                  <div class="p-name">
                                    <a href="http://www.youyijia.com/product/${product.productId}.html" target='_blank'>
                                        ${product.productTitle }
                                    </a>
                                </div>
                              </td>
                              <td style="width: 16.6%;">
                                  <div class="p-more">商品编号：${product.productId}<br/>
                                        <!-- icon图标预留-->
                                        <span id="promise_1057746" class="promise411"></span>
                                    </div>
                              </td>
                              <td class="p-price" style="width: 16.6%;">
                                  <strong>￥<fmt:formatNumber groupingUsed="false" maxFractionDigits="2"  minFractionDigits="2" value="${product.productPrice / 100 }"/></strong>
                              </td>
                              <td class="fore2" style="width: 16.6%;">x ${product.num}</td>
                              <td class="fore2 p-inventory" skuId="1057746" style="width: 16.6%;">有货</td>
                           </tr>
                    </tbody>
                </table>
            </c:forEach>
        </div>


    <!--********滿贈套装結束*********-->
</div>
                                        <!--**********商品清单内容列表结束************-->
                                        </span>
                                    <div class="order-summary" style="margin-top: 20px;">
                                        <div class="summary-form fl">
                                            <div class="safe-tip" style="display:none" id="safeVerciryPromptPart">&nbsp;&nbsp;&nbsp;&nbsp;为保障您的账户资金安全，余额暂时不可用，请先<a
                                                    target="_blank"
                                                    href="http://safe.jd.com/user/paymentpassword/safetyCenter.action">开启支付密码</a>
                                            </div>
                                        </div>
                                        <!--  预售 计算支付展现方式 begin -->
                                        <div class="statistic fr">
                                            <div class="list">
                                                <span>
                                                    <em id="span-skuNum" style="color: red;" v="<fmt:formatNumber groupingUsed="false" maxFractionDigits="2" minFractionDigits="2" value="${totalNum}"/>">${totalNum}</em> 件商品，总商品金额：
                                                </span>
                                                <em class="price" id="warePriceId" v="<fmt:formatNumber groupingUsed="false" maxFractionDigits="2" minFractionDigits="2" value="${totalPrice/100 }"/>">
                                                    ￥<fmt:formatNumber groupingUsed="false" maxFractionDigits="2" minFractionDigits="2" value="${totalPrice/100 }"/>
                                                </em>
                                            </div>
                                            <div class="list" style="padding-left: 70px;">
                                                <span>应付总额：</span>
                                                <em id="sumPayPriceId" class="price">
                                                    ￥<fmt:formatNumber groupingUsed="false" maxFractionDigits="2" minFractionDigits="2" value="${totalPrice/100 }"/>
                                                </em>
                                            </div>
                                        </div>
                                        <div class="span clr"></div>

                                    </div><!--@end div.order-summary-->
                                </div>
                            </div><!--@end div#order-cart-->
                        </div>
                        <!-- 验证码 -->
                        <div class="check-code group" id="checkCodeDiv"></div>
                        <span class="clr"></span>
                    </div><!--@end div#part-order-->
                    <div id="checkout-floatbar" class="checkout-buttons group">
                        <div class="inner">
                            <style type="text/css">.checkout-buttons .checkout-submit {
                                background-color: #e00;
                                position: relative;
                                line-height: 36px;
                                overflow: hidden;
                                color: #fff;
                                font-weight: bold;
                                font-size: 16px;
                            }

                            .checkout-buttons .checkout-submit b {
                                position: absolute;
                                left: 0;
                                top: 0;
                                width: 135px;
                                height: 36px;
                                background: url(http://misc.360buyimg.com/purchase/trade/skin/i/btn-submit.jpg) no-repeat;
                                cursor: pointer;
                                overflow: hidden;
                            }

                            .checkout-buttons .checkout-submit:hover {
                                background-color: #EF494D;
                            }

                            .checkout-buttons .checkout-submit:hover b {
                                background-position: 0 -36px;
                            }

                            .checkout-buttons .checkout-submit-disabled {
                                background-color: #ccc;
                                position: relative;
                                line-height: 36px;
                                font-weight: bold;
                                font-size: 16px;
                                cursor: not-allowed;
                            }

                            .checkout-buttons .checkout-submit-disabled b {
                                position: absolute;
                                left: 0;
                                top: 0;
                                width: 135px;
                                height: 36px;
                                background: url(http://misc.360buyimg.com/purchase/trade/skin/i/btn-disabled.png) no-repeat;
                                cursor: not-allowed;
                            }</style>
                            <!--input type="submit"  class="checkout-submit" value="" id="order-submit" onclick="javascript:submit_Order();"/-->
                            <button type="submit" class="checkout-submit" id="order-submit"
                                    onclick="javascript:submit_Order();" style="width:135px;margin-left: 854px;">
                                提交订单
                                <b></b>
                            </button>
                            <div class="checkout-submit-tip" id="changeAreaAndPrice" style="display: none;">
                                由于地址更换，价格可能发生变化，请核对后再提交订单
                            </div>
                            <div style="display:none" id="factoryShipCodShowDivBottom" class="dispatching">
                                部分商品货到付款方式：先由商品配送“提货单”并收款，然后厂商发货。
                            </div>
                        </div>
                        <span id="submit_message" style="display:none" class="submit-error"></span>
                        <div class="submit-check-info" id="submit_check_info_message" style="display:none"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- freight 弹窗,不放到orderInfo中弹框 就会串行-->
<div class="bt bt-w freight-tooltip hide" id="transport">
</div>
<div class="w">

    <!-- links start -->
    <jsp:include page="../commons/footer-links.jsp"></jsp:include>
    <!-- links end -->
</div><!-- footer end -->
<script type="text/javascript" src="/mycart/js/order.js"></script>
<script type="text/javascript" src="/mycart/js/base-2011.js"></script>
<script type="text/javascript" src="/mycart/js/lib-v1.js"></script>
<script type="text/javascript" src="/mycart/js/jTips.js"></script>
<script type="text/javascript" src="/mycart/js/calendar.js"></script>
<script type="text/javascript">

    $('#bill-tip-btn').Jtips({//随点随帮tip弹出
        "content": '<a target="_blank" href="http://help.jd.com/help/question-61.html#kjfpf">如何区分商品销售和第三方卖家销售的商品?</a>',
        "close": true,
        "position": 'bottom'
    });
    //<![CDATA[
    $(function () {
        $("#checkout-floatbar").jSticky();
    });
    //]]>
</script>
<script type="text/javascript">
    //<![CDATA[
    var couponToggle = (function () {
        var obj = $('[data-bind="coupon"]'),
            tObj = obj.find(".item");

        var init = function () {
            tObj.each(function () {
                var that = $(this);
                var toggler = $(this).find(".toggler");
                var toggled = false;

                toggler.bind("click", function (e) {
                    e.preventDefault();
                    toggled = !toggled;

                    toggler.parent().parent()[toggled ? "addClass" : "removeClass"]("toggle-active");

                    that.find(".toggle-wrap")[toggled ? "removeClass" : "addClass"]("hide").css("display", toggled ? "block" : "none");
                });
            });
        };

        return {
            init: init
        };
    })();


    var invoiceMore = (function () {
        var expandHolder = $("#invoice-list"),
            expandHandle = $("#invoice-more-btn"),
            item = expandHolder.find(".item-fore");
        expand = false;

        var init = function () {
            expandHandle.bind("click", function () {
                expand = !expand;

                item[expand ? "removeClass" : "addClass"]("hide").css("display", expand ? "block" : "none");


                expandHandle.removeClass(expand ? "select-expand" : "select-collapse").addClass(expand ? "select-collapse" : "select-expand").find("span").html(expand ? "\u6536\u8D77" : "\u66F4\u591A\u5E38\u7528\u5730\u5740");

                if (expand) {

                } else {

                }
            });
        };

        return {
            init: init
        };
    })();
    //]]>

    //防止窗口变换，弹窗错位
    $(window).resize(function () {
        var obj = $("#freightSpan");
        if ($("#transport").html() != null) {
            $("#transport").css({
                position: "absolute",
                top: obj.offset().top + "px",
                left: (obj.offset().left - 345) + "px"
            })
        }
    });
</script>
</body>
</html>