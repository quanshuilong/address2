package com.tscloud.address.server.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tscloud.address.domain.entity.DistrictEntity;
import com.tscloud.address.domain.entity.SearchParams;
import com.tscloud.address.domain.service.IDistrictService;
import com.tscloud.address.server.mapper.DistrictMapper;
import com.tscloud.common.framework.Exception.ServiceException;
import com.tscloud.common.framework.mapper.BaseInterfaceMapper;
import com.tscloud.common.framework.service.impl.BaseInterfaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DistrictServiceImpl extends BaseInterfaceServiceImpl<DistrictEntity> implements IDistrictService {

    @Autowired
  private DistrictMapper districtMapper;

    @Override
    public BaseInterfaceMapper<DistrictEntity> getBaseInterfaceMapper() {
        return this.districtMapper;
    }

    @Override
    public PageInfo<DistrictEntity> findAllByPage(SearchParams params) throws ServiceException {
        List<DistrictEntity> result=null;
        //不通的添加查询不同的阶段数据
        Map<String,Object> param=new HashMap<>();
        param.put("addrId",params.getSearchId());
        param.put("dataType",params.getDataType());

        //默认调用 获取街道
        if (true){
            PageHelper.startPage(params.getPageNum(), params.getPageSize(), true);
            result=districtMapper.getDistrict(param);
        }

//        if (params.getDataType())



        return new PageInfo<>(result);
    }

    /**
     * 街道办数据
     * @param params
     * @return
     * @throws ServiceException
     */
    @Override
    public PageInfo<DistrictEntity> getDistrict(SearchParams params) throws ServiceException {
        Map<String,Object> param=new HashMap<>();
        param.put("addrId",params.getSearchId());
        PageHelper.startPage(params.getPageNum(), params.getPageSize(), true);
        return new PageInfo<>(districtMapper.getDistrict(param));
    }

    /**
     *  社区居委会
     * @param params
     * @return
     * @throws ServiceException
     */
    @Override
    public PageInfo<DistrictEntity> getVillage(SearchParams params) throws ServiceException {
        Map<String,Object> param=new HashMap<>();
        param.put("addrId",params.getSearchId());
        PageHelper.startPage(params.getPageNum(), params.getPageSize(), true);
        return new PageInfo<>(districtMapper.getVillage(param));
    }

    /**
     *  获取道路
     * @param params
     * @return
     * @throws ServiceException
     */
    @Override
    public PageInfo<DistrictEntity> getRoad(SearchParams params) throws ServiceException {
        Map<String,Object> param=new HashMap<>();
        param.put("addrId",params.getSearchId());
        PageHelper.startPage(params.getPageNum(), params.getPageSize(), true);
        return new PageInfo<>(districtMapper.getRoad(param));
    }

    /**
     * 获取路号
     * @param params
     * @return
     * @throws ServiceException
     */
    @Override
    public PageInfo<DistrictEntity> getStreetno(SearchParams params) throws ServiceException {
        Map<String,Object> param=new HashMap<>();
        param.put("addrId",params.getSearchId());
        PageHelper.startPage(params.getPageNum(), params.getPageSize(), true);
        return new PageInfo<>(districtMapper.getStreetno(param));
    }

    /**
     * 小区数据
     * @param params
     * @return
     * @throws ServiceException
     */
    @Override
    public PageInfo<DistrictEntity> getCommunity(SearchParams params) throws ServiceException {
        Map<String,Object> param=new HashMap<>();
        param.put("addrId",params.getSearchId());
        PageHelper.startPage(params.getPageNum(), params.getPageSize(), true);
        return new PageInfo<>(districtMapper.getCommunity(param));
    }

    /**
     * 标记物
     * @param params
     * @return
     * @throws ServiceException
     */
    @Override
    public PageInfo<DistrictEntity> getLandmark(SearchParams params) throws ServiceException {
        Map<String,Object> param=new HashMap<>();
        param.put("addrId",params.getSearchId());
        PageHelper.startPage(params.getPageNum(), params.getPageSize(), true);
        return new PageInfo<>(districtMapper.getLandmark(param));
    }

    /**
     * 楼栋
     * @param params
     * @return
     * @throws ServiceException
     */
    @Override
    public PageInfo<DistrictEntity> getBuilding(SearchParams params) throws ServiceException {
        Map<String,Object> param=new HashMap<>();
        param.put("addrId",params.getSearchId());
        PageHelper.startPage(params.getPageNum(), params.getPageSize(), true);
        return new PageInfo<>(districtMapper.getBuilding(param));
    }
}
