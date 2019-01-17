package com.tscloud.common.jsonobject;

import com.tscloud.common.utils.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

/**
 * 封装调用我秀地址匹配接口反馈的JSON对象
 */
public class AddressInfo {

    private String status; // 返回结果状态值，成功返回0
    private String message; // 状态说明
    private Object[] results; // 返回结果

    // 经度偏移量
    private static BigDecimal offsetlng = new BigDecimal("0.001330");
    // 纬度偏移量
    private static BigDecimal offsetlat = new BigDecimal("-0.000030");

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getResults() {
        return results;
    }

    public void setResults(Object[] results) {
        this.results = results;
    }

    /**
     * 获取JSON对象里面的key-value
     */
    public String getValue(String key) {
        Map<String, Object> results = (Map<String, Object>) this.getResults()[0];
        if (results != null) if (results.containsKey(key)) {
            return StringUtils.valueOf(results.get(key));
        }
        Map<String, Object> addresses = (Map<String, Object>) results.get("addresses");
        if (addresses.containsKey(key)) {
            return StringUtils.valueOf(addresses.get(key));
        }
        Map<String, Object> location = (Map<String, Object>) results.get("location");
        if (location.containsKey(key)) {
            return StringUtils.valueOf(shifting(key, StringUtils.valueOf(location.get(key))));
        }
        return "";
    }

    /**
     * 根据接口反馈的信息判断调用是否成功
     */
    public boolean isSuccess() {
        return ("0").equals(this.getStatus()) ? true : false;
    }

    /**
     * 坐标线性偏移
     */
    public String shifting(String key, String value) {
        BigDecimal v = new BigDecimal(value);
        if (key.equals("lng")) {
            v = v.add(offsetlng);
        } else if (key.equals("lat")) {
            v = v.add(offsetlat);
        }
        return v.toString();
    }

    @Override
    public String toString() {
        return "AddressInfo{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", results=" + Arrays.toString(results) +
                '}';
    }
}
