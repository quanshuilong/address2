package com.tscloud.common.framework.service;

import com.tscloud.common.framework.Exception.ServiceException;
import com.tscloud.common.framework.domain.TrackableEntity;
import com.tscloud.common.framework.rest.view.Page;

import java.util.List;
import java.util.Map;


public interface IBaseInterfaceService<Entity extends TrackableEntity> {

    public Page findByPage(Page page, Map<String, Object> map) throws ServiceException;

    public String save(Entity entity) throws ServiceException;

    public boolean update(Entity entity) throws ServiceException;

    public boolean deleteById(String id) throws ServiceException;

    public Entity findById(String id) throws ServiceException;

    public List<Entity> findAll() throws ServiceException;

    public List<Entity> findByMap(Map<String, Object> map) throws ServiceException;
}
