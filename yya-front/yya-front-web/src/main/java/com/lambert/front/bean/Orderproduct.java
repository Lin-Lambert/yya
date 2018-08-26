package com.lambert.front.bean;

public class Orderproduct {
	
	private Long productId;//商品id
	private String orderId;//订单id
	private Integer num;//商品购买数量
	private String title;//商品标题
	private Long price;//商品单价
	private Long totalFee;//商品总价
	private String imagePath;//图片路径
	
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }
    public Long getTotalFee() {
        return totalFee;
    }
    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
	
	
}
