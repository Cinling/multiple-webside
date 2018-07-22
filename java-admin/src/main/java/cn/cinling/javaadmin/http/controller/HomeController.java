//package cn.cinling.javacommon.http.controller;
//
//import cn.cinling.javaadmin.manager.AuthManager;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping(value = {"", "/", "home"})
//public class HomeController extends BaseController {
//    @RequestMapping(value = {"", "/", "index"})
//    public String Index() {
//        return "index";
//    }
//
//    @RequestMapping(value = {"get-menu"}, produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String GetMenu() {
//        return AuthManager.GetInstance().GetMenuJsonStr();
//    }
//}
