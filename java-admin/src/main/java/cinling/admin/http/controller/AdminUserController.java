package cinling.admin.http.controller;

import cinling.admin.database.service.admin_user.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "admin-user")
public class AdminUserController extends BaseController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 管理员查看的页面
     * @return html页面
     */
    @GetMapping("home")
    public String AdminManagementPage() {
        return "admin-user/home";
    }

    /**
     * 初始化管理员账号的页面
     * @return html页面
     */
    @GetMapping("init")
    public String InitAdminUserPage() {
        return "admin-user/init";
    }

    @ResponseBody
    @RequestMapping("test")
    public String Test() {
        String text = String.valueOf(adminUserService.GetCount());

        return text;
    }
}
