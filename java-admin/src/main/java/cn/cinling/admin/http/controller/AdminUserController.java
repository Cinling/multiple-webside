package cn.cinling.admin.http.controller;

import cn.cinling.admin.database.entity.AdminUserEntity;
import cn.cinling.admin.database.service.admin_user.AdminUserService;
import cn.cinling.admin.model.AdminUserModel;
import cn.cinling.admin.model.ApiResponse;
import cn.cinling.admin.model.exception.AdminUserModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理员控制器
 */
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
    @GetMapping("init-page")
    public String InitAdminUserPage() {
        // 如果已经创建了管理员账号，则跳转到登陆页面
        if (AdminUserModel.GetInstance().IsInitAdminUserAccount()) {
            return this.LoginPage();
        }
        return "admin-user/init";
    }

    /**
     * 登陆页面
     * @return html页面
     */
    @GetMapping("login-page")
    public String LoginPage() {
        return "admin-user/login";
    }

    /**
     * @return 初始化管理员账号
     */
    @PostMapping(value = "init-admin-user", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String InitAdminUser() {
        HttpServletRequest request = this.GetRequest();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (AdminUserModel.GetInstance().IsInitAdminUserAccount()) {
            return new ApiResponse().ReturnJson(ApiResponse.FAIL_ADMIN_USER_WAS_EXISTS);
        }

        if (username.isEmpty() || password.isEmpty()) {
            return new ApiResponse().ReturnJson(ApiResponse.FAIL_EMPTY_ACCOUNT_OR_PASSWORD);
        }

        int createTime = (int) (System.currentTimeMillis() / 1000);
        AdminUserEntity adminUserEntity = new AdminUserEntity(username, password, "系统管理员", createTime);
        adminUserService.AddAdminUser(adminUserEntity);

        return new ApiResponse().ReturnJson(ApiResponse.SUCC_SUCC);
    }

    /**
     * @return 测试
     */
    @ResponseBody
    @RequestMapping("test")
    public String Test() {
        return String.valueOf(adminUserService.GetCount());
    }

    /**
     * 进行登录
     * @return json
     */
    @ResponseBody
    @PostMapping(value = "login", produces = "application/json;charset=UTF-8")
    public String Login() {
        HttpServletRequest request = this.GetRequest();
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        try {
            AdminUserModel.GetInstance().Login(account, password);
        } catch (AdminUserModelException e) {
            return e.GetApiResponse().ReturnJson();
        }

        return new ApiResponse(ApiResponse.SUCC_LOGIN).ReturnJson();
    }
}
