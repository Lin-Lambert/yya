package com.lambert.manager.controller;

import com.lambert.manager.pojo.Product;
import com.lambert.manager.service.ProductService;
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
@RequestMapping("page")
public class ApiProductController {

    @Autowired
    private ProductService productService;

    /**
     * 根据商品id查询商品信息
     */
    @RequestMapping(value="api/product/{id}")
    @ResponseBody
    public ResponseEntity<Product> queryProductById(@PathVariable("id") Long id){
        try {
            Product product = this.productService.queryById(id);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 查询所有商品数据
     * http://manage.atguigu.com/page/api/product
     */
    @RequestMapping(value="api/product",method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Product>> queryAll(){

        try {
            List<Product> list = this.productService.queryAll();

            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 查询多个商品数据
     */
    @RequestMapping(value = "api/products/{pids}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Product>> queryProductByIds(@PathVariable("pids") String pids) {

        try {
            List<Product> list =  this.productService.queryByIds(pids);

            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
