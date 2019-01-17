/**
 * Created by hudaowan on 2014/12/29.
 */

requirejs.config({
    baseUrl: window.fpath + "javascripts",
    urlArgs: 'v=v3',
    map: {
        '*': {
            'css': 'css'
        }
    },
    shim: {
        "bootstrap": {"deps": ["jquery"]},
        "ztree": {"deps": ["jquery"]},
        "datepicker": {"deps": ["jquery"]},
        "form-validate": {"deps": ["jquery-validation"]},
        "ajaxfileupload": {"deps": ["jquery"]},
        //"olprefix": {"deps": ["ol"]}
    },
    paths: {
        "json2": "json2",
        "jquery": "jquery",
        "jquery-ui-css": "../stylesheets/jquery-ui",
        //"ol": "components/openlayers3/ol",
        //"olprefix": "components/openlayers3/olprefix",
        "underscore": "underscore",
        "bootstrap": "bootstrap",
        "avalon": "avalon",
        "etpl": "etpl",
        "bootstrap-dialog": "components/dialog/bootstrap-dialog",
        "base": "leador-components/base",
        "table_template": "leador-components/leador-lib",
        "avalon_filter": "leador-components/avalon_filter",
        "db_type_filter": window.contentPath + "/db_type_filter",
        "ztree": "components/tree/jquery.ztree.all-3.5",
        "ztree-css": "../stylesheets/tree",

        "jquery-validation": "jquery.validate",
        "form-validate": "components/validation/form-validate",

        "datepicker": "components/date/My97DatePicker/WdatePicker",
        "datepicker-css": "components/date/My97DatePicker/skin/WdatePicker",

        "picklist": "leador-components/picklist",
        "picklist-css": "../stylesheets/picklist",

        "scroll": "nicescroll",
        "ajaxfileupload": "components/file/ajaxfileupload",
        "fileupload": "components/file/SimpleAjaxUploader",
        'dragplugin': 'components/jquery-ui/jquery.drag-drop.plugin',

        'zrender': 'components/zrender/zrender',
        'zrender/shape/Image': 'components/zrender/zrender',
        'zrender/shape/Polyline': 'components/zrender/zrender',
        'zrender/shape/Line': 'components/zrender/zrender',
        'zrender/shape/Rectangle': 'components/zrender/zrender',
        'zrender/tool/color': 'components/zrender/zrender',
        'zrender/Group': 'components/zrender/zrender',
        'zrender/tool/event ': 'components/zrender/zrender',

        "leador": "leador-components/leador",
        "leador-component": "leador-components/leador-component",
        "leador-tip-css": "../stylesheets/tip",

        "stepbox": "leador-components/stepBox",

        "map-box": "../stylesheets/haikou",

        "dialog-plus": "components/dialog/artDialog",
        "dialog-plus-css": "../stylesheets/ui-dialog",

        "lightbox": "components/lightbox/lightbox",
        "lightbox-css": "../stylesheets/lightbox",

        "select2": "components/select/select2",
        "select2-css": "../stylesheets/select2",

        "webuploader": "components/webuploader/webuploader",
        "webuploader-css": "../stylesheets/webuploader"

    }

});

define(["base"], function (base) {
    // var menu_url = "rest/database/menuRestServer/getMenu?module=DATABASE";
    var menu_url = "rest/address/menuRestServer/getMenu";
    var index = new base.baseIndex({menu_url: menu_url, pageContent_url: contentPath + "/module/home.html"});
    index.init();
    //加载页面时，获取用户信息
    var userId = undefined;
    var userName = undefined;
    base.doGetData(contentPath + "rest/address/userRestServer/getUserInfo").done(function (data) {
        if (data.status == "success") {
            var content = data.content;
            /*userId = content.id;
            userName = content.userName;
            $("#userName").html(userName);			//登录成功在右上角显示用户名称
            */
        }
    });

    //点击人像图标，弹出基本信息窗口
    $("#editMyself").on("click", function (id) {
        base.openModal("基本信息", "user_info.html", {'type': 'view', 'id': userId});
    });

    //点击修改密码，弹出修改密码窗口
    $("#editPwd").on("click", function (id) {
        base.openModal("修改密码", "edit_password.html", {'type': 'update', 'id': userId}, 400);
    });


    //退出系统
    $("#exitSystem").on("click", function () {
        $.confirm({
            text: "是否要退出当前系统？", confirm: function (button) {
                var url = "http://192.168.20.18:6040/dm-ui/database.jsp?" + base.uuid.generate();
                var loginUrl = "http://192.168.18.30:8080/cas/login?service=" + encodeURIComponent(url);
                location.href = "http://192.168.18.30:8080/cas/logout?service=" + encodeURIComponent(loginUrl);
            }
        });
    })

    //给跳转到运维平台的路径加上token
    /*$(function(){
     $("#_href_console").attr("href", $("#_href_console").attr("href")+"?token="+base.getURLParameter("token"))
     });*/
});

