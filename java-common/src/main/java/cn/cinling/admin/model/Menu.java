package cn.cinling.admin.model;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单模块
 */
public class Menu {
    private String id;
    private String name;
    private String url;
    private String icon;    // awesome icon 的 class
    private List<Menu> childList = null;

    public Menu(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.icon = "";
    }

    public Menu AddChild(Menu childMenu) {
        if (this.childList == null) {
            this.childList = new ArrayList<>();
        }

        this.childList.add(childMenu);
        return this;
    }

    public static final String ICON_COGS = "fas fa-cogs";

    public Menu SetIcon(String icon) {
        this.icon = icon;

        return this;
    }

    public JSONObject ToJsonObject() {
        JSONObject o = new JSONObject();

        o.put("id", this.id);
        o.put("name", this.name);
        o.put("url", this.url);
        o.put("icon", this.icon);
        if (this.childList != null) {
            o.put("child", this.GetChildJsonArray());
        }

        return o;
    }

    private JSONArray GetChildJsonArray() {
        JSONArray a = new JSONArray();

        for(Menu menu: this.childList) {
            JSONObject o = new JSONObject();
            o.put("id", menu.id);
            o.put("name", menu.name);
            o.put("url", menu.url);
            o.put("icon", menu.icon);

            if (menu.childList != null) {
                o.put("child", menu.GetChildJsonArray());
            }

            a.appendElement(o);
        }

        return a;
    }
}
