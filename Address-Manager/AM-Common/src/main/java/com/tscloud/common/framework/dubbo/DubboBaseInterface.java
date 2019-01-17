package com.tscloud.common.framework.dubbo;

import com.tscloud.common.framework.Exception.DubboProviderException;
import com.tscloud.common.framework.domain.TrackableEntity;
import com.tscloud.common.framework.rest.view.Page;

import java.util.List;
import java.util.Map;

/**
 * dubbo抽象接口，  所有dubbo的接口都需要继承
 * @param <Entity>
 */
public interface DubboBaseInterface<Entity extends TrackableEntity>{

	/**
	 * 分页查询
	 * @param page
	 * @param map
	 * @return
	 * @throws DubboProviderException
	 */
    public Page findByPage(Page page, Map<String, Object> map)throws DubboProviderException;

	/**
	 * 持久化对象的信息
	 *  @param entity
	 * @return
	 * @throws DubboProviderException
	 */
	public String save(Entity entity) throws DubboProviderException;

	/**
	 * 修改对象的信息
	 * @param entity
	 * @return
	 * @throws DubboProviderException
	 */
	public boolean update(Entity entity)throws DubboProviderException;

	/**
	 * 根据ID 删除指定的对象
	 * @param id
	 * @return
	 * @throws DubboProviderException
	 */
	public boolean deleteById(String id)throws DubboProviderException;

	/**
	 * 根据ID 查找指定的对象
	 * @param id
	 * @return
	 * @throws DubboProviderException
	 */
	public Entity findById(String id)throws DubboProviderException;

	/**
	 * 得到所有的对象
	 * @return
	 * @throws DubboProviderException
	 */
	public List<Entity> findAll()throws DubboProviderException;

	/**
	 * 用于多条件查询
	 * @param map
	 * @return
	 * @throws DubboProviderException
	 */
	public List<Entity> findByMap(Map<String, Object> map)throws DubboProviderException;



}
