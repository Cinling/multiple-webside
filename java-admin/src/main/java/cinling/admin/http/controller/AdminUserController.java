package cinling.admin.http.controller;

import cinling.admin.database.entity.AdminUserEntity;
import cinling.admin.database.service.admin_user.AdminUserService;
import cinling.admin.model.AdminUserModel;
import cinling.admin.model.ApiResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
        // 如果已经创建了管理员账号，则跳转到登陆页面
        if (AdminUserModel.GetInstance().IsInitAdminUserAccount()) {

        }
        return "admin-user/init";
    }

    /**
     * 登陆页面
     * @return html页面
     */
    @GetMapping("login-page")
    public String LoginPage() {
        return "";
    }

    @PostMapping(value = "init-admin-user", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String InitAdminUser() {
        HttpServletRequest request = this.GetRequest();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (AdminUserModel.GetInstance().IsInitAdminUserAccount()) {
            return ApiResponseModel.GetInstance().ReturnJson(ApiResponseModel.FAIL_ADMIN_USER_WAS_EXISTS);
        }

        if (username.isEmpty() || password.isEmpty()) {
            return ApiResponseModel.GetInstance().ReturnJson(ApiResponseModel.FAIL_EMPTY_USERNAME_OR_PASSWORD);
        }

        int createTime = (int) (System.currentTimeMillis() / 1000);
        AdminUserEntity adminUserEntity = new AdminUserEntity(username, password, "系统管理员", createTime);
        adminUserService.AddAdminUser(adminUserEntity);

        return ApiResponseModel.GetInstance().ReturnJson(ApiResponseModel.SUCC_SUCC);
    }

    @ResponseBody
    @RequestMapping("test")
    public String Test() {
        return String.valueOf(adminUserService.GetCount());
    }
}
