package com.lambert.manager.service;

import com.lambert.manager.bean.HttpResult;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HttpClientUtils {

    @Autowired(required = false)
    private CloseableHttpClient httpclient;

    @Autowired(required = false)
    private RequestConfig config;

    /**
     * 没有带参数doget
     *
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String doget(String url) throws Exception {

        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);

        // 设置请求配置信息
        httpGet.setConfig(config);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                return content;
            }
        } finally {
            if (response != null) {
                response.close();
            }
            // httpclient.close();
        }
        return null;
    }

    /**
     * 带有参数的Doget请求
     */
    public String doget(String url, Map<String, Object> map) throws Exception {

        // 定义请求的参数
        URIBuilder uriBuilder = new URIBuilder(url);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
        }
        URI uri = uriBuilder.build();

        return this.doget(uri.toString());
    }

    /**
     * 带有参数的dopost请求
     */
    public HttpResult dopost(String url,Map<String, Object> map) throws Exception {

        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);

        // 设置2个post参数，一个是scope、一个是q
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);

        if (map!=null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(),entry.getValue().toString()));
            }
        }

        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters,"UTF-8");
        // 将请求实体设置到httpPost对象中
        httpPost.setEntity(formEntity);

        //设置请求参数信息
        httpPost.setConfig(config);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String body = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (body.length() == 0) {
                    return new HttpResult(response.getStatusLine().getStatusCode(), null);
                }
                return new HttpResult(response.getStatusLine().getStatusCode(), body);
            }
        } finally {
            if (response != null) {
                response.close();
            }
//            httpclient.close();
        }

        return new HttpResult(response.getStatusLine().getStatusCode(), null);
    }

    /**
     * 没有带参数dopost请求
     */
    public HttpResult dopost(String url) throws Exception{
        return this.dopost(url, null);
    }


    //TODO 同学完成
    //delete put  postjson  getjson....

    /**
     * 指定POST请求
     *
     * @param url
     * @param param 请求参数
     * @return 状态码和请求的body
     * @throws IOException
     */
    public HttpResult doPostJson(String url, String json) throws IOException {
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.config);
        if (json != null) {
            // 构造一个字符串的实体
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(stringEntity);
        }

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                String body = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (body.length() == 0) {
                    return new HttpResult(response.getStatusLine().getStatusCode(), null);
                }
                return new HttpResult(response.getStatusLine().getStatusCode(), body);
            }
            return new HttpResult(response.getStatusLine().getStatusCode(), null);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * 执行PUT请求
     *
     * @param url
     * @param param 请求参数
     * @return 状态码和请求的body
     * @throws IOException
     */
    public HttpResult doPut(String url, Map<String, Object> param) throws IOException {
        // 创建http POST请求
        HttpPut httpPut = new HttpPut(url);
        httpPut.setConfig(config);
        if (param != null) {
            // 设置post参数
            List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造一个form表单式的实体,并且指定参数的编码为UTF-8
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
            // 将请求实体设置到httpPost对象中
            httpPut.setEntity(formEntity);
        }

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpPut);
            if (response.getStatusLine().getStatusCode() == 200) {
                String body = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (body.length() == 0) {
                    return new HttpResult(response.getStatusLine().getStatusCode(), null);
                }
                return new HttpResult(response.getStatusLine().getStatusCode(), body);
            }
            return new HttpResult(response.getStatusLine().getStatusCode(), null);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * 执行PUT请求
     *
     * @param url
     * @return 状态码和请求的body
     * @throws IOException
     */
    public HttpResult doPut(String url) throws IOException {
        return this.doPut(url, null);
    }

    /**
     * 执行DELETE请求,通过POST提交，_method指定真正的请求方法
     *
     * @param url
     * @param param 请求参数
     * @return 状态码和请求的body
     * @throws IOException
     */
    public HttpResult doDelete(String url, Map<String, Object> param) throws Exception {
        param.put("_method", "DELETE");
        return this.dopost(url, param);
    }

    /**
     * 执行DELETE请求(真正的DELETE请求)
     *
     * @param url
     * @return 状态码和请求的body
     * @throws IOException
     */
    public HttpResult doDelete(String url) throws Exception {
        // 创建http DELETE请求
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setConfig(this.config);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpDelete);
            if (response.getStatusLine().getStatusCode() == 200) {
                String body = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (body.length() == 0) {
                    return new HttpResult(response.getStatusLine().getStatusCode(), null);
                }
                return new HttpResult(response.getStatusLine().getStatusCode(), body);
            }
            return new HttpResult(response.getStatusLine().getStatusCode(), null);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

}
