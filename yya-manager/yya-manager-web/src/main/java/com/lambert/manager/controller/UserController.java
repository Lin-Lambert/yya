package com.lambert.manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambert.common.pojo.DataTableJSONResponse;
import com.lambert.manager.pojo.Product;
import com.lambert.manager.pojo.User;
import com.lambert.manager.service.UserService;
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

import java.util.List;

@Controller
@RequestMapping(value = "page")
public class UserController {

    @Autowired
    private UserService userService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping(value = "user", method = RequestMethod.GET)
    @ResponseBody
    public String queryByCid(
            @RequestParam(value = "aodata", required = false) String aodata,
            @RequestParam(value = "cid", required = false) Long cid) {
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
            Integer queryByWhereCount = this.userService.queryByWhereCount(null);
            List<User> aaData = this.userService.queryByWhere(null);

            //分页
//            if (queryByWhereCount > iDisplayLength) {
//                if ((queryByWhereCount-iDisplayStart)>iDisplayLength) {
//                    aaData = aaData.subList(iDisplayStart, iDisplayStart+iDisplayLength);
//                }else{
//                    aaData = aaData.subList(iDisplayStart, queryByWhereCount);
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

}
