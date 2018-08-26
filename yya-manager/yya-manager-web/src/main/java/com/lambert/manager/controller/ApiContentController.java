package com.lambert.manager.controller;

import com.lambert.manager.pojo.Content;
import com.lambert.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value="page/api")
public class ApiContentController {
    
    @Autowired
    private ContentService contentService;

    /**
     * 根据内容分类id查询广告商品信息
     * http://manage.atguigu.com/page/api/content/{categoryId}
     */
    @RequestMapping(value="content/{categoryId}",method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Content>> queryByCategoryId(@PathVariable("categoryId") Long categoryId){
        try {
            Content content = new Content();
            content.setCategoryid(categoryId);
            List<Content> list = this.contentService.queryByWhere(content);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
