package cinling.admin.http.controller;

import cinling.admin.database.entity.UserEntity;
import cinling.admin.database.service.user.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/add")
    public String addUser(){
        UserEntity user = new UserEntity();
        user.setUserName("aa");
        user.setPassword("bb");
        user.setPhone("cc");
        userService.addUser(user);

        return "add success";
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        //开始分页
        PageHelper.startPage(pageNum,pageSize);
        return userService.findAllUser(pageNum,pageSize);
    }

//    @ResponseBody
//    @GetMapping("set-session")
//    public String TSetSession() {
//        HttpSession session = this.GetSession();
//        session.setAttribute("a", "qqq");
//
//        return "succ";
//    }
//
//    @ResponseBody
//    @GetMapping("get-session")
//    public String TGetSession() {
//        HttpSession session = this.GetSession();
//
//        String ret;
//        try {
//            ret = session.getAttribute("a").toString();
//        } catch (Exception e) {
//            ret = "没有东西";
//        }
//
//        return ret;
//    }
}
