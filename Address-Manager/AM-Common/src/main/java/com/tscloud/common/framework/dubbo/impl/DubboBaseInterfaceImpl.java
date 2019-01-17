package com.tscloud.common.framework.dubbo.impl;

import com.tscloud.common.framework.Exception.DubboProviderException;
import com.tscloud.common.framework.Exception.ServiceException;
import com.tscloud.common.framework.domain.TrackableEntity;
import com.tscloud.common.framework.dubbo.DubboBaseInterface;
import com.tscloud.common.framework.rest.view.Page;
import com.tscloud.common.framework.service.IBaseInterfaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hudaowan on 2015/2/10.
 */
public abstract class DubboBaseInterfaceImpl<Entity extends TrackableEntity> implements DubboBaseInterface<Entity> {
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Page findByPage(Page page, Map<String, Object> map) throws DubboProviderException {
        Page pageLoad = new Page();
        try {
            pageLoad = this.getBaseInterfaceService().findByPage(page, map);
        } catch (ServiceException e) {
            log.error("DubboBaseInterfaceImpl findByPage ", e);
            throw new DubboProviderException(e.getMessage(),e);
        }
        return pageLoad;
    }

    @Override
    public String save(Entity entity) throws DubboProviderException {
       String id = null;
        try {
            id = this.getBaseInterfaceService().save(entity);
        } catch (ServiceException e) {
            log.error("DubboBaseInterfaceImpl save ",e);
            throw new DubboProviderException(e.getMessage(),e);
        }
        return id;
    }

    @Override
    public boolean update(Entity entity) throws DubboProviderException {
        boolean flag = false;
        try {
            flag = this.getBaseInterfaceService().update(entity);
        } catch (ServiceException e) {
            log.error("DubboBaseInterfaceImpl update ", e);
            throw new DubboProviderException(e.getMessage(),e);
        }
        return flag;
    }

    @Override
    public boolean deleteById(String id) throws DubboProviderException {
        boolean flag = false;
        try {
            flag = this.getBaseInterfaceService().deleteById(id);
        } catch (ServiceException e) {
            log.error("DubboBaseInterfaceImpl deleteById ", e);
            throw new DubboProviderException(e.getMessage(),e);
        }
        return flag;
    }

    @Override
    public Entity findById(String id) throws DubboProviderException {
        Entity  entity = null;
        try {
            entity = this.getBaseInterfaceService().findById(id);
        } catch (ServiceException e) {
            log.error("DubboBaseInterfaceImpl findById ", e);
            throw new DubboProviderException(e.getMessage(),e);
        }
        return entity;
    }

    @Override
    public List<Entity> findAll() throws DubboProviderException {
        List<Entity> list = new ArrayList<Entity>();
        try {
            list = this.getBaseInterfaceService().findAll();
        } catch (ServiceException e) {
            log.error("DubboBaseInterfaceImpl findAll ", e);
            throw new DubboProviderException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List<Entity> findByMap(Map<String, Object> map) throws DubboProviderException {
        List<Entity> list = new ArrayList<Entity>();
        try {
            list = this.getBaseInterfaceService().findByMap(map);
        } catch (ServiceException e) {
            log.error("DubboBaseInterfaceImpl findByMap", e);
            throw new DubboProviderException(e.getMessage(),e);
        }
        return list;
    }

    /**
     * 抽象方法需要实现，  得到基础服务接口
     * @return
     */
    public abstract IBaseInterfaceService<Entity> getBaseInterfaceService();
}
