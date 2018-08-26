package com.lambert.manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambert.common.pojo.DataTableJSONResponse;
import com.lambert.manager.pojo.Content;
import com.lambert.manager.service.ContentService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("page")
public class ContentController {

    @Autowired
    private ContentService contentService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping(value="content",method= RequestMethod.GET)
    @ResponseBody
    public String queryById(
            @RequestParam(value = "aodata", required = false) String aodata,
            @RequestParam(value = "categoryid", required = false) Long categoryid) {
        DataTableJSONResponse dataTableJSONResponse = new DataTableJSONResponse();

        try {
            String sEcho = "0";//记录访问次数
            int iDisplayStart = 0; //从第0条件
            int iDisplayLength = 10; //每页显示的记录数

//            if (aodata!=null) {
//                JSONArray jsonArray = new JSONArray(aodata);
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jsonObject = (JSONObject)jsonArray.get(i);
//
//                    if (jsonObject.get("name").equals("sEcho")) {
//                        sEcho = jsonObject.get("value").toString();
//                    }
//
//                    if (jsonObject.get("name").equals("iDisplayStart")) {
//                        iDisplayStart = jsonObject.getInt("value");
//                    }
//
//                    if (jsonObject.get("name").equals("iDisplayLength")) {
//                        iDisplayLength = jsonObject.getInt("value");
//                    }
//                }
//            }

            Content content = new Content();

            if (categoryid !=null) {
                content.setCategoryid(categoryid);
            }

            Integer queryByWhereCount = this.contentService.queryByWhereCount(content);
            List<Content> aaData = this.contentService.queryByWhere(content);

            //分页
//            if (queryByWhereCount > iDisplayLength) {
//                if ((queryByWhereCount-iDisplayStart)>iDisplayLength) {
//                    aaData = aaData.subList(iDisplayStart, iDisplayStart+iDisplayLength);
//                }else{
//                    aaData = aaData.subList(iDisplayStart, iDisplayStart + (queryByWhereCount-iDisplayStart));
//                }
//            }

//            dataTableJSONResponse =  new DataTableJSONResponse(sEcho, queryByWhereCount, queryByWhereCount, aaData);
            return MAPPER.writeValueAsString(aaData);
//            return ResponseEntity.status(HttpStatus.OK).body(dataTableJSONResponse);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 添加内容
     */
    @RequestMapping(value="content",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> saveContent(Content content){
        try {
            this.contentService.saveSelective(content);
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }

    /**
     * 删除内容
     */
    @RequestMapping(value="content",method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Boolean> deleteContent(String ids){
        try {
            List<Object> deleteIds = new ArrayList<Object>();
            String[] split = ids.split(",");
            for (int i = 0; i < split.length; i++) {
                deleteIds.add(Long.parseLong(split[i]));
            }
            this.contentService.deleteByIds(deleteIds);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }

}
