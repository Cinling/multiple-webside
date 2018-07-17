
Vue.component('sys-table', {
    /**
     * 可通过标签赋值的属性
     */
    props: {
        /**
         * 表格标题
         */
        title: {
            type: String,
            request: false,
            default: "数据表"
        },

        /**
         * 获取数据的 url 地址
         */
        url: {
            type: String,
            request: false,
            default: undefined
        },

        /**
         * 导出数据之前执行的方法
         */
        beforeExport: {
            type: Function,
            request: false,
            default: function() {}
        },

        /**
         * 导出表格的 url
         */
        exportUrl: {
            type: String,
            request: false,
            default: undefined
        },

        /**
         * 数据上传地址
         */
        uploadUrl: {
            type: String,
            request: false,
            default: undefined
        },

        /**
         * 请求方式。"post" 或 "get"
         */
        requestMethod: {
            type: String,
            request: false,
            default: "post"
        },

        /**
         * 是否显示翻页控件
         */
        showPagination: {
            type: Boolean,
            request: false,
            default: true
        },

        /**
         * 提供给用户选择单页显示多少条数据 的配置
         */
        paginationNumList: {
            type: Array,
            request: false,
            default: [10, 20, 50, 100, 500]
        },


        /**
         * 宽度
         */
        width: {
            type: String,
            request: false,
            default: "100%"
        }
    },

    /**
     * 控件的主要属性
     * @returns {{dataList: {title: string, header: Array, data: Array, count: number, page: number, rows: number}}}
     */
    data: function() {
        return {
            // 表格的数据
            dataList: {
                title: "",  // 表格名字
                header: [], // 列标题配置
                data: [],   // 数据列表
                count: 0,   // 总数据条数
                page: 1,    // 当前的页数
                rows: 10    // 每页显示的数据条数
            },
        }
    },

    /**
     * 控件加载完之后执行的方法
     */
    mounted: function() {
        this.Refresh();
    },

    /**
     * 控件的类方法
     */
    methods: {
        /**
         * 请求数据
         */
        Request: function() {
            if (this.url === undefined) {
                return;
            }

            let thisVue = this;

            $.ajax({
                url: this.url,
                data: {"page": this.dataList.page, "rows": this.dataList.rows},
                method: thisVue.requestMethod,
                dataType: "json",
                success: function(json) {
                    thisVue.dataList.title = json.title;
                    thisVue.dataList.header = Object.assign(json.header);
                    thisVue.dataList.data = Object.assign(json.data);
                    thisVue.dataList.count = json.count;
                },
                error: function(error) {
                    console.error(error);
                }
            });
        },

        /**
         * 刷新
         */
        Refresh: function() {
            if (this.dataList.page <= 0) {
                this.dataList.page = 1;
            } else if (this.dataList.page > this.GetMaxPage() && this.GetMaxPage() !== 0) {
                this.dataList.page = this.GetMaxPage();
            }

            this.Request();
        },

        /**
         * 获取最大页数
         * @return {number}
         */
        GetMaxPage: function() {
            return Math.floor((this.dataList.count - 1) / this.dataList.rows + 1);
        },

        /**
         * 首页
         */
        HeadPage: function() {
            this.dataList.page = 1;
            this.Refresh();
        },

        /**
         * 上一页
         */
        PrevPage: function() {
            this.dataList.page -= 1;
            this.Refresh();
        },

        /**
         * 下一页
         */
        NextPage: function() {
            this.dataList.page += 1;
            this.Refresh();
        },

        /**
         * 末页
         */
        LastPage: function() {
            this.dataList.page = this.GetMaxPage();
            this.Refresh();
        },

        /**
         * 修改单页显示的数据条数
         */
        ChangeRows: function(event) {
            let rows = $(event.target).html();
            this.dataList.rows = parseInt(rows.toString());
            this.Refresh();
        },

        /**
         * 导出表格
         */
        Export: function() {
            this.beforeExport();
            window.open(this.exportUrl.toString());
        },

        /**
         * 导入数据表格
         */
        UploadFile: function() {
            var formData = new FormData();
            formData.append('file', $('#file')[0].files[0]);


            $.ajax({
                url: this.uploadUrl,
                method: "post",
                data: formData,
                contentType: false,
                processData: false,
                dataType: "text",
                success:function(data){
                    console.log(data);
                },
                error: function(error) {
                    console.error(error);
                }
            })
        },

        OpenUploadModal: function() {
            $("#uploadModal").modal("show");
        },

        /**
         * 关闭
         */
        CloseUploadModal: function() {
            $("#uploadModal").modal("hide");
        }
    },

    /**
     * 控件 html 模板
     */
    template: '\n' +
        '<div>\n' +


        // 控制组件栏
        '       <div class="sys-table-pagination"\n' +
        '            v-if="showPagination">\n' +
        '           <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">\n' +
        '               <div v-if="exportUrl !== undefined"\n' +
        '                   class="btn-group" role="group" aria-label="Third group">\n' +
        '                   <button v-if="exportUrl !== undefined" type="button" class="btn btn-light" v-on:click="Export">导出</button>\n' +
        '                   <button v-if="uploadUrl !== undefined" type="button" class="btn btn-light" v-on:click="OpenUploadModal">导入</button>\n' +
        '                   <div class="sys-table-pagination-space"></div>\n' +
        '               </div>\n' +
        '               <div class="btn-group mr-2" role="group" aria-label="First group">\n' +
        '                   <button type="button" class="btn btn-light" v-on:click="HeadPage">首页</button>\n' +
        '                   <button type="button" class="btn btn-light" v-on:click="PrevPage">上页</button>\n' +
        '               </div>\n' +
        '               <div class="btn-group mr-2" role="group" aria-label="Second group">\n' +
        '                   <span class="sys-table-pagination-page-num">{{dataList.page}}/{{GetMaxPage()}}</span>\n' +
        '               </div>\n' +
        '               <div class="btn-group" role="group" aria-label="Third group">\n' +
        '                   <button type="button" class="btn btn-light" v-on:click="NextPage">下页</button>\n' +
        '                   <button type="button" class="btn btn-light" v-on:click="LastPage">末页</button>\n' +
        '               </div>\n' +
        '               <div class="btn-group" role="group" aria-label="Third group">\n' +
        '                   <div class="sys-table-pagination-space"></div>\n' +
        '               </div>\n' +
        '               <div class="btn-group" role="group" aria-label="Third group">\n' +
        '                   <button type="button" class="btn btn-light">刷新</button>\n' +
        '                   <button id="btnGroupDrop1" type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">{{dataList.rows}}</button>\n' +
        '                   <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">\n' +
        '                       <a v-for="pageNum in paginationNumList"' +
        '                           class="dropdown-item" href="#" v-on:click="ChangeRows">{{pageNum}}</a>\n' +
        '                   </div>\n' +
        '               </div>\n' +
        '               \n' +
        '           </div>' +
        '       </div>\n' +
        '\n' +

        // 表格标题
        '   <div class="sys-table-title"\n' +
        '       v-bind:style="\'width:\' + width ">{{title}}</div>\n' +


        // 表格模板
        '   <div v-bind:style="\'width:\' + width">\n' +
        '       <table class="table table-sm table-bordered">\n' +
        '           <thead>\n' +
        '               <tr>\n' +
        '                   <th scope="col">#</th>\n' +
        '                   <template v-for="(name, id) in dataList.header">\n' +
        '                   <th scope="col" v-bind:data-key="id">{{name}}</th>\n' +
        '                   </template>\n' +
        '               </tr>\n' +
        '           </thead>\n' +
        '           <tbody>\n' +
        '               <tr v-for="(item, index) in dataList.data">\n' +
        '                   <th>{{index + 1}}</th>\n' +
        '                   <template v-for="(value, key) in dataList.header">\n' +
    '                           <td v-if="dataList.header.hasOwnProperty(key)" v-bind:data-key="key">{{item[key]}}</td>\n' +
        '                   </template>\n' +
        '               </tr>\n' +
        '           </tbody>\n' +
        '       </table>\n' +
        '   </div>' +


        '   <!-- Modal -->\n' +
        '   <div v-if="uploadUrl !== undefined" class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="uploadModalTitle" aria-hidden="true">\n' +
        '       <div class="modal-dialog modal-dialog-centered" role="document">\n' +
        '           <div class="modal-content">\n' +
        '               <div class="modal-header">\n' +
        '                   <h5 class="modal-title" id="uploadModalTitle">上传数据</h5>\n' +
        '                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">\n' +
        '                       <span aria-hidden="true">&times;</span>\n' +
        '                   </button>\n' +
        '               </div>\n' +
        '               <div class="modal-body">\n' +
        '                   <form id="fromUpload" enctype="multipart/form-data">\n' +
        '                       <input id="file" type="file" name="file" ><br><br>\n' +
        '                       <input type="button" v-on:click="UploadFile" value="提交">\n' +
        '                   </form>\n' +
        '               </div>\n' +
        '           </div>\n' +
        '       </div>\n' +
        '   </div>' +


        '</div>'
});