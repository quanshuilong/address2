package com.tscloud.address.ui.restful;


import com.tscloud.common.framework.rest.BaseRestServerInterface;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface IDistrictRestServer  extends BaseRestServerInterface {

    @POST
    @Path("getList1")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getList(String jsonStr);


    @GET
    @Path("getList")
    @Produces(MediaType.APPLICATION_JSON)
    public String getList1(@QueryParam("addrId")Integer searchId, @QueryParam("dataType") String dataType, @QueryParam("pageSize") @DefaultValue("5") Integer pageSize, @QueryParam("pageNum") Integer pageNum);

    @GET
    @Path("getDistrict")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDistrict(@QueryParam("addrId")Integer searchId, @QueryParam("dataType") String dataType, @QueryParam("pageSize") @DefaultValue("5") Integer pageSize, @QueryParam("pageNum") Integer pageNum);

    @GET
    @Path("getVillage")
    @Produces(MediaType.APPLICATION_JSON)
    public String getVillage(@QueryParam("addrId")Integer searchId, @QueryParam("dataType") String dataType, @QueryParam("pageSize") @DefaultValue("5") Integer pageSize, @QueryParam("pageNum") Integer pageNum);

    @GET
    @Path("getRoad")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRoad(@QueryParam("addrId")Integer searchId, @QueryParam("dataType") String dataType, @QueryParam("pageSize") @DefaultValue("5") Integer pageSize, @QueryParam("pageNum") Integer pageNum);

    @GET
    @Path("getStreetno")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStreetno(@QueryParam("addrId")Integer searchId, @QueryParam("dataType") String dataType, @QueryParam("pageSize") @DefaultValue("5") Integer pageSize, @QueryParam("pageNum") Integer pageNum);


    @GET
    @Path("getCommunity")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCommunity(@QueryParam("addrId")Integer searchId, @QueryParam("dataType") String dataType, @QueryParam("pageSize") @DefaultValue("5") Integer pageSize, @QueryParam("pageNum") Integer pageNum);



    @GET
    @Path("getLandmark")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLandmark(@QueryParam("addrId")Integer searchId, @QueryParam("dataType") String dataType, @QueryParam("pageSize") @DefaultValue("5") Integer pageSize, @QueryParam("pageNum") Integer pageNum);


    @GET
    @Path("getBuilding")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBuilding(@QueryParam("addrId")Integer searchId, @QueryParam("dataType") String dataType, @QueryParam("pageSize") @DefaultValue("5") Integer pageSize, @QueryParam("pageNum") Integer pageNum);





}
