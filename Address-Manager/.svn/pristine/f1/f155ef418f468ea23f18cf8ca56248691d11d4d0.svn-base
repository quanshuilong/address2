package com.tscloud.common.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 *文件名过滤器气
 */
public class FileNameSelector implements FilenameFilter {
    String[] extentions = null;

    public FileNameSelector(String fileExtensionNoDot) {
        extentions = fileExtensionNoDot.split(";");
    }

    @Override
    public boolean accept(File dir, String name) {
        if (extentions == null) {
            return true;
        }
        String extendtion = name.substring(name.lastIndexOf(".") + 1, name.length());
        for (String e : extentions) {
            if (e.equals(extendtion))
                return true;
        }
        return false;
    }
}

/**
 * 文件过滤器
 *
 * @author Administrator
 */
class FileSelector implements FileFilter {
    String[] extentions = null;

    public FileSelector(String fileExtensionNoDot) {
        extentions = fileExtensionNoDot.split(";");
    }

    @Override
    public boolean accept(File pathname) {
        if (extentions == null) {
            return true;
        }
        String name = pathname.getName();
        String extendtion = name.substring(name.lastIndexOf(".") + 1, name.length() - 1);
        for (String e : extentions) {
            if (e.equals(extendtion)) {
                return true;
            }
        }
        return false;
    }

}
