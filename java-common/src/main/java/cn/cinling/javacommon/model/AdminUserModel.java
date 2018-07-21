package cn.cinling.javacommon.model;

import cn.cinling.javacommon.config.define.SES;
import cn.cinling.javacommon.database.entity.AdminUserEntity;
import cn.cinling.javacommon.database.service.admin_user.AdminUserService;
import cn.cinling.javacommon.database.service.admin_user.impl.AdminUserServiceImpl;
import cn.cinling.javacommon.manager.SessionManager;
import cn.cinling.javacommon.model.exception.AdminUserModelException;

import javax.servlet.http.HttpSession;

/**
 * 用于处理管理员账号的模块
 */
public class AdminUserModel {

    private static AdminUserModel shareInstance = null;
    private AdminUserModel() {

    }
    public static AdminUserModel GetInstance() {
        if (AdminUserModel.shareInstance == null) {
            AdminUserModel.shareInstance = new AdminUserModel();
        }
        return AdminUserModel.shareInstance;
    }


    /**
     * @return 是否登陆
     */
    public boolean IsLogin() {
        SessionManager session = SessionManager.GetInstance();
        Object object = session.Get(SessionManager.KEY_USER_ID, null);
        if (object == null) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否已经初始化系统的管理员账号
     * @return 是否已经初始化系统的管理员账号
     */
    public boolean IsInitAdminUserAccount() {
        AdminUserService adminUserService = SpringUtil.getBean(AdminUserServiceImpl.class);

        return adminUserService.GetCount() != 0;
    }

    /**
     *
     * @param account
     * @param password
     */
    public void Login(String account, String password) throws AdminUserModelException {

       if (account.isEmpty() || password.isEmpty()) {
            throw new AdminUserModelException("账号或密码为空", new ApiResponse(ApiResponse.FAIL_EMPTY_ACCOUNT_OR_PASSWORD, ""));
       }

        AdminUserService adminUserService = SpringUtil.getBean(AdminUserServiceImpl.class);
        AdminUserEntity adminUserEntity = adminUserService.GetByAccount(account);

        if (adminUserEntity == null) {
            throw new AdminUserModelException("账号不存在", new ApiResponse(ApiResponse.FAIL_USER_NOT_EXISTS, ""));
        }

        if (!adminUserEntity.getPassword().equals(password)) {
            throw new AdminUserModelException("密码错误", new ApiResponse(ApiResponse.FAIL_PASSWORD_ERROR, ""));
        }

        SessionManager.GetInstance().Set(SessionManager.KEY_USER_ID, adminUserEntity.getId());
    }

    /**
     *
     * @param session
     */
    public void Logout(HttpSession session) {
        session.removeAttribute(SES.USER_ID);
    }

}
