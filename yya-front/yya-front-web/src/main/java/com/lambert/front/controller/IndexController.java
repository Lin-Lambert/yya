package com.lambert.front.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambert.cart.service.HttpClientUtils;
import com.lambert.manager.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private HttpClientUtils httpClientUtils;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Value("${ATGUIGU_MANAGER_CONTENT_BIGAD_URL}")
    private String ATGUIGU_MANAGER_CONTENT_BIGAD_URL;

    @Value("${ATGUIGU_MANAGER_CONTENT_SMALLAD_URL}")
    private String ATGUIGU_MANAGER_CONTENT_SMALLAD_URL;

    @Value("${ATGUIGU_MANAGER_CONTENT_MIAOSHA_URL}")
    private String ATGUIGU_MANAGER_CONTENT_MIAOSHA_URL;
    /**
     * 显示首页
     */
    @RequestMapping(value="index")
    public ModelAndView toIndex(){
        ModelAndView mv = new ModelAndView("index");
        try {
            //大广告位
            String jsonData = this.httpClientUtils.doget(ATGUIGU_MANAGER_CONTENT_BIGAD_URL);

            if (jsonData!=null) {
                JavaType valueType = MAPPER.getTypeFactory().constructCollectionType(List.class, Content.class);
                List<Content> bigADList = MAPPER.readValue(jsonData, valueType);
                mv.addObject("bigADList", bigADList);
            }

            //小广告位
            String jsonData2 = this.httpClientUtils.doget(ATGUIGU_MANAGER_CONTENT_SMALLAD_URL);
            if (jsonData2!=null) {
                JavaType valueType = MAPPER.getTypeFactory().constructCollectionType(List.class, Content.class);
                List<Content> smallADList = MAPPER.readValue(jsonData2, valueType);
                mv.addObject("smallADList", smallADList);
            }

            //秒杀
            String jsonData3 = this.httpClientUtils.doget(ATGUIGU_MANAGER_CONTENT_MIAOSHA_URL);
            if (jsonData3!=null) {
                JavaType valueType = MAPPER.getTypeFactory().constructCollectionType(List.class, Content.class);
                List<Content> seckillList = MAPPER.readValue(jsonData3, valueType);
                mv.addObject("seckillList", seckillList);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mv;
    }

}
