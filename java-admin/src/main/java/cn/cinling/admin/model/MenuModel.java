package cn.cinling.admin.model;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单模块
 */
public class MenuModel {
    private String id;
    private String name;
    private String url;
    private List<MenuModel> childList = null;

    public MenuModel(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public MenuModel AddChild(MenuModel childMenu) {
        if (this.childList == null) {
            this.childList = new ArrayList<>();
        }

        this.childList.add(childMenu);
        return this;
    }

    public JSONObject ToJsonObject() {
        JSONObject o = new JSONObject();

        o.put("id", this.id);
        o.put("name", this.name);
        o.put("url", this.url);
        if (this.childList != null) {
            o.put("child", this.GetChildJsonArray());
        }

        return o;
    }

    private JSONArray GetChildJsonArray() {
        JSONArray a = new JSONArray();

        for(MenuModel menu: this.childList) {
            JSONObject o = new JSONObject();
            o.put("id", menu.id);
            o.put("name", menu.name);
            o.put("url", menu.url);

            if (menu.childList != null) {
                o.put("child", menu.GetChildJsonArray());
            }

            a.appendElement(o);
        }

        return a;
    }
}
