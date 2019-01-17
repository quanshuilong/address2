package com.tscloud.common.utils;

import com.tscloud.common.framework.config.ConfigHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MAPUtil {

    private static Logger log = LoggerFactory.getLogger(MAPUtil.class);

    private static final String KEY = ConfigHelper.getValue("map.key");
    private static final String CITY = ConfigHelper.getValue("map.city");
    private static final String URL = ConfigHelper.getValue("map.url");

    // eg:http://api.ishowchina.com/v3/geo?address=何庙村&city=荆州市&ak=70f4c2ef2a9055b5972385c56af29f71
    // eg:http://10.168.14.95:25001/v3/geo?address=武汉火车站&ak=ec85d3648154874552835438ac6a02b2

    /**
     * 根据地址信息，查询获得该地址所对应的完整信息
     *
     * @param address
     * @param city    默认为佛山市
     * @return jsonStr
     */
    public static String getAddressInfo(String address, String city) {
        if (StringUtils.isNotBlank(address)) {
            try {
                if (StringUtils.isBlank(city)) {
                    city = CITY;
                }
                address = address.replaceAll("\\s*", ""); // 去除空格

                String urlString = URL + "/v3/geo?address=" + address.trim() + "&city=" + city + "&ak=" + KEY + "&out_coord_type=wgs84";
                log.info("MapUtil.urlString:" + urlString);
                URL url = new URL(urlString);
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                String s;
                StringBuilder sb = new StringBuilder("");
                while ((s = in.readLine()) != null) {
                    sb.append(s.trim());
                }
                in.close();
                log.info("MapUtils.response:" + sb.toString());
                return sb.toString();
            } catch (Exception e) {
                log.error("MAPUtil.getAddressInfo error", e);
            }
        }
        return null;
    }

}