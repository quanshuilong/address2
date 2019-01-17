package com.tscloud.common.utils;

import java.util.UUID;

public class Tools {

    public static String createUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取最大值
     *
     * @param number
     * @return int
     */
    public static int max(int... number) {
        int temp = number[0];
        for (int i = 0; i < number.length; i++) {
            if (number[i] > temp) {
                temp = number[i];
            }
        }
        return temp;
    }

    /**
     * 合理计算每个线程需要处理的数据数量
     *
     * @param count 数据总数
     * @return int
     */
    public static int getThreaPoolSize(int count) {
        int size;
        if (count <= 500) {
            size = 100;
        } else if (count <= 1000) {
            size = 200;
        } else if (count <= 10000) {
            size = 500;
        } else {
            size = 1000;
        }
        return size;
    }

}
