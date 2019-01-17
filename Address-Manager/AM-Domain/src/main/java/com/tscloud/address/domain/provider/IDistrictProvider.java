package com.tscloud.address.domain.provider;


import com.github.pagehelper.PageInfo;
import com.tscloud.address.domain.entity.DistrictEntity;
import com.tscloud.address.domain.entity.SearchParams;
import com.tscloud.common.framework.Exception.DubboProviderException;
import com.tscloud.common.framework.dubbo.DubboBaseInterface;

public interface IDistrictProvider extends DubboBaseInterface<DistrictEntity> {

    PageInfo<DistrictEntity> findAllByPage(SearchParams params) throws DubboProviderException;

    PageInfo<DistrictEntity> getDistrict(SearchParams params) throws DubboProviderException;

    PageInfo<DistrictEntity> getVillage(SearchParams params) throws DubboProviderException;

    PageInfo<DistrictEntity> getRoad(SearchParams params) throws DubboProviderException;

    PageInfo<DistrictEntity> getStreetno(SearchParams params) throws DubboProviderException;

    PageInfo<DistrictEntity> getCommunity(SearchParams params) throws DubboProviderException;

    PageInfo<DistrictEntity> getLandmark(SearchParams params) throws DubboProviderException;

    PageInfo<DistrictEntity> getBuilding(SearchParams params) throws DubboProviderException;

}
