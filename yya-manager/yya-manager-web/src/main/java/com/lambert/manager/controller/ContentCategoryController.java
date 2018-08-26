package com.lambert.manager.controller;

import com.lambert.manager.pojo.Contentcategory;
import com.lambert.manager.service.ContentcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("page")
public class ContentCategoryController {

    @Autowired
    private ContentcategoryService contentcategoryService;

    /**
     * 显示内容分类菜单
     */
    @RequestMapping(value="contentcategory",method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Contentcategory>> queryAll(){

        try {
            List<Contentcategory> list = this.contentcategoryService.queryAll();

            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 添加分类
     */
    @RequestMapping(value="contentcategory",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> saveContentcategory(Contentcategory contentcategory){
        try {
            this.contentcategoryService.saveSelective(contentcategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }

    /**
     * 修改分类
     */
    @RequestMapping(value="contentcategory",method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Boolean> updateContentcategory(Contentcategory contentcategory){
        try {
            this.contentcategoryService.updateSelective(contentcategory);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }

    /**
     * 批量删除
     */
    @RequestMapping(value="contentcategory",method=RequestMethod.DELETE)
    @ResponseBody
    public  ResponseEntity<Boolean> deleteContentcategoryById(Contentcategory contentcategory){

        try {
            this.contentcategoryService.deleteContentcategoryById(contentcategory);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }
}
