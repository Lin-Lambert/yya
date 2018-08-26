package com.lambert.cart.bean;

public class HttpResult {
    
    //状态码
    private Integer code;
    
    //主体内容
    private String body;
    
    

    public HttpResult() {
        super();
    }

    public HttpResult(Integer code, String body) {
        super();
        this.code = code;
        this.body = body;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpResult [code=" + code + ", body=" + body + "]";
    }
    
}
