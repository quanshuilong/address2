package com.tscloud.address.ui.utils;


import com.tscloud.common.framework.config.ConfigHelper;
import com.tscloud.common.utils.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

public class LoginFilter implements Filter {

    private Pattern pattern;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        HttpSession session = request.getSession();
        String username = request.getRemoteUser();
        String managerAppCode = ConfigHelper.getValue("TrueCloudManager.appCode");
        String managerIp = ConfigHelper.getValue("TrueCloudManager.ip");
        String managerPort = ConfigHelper.getValue("TrueCloudManager.port");
        String managerServer = ConfigHelper.getValue("TrueCloudManager.server");
        boolean needForward = false;
        String requestPath = request.getServletPath();
        String pathInfo = request.getPathInfo();

        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        //不需要处理的静态文件直接忽略
        if (pattern.matcher(requestPath).find()) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        /**
         * 外部应用调用rest服务直接过滤
         * 在config.properties配置
         */
        if (StringUtils.isNotBlank(pathInfo)) {
            String exPath = ConfigHelper.getValue("CA.filter.path");
            if (StringUtils.isNotBlank(exPath)) {
                String[] arr = exPath.split("|");
                for (String _expath:arr){
                    if (pathInfo.startsWith(_expath)) {
                        chain.doFilter(servletRequest,servletResponse);
                        return;
                    }
                }
            }
        }

        if (!StringUtils.isBlank(username)) {

        }else{

        }


        //没有权限访问该系统则跳转到提示页面
        if (needForward || session.getAttribute("menuRoleJsonStr") == null) {

        }

        chain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
