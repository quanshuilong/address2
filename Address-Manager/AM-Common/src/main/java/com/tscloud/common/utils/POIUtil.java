package com.tscloud.common.utils;

import com.tscloud.common.framework.config.ConfigHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class POIUtil {

    /*
        1.一级分类：http://10.168.14.60:8020/gisrest/rest/poiInfoRestServer/findChildrenByCode?poiTypeCode=1&level=1&_=1501665147583
        2.二级分类：http://10.168.14.60:8020/gisrest/rest/poiInfoRestServer/findChildrenByCode?poiTypeCode=1&code=1100000000000&level=2&_=1501665209800
        3.三级分类：http://10.168.14.60:8020/gisrest/rest/poiInfoRestServer/findChildrenByCode?poiTypeCode=1&code=1100010000000&level=3&_=1501665209801
    */

    // http://10.168.14.60:8020/gisrest/rest/gisServiceRest/updateByPoiCode?poiTypeCode=WJ_FYLYLJG

    private static Logger log = LoggerFactory.getLogger(POIUtil.class);
    private static String ADDRESS = ConfigHelper.getValue("address");

    /**
     * 获取业务图层资源
     */
    public static String getLayerResource(int level, String code) {
        try {
            String urlString = ADDRESS + "/gisrest/rest/poiInfoRestServer/findChildrenByCode";
            String param = "poiTypeCode=1&level=" + level;
            if (level != 1) {
                param += "&code=" + code;
            }
            return HttpRequest.sendGet(urlString, param);
        } catch (Exception e) {
            log.error("获取业务图层资源失败：", e);
        }
        return null;
    }

    /**
     * 获取业务图层资源占比
     */
    public static String getLayerPercent() {
        try {
            String urlString = ADDRESS + "/gisrest/rest/poiInfoRestServer/findPOITypeInfo";
            URL url = new URL(urlString);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            StringBuilder sb = new StringBuilder("");
            while ((s = in.readLine()) != null) {
                sb.append(s.trim());
            }
            in.close();
            return sb.toString();
        } catch (Exception e) {
            log.error("获取业务图层资源占比失败：", e);
        }
        return null;
    }

    /**
     * 更新图层资源发布时间
     */
    public static String updateDate(String tableId) {
        try {
            String urlString = ADDRESS + "/gisrest/rest/gisServiceRest/updateByPoiCode";
            String param = "poiTypeCode=" + tableId;
            return HttpRequest.sendGet(urlString, param);
        } catch (Exception e) {
            log.error("更新图层资源发布时间失败：", e);
        }
        return null;
    }
}
