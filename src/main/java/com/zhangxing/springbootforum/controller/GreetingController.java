package com.zhangxing.springbootforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/8 17:11
 */
@Controller
public class GreetingController {

    @GetMapping("/")
    public String greeting() {
        return "index";
    }
}
