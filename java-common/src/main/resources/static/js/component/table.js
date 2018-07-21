
Vue.component('mwa-table', {
    props: {
        title: {
            type: String,
            required: false,
            default: "数据表"
        }
    },

    data: function() {
        return {

        }
    },

    mounted: function() {

    },

    methods: {

    },

    template: '' +
    '   <div class="mwa-card">\n' +
    '       <div class="tab-header">\n' +
    '           <h2>{{title}}</h2>\n' +
    '       </div>\n' +
    '       <div class="tab-body">\n' +
    '           <table class="table table-striped">\n' +
    '               <thead>\n' +
    '                   <tr>\n' +
    '                       <th>Name</th>\n' +
    '                       <th>Cost</th>\n' +
    '                       <th>Profit</th>\n' +
    '                       <th>Fun</th>\n' +
    '                   </tr>\n' +
    '               </thead>\n' +
    '           <tbody>\n' +
    '               <tr>\n' +
    '                   <td tabindex="1">Car</td>\n' +
    '                   <td tabindex="1">100</td>\n' +
    '                   <td tabindex="1">200</td>\n' +
    '                   <td tabindex="1">0</td>\n' +
    '               </tr>\n' +
    '           </tbody>\n' +
    // '           <tfoot>\n' +
    // '               <tr>\n' +
    // '                   <th><strong>TOTAL</strong></th>\n' +
    // '                   <th>1290</th>\n' +
    // '                   <th>1420</th>\n' +
    // '                   <th>5</th>\n' +
    // '               </tr>\n' +
    // '           </tfoot>\n' +
    '       </table>\n' +
    '   </div>' +
    ''
});