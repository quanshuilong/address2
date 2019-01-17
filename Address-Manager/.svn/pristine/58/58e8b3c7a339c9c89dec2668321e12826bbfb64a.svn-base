package com.tscloud.address.ui.utils;

import com.tscloud.common.framework.config.ConfigHelper;
import com.tscloud.common.utils.IDGenerator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Leighton
 * @create 2018-11-16.
 */
public class UploadFileHandle {
    private final static long FILE_SIZE_MAX = 1024 * 1024 * Long.valueOf(ConfigHelper.getValue("file.size.max"));
    private final static long SIZE_MAX = 1024 * 1024 * (Long.valueOf(ConfigHelper.getValue("file.size.max")) + 10);
    public final static String FILE_SAVE_PATH = ConfigHelper.getValue("file.save.path");

    /**
     * 文件上传
     *
     * @param request
     * @return 文件保存路径
     */
    public static Map<String, String> upload(HttpServletRequest request) {
        File uploadDir = new File(FILE_SAVE_PATH);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1000 * 3);//设置缓冲区的大小为3M，如果不指定，那么缓冲区的大小默认是10KB
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        // 设置上传单个文件的大小的最大值
        upload.setFileSizeMax(FILE_SIZE_MAX);
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(SIZE_MAX);
        Map<String, String> map = new HashMap<>();
        String filePath = null;
        try {
            // 解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                if (item.isFormField()) {
                    map.put(item.getFieldName(), item.getString());
                    // 如果fileitem中封装的是普通输入项的数据,则不处理
                    continue;
                } else {
                    // 防止文件名重复，文件名后加一个UUID
                    String fileName = item.getName();
                    String suffix = fileName.substring(fileName.lastIndexOf("."));
                    fileName = fileName.substring(0, fileName.lastIndexOf(".")) + IDGenerator.getID() + suffix;
                    filePath = FILE_SAVE_PATH + File.separator + fileName;
                    File file = new File(filePath);
                    // 保存文件
                    item.write(file);
                    map.put("filePath", filePath);
                    map.put("status", "success");
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            map.put("status", "faile");
            map.put("msg", "单个文件大小超出最大值");
        } catch (Exception e) {
            map.put("status", "faile");
            map.put("msg", "文件上传失败");
        }
        return map;
    }

    //递归删除文件夹
    public static void deleteFile(File file) {
        if (file.exists()) {//判断文件是否存在
            if (file.isFile()) {//判断是否是文件
                file.delete();//删除文件
            } else if (file.isDirectory()) {//否则如果它是一个目录
                File[] files = file.listFiles();//声明目录下所有的文件 files[];
                for (int i = 0;i < files.length;i ++) {//遍历目录下所有的文件
                    deleteFile(files[i]);//把每个文件用这个方法进行迭代
                }
                file.delete();//删除文件夹
            }
        } else {
            System.out.println("所删除的文件不存在");
        }
    }

    public static void deleteAllFilesOfDir(File path) {
        if (!path.exists())
            return;
        if (path.isFile()) {
            path.delete();
            return;
        }
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            deleteAllFilesOfDir(files[i]);
        }
        path.delete();
    }
}
