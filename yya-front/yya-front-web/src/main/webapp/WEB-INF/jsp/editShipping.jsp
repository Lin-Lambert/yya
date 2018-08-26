<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache,must-revalidate">
<title>编辑收货地址</title>
<link type="text/css" rel="stylesheet" href="/css/reg.css"/>
<script type="text/javascript" async="" src="/js/sdk.js"></script>
<script type="text/javascript" async="" src="/js/script.js"></script>
<script type="text/javascript" async="" src="/js/s.js"></script>
<script type="text/javascript" src="/js/jquery-1.12.4.js"></script>
  <script src="/js/distpicker.js" type="text/javascript" charset="utf-8"></script>
  <script src="/js/layer/2.4/layer.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="/css/tinyscrollbar.css"/>
<link type="text/css" rel="stylesheet" href="/css/saved_resource.css" source="widget"/>
  <script type="text/javascript">
      $(function () {
        $('#target').distpicker({
            province: '${userShipping.state}',
            city: '${userShipping.city}',
            district: '${userShipping.district}'
        });
        $("#btn-register").click(function(){
            var state = $("#state option:selected").val();
            if(state == ""||state == "---- 所在省 ----"){
              layer.alert("请选择省份");
              return false;
            }
            var city = $("#city option:selected").val();
            if(city == ""||city == "---- 所在市 ----"){
                layer.alert("请选择城市");
                return false;
            }
            var district = $("#district option:selected").val();
            if(district == ""||district == "---- 所在区 ----"){
                layer.alert("请选择区/县");
                return false;
            }
            $("#shipping-form").append("  <input type='hidden' name='state' value='"+ state +"'>");
            $("#shipping-form").append("  <input type='hidden' name='city' value='"+ city +"'>");
            $("#shipping-form").append("  <input type='hidden' name='district' value='"+ district +"'>");
            $("#shipping-form").submit();
        });
      })
  </script>
</head>
<body>
<div id="form-header" class="header">
  <div class="logo-con w clearfix"> <a href="/" class="logo"><img src="/images/reglogo.png" width="200" height="57" /></a>
    <div class="logo-title">编辑收货地址</div>
  </div>
</div>
<div class="container w">
  <div class="main clearfix" id="form-main">
    <div class="reg-form fl">
      <form action="/order/doEditShipping.html" id="shipping-form" method="post">
        <input type="hidden" name="pids" value="${param.pids}">
        <input type="hidden" name="id" value="${userShipping.id}">
        <div class="form-item">
          <label>收货人</label>
          <txt style="position: absolute; z-index: 2; line-height: 46px; margin-left: 20px; margin-top: 1px; font-size: 14px; font-family: &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;; color: rgb(204, 204, 204); display: inline;"> </txt>
          <input type="text" name="name" value="${userShipping.name}" id="form-pwd" class="field" maxlength="20" placeholder=" " default="&lt;i class=i-def&gt;&lt;/i&gt;建议使用字母、数字和符号两种及以上的组合，6-20个字符">
          <i class="i-status"></i>
        </div>
        <div class="input-tip"> <span></span> </div>
        <div class="form-item">
          <label>手机号码</label>
          <txt style="position: absolute; z-index: 2; line-height: 46px; margin-left: 20px; margin-top: 1px; font-size: 14px; font-family: &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;; color: rgb(204, 204, 204); display: inline;">  </txt>
          <input type="text" name="phone" value="${userShipping.phone}" id="form-equalTopwd" class="field" placeholder=" " maxlength="20">
          <i class="i-status"></i>
        </div>
        <div class="input-tip"> <span></span> </div>
        <div class="form-item form-item-account" id="form-item-account">
          <label>固定电话</label>
          <txt style="position: absolute; z-index: 2; line-height: 46px; margin-left: 20px; margin-top: 1px; font-size: 14px; font-family: &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;; color: rgb(204, 204, 204); display: inline;">   </txt>
          <input type="text" id="form-account" name="mobile" value="${userShipping.mobile}" class="field">
          <i class="i-status"></i> </div>
        <div class="input-tip"> <span></span> </div>
        <div class="form-item form-item-account" id="form-item-account">
          <label>所在地区</label>
          <div id="target" style="margin-top: 4px;">
            <select id="state" style="height: calc(2.25rem + 2px);border:none;border-bottom: 1px solid #ddd;color:#666666;margin-right: 8px"></select>
            <select id="city" style="height: calc(2.25rem + 2px);border:none;border-bottom: 1px solid #ddd;color:#666666;margin-right: 8px"></select>
            <select id="district" style="height: calc(2.25rem + 2px);border:none;border-bottom: 1px solid #ddd;color:#666666;"></select>
          </div>
          <i class="i-status"></i> </div>
        <div class="input-tip"> <span></span> </div>
        <div class="form-item form-item-account" id="form-item-account">
          <label>收货地址</label>
          <txt style="position: absolute; z-index: 2; line-height: 46px; margin-left: 20px; margin-top: 1px; font-size: 14px; font-family: &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;; color: rgb(204, 204, 204); display: inline;">   </txt>
          <input type="text" id="form-account" name="address" value="${userShipping.address}" class="field">
          <i class="i-status"></i> </div>
        <div class="input-tip"> <span></span> </div>
        <div class="form-item form-item-account" id="form-item-account">
          <label>邮政编码</label>
          <txt style="position: absolute; z-index: 2; line-height: 46px; margin-left: 20px; margin-top: 1px; font-size: 14px; font-family: &quot;Microsoft YaHei&quot;, &quot;Hiragino Sans GB&quot;; color: rgb(204, 204, 204); display: inline;">   </txt>
          <input type="text" id="form-account" name="zip" value="${userShipping.zip}" class="field">
          <i class="i-status"></i> </div>
        <div class="input-tip"> <span></span> </div>
        <div>
          <input type="button"class="btn-register" id="btn-register" value="提交" style="border: 2px solid #ddd;width: 130px;"/>
        </div>
      </form>
    </div>
  </div>
</div>
<div id="form-footer" class="footer w">
  <div class="links"> <a rel="nofollow" target="_blank" href="#"> 关于我们</a>| <a rel="nofollow" target="_blank" href="#">联系我们</a>| <a rel="nofollow" target="_blank" href="#">人才招聘</a>| <a rel="nofollow" target="_blank" href="#">商家入驻</a>| <a rel="nofollow" target="_blank" href="#">广告服务</a>| <a rel="nofollow" target="_blank" href="#">手机商品</a>| <a target="_blank" href="#">友情链接</a>| <a target="_blank" href="#">销售联盟</a>| <a href="#" target="_blank">商品社区</a>| <a href="#" target="_blank">商品公益</a>| <a target="_blank" href="#" clstag="pageclick|keycount|20150112ABD|9">English Site</a> </div>
  <div class="copyright"> Copyright&#169;2004-2016&nbsp;&nbsp;商品xxx.com&nbsp;版权所有 </div>
</div>
<script type="text/javascript">
$(function(){
	   $("#btn-register").click(function(){
	       if(checkInput()) {
	           $("#register-form").action("/user/doreg.html");
	       }else{
	           return false;
	       }

	   });
	   
	function checkInput(){

	        //判断用户名
	        if($("input[name=username]").val() == null || $("input[name=username]").val() == ""){
	            $("input[name=username]").focus();
	            return false;
	        }
	        //判断密码
	        if($("input[name=password]").val() == null || $("input[name=password]").val() == ""){
	            $("input[name=password]").focus();
	            return false;
	        }
	        //判断邮箱
	        if($("input[name=email]").val() == null || $("input[name=email]").val() == ""){
	            $("input[name=email]").focus();
	            return false;
	        }
	      	//判断手机
	        if($("input[name=phone]").val() == null || $("input[name=phone]").val() == ""){
	            $("input[name=phone]").focus();
	            return false;
	        }
	        return true;
	    }

	});
</script>
<a target="_blank" href="#" class="feedback" style="margin-top: -85px; position: fixed; right: 0px; bottom: 50%;"></a>
</body></html>