package cn.cinling.javaadmin.http.controller;

import cn.cinling.javacommon.database.entity.AdminUserEntity;
import cn.cinling.javacommon.database.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @ResponseBody
    @RequestMapping(value = "test", produces = "application/json;charset=UTF-8")
    public String Test() {
        StringBuilder test = new StringBuilder();

        List<AdminUserEntity> adminList =  adminUserMapper.SelectAll();

        for (AdminUserEntity adminUserEntity : adminList) {
            test.append(adminUserEntity.getId());
        }

        return test.toString();
    }

    @RequestMapping(value = "index-page")
    public String IndexPage() {
        return "test/index";
    }
}
