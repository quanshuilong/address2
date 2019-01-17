package com.tscloud.common.framework.rest;

import com.tscloud.common.framework.Exception.DubboProviderException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 * rest服务基础接口
 * @author hudaowan
 *
 */
public interface BaseRestServerInterface {
	
	/**
	 * 分页查询
	 * jsonStr格式是有map转换生成，pageJsonStr 对象jsonStr
	 * @param jsonStr
	 * @return
	 */
	@POST
    @Path("getPage")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getPage(String jsonStr);
	
	/**
	 * 获取所有数据
	 * @return
	 */
	@GET
	@Path("getAll")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String  getAll();
	
	/**
	 * 根据条件查询 
	 * 将条件实体bean转化成jsonStr
	 * @param jsonStr
	 * @return
	 */
	@POST
    @Path("getByWhere")
    @Consumes(MediaType.APPLICATION_JSON)
	public String getByWhere(String jsonStr);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@GET
	@Path("getById")
	public String getById(@QueryParam("id") String id);

	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	@GET
	@Path("getByName")
	public String getByName(@QueryParam("name") String name);
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@GET
	@Path("deleteById")
	public String deleteById(@QueryParam("id") String id);

	/**
	 * 根据id批量删除数据
	 * @param ids
	 * @return
	 */
	@GET
	@Path("deleteByIds")
	public String deleteByIds(@QueryParam("ids") String ids) throws DubboProviderException;

	/**
	 * 保存   将实体bean转化成jsonStr
	 * @param jsonStr
	 * @return
	 */
	@POST
    @Path("save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String save(String jsonStr);
	
	/**
	 * 编辑   将实体bean转化成jsonStr
	 * @param jsonStr
	 * @return
	 */
	@POST
    @Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String update(String jsonStr);

	/**
	 * 以get方式，根据条件获取树类型的数据
	 * @param orgId
	 * @return
	 */
	@GET
	@Path("getTreeData")
	public String getTreeData(@QueryParam("orgId") String orgId);

}
