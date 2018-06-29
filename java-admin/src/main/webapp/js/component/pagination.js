Vue.component("srv-pagination", {
    props:{
        /**
         * 当分页控件某按钮被点击时触发的方法
         * @param {int} page
         * @param {int} rows
         */
        OnChange: {
            type: Function,
            required: true,
        },
        /**
         * 数据的总条数
         */
        count: {
            type: Number,
            required: true,
        },
        /**
         * 分页控件的页数
         */
        page: {
            type: Number,
            required: false,
            default: 1
        },
        /**
         * 分页控件每页显示的数据条数
         */
        rows: {
            type: Number,
            required: false,
            default: 10
        }
    },
    data: function() {

    },
    mounted: function() {
        this.CheckPropsDefined();
    },
    methods: {
        /**
         * 判断需要的属性是否完成初始化
         */
        CheckPropsDefined: function() {
            if (this.OnChange === undefined || this.count === undefined) {
                console.error({
                    msg: "undefined required value",
                    values: {
                        OnChange: this.OnChange,
                        count: this.count
                    }
                });
            }
        },
        /**
         * 跳转到第一页
         */
        FirstPage: function() {
            this.page = 1;
            this.OnChange(this.page, this.rows);
        },
        /**
         * 上一页
         */
        PrevPage: function () {
            this.page = this.page - 1;
            if (this.page < 1) {
                this.page = 1;
            }
            this.OnChange(this.page, this.rows);
        },
        /**
         * 下一页
         */
        NextPage: function() {
            this.page = this.page + 1;
            let maxPage = this.GetMaxPageNum();
            if (this.page > maxPage) {
                this.page = maxPage;
            }
            this.OnChange(this.page, this.rows);
        },
        /**
         * 最后一页
         */
        LastPage: function () {
            this.page = parseInt(this.count / this.rows) + 1;
            this.OnChange(this.page, this.rows);
        },
        /**
         * 设置页数
         * @param event
         */
        SetPage: function(event) {
            this.page = parseInt($(event.target).val());
        },
        /**
         * 跳转到设置的页数
         */
        JumpToPage: function() {
            this.OnChange(this.page, this.rows);
        },
        /**
         * 获取最大的页码
         * @return {int}
         */
        GetMaxPageNum : function() {
            return (parseInt(this.count / this.rows) + ((parseInt(this.count / this.rows) === (this.count / this.rows)) ? 0 : 1));
        }
    },
    watch: {
    },
    template: '' +
    '<div class="btn-toolbar" role="toolbar">\n' +
    '  <div class="btn-group mr-2" role="group">\n' +
    '    <button type="button" class="btn btn-outline-secondary" v-on:click="FirstPage"> << </button>\n' +
    '    <button type="button" class="btn btn-outline-secondary" v-on:click="PrevPage"> < </button>\n' +
    '      &nbsp;' +
    '    <input type="text" class="form-control" style="width:60px" v-bind:value="page" v-on:blur="SetPage">' +
    '    <div class="input-group-append">' +
    '      <span class="input-group-text" id="basic-addon2">/{{GetMaxPageNum()}}</span>\n' +
    '    </div>\n' +
    '    <button class="btn btn-outline-secondary" type="button" v-on:click="JumpToPage">GO</button>\n' +
    '      &nbsp;' +
    '    <button type="button" class="btn btn-outline-secondary" v-on:click="NextPage"> > </button>\n' +
    '    <button type="button" class="btn btn-outline-secondary" v-on:click="LastPage"> >> </button>\n' +
    '  </div>\n' +
    '</div>'
});