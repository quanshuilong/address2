package com.tscloud.address.domain.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 行政级别枚举
 */
public enum DistrictLevel {

    firstLevel(1,"省、自治区、直辖市、特别行政区"),
    secondLevel(2,"市"),
    thirdLevel(3,"县、区"),
    fourLevel(4,"镇、乡、街道"),
    fiveLevel(5,"村、社区");


    private  Integer level;
    private String name;

    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    DistrictLevel( Integer level,String name) {
        this.name=name;
        this.level=level;
    }


    public static Map<Integer,Object>  enumToMap(){
        Map<Integer,Object> map=new HashMap<>();
         for(DistrictLevel l: DistrictLevel.values()){
             map.put(l.getLevel(),l.getName());
        }
        return map;
    }

}

