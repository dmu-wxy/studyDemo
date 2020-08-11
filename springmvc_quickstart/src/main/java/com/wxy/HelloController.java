package com.wxy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.RequestWrapper;

@Controller
public class HelloController {
    @RequestMapping(path = "/hello") //指定方法对应URL
    public String helloHandler(){
        System.out.println("Hello SpringMVC!!!");
        return "success"; //指定跳转的视图地址，被ViewResolver解析为 /WEB-INF/pages/success.jsp
    }
}
