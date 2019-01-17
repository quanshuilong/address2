package com.tscloud.address.ui.restful.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.tscloud.address.domain.entity.DistrictEntity;
import com.tscloud.address.domain.entity.SearchParams;
import com.tscloud.address.domain.provider.IDistrictProvider;
import com.tscloud.address.domain.utils.ConstantUtils;
import com.tscloud.address.ui.restful.IDistrictRestServer;
import com.tscloud.common.framework.Exception.DubboProviderException;
import com.tscloud.common.framework.config.ConfigHelper;
import com.tscloud.common.framework.dubbo.DubboBaseInterface;
import com.tscloud.common.framework.rest.impl.BaseRestServerInterfaceImpl;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path(ConstantUtils.REST_PATH_PREFIX+"search")
public class DistrictRestServerImpl  extends BaseRestServerInterfaceImpl<DistrictEntity> implements IDistrictRestServer {

    @Reference
    private IDistrictProvider districtProvider;

    @Override
    public DubboBaseInterface<DistrictEntity> getDubboBaseInterface() {
        return this.districtProvider;
    }

    @Override
    public String getList(String jsonStr) {
        try {
            SearchParams params = JSON.parseObject(jsonStr, SearchParams.class);
        PageInfo<DistrictEntity> page=this.districtProvider.findAllByPage(params);
            jsonView.successPack(page);
        } catch (DubboProviderException e) {
            jsonView.failPack("获取列表失败");
            this.log.error("DistrictRestServerImpl.getDataList error", e);
            e.printStackTrace();
        }
        return this.dataFormat();
    }

    @Override
    public String getList1(Integer searchId,String dataType,Integer pageSize,Integer pageNum) {
        try {
            SearchParams params=new SearchParams(pageSize,pageNum,searchId,dataType);
            PageInfo<DistrictEntity> page=this.districtProvider.findAllByPage(params);
            jsonView.successPack(page);
        } catch (DubboProviderException e) {
            jsonView.failPack("获取列表失败");
            this.log.error("DistrictRestServerImpl.getDataList error", e);
            e.printStackTrace();
        }
         return this.dataFormat();
    }


    /**
     * 街道办数据
     * @return
     */
    @Override
    public String getDistrict(Integer searchId,String dataType,Integer pageSize,Integer pageNum){
        try {
            String id=ConfigHelper.getValue("district.default.id");
            searchId = id.equals("")?null:Integer.valueOf(id);
            SearchParams params=new SearchParams(pageSize,pageNum,searchId,dataType);
            PageInfo<DistrictEntity> page=this.districtProvider.getDistrict(params);
            jsonView.successPack(page);
        } catch (DubboProviderException e) {
            jsonView.failPack("获取街道列表失败");
            this.log.error("DistrictRestServerImpl.getDistrict error", e);
            e.printStackTrace();
        }
        return this.dataFormat();
    }

    /**
     *社区居委会
     * @return
     */
    @Override
    public String getVillage(Integer searchId,String dataType,Integer pageSize,Integer pageNum){
        try {
            SearchParams params=new SearchParams(pageSize,pageNum,searchId,dataType);
            PageInfo<DistrictEntity> page=this.districtProvider.getVillage(params);
            jsonView.successPack(page);
        } catch (DubboProviderException e) {
            jsonView.failPack("获取街道列表失败");
            this.log.error("DistrictRestServerImpl.getVillage error", e);
            e.printStackTrace();
        }
        return this.dataFormat();
    }

    /**
     * 获取道路
     * @return
     */
    @Override
    public String getRoad(Integer searchId,String dataType,Integer pageSize,Integer pageNum){
        try {
            SearchParams params=new SearchParams(pageSize,pageNum,searchId,dataType);
            PageInfo<DistrictEntity> page=this.districtProvider.getRoad(params);
            jsonView.successPack(page);
        } catch (DubboProviderException e) {
            jsonView.failPack("获取路列表失败");
            this.log.error("DistrictRestServerImpl.getRoad error", e);
            e.printStackTrace();
        }
        return this.dataFormat();
    }

    /**
     * 获取路号
     * @return
     */
    @Override
    public String getStreetno(Integer searchId,String dataType,Integer pageSize,Integer pageNum){
        try {
            SearchParams params=new SearchParams(pageSize,pageNum,searchId,dataType);
            PageInfo<DistrictEntity> page=this.districtProvider.getStreetno(params);
            jsonView.successPack(page);
        } catch (DubboProviderException e) {
            jsonView.failPack("获取路号列表失败");
            this.log.error("DistrictRestServerImpl.getStreetno error", e);
            e.printStackTrace();
        }
        return this.dataFormat();
    }

    /**
     * 小区数据
     * @return
     */
    @Override
    public String getCommunity(Integer searchId,String dataType,Integer pageSize,Integer pageNum){
        try {
            SearchParams params=new SearchParams(pageSize,pageNum,searchId,dataType);
            PageInfo<DistrictEntity> page=this.districtProvider.getCommunity(params);
            jsonView.successPack(page);
        } catch (DubboProviderException e) {
            jsonView.failPack("获取小区列表失败");
            this.log.error("DistrictRestServerImpl.getCommunity error", e);
            e.printStackTrace();
        }
        return this.dataFormat();
    }

    /**
     * 标记物
     * @return
     */
    @Override
    public String getLandmark(Integer searchId,String dataType,Integer pageSize,Integer pageNum){
        try {
            SearchParams params=new SearchParams(pageSize,pageNum,searchId,dataType);
            PageInfo<DistrictEntity> page=this.districtProvider.getLandmark(params);
            jsonView.successPack(page);
        } catch (DubboProviderException e) {
            jsonView.failPack("获取门牌号楼层房号列表失败");
            this.log.error("DistrictRestServerImpl.getRoomUnit error", e);
            e.printStackTrace();
        }
        return this.dataFormat();
    }

    /**
     * 楼栋
     * @return
     */
    @Override
    public String getBuilding(Integer searchId,String dataType,Integer pageSize,Integer pageNum){
        try {
            SearchParams params=new SearchParams(pageSize,pageNum,searchId,dataType);
            PageInfo<DistrictEntity> page=this.districtProvider.getBuilding(params);
            jsonView.successPack(page);
        } catch (DubboProviderException e) {
            jsonView.failPack("获取楼栋列表失败");
            this.log.error("DistrictRestServerImpl.getBuilding error", e);
            e.printStackTrace();
        }
        return this.dataFormat();
    }


}
