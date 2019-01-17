package com.tscloud.address.ui.restful;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tscloud.common.framework.Constants;
import com.tscloud.common.framework.config.ConfigHelper;
import com.tscloud.common.framework.domain.entity.manager.MenuRole;
import com.tscloud.common.framework.rest.view.JsonViewObject;
import com.tscloud.common.utils.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * 不启用云平台单点的情况下，所有的菜单直接从JSON文件中读取
 */
@Path("address/" + "menuRestServer")
public class MenuRestServer {

    @Context
    protected HttpServletRequest request;
    /**
     * 获取全部菜单
     *
     * @return
     */
    @GET
    @Path("getMenu")
    @Consumes(MediaType.APPLICATION_JSON)
    public String getMenu() {

        //单点启用状态
        String CAS_ENABLE = "false";//ConfigHelper.getValue("CAS_ENABLE");
        //读取配置JSON文件
        String basePath = request.getSession().getServletContext().getRealPath("/WEB-INF/menujson/") + System.getProperty("file.separator");
        //绝对目录
        String path = basePath + ConfigHelper.getValue("menu.path");
        //读取配置JSON文件
        String jsonStr = FileUtil.readFile(path);

        JSONObject obj = JSON.parseObject(jsonStr);

        JSONArray childArr = obj.getJSONArray("child");

        JsonViewObject jsonView = new JsonViewObject();


        if ("false".equals(CAS_ENABLE)) {

            jsonView.successPack(childArr.toJSONString());
            return JSON.toJSONString(jsonView);

        } else {

            //从session中获取当前登录用户菜单信息
            List<MenuRole> menuRoles = (List<MenuRole>) request.getSession().getAttribute("menuRoleList");

            if (menuRoles == null || menuRoles.size() == 0) {
                jsonView.failPack("没有找到已授权的菜单信息!");
                return JSON.toJSONString(jsonView);
            }

            //创建JSONArray 保存授权通过的菜单
            JSONArray array = new JSONArray();

            for (int i = 0; i < childArr.size(); i++) {

                JSONObject data = childArr.getJSONObject(i);
                String menuId = data.getString("id");
                if (!isAuth(menuId, menuRoles)) {
                    continue;
                }

                JSONObject root = new JSONObject();
                root.put("id", data.getString("id"));
                root.put("img", data.getString("img"));
                root.put("name", data.getString("name"));
                root.put("url", data.getString("url"));

                JSONArray childMenus = data.getJSONArray("child_menu");
                childMenus = getChildMenu(menuRoles, childMenus);
                root.put("child_menu", childMenus);
                array.add(root);
            }

            jsonView.successPack(array.toJSONString());
            return JSON.toJSONString(jsonView);
        }
    }

    /**
     * 获取二级菜单
     *
     * @return
     */
    private JSONArray getChildMenu(List<MenuRole> menuRoles, JSONArray array) {
        JSONArray childMenu = new JSONArray();
        for (int i = 0; i < array.size(); i++) {
            JSONObject data = array.getJSONObject(i);
            boolean flag = isAuth(data.getString("id"), menuRoles);
            if (!flag) {
                continue;
            }
            childMenu.add(data);
        }
        return childMenu;
    }


    /**
     * 根据菜单ID，判断是否授权
     *
     * @param menuId
     * @param menuRoles
     * @return
     */
    private boolean isAuth(String menuId, List<MenuRole> menuRoles) {
        boolean flag = false;
        for (MenuRole menuRole : menuRoles) {
            if (menuId.equals(menuRole.getMenuId())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 根据menuId获取菜单
     *
     * @param menuId
     * @return
     */
    private JSONObject getMenuById(String menuId, JSONArray childArr) {

        for (int i = 0; i < childArr.size(); i++) {
            JSONObject data = childArr.getJSONObject(i);
            if (menuId.equals(data.getString("id"))) {
                return data;
            }
        }
        return null;
    }

}
