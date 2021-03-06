package cn.cinling.javaadmin.manager;

import cn.cinling.javaadmin.manager.exception.AuthManagerException;
import cn.cinling.javaadmin.model.ApiResponse;
import cn.cinling.javaadmin.model.Menu;
import cn.cinling.javaadmin.util.SessionUtil;
import cn.cinling.javaadmin.util.UrlUtil;
import cn.cinling.javacommon.database.entity.AdminUserEntity;
import cn.cinling.javacommon.database.mapper.AdminUserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 权限管理模块
 */
@Service
public class AuthManager extends BaseManager {

    /**
     * 路径管理工具
     */
    private final UrlUtil urlUtil;
    /**
     * 管理员表 mapper 对象
     */
    private final AdminUserMapper adminUserMapper;


    /**
     * 菜单对象
     */
    private Menu menu;

    @Autowired
    public AuthManager(UrlUtil urlUtil, AdminUserMapper adminUserMapper) {
        this.urlUtil = urlUtil;
        this.adminUserMapper = adminUserMapper;

        this.InitMenu();
    }


    /**
     * 添加管理员账号
     */
    public void AddAdminUser(AdminUserEntity adminUserEntity) {
        adminUserMapper.Insert(adminUserEntity);
    }

    /**
     * @return 菜单的json字符串
     */
    public String GetMenuJsonStr() {
        ObjectMapper mapper = new ObjectMapper();
        String menuStr = "{}";

        try {
            menuStr = mapper.writeValueAsString(this.menu.ToJsonObject());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return menuStr;
    }


    /**
     * 初始化菜单
     */
    private void InitMenu() {
        // 菜单根节点
        this.menu = new Menu("root", "菜单", "")
                .AddChild(new Menu("sys","系统", "").SetIcon(Menu.ICON_COGS)
                        .AddChild(new Menu("sys-status", "服务器状态", this.urlUtil.To("admin-system-monitor/home")))
                )
                .AddChild(new Menu("test", "测试", "")
                        .AddChild(new Menu("index", "首页", this.urlUtil.To("test/index-page")))
                );
    }

    /**
     * @return 数据库中是否已经拥有超级管理员（当前只判断是否已经有账号）
     */
    public boolean IsInitAdminUserAccount() {
        return (adminUserMapper.SelectCount() != 0);
    }

    /**
     * @return 是否已经登录
     */
    public boolean IsLogin() {
        Object userId = SessionUtil.Get(SessionUtil.KEY_USER_ID, null);
        return (userId != null);
    }

    /**
     * 进行登录。同时设置 session、cookie、缓存等
     * @param account 账号
     * @param password 密码
     */
    public void Login(String account, String password) throws AuthManagerException {

        AdminUserEntity adminUserEntity = adminUserMapper.SelectByAccount(account);
        if (adminUserEntity == null) {
            throw new AuthManagerException("account not exists", new ApiResponse(ApiResponse.FAIL_USER_NOT_EXISTS));
        }
        if (!password.equals(adminUserEntity.getPassword())) {
            throw new AuthManagerException("password error", new ApiResponse(ApiResponse.FAIL_PASSWORD_ERROR));
        }

        SessionUtil.Set(SessionUtil.KEY_USER_ID, adminUserEntity.getId());
    }
}
