package cn.cinling.javaadmin.model;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

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

    public ObjectNode ToJsonObject() {
        ObjectNode o = JsonNodeFactory.instance.objectNode();

        o.put("id", this.id);
        o.put("name", this.name);
        o.put("url", this.url);
        o.put("icon", this.icon);
        if (this.childList != null) {
            o.putPOJO("child", this.GetChildJsonArray());
        }

        return o;
    }

    private ArrayNode GetChildJsonArray() {
        ArrayNode a = JsonNodeFactory.instance.arrayNode();

        for(Menu menu: this.childList) {
            ObjectNode o = JsonNodeFactory.instance.objectNode();
            o.put("id", menu.id);
            o.put("name", menu.name);
            o.put("url", menu.url);
            o.put("icon", menu.icon);

            if (menu.childList != null) {
                o.putPOJO("child", menu.GetChildJsonArray());
            }

            a.add(o);
        }

        return a;
    }
}
