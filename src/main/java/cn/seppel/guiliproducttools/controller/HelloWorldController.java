package cn.seppel.guiliproducttools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloWorldController {
    @RequestMapping(value = "/hello/index", method = RequestMethod.GET)
    public String helloWorld() {
        return "Hello World!";
    }
}
