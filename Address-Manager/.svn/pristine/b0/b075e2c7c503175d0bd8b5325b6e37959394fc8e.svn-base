<%@ page import="com.tscloud.common.framework.config.ConfigHelper" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%
        String fpath = ConfigHelper.getValue("staticfileurl");
        String contentPath = request.getContextPath();
    %>

    <link href="<%=fpath%>stylesheets/index.css" rel="stylesheet"/>
    <!--[if lt IE 9]>
    <script src="<%=fpath%>javascripts/html5shiv.js"></script>
    <script src="<%=fpath%>javascripts/respond.js"></script>
    <![endif]-->

    <script type="text/javascript">
        var contentPath = "<%=contentPath%>", fpath = "<%=fpath%>";
    </script>
</head>
<body>
<div class="container">
    <div class="viewport" id="console">
        <div class="navbar">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand database" href="#">地名地址管理系统</a>
                </div>
                <div class="navbar-usericos pull-right">
                    <ul>
                        <li><a href="javascript:void(0)"><span class="glyphiconText" id="userName"></span></a>
                        </li>
                        <li><a href="#" id="editMyself"><span class="trueicon trueicon-user3"></span></a>
                        </li>
                        <li><a href="#" id="editPwd"> <span class="trueicon trueicon-key3"></span></a>
                        </li>
                        <!-- <li class="on" id="messengerCtrl"><a href="javascript:void(0)"><span class="trueicon trueicon-bell2"><span class="unreadtip">2</span></span></a>
                         </li>
                         <li><a href="../index.html" title="进入运维管理平台"><span class="trueicon trueicon-codepen"></span></a>
                         </li>-->
                        <li><a id="_href_console" href="http://172.192.100.62:5062/manager-service/choose_new.jsp"
                               target="_blank" title="进入首页"><span class="trueicon trueicon-codepen"></span></a>
                        </li>
                        <li><a href="#" id="exitSystem"><span class="trueicon trueicon-switch4"></span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="navside inside-scroll" id="navside" ms-controller="avalon-leftmenu">
            <div class="navside-controller" id="navslider" ms-click="navsliderClick()">
                <span class="trueicon trueicon-fast-rewind pull-right"></span>
            </div>
            <ul ms-class-1="menu" ms-class-2="menu-level1" ms-class-3="on:flag?currentIndex===$index:false"
                ms-repeat-item="menu_data" ms-click="menuTrigger()">
                <li>
                    <a href="#" class="links-lv1" ms-click="toggle($index,item.url)">
                        <span ms-class="{{item.img}}"></span>
                        <span class="texts">{{item.name}}</span>
                        <span class="icon pull-right" ms-if="item.child_menu.length>0"
                              ms-class="trueicon-angle-down:item.child_menu.length>0"></span>
                    </a>
                </li>
                <ul class="menu menu-level2" ms-class="menu-open:currentIndex===$index" ms-each-el="item.child_menu"
                    ms-if="item.child_menu.length>0">
                    <li>
                        <a href="#" ms-click="toggle(null,el.url)">{{el.name}}</a>
                    </li>
                </ul>
            </ul>
        </div>
        <!--neirong-->
        <div id="content" class="tranform-box" style="overflow-y: auto">

        </div>
    </div>
</div>
<div class="loading-svg">
    <span style="position: absolute;top:24px;font-size:12px;left:8px;">响应中..</span>
</div>
<!--<input type="hidden" id="orgId" value="1"/>-->
<script src="js/map/ld.min.js"></script>
<script src="js/map/ldMap.js"></script>


<script type="text/javascript" data-main="database_index" src="<%=fpath%>javascripts/require.js"></script>

</body>
</html>
