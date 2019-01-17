package com.tscloud.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;


public class HttpRequest {
   private  static Logger logger= LoggerFactory.getLogger(HttpRequest.class);
    /**
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) throws IOException{
        logger.info("get请求的url:{}",URLDecoder.decode(url,"UTF-8"));
        System.out.println(URLDecoder.decode(url,"UTF-8"));
        String result = "";
        URLConnection connection = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
//            connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.9");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            // 建立实际的连接
            connection.connect();
            result = readStrByCode(connection.getInputStream(), "UTF-8");
        } catch (IOException e) {
            logger.error("发送GET请求出现异常！{}",e);
            throw e;
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @param params 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url,String params) throws IOException {
        if (url.indexOf("?")<0){
            return sendGet(url + "?" + params);
        }
        return sendGet(url + "&" + params);
    }



    public static String readStrByCode(InputStream is, String code) {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new InputStreamReader(is, code));
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) throws IOException {
        logger.info("post请求的url:{}",url);
        PrintWriter out = null;
        BufferedReader in = null;
        String resultMap = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                resultMap += line;
            }
        } catch (IOException e) {
            logger.error("发送 POST 请求出现异常！{}",e);
            throw e;
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                throw ex;
            }
        }
        return resultMap;
    }


    public static void main(String[] args) {
        String url="http://192.168.100.28:8008/?opt=adddata&address=广东省珠海市香洲区前山街道翠前新村&adcode=440402&level=6&x=113.51844874555556&y=22.268632811944443&type=170000";
        try {
        // URLEncoder.encode(url, "utf-8")

           String result=sendGet(url);
          JSONObject json=JSON.parseObject(result);

            System.out.println(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}