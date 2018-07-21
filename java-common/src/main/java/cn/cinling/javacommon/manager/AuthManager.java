package cn.cinling.javacommon.manager;

import cn.cinling.javacommon.model.Menu;

public class AuthManager {
    private static AuthManager shareInstance = null;
    private AuthManager() {
        this.InitMenu();
    }
    public static AuthManager GetInstance() {
        if (AuthManager.shareInstance == null) {
            AuthManager.shareInstance = new AuthManager();
        }
        return AuthManager.shareInstance;
    }


    /**
     * 菜单对象
     */
    private Menu menu;

    /**
     * 初始化菜单
     */
    private void InitMenu() {
        AssetManager a = AssetManager.GetInstance();

        // 菜单根节点
        this.menu = new Menu("root", "菜单", "")
                .AddChild(new Menu("sys","系统", "").SetIcon(Menu.ICON_COGS)
                        .AddChild(new Menu("sys-status", "服务器状态", a.To("/admin-system-monitor/home")))
                );
    }

    public String GetMenuJsonStr() {
        return menu.ToJsonObject().toJSONString();
    }
}
