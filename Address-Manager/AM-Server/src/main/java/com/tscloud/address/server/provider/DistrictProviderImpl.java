package com.tscloud.address.server.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.tscloud.address.domain.entity.DistrictEntity;
import com.tscloud.address.domain.entity.SearchParams;
import com.tscloud.address.domain.provider.IDistrictProvider;
import com.tscloud.address.domain.service.IDistrictService;
import com.tscloud.common.framework.Exception.DubboProviderException;
import com.tscloud.common.framework.Exception.ServiceException;
import com.tscloud.common.framework.dubbo.impl.DubboBaseInterfaceImpl;
import com.tscloud.common.framework.service.IBaseInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DistrictProviderImpl extends DubboBaseInterfaceImpl<DistrictEntity> implements IDistrictProvider {

    @Autowired
    private IDistrictService districtService;

    @Override
    public IBaseInterfaceService<DistrictEntity> getBaseInterfaceService() {
        return this.districtService;
    }

    @Override
    public PageInfo<DistrictEntity> findAllByPage(SearchParams params) throws DubboProviderException {
        try {
            return districtService.findAllByPage(params);
        }catch (ServiceException e){
            log.error("DistrictProviderImpl.findAllByPage error ", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
    }


    @Override
    public PageInfo<DistrictEntity> getDistrict(SearchParams params) throws DubboProviderException {
        try {
            return districtService.getDistrict(params);
        }catch (ServiceException e){
            log.error("DistrictProviderImpl.getDistrict error ", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
    }

    @Override
    public PageInfo<DistrictEntity> getVillage(SearchParams params) throws DubboProviderException {
        try {
            return districtService.getVillage(params);
        }catch (ServiceException e){
            log.error("DistrictProviderImpl.getVillage error ", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
    }

    @Override
    public PageInfo<DistrictEntity> getRoad(SearchParams params) throws DubboProviderException {
        try {
            return districtService.getRoad(params);
        }catch (ServiceException e){
            log.error("DistrictProviderImpl.getRoad error ", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
    }

    @Override
    public PageInfo<DistrictEntity> getStreetno(SearchParams params) throws DubboProviderException {
        try {
            return districtService.getStreetno(params);
        }catch (ServiceException e){
            log.error("DistrictProviderImpl.getStreetno error ", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
    }

    @Override
    public PageInfo<DistrictEntity> getCommunity(SearchParams params) throws DubboProviderException {
        try {
            return districtService.getCommunity(params);
        }catch (ServiceException e){
            log.error("DistrictProviderImpl.getCommunity error ", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
    }

    @Override
    public PageInfo<DistrictEntity> getLandmark(SearchParams params) throws DubboProviderException {
        try {
            return districtService.getLandmark(params);
        }catch (ServiceException e){
            log.error("DistrictProviderImpl.getLandmark error ", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
    }

    @Override
    public PageInfo<DistrictEntity> getBuilding(SearchParams params) throws DubboProviderException {
        try {
            return districtService.getBuilding(params);
        }catch (ServiceException e){
            log.error("DistrictProviderImpl.getBuilding error ", e);
            throw new DubboProviderException(e.getMessage(), e);
        }
    }
}
