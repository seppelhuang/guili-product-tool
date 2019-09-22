package cn.seppel.guiliproducttools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description:
 * @author: Huangsp
 * @create: 2019-09-22
 **/
@Controller
public class ProductController {
    @RequestMapping(value = "/product/tool", method = RequestMethod.GET)
    public String upload() {
        return "product/product-tool";
    }
}
