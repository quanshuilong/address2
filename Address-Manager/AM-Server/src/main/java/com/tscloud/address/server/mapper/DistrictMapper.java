package com.tscloud.address.server.mapper;

import com.tscloud.address.domain.entity.DistrictEntity;
import com.tscloud.common.framework.mapper.BaseInterfaceMapper;

import java.util.List;
import java.util.Map;

/**
 * @author Leighton
 * @create 2018-12-28.
 */
public interface DistrictMapper  extends BaseInterfaceMapper<DistrictEntity> {

    /**
     * 街道办数据
     * @param params
     * @return
     */
    List<DistrictEntity> getDistrict(Map<String,Object> params);

    /***
     * 社区居委会
     * @param params
     * @param
     */
    List<DistrictEntity> getVillage(Map<String,Object> params);

    /**
     * 获取道路
     * @param params
     * @return
     */
    List<DistrictEntity> getRoad(Map<String,Object> params);

    /**
     * 获取路号
     * @param params
     * @return
     */
    List<DistrictEntity> getStreetno(Map<String,Object> params);

    /**
     * 小区数据
     * @param params
     * @return
     */
    List<DistrictEntity> getCommunity(Map<String,Object> params);

    /**
     * 标记物
     * @param params
     * @return
     */
    List<DistrictEntity> getLandmark(Map<String,Object> params);

    /**
     * 楼栋
     * @param params
     * @return
     */
    List<DistrictEntity> getBuilding(Map<String,Object> params);

//    List<DistrictEntity> getRoomUnit(Map<String,Object> params);
}
