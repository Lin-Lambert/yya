package com.lambert.front.controller;

import com.lambert.manager.pojo.Product;
import com.lambert.front.service.ProductdescService;
import com.lambert.manager.pojo.Productdesc;
import com.lambert.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductdescService productdescService;
    /**
     * http://product.atguigu.com/1231214.html
     */
    @RequestMapping(value="{productId}",method=RequestMethod.GET)
    public ModelAndView queryProductById(@PathVariable("productId")Long productId){
        ModelAndView mv = new ModelAndView("product");

        Product product = this.productService.queryProductById(productId);
        Productdesc productdesc = this.productdescService.queryProductdescByProductId(productId);
        mv.addObject("product", product);
        mv.addObject("productdesc", productdesc);
        return mv;
    }

}
