package com.tscloud.common.utils;


import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.Enumeration;


public class ZipUtil {
    /**
     * 解压文件
     *
     * @param zipFile
     * @param dir
     * @throws IOException
     */
    public static synchronized void unZipFile(File zipFile, String dir) throws IOException {
        File pathFile = new File(dir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = new ZipFile(zipFile,"GBK");
        for (Enumeration entries = zip.getEntries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            String outPath = (dir + "/" + zipEntryName).replaceAll("\\\\", "/");
            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            //判断文件全路径是否为文件夹,如果是上面已经创建,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            // 写文件
            InputStream inputStream = zip.getInputStream(entry);
            OutputStream out = new FileOutputStream(outPath);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            inputStream.close();
            out.close();
        }
    }

    /**
     * 压缩文件
     *
     * @param resourcesPath 源文件/文件夹
     * @param targetPath    压缩文件保存路径
     * @throws IOException
     */
    public static synchronized void toZipFile(String resourcesPath, String targetPath) throws IOException {
        File resourcesFile = new File(resourcesPath);     //源文件
        File targetFile = new File(targetPath);           //目的
        //如果目的路径不存在，则新建
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        String targetName = resourcesFile.getName() + ".zip";   //目的压缩文件名
        FileOutputStream outputStream = new FileOutputStream(targetPath + "\\" + targetName);
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));
        out.setEncoding("GBK");
        toZipFile(out, resourcesFile, "");
        out.close();
    }

    /**
     * 压缩文件
     *
     * @param out
     * @param file
     * @param dir
     * @throws IOException
     */
    public static synchronized void toZipFile(ZipOutputStream out, File file, String dir) throws IOException {
        if (file.isDirectory()) {
            //如果当前的是文件夹，则进行进一步处理
            File[] files = file.listFiles();
            //将文件夹添加到下一级打包目录
            out.putNextEntry(new ZipEntry(dir + "/"));
            dir = dir.length() == 0 ? "" : dir + "/";
            //循环将文件夹中的文件打包
            for (int i = 0; i < files.length; i++) {
                //递归处理
                toZipFile(out, files[i], dir + files[i].getName());
            }
        } else {
            //当前的是文件，打包处理
            FileInputStream fis = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(dir));
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            fis.close();
        }
    }

    /**
     * 解压rar文件
     *
     * @param filePath 文件地址
     * @param dir      解压的文件地址
     */
    public static synchronized void unRarFile(String filePath, String dir) throws IOException, RarException {
        File rarFile = new File(filePath);
        unRarFile(rarFile, dir);
    }

    /**
     * 将文件解压到指定目录
     *
     * @param rarFile 解压文件
     * @param dir     解压的文件目录
     * @throws IOException
     * @throws RarException
     */
    public static synchronized void unRarFile(File rarFile, String dir) throws IOException, RarException {
        File pathFile = new File(dir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        Archive archive = new Archive(rarFile);
        if (archive != null) {
            FileHeader fileHeader = archive.nextFileHeader();
            while (fileHeader != null) {
                // 中文乱码处理
                String fileName = fileHeader.getFileNameW().isEmpty() ? fileHeader.getFileNameString() : fileHeader.getFileNameW();
                if (fileHeader.isDirectory()) {
                    // 文件夹
                    File fol = new File(dir + File.separator + fileName);
                    fol.mkdirs();
                } else {
                    // 文件
                    File out = new File(dir + File.separator + fileName.trim());
                    if (!out.exists()) {
                        if (!out.getParentFile().exists()) {
                            // 相对路径可能多级，可能需要创建父目录.
                            out.getParentFile().mkdirs();
                        }
                        out.createNewFile();
                    }
                    FileOutputStream os = new FileOutputStream(out);
                    archive.extractFile(fileHeader, os);
                    os.close();
                }
                fileHeader = archive.nextFileHeader();
            }
            archive.close();
        }
    }
}
