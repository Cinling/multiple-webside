package cn.cinling.javaadmin.model;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单模型
 */
public class Menu {

    /**
     * 三个齿轮的icon
     */
    public static final String ICON_COGS = "fas fa-cogs";

    /**
     * 菜单id
     */
    private String id;
    /**
     * 菜单名字
     */
    private String name;
    /**
     * 跳转的 url
     */
    private String url;
    /**
     * awesome icon 的 class
     */
    private String icon;
    /**
     * 子菜单列表
     */
    private List<Menu> childList = null;

    /**
     * @param id 菜单id
     * @param name 菜单名字
     * @param url 点击菜单后跳转的 url。如果没有可以输入：""
     */
    public Menu(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.icon = "";
    }

    /**
     * 为这个菜单增加一个子菜单
     * @param childMenu 子菜单
     * @return this
     */
    public Menu AddChild(Menu childMenu) {
        if (this.childList == null) {
            this.childList = new ArrayList<>();
        }

        this.childList.add(childMenu);
        return this;
    }

    /**
     * 设置显示的 icon
     * @param icon class 属性。请使用类中 ICON_ 开头的常量
     * @return this
     */
    public Menu SetIcon(String icon) {
        this.icon = icon;

        return this;
    }

    /**
     * @return 菜单对象的 json 对象
     */
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

    /**
     * @return 子菜单的 json 数组
     */
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
