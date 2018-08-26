package com.lambert.manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambert.common.pojo.DataTableJSONResponse;
import com.lambert.manager.pojo.Product;
import com.lambert.manager.service.HttpClientUtils;
import com.lambert.manager.service.ProductService;
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
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private HttpClientUtils httpClientUtils;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @ResponseBody
    @RequestMapping(value = "product", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteByIds(@RequestParam("ids") String ids) {
        try {
            List<Object> list = new ArrayList<>();
            String[] split = ids.split(",");
            for (String s : split) {
                list.add(Long.parseLong(s));
            }
            productService.deleteByIds(list);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }

    @RequestMapping(value = "product", method = RequestMethod.GET)
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

            Product product = new Product();

            if (cid != null) {
                product.setCid(cid);
            }

            Integer queryByWhereCount = this.productService.queryByWhereCount(product);
            List<Product> aaData = this.productService.queryByWhere(product);

            //分页
//            if (queryByWhereCount > iDisplayLength) {
//                if ((queryByWhereCount - iDisplayStart) > iDisplayLength) {
//                    aaData = aaData.subList(iDisplayStart, iDisplayStart + iDisplayLength);
//                } else {
//                    aaData = aaData.subList(iDisplayStart, queryByWhereCount);
//                }
//            }
//            dataTableJSONResponse = new DataTableJSONResponse(sEcho, queryByWhereCount, queryByWhereCount, aaData);
            return MAPPER.writeValueAsString(aaData);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            return null;
    }

    @ResponseBody
    @RequestMapping(value = "product", method = RequestMethod.POST)
    public ResponseEntity<Boolean> saveProduct(Product product, String editorValue) {
        try {
//            productService.save(product);
            productService.saveProductAndDeac(product, editorValue);
            httpClientUtils.doget("http://localhost:8180/solr/dataimport?command=full-import");
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }

}
