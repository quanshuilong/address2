package com.tscloud.address.domain.service;

import com.github.pagehelper.PageInfo;
import com.tscloud.address.domain.entity.DistrictEntity;
import com.tscloud.address.domain.entity.SearchParams;
import com.tscloud.common.framework.Exception.ServiceException;
import com.tscloud.common.framework.service.IBaseInterfaceService;



public interface IDistrictService extends IBaseInterfaceService<DistrictEntity> {

    PageInfo<DistrictEntity> findAllByPage(SearchParams params) throws ServiceException;

    PageInfo<DistrictEntity> getDistrict(SearchParams params) throws ServiceException;

    PageInfo<DistrictEntity> getVillage(SearchParams params) throws ServiceException;

    PageInfo<DistrictEntity> getRoad(SearchParams params) throws ServiceException;

    PageInfo<DistrictEntity> getStreetno(SearchParams params) throws ServiceException;

    PageInfo<DistrictEntity> getCommunity(SearchParams params) throws ServiceException;

    PageInfo<DistrictEntity> getLandmark(SearchParams params) throws ServiceException;

    PageInfo<DistrictEntity> getBuilding(SearchParams params) throws ServiceException;



}
