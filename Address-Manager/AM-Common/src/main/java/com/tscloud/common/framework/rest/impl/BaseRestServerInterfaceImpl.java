package com.tscloud.common.framework.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tscloud.common.framework.Exception.DubboProviderException;
import com.tscloud.common.framework.domain.TrackableEntity;
import com.tscloud.common.framework.dubbo.DubboBaseInterface;
import com.tscloud.common.framework.logcache.LogCache;
import com.tscloud.common.framework.rest.BaseRestServerInterface;
import com.tscloud.common.framework.rest.view.JsonViewObject;
import com.tscloud.common.framework.rest.view.Page;
import com.tscloud.common.utils.BeanObjectToMap;
import com.tscloud.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hudaowan on 2015/2/10.
 */
public abstract class BaseRestServerInterfaceImpl<Entity extends TrackableEntity> implements BaseRestServerInterface {
    protected Logger log = LoggerFactory.getLogger(getClass());
    protected JsonViewObject jsonView = new JsonViewObject();
    @Context
    protected HttpServletResponse response;
    @Context
    protected HttpServletRequest request;

    protected String modelName;

    protected String systemName;

    protected String userName;

    {
        getDubboBaseInterface();
    }

    /**
     * 添加系统操作日志
     */
    protected void addOperationLog(String operation, String paramJson, boolean status, String msg) {
        //从session取数据用户数据

//        String userName = (User)getRequest().getSession().getAttribute(Constants.user.COOKIE_USER);
        String name = modelName;
        String sysName = systemName;
        String createDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        String jsonStr = "{'name':'" + name
                + "','status':'" + String.valueOf(status)
                + "','description':'" + operation + "===============" + msg
                + "','createDate':'" + createDate
                + "','operator':'" + (StringUtils.isBlank(userName) ? "admin" : userName)
                + "','param':'" + paramJson
                + "'}";
        LogCache cache = LogCache.init();
        //生成UUID
        UUID uid = UUID.randomUUID();
        String logKey = sysName + ".sysLog" + uid.toString();
        //写入缓存
        cache.putValue(logKey, jsonStr);
    }


    /**
     * 添加操作日志
     *
     * @param optCotent 操作内容
     * @param param     方法参数
     * @param status    结果状态
     */
    protected void addOperationLog(String optCotent, String param, boolean status) {
        //构建参数JSON
        JSONObject data = new JSONObject();
        data.put("name", this.modelName);//模块名称
        data.put("description", optCotent);//操作内容
        data.put("status", status);//操作状态
        data.put("operator", StringUtils.isBlank(userName) ? "admin" : userName);//操作人
        data.put("createDate", new Date());
        data.put("param", param);
        LogCache cache = LogCache.init();
        UUID uid = UUID.randomUUID();
        String logKey = systemName + ".sysLog" + uid.toString();
        cache.putValue(logKey, data.toJSONString()); //写入缓存
    }


    @Override
    public String getPage(String jsonStr) {
        String result = "";
        Page page = null;
        try {
            Map<String, Object> mapBean = new HashMap<String, Object>();
            if (!StringUtils.isBlank(jsonStr)) {
                page = JSON.parseObject(jsonStr, Page.class);
                if (page != null) {
                    Entity entity = null;
                    String objCondition = page.getObjCondition().toString();
                    if (StringUtils.isNotBlank(objCondition) && !"{}".equalsIgnoreCase(objCondition)) {
                        entity = JSON.parseObject(objCondition, this.getEntityClass());
                    }
                    mapBean = BeanObjectToMap.convertBean(entity);
                }
            } else {
                page = new Page();
            }
            page = this.getDubboBaseInterface().findByPage(page, mapBean);
            result = JSON.toJSONStringWithDateFormat(page, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteMapNullValue);
            jsonView.successPack(result);
            this.addOperationLog("分页查询", jsonStr, true);
        } catch (Exception e) {
            jsonView.failPack(e);
            this.addOperationLog("分页查询", jsonStr, false);
            log.error("BaseRestServerInterfaceImpl getPage is error,{jsonStr:" + jsonStr + "}", e);
        }
        result = JSON.toJSONString(jsonView);
        return result;
    }

    @Override
    public String getAll() {
        try {
            List<Entity> list = this.getDubboBaseInterface().findAll();
            //result = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss");
            this.addOperationLog("查询所有数据", "", true);
            jsonView.successPack(list);
        } catch (Exception e) {
            jsonView.failPack(e);
            this.addOperationLog("查询所有数据", "", false);
            log.error("BaseRestServerInterfaceImpl getAll is error", e);
        }
        //result = JSON.toJSONString(jsonView);
        return this.dataFormat();
    }

    @Override
    public String getByWhere(String jsonStr) {
        String result = "";
        try {
            Entity entity = JSON.parseObject(jsonStr, this.getEntityClass());
            Map<String, Object> mapBean = BeanObjectToMap.convertBean(entity);
            List<Entity> list = this.getDubboBaseInterface().findByMap(mapBean);
            if (list != null) {
                result = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss");
                this.addOperationLog("条件查询", jsonStr, true);
            }
            jsonView.successPack(result);
        } catch (Exception e) {
            jsonView.failPack(e);
            this.addOperationLog("条件查询", jsonStr, false);
            log.error("BaseRestServerInterfaceImpl getByWhere is error，{jsonStr:" + jsonStr + "}", e);
        }
        result = JSON.toJSONString(jsonView);
        return result;
    }

    @Override
    public String getById(String id) {
        try {
            if (StringUtils.isNotBlank(id)) {
                Entity entity = this.getDubboBaseInterface().findById(id);
                if (entity != null) {
//                    result = JSON.toJSONStringWithDateFormat(entity, "yyyy-MM-dd HH:mm:ss");
                    this.addOperationLog("条件查询", "id=" + id, true);
                }
                jsonView.successPack(entity,"数据查询成功");
            }
        } catch (Exception e) {
            jsonView.failPack(e);
            this.addOperationLog("条件查询", "id=" + id, false);
            log.error("BaseRestServerInterfaceImpl getById is error,{id:" + id + "}", e);
        }
//        result = JSON.toJSONString(jsonView);
        return this.dataFormat();
    }

    @Override
    public String getByName(String name) {
        String result = "";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        try {
            List<Entity> list = this.getDubboBaseInterface().findByMap(map);
            if (list != null && !list.isEmpty()) {
                result = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss");
                this.addOperationLog("按名称查询", "name=" + name, true);
            }
            jsonView.successPack(result);
        } catch (Exception e) {
            jsonView.failPack(e);
            this.addOperationLog("按名称查询", "name=" + name, false);
            log.error("BaseRestServerInterfaceImpl getByName is error,{Name:" + name + "}", e);
        }
        result = JSON.toJSONString(jsonView);
        return result;
    }

    @Override
    public String deleteById(String id) {
        boolean flag = false;
        try {
            flag = this.getDubboBaseInterface().deleteById(id);
            jsonView.successPack(JSON.toJSONString(flag),"数据删除成功");
            this.addOperationLog("删除数据", "id=" + id, true);
        } catch (Exception e) {
            jsonView.failPack(JSON.toJSONString(flag));
            this.addOperationLog("删除数据", "id=" + id, false);
            log.error("BaseRestServerInterfaceImpl deleteById is error,{Id:" + id + "}", e);
        }
        return JSON.toJSONString(jsonView);
    }

    @Override
    public String deleteByIds(String ids) throws DubboProviderException {
        boolean flag = true;
        int count = 0;
        try {
            if (ids != null) {
                String[] idArray = ids.split(",");
                for (String id : idArray) {
                    flag = this.getDubboBaseInterface().deleteById(id);
                    if (!flag) {
                        continue;
                    } else {
                        count++;
                    }
                }
            }
            if (count > 0) {
                jsonView.successPack("true", "删除数据成功!");
                this.addOperationLog("删除数据", "ids=" + ids, false);
            } else {
                jsonView.successPack("false", "删除数据");
                this.addOperationLog("删除数据", "ids=" + ids, false);
            }
        } catch (Exception e) {
            jsonView.failPack("false", "删除数据失败!");
            this.addOperationLog("删除数据", "id=" + ids, false);
            log.error("BaseRestServerInterfaceImpl deleteByIds is error,{Id:" + ids + "}", e);
        }
        return JSON.toJSONString(jsonView);
    }

    @Override
    public String save(String jsonStr) {
        String result = "";
        try {
            Entity entity = JSON.parseObject(jsonStr, this.getEntityClass());
            if (entity != null) {
                result = this.getDubboBaseInterface().save(entity);
                if("exists".equals(result)){
                    jsonView.setMessage("exists");
                }else{
                    jsonView.successPack(result);
                    this.addOperationLog("保存数据", "jsonStr=" + jsonStr, true);
                }
            }
        } catch (Exception e) {
            String message=e.getMessage();
            if(StringUtils.isBlank(message))message="保存数据失败！";
            jsonView.failPack("false",message);
            this.addOperationLog("保存数据", "jsonStr=" + jsonStr, false);
            log.error("BaseRestServerInterfaceImpl save is error,{jsonStr:" + jsonStr + "}," + e.getMessage(), e);
        }
        result = JSON.toJSONString(jsonView);
        return result;
    }

    @Override
    public String update(String jsonStr) {
        String result = "";
        try {
            Entity entity = JSON.parseObject(jsonStr, this.getEntityClass());
            if (entity != null) {
                result = String.valueOf(this.getDubboBaseInterface().update(entity));
                jsonView.successPack(result);
                this.addOperationLog("更新数据", "jsonStr=" + jsonStr, true);
            }
        } catch (Exception e) {
            String message=e.getMessage();
            if(StringUtils.isBlank(message))message="更新数据失败！";
            jsonView.failPack("false",message);
            this.addOperationLog("更新数据", "jsonStr=" + jsonStr, false);
            log.error("BaseRestServerInterfaceImpl update is error,{jsonStr:" + jsonStr + "}," + e.getMessage(), e);
        }
        result = JSON.toJSONString(jsonView);
        return result;
    }

    @Override
    public String getTreeData(String orgId) {
        String result = "";
        try {
            Map<String, Object> mapBean = new HashMap<String, Object>();
            mapBean.put("orgId", orgId);
            List<Entity> list = this.getDubboBaseInterface().findByMap(mapBean);
            if (list != null) {
                result = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss");
            }
            jsonView.successPack(result);
            this.addOperationLog("条件查询", orgId, true);
        } catch (Exception e) {
            jsonView.failPack(e);
            this.addOperationLog("条件查询", orgId, false);
            log.error("BaseRestServerInterfaceImpl getTreeData is error，orgId:" + orgId + "", e);
        }
        return this.dataFormat();
    }


    /**
     * 得到当前的对象class
     *
     * @return
     */
    public Class<Entity> getEntityClass() {
        return (Class<Entity>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 抽象方法需要实现，  得到基础服务接口
     *
     * @return
     */
    public abstract DubboBaseInterface<Entity> getDubboBaseInterface();

    protected String dataFormat(){
        return JSON.toJSONStringWithDateFormat(jsonView,"yyyy-MM-dd HH:mm:ss");
    }
}
