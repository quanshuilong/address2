package com.tscloud.common.framework.config;

import com.tscloud.common.framework.Constants;
import com.tscloud.common.utils.DateUtil;
import com.tscloud.common.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;


/**
 * 资源文件帮助类，加载配置信息
 */
public class ConfigHelper {
    private static final Logger log = LoggerFactory.getLogger(ConfigHelper.class);
    private static Properties properties;
    //容器参数
    private static Properties serverProperties;

    private static String filePath;

    static {
        System.out.println("[" + DateUtil.getDateTime() + "] Loading config.properties");
        try {
            filePath = FileUtil.getFilePath("config.properties", Constants.Env.BASE_HOME);
        } catch (Exception e) {
            //ignore
        }

        //容器参数配置
        serverProperties = loadProperties("jettyServer.properties");
        //系统参数配置
        properties = loadProperties("config.properties");

    }


    public static Properties loadProperties(String fileName) {
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream(FileUtil.getFile(fileName, Constants.Env.BASE_HOME));
            prop.load(input);
        } catch (Exception e) {
            log.error("Loading config.properties fails", e);
        }

        return prop;
    }

    /**
     * 加载系统参数
     *
     * @param key
     * @return
     */
    public static String getJettyParameter(String key) {
        return serverProperties.getProperty(key);
    }


    public static String getValue(String key) {
        String value = null;
        try {
            value = new String(properties.getProperty(key).getBytes("ISO-8859-1"), "UTF-8").trim();
        } catch (Exception e) {
            log.error("key:" + key + " 资源参数加载失败！", e);
        }
        return value;

    }

    /**
     * @param key
     * @param value
     */
    public static void setProperties(String key, String value) {
        try {
            FileInputStream input = new FileInputStream(filePath);
            SafeProperties safeProp = new SafeProperties();
            safeProp.load(input);
            input.close();
            if (!"".equals(value) && value != null) {
                // safeProp.addComment("New Comment测试");
                safeProp.put(key, value);
            }
            if (key != null) {
                if (value == null || "".equals(value)) {
                    safeProp.remove(key);
                }
            }
            FileOutputStream output = new FileOutputStream(filePath);
            safeProp.store(output, null);
            output.close();
        } catch (Exception e) {
            log.error("Visit " + filePath + " for updating " + key + " value error", e.getMessage());
        }

    }

    /**
     * 删除
     *
     * @param key
     */
    public static void removeProperties(String key) {
        try {
            FileInputStream input = new FileInputStream(filePath);
            SafeProperties safeProp = new SafeProperties();
            safeProp.load(input);
            input.close();
            if (key != null) {
                safeProp.remove(key);
            }
            FileOutputStream output = new FileOutputStream(filePath);
            safeProp.store(output, null);
            output.close();
        } catch (Exception e) {
            log.error("Visit " + filePath + " for updating " + key + " value error", e.getMessage());
        }

    }

}
