package cn.cinling.javaadmin.http.controller;

import cn.cinling.javacommon.utils.TestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "test")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "test")
    public String Test() {
        return TestUtil.Test();
    }
}
