/**
 * HTML控件帮助对象
 * @var {object} Menu
 */

var Menu = {
    /**
     * 菜单id
     */
    menuId: 0,
    /**
     * @async
     * 获取菜单的html
     * @param {function(string)} afterFinish 异步执行完后，需要回掉的方法
     * @return {string}
     */
    GetMenuHtml: function(afterFinish) {
        <!--导航栏-->

        $.ajax({
            url: "get-menu",
            method: "get",
            async: true,
            dataType: "text",
            success: function(text) {
                try {
                    let menu = JSON.parse(text);
                    let menuId = Menu.GetNextMenuId();
                    let menuHtml;

                    menuHtml = "" +
                        "<div class=\"srv-menu\">" +
                        "  <nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n" +
                        "    <a class=\"navbar-brand srv-menu-item\" data-menu_id='" + menuId + "'>" + menu.name + "</a>\n" +
                        "  </nav>" +
                        "  <div class='srv-menu-group'>";

                    menuHtml += Menu.OrderSetHTML("", menu.child, 1, menuId);

                    menuHtml += "" +
                        "</div>" +
                        "</div>";

                    afterFinish(menuHtml);
                    setTimeout(function(){
                        Menu.InitMenuEvents();
                    });

                } catch (e) {
                    console.error(e);
                    alert("菜单加载失败：js异常");
                }
            },
            error: function(error) {
                console.error(error);
                alert("菜单加载失败：HTTP 500");
            }
        });
    },
    /**
     * 遍历设置菜单的 html 内容
     * @param {string} tmpHtml
     * @param {object} menuList 菜单对象列表
     * @param {number} level
     * @param {number} parentId 父级菜单id
     * @return {string}
     */
    OrderSetHTML: function(tmpHtml, menuList, level, parentId) {
        tmpHtml += "<div class=\"list-group srv-menu-items srv-menu-items-" + level + "\" data-parent_id='" + parentId + "'>";
        for (let menuIndex in menuList) {
            let menu = menuList[menuIndex];
            let menuId = this.GetNextMenuId();
            let name = menu.name;
            let url = menu.url;

            tmpHtml += "<a class=\"list-group-item list-group-item-action srv-menu-item\" data-menu_id='" + menuId + "' data-url='" + url + "'>" + name + "</a>";

            if (menu.hasOwnProperty("child")) {
                tmpHtml += Menu.OrderSetHTML(tmpHtml, menu.child, level + 1, menuId);
            }
        }
        tmpHtml += "</div>";

        return tmpHtml;
    },
    /**
     * 初始化菜单的监听事件
     */
    InitMenuEvents: function() {
        // 显示子菜单
        let srvMenuItemList = $(".srv-menu-item");
        srvMenuItemList.unbind("mouseover");
        srvMenuItemList.on("mouseover", function(event) {
            // 显示子菜单
            let menuId = $(event.target).data("menu_id");
            $(".srv-menu-items[data-parent_id='" + menuId + "']").show();
        });

        // 当鼠标离开菜单区域时，隐藏菜单
        let srvMenuGroup = $(".srv-menu-group");
        srvMenuGroup.unbind("mouseleave");
        srvMenuGroup.on("mouseleave", function() {
            $(".srv-menu-items[data-parent_id]").hide();
        });

        // 点击链接出发的方法
        srvMenuItemList.unbind("click");
        srvMenuItemList.on("click", function(event) {
            let url = $(event.target).data("url");
            if (url !== null && url !== undefined) {
                Menu.JumpToUrl(url);
            } else {
                // 如果是没有链接，不会触发任何操作
            }
        });
    },
    /**
     * 获取下一个菜单id
     * @return {number}
     */
    GetNextMenuId: function() {
        this.menuId += 1;
        return this.menuId;
    },
    /**
     * 跳转路径
     * @param url
     */
    JumpToUrl: function(url) {
        window.location = url;
    }
};


// 从 服务器 获取菜单的数据，并加载到 VUE 中
Menu.GetMenuHtml(function(html) {
    Vue.component("srv-menu", {
        data: function() {
            return {
                menuHtml: html
            };
        },
        mounted: function() {
            // 初始化菜单的按钮
            Menu.InitMenuEvents();
        },
        methods: {

        },
        template: "<span v-html='menuHtml'></span>"
    });
});




