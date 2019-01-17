package com.tscloud.address.domain.entity;

import com.tscloud.common.framework.domain.TrackableEntity;

/**
 * 乡镇、街道实体 对应gdb_district表
 */
public class DistrictEntity  extends TrackableEntity {


  private Integer addrId;
  private String fullName;

  private String name;

  private  Double x;

  private Double  y;

  private String dataType;


  public Integer getAddrId() {
    return addrId;
  }

  public void setAddrId(Integer addrId) {
    this.addrId = addrId;
  }

  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }


  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getX() {
    return x;
  }

  public void setX(Double x) {
    this.x = x;
  }

  public Double getY() {
    return y;
  }

  public void setY(Double y) {
    this.y = y;
  }
}
