package com.tscloud.common.framework.service.impl;

import com.tscloud.common.framework.Exception.ServiceException;
import com.tscloud.common.framework.domain.TrackableEntity;
import com.tscloud.common.framework.mapper.BaseInterfaceMapper;
import com.tscloud.common.framework.rest.view.Page;
import com.tscloud.common.framework.service.IBaseInterfaceService;
import com.tscloud.common.utils.IDGenerator;
import com.tscloud.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class BaseInterfaceServiceImpl<Entity extends TrackableEntity> implements IBaseInterfaceService<Entity> {
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Context
    protected HttpServletResponse response;
    @Context
    protected HttpServletRequest request;

    public Page findByPage(Page page, Map<String, Object> map) throws ServiceException {
        try {
            page.setTotal(this.getBaseInterfaceMapper().getCount(map));
            map.put("startRowNum", page.getStartRowNum());
            map.put("pageSize", page.getPageSize());
            map.put("endRowNum", page.getEndRowNum());
            page.setRows(this.getBaseInterfaceMapper().findByPage(map));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return page;
    }

    public String save(Entity entity) throws ServiceException {
        String id = IDGenerator.getID();
        try {
            if (StringUtils.isBlank(entity.getId()) || entity.getId().equalsIgnoreCase("0")) {
                entity.setId(id);
            }
            entity.setCreateDate(new Date());
            this.getBaseInterfaceMapper().save(entity);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return id;
    }

    public boolean update(Entity entity) throws ServiceException {
        try {
            entity.setUpdateDate(new Date());
            this.getBaseInterfaceMapper().update(entity);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    public boolean deleteById(String id) throws ServiceException {
        try {
            this.getBaseInterfaceMapper().deleteById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    public Entity findById(String id) throws ServiceException {
        Entity entity = null;
        try {
            entity = this.getBaseInterfaceMapper().findById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return entity;
    }

    public List<Entity> findAll() throws ServiceException {
        List<Entity> list = null;
        try {
            list = this.getBaseInterfaceMapper().findAll();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return list;
    }

    public List<Entity> findByMap(Map<String, Object> map) throws ServiceException {
        List<Entity> list = null;
        try {
            list = this.getBaseInterfaceMapper().findByMap(map);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return list;
    }

    /**
     * 抽象方法需要实现，  得到基础服务接口
     *
     * @return
     */
    public abstract BaseInterfaceMapper<Entity> getBaseInterfaceMapper();

}
