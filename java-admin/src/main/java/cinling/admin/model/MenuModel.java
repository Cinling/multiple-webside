package cinling.admin.model;

import cinling.admin.model.object.Menu;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.List;

/**
 * 菜单模块
 */
public class MenuModel {
    private static MenuModel shareInstance = null;
    private MenuModel() {
        this.InitMenu();
    }
    public static MenuModel GetInstance() {
        if (MenuModel.shareInstance == null) {
            MenuModel.shareInstance = new MenuModel();
        }
        return MenuModel.shareInstance;
    }


    /**
     * 菜单对象
     */
    private Menu menu;

    /**
     * 初始化菜单
     */
    private void InitMenu() {
        // 菜单根节点
        this.menu = new Menu("菜单");

        // 系统
        Menu system = new Menu("系统");
        this.menu.AddChild(system);

        // 系统 - 服务器状态
        system.AddChild(new Menu("服务器状态", "/system-monitor/"));
    }

    /**
     * @return 菜单 JSONObject 对象
     */
    public JSONObject GetMenuJSONObject() {
        JSONObject menuJson = new JSONObject();
        menuJson.put("name", this.menu.getName());
        menuJson.put("child", this.GetChildArray(this.menu.GetChildList()));
        return menuJson;
    }

    /**
     * 递归函数 Menu 子菜单列表转为 JSONArray 对象
     * @param menuList 子菜单列表
     * @return 子菜单列表转换的 JSONArray 对象
     */
    private JSONArray GetChildArray(List<Menu> menuList) {
        JSONArray childArray = new JSONArray();

        for (Menu menu: menuList) {
            JSONObject menuObject = new JSONObject();
            menuObject.put("name", menu.getName());
            menuObject.put("url", menu.getUrl());

            List<Menu> itemMenuList = menu.getItemList();
            if (itemMenuList != null) {
                menuObject.put("child", this.GetChildArray(itemMenuList));
            }
            childArray.appendElement(menuObject);
        }

        return childArray;
    }
}
