package cn.cinling.admin.manager;

import cn.cinling.admin.model.MenuModel;

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
    private MenuModel menu;

    /**
     * 初始化菜单
     */
    private void InitMenu() {
        // 菜单根节点
        this.menu = new MenuModel("root", "菜单", "")
                .AddChild(new MenuModel("sys","系统", "")
                        .AddChild(new MenuModel("sys-status", "服务器状态", "/admin-system-monitor/"))
                        .AddChild(new MenuModel("test-1", "测试1", ""))
                ).AddChild(new MenuModel("test-1", "一级菜单1", "")
                        .AddChild(new MenuModel("test-1-1", "二级菜单", "")
                                .AddChild(new MenuModel("test-1-1-1", "三级菜单", ""))
                        )
                ).AddChild(new MenuModel("test-1", "一级菜单1", "")
                        .AddChild(new MenuModel("test-1-1", "二级菜单", "")
                                .AddChild(new MenuModel("test-1-1-1", "三级菜单", ""))
                        )
                ).AddChild(new MenuModel("test-1", "一级菜单1", "")
                        .AddChild(new MenuModel("test-1-1", "二级菜单", "")
                                .AddChild(new MenuModel("test-1-1-1", "三级菜单", ""))
                        )
                ).AddChild(new MenuModel("test-1", "一级菜单1", "")
                        .AddChild(new MenuModel("test-1-1", "二级菜单", "")
                                .AddChild(new MenuModel("test-1-1-1", "三级菜单", ""))
                        )
                ).AddChild(new MenuModel("test-1", "一级菜单1", "")
                        .AddChild(new MenuModel("test-1-1", "二级菜单", "")
                                .AddChild(new MenuModel("test-1-1-1", "三级菜单", ""))
                        )
                ).AddChild(new MenuModel("test-1", "一级菜单1", "")
                        .AddChild(new MenuModel("test-1-1", "二级菜单", "")
                                .AddChild(new MenuModel("test-1-1-1", "三级菜单", ""))
                        )
                ).AddChild(new MenuModel("test-1", "一级菜单1", "")
                        .AddChild(new MenuModel("test-1-1", "二级菜单", "")
                                .AddChild(new MenuModel("test-1-1-1", "三级菜单", ""))
                        )
                );
    }

    public String GetMenuJsonStr() {
        return menu.ToJsonObject().toJSONString();
    }
}
