package cn.cinling.javawxapi.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "test")
public class TestController extends BaseController {

    @ResponseBody
    @RequestMapping(value = "test")
    public String Test() {
        return "wxapi/test/test";
    }
}
