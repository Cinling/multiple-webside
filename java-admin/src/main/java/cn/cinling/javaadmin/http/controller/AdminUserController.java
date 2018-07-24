package cn.cinling.javaadmin.http.controller;

import cn.cinling.javaadmin.manager.AuthManager;
import cn.cinling.javaadmin.manager.exception.AuthManagerException;
import cn.cinling.javaadmin.model.ApiResponse;
import cn.cinling.javacommon.database.entity.AdminUserEntity;
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

    private final AuthManager authManager;

    @Autowired
    public AdminUserController(AuthManager authManager) {
        this.authManager = authManager;
    }

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
        if (authManager.IsInitAdminUserAccount()) {
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

        if (authManager.IsInitAdminUserAccount()) {
            return new ApiResponse(ApiResponse.FAIL_ADMIN_USER_WAS_EXISTS).ReturnJson();
        }

        if (username.isEmpty() || password.isEmpty()) {
            return new ApiResponse(ApiResponse.FAIL_EMPTY_ACCOUNT_OR_PASSWORD).ReturnJson();
        }

        int createTime = (int) (System.currentTimeMillis() / 1000);
        AdminUserEntity adminUserEntity = new AdminUserEntity(username, password, "系统管理员", createTime);
        authManager.AddAdminUser(adminUserEntity);

        return new ApiResponse(ApiResponse.SUCC_SUCC).ReturnJson();
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
            authManager.Login(account, password);
        } catch (AuthManagerException e) {
            return e.getApiResponse().ReturnJson();
        }

        return new ApiResponse(ApiResponse.SUCC_LOGIN).ReturnJson();
    }
}
