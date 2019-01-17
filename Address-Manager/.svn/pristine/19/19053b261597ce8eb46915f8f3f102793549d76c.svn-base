package com.tscloud.common.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelHelper {

    private static Logger log = LoggerFactory.getLogger(ExcelHelper.class);

    /**
     * 读取Excel的列名和数据
     *
     * @param filePath excel路径
     * @return Map<String, Object>
     */
    public static Map<String, Object> readExcel(String filePath) {
        Workbook book = null;
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取excel的列信息
        List<String> column = new ArrayList<String>();
        // 获取excel的数据
        List<Object[]> data = new ArrayList<Object[]>();
        book = getExcelWorkbook(filePath);
        Sheet sheet = book.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        Row row = null;
        for (int i = 0; i <= lastRowNum; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                int lastCellNum = row.getLastCellNum();
                Cell cell = null;
                Object[] object = new Object[lastCellNum];
                for (int j = 0; j < lastCellNum; j++) {
                    cell = row.getCell(j);
                    if (cell != null) {
                        int type = cell.getCellType();
                        String value = "";
                        switch (type) {
                            case 0:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    Date date = cell.getDateCellValue();
                                    value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                                } else {
                                    double tempValue = cell.getNumericCellValue();
                                    value = String.valueOf(tempValue);
                                }
                                break;
                            case 1:
                                value = cell.getStringCellValue();
                                break;
                            case 2:
                                value = cell.getCellFormula();
                                break;
                            case 3:
                                value = cell.getStringCellValue();
                                break;
                            case 4:
                                boolean tempValue = cell.getBooleanCellValue();
                                value = String.valueOf(tempValue);
                                break;
                            case 5:
                                byte b = cell.getErrorCellValue();
                                value = String.valueOf(b);
                            default:
                                break;
                        }
                        if (i == 0) {
                            // 第一行是列名
                            column.add(value);
                        } else {
                            // 数据
                            object[j] = value;
                        }

                    }
                }
                if (i == 0) {
                    continue;
                }
                data.add(object);
            }
        }
        map.put("column", column);
        map.put("data", data);
        return map;
    }

    /**
     * 获取Workbook
     *
     * @param filePath 文件路径
     * @return Workbook
     */
    public static Workbook getExcelWorkbook(String filePath) {
        Workbook book = null;
        File file = null;
        FileInputStream fis = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                throw new RuntimeException("文件不存在");
            } else {
                fis = new FileInputStream(file);
                book = WorkbookFactory.create(fis);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return book;
    }

    public static SXSSFWorkbook getSXSSFWorkbook(Map<String, Object> map) {
        List<String> column = (List<String>) map.get("column"); // 列名
        List<Object[]> data = (List<Object[]>) map.get("data"); // 数据

        // 每当行数达到设置的值（1000）就刷新数据到硬盘,以清理内存
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        Sheet sheet = wb.createSheet();
        CellStyle cs = null;
        Font font = null;
        Row row = null;
        Cell cell = null;
        int rowNum = 0;
        Object[] objects = null;
        // 设置表头
        row = sheet.createRow(rowNum);
        for (int i = 0; i < column.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(column.get(i));
            // 设置样式
            cs = wb.createCellStyle();
            cs.setAlignment(CellStyle.ALIGN_CENTER);
            font = wb.createFont();
            font.setFontHeightInPoints((short) 13);
            cs.setFont(font);
            cell.setCellStyle(cs);
        }
        rowNum++;
        // 填充数据
        for (int i = 0; i < data.size(); i++) {
            // 创建行
            row = sheet.createRow(i + rowNum);
            objects = data.get(i);
            // 创建列
            for (int k = 0; k < objects.length; k++) {
                cell = row.createCell(k);
                if (objects[k] != null) {
                    cell.setCellValue(objects[k].toString());
                }
                // 设置每列宽度
                sheet.setColumnWidth(k, 5000);
            }
        }
        return wb;
    }

    /**
     * 通用导出请调用这个
     */
    public static byte[] wbToByte(Map<String, Object> map) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        SXSSFWorkbook wb = getSXSSFWorkbook(map);
        try {
            if (wb != null) {
                wb.write(out);
                if (out != null) {
                    out.flush();
                    out.close();
                }
                return out.toByteArray();
            }
        } catch (Exception e) {
            throw new NullPointerException("获取EXCEL信息流失败");
        }
        return null;
    }

}
