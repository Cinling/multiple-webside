Vue.component("mwa-menu", {
    props: {
        /**
         * @param {String} 请求菜单的url地址
         */
        url: {
            type: String,
            required: true
        }
    },
    data: function() {
        return {
            menuList: []
        };
    },
    created: function() {
        this.RequestMenuData();
    },
    mounted: function() {

    },
    methods: {
        RequestMenuData: function() {
            let thisVue = this;

            $.ajax({
                url: this.url,
                method: "post",
                async: true,
                dataType: "json",
                success: function(json) {
                    thisVue.menuList = json;
                },
                error: function(error) {
                    console.error(error);
                }
            });
        }
    },
    template: '' +
    '<div class="mwa-menu-container">' +
    '   <mwa-menu-item parent-id="mwa" v-bind:menu-list="menuList.child" level="0"></mwa-menu-item>' +
    '</div>'
});

Vue.component("mwa-menu-item", {
    props: {
        /**
         * 父级id
         */
        parentId: {
            type: String,
            required: true
        },

        /**
         * 菜单列表
         */
        menuList: {
            type: [Array, Object],
            required: true
        },

        /**
         * 层次、等级
         */
        level: {
            type: Number,
            required: true
        }
    },

    data: function() {
        return {
            id: ""
        };
    },
    mounted: function() {
        this.HideChild();
    },
    methods: {
        ShowChild: function(event) {
            let thisId = $(event.target).data("item-id");

            this.HideChild();
            // 显示自己的子项
            $("ul[data-parent-id='" + this.parentId + "|" + thisId + "']").show();

            console.debug(this.parentId + "|" + thisId );
        },

        HideChild: function() {
            // 隐藏所有下级的子项
            $(".mwa-menu-ul-l" + (Number(this.level) + 1)).hide();
        }
    },
    template: '' +
    '<ul class="list-group mwa-menu-ul" v-bind:class="\'mwa-menu-ul-l\' + level" v-bind:data-parent-id="parentId">' +
    '   <template v-for="(item, index) in menuList">' +
    '       <li class="list-group-item" v-on:mouseover="ShowChild" v-bind:data-item-id="item.id">{{item.name}}</li>' +
    '       <template v-if="item.hasOwnProperty(\'child\')">' +
    '           <mwa-menu-item v-bind:parent-id="parentId + \'|\' + item.id" v-bind:menu-list="item.child" v-bind:level="Number(level) + 1"></mwa-menu-item>' +
    '       </template>' +
    '   </template>' +
    '</ul>' +
    ''
});