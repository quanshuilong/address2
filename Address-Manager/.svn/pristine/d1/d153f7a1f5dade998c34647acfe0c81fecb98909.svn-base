package com.tscloud.common.utils;

import com.tscloud.common.framework.domain.Excel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExportUtil {

    /**
     * 通用导出请调用这个
     */
    public static byte[] export(Excel excel) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        SXSSFWorkbook wb = getSXSSFWorkbook(excel);
        try {
            if (wb != null) {
                wb.write(out);
                if (out != null) {
                    out.flush();
                    out.close();
                }
                return out.toByteArray();
            } else {
                throw new NullPointerException("获取导出信息流为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SXSSFWorkbook getSXSSFWorkbook(Excel excel) {
        String[] postName = excel.getPostName();
        String[] paraName = excel.getParaName();
        List<?> data = excel.getData();
        // 每当行数达到设置的值（1000）就刷新数据到硬盘,以清理内存
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        Sheet sheet = wb.createSheet();
        CellStyle cs = null;
        Font font = null;
        Row row = null;
        Cell cell = null;
        int rowNum = 0;
        try {
            // 设置标题
            if (!StringUtils.isBlank(excel.getTitle())) {
                // 合并行（首行，尾行，首列，尾列）
                CellRangeAddress cra = new CellRangeAddress(0, 0, 0, excel.getPostName().length - 1);
                sheet.addMergedRegion(cra);
                row = sheet.createRow(rowNum);
                cell = row.createCell(rowNum);
                cell.setCellValue(excel.getTitle());
                // 设置样式
                cs = wb.createCellStyle();
                font = wb.createFont();
                cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
                cs.setAlignment(CellStyle.ALIGN_LEFT);
                font.setFontHeightInPoints((short) 15);
                cs.setFont(font);
                cell.setCellStyle(cs);
                rowNum++;
            }
            // 设置表头
            if (excel.getPostName().length > 0) {
                row = sheet.createRow(rowNum);
                for (int j = 0; j < postName.length; j++) {
                    cell = row.createCell(j);
                    cell.setCellValue(postName[j].toString());
                    // 设置样式
                    cs = wb.createCellStyle();
                    cs.setAlignment(CellStyle.ALIGN_CENTER);
                    font = wb.createFont();
                    font.setFontHeightInPoints((short) 13);
                    cs.setFont(font);
                    cell.setCellStyle(cs);
                }
                rowNum++;
            } else {
                throw new NullPointerException("未设置表头");
            }
            for (int i = rowNum; i < data.size() + rowNum; i++) {
                // 创建行
                row = sheet.createRow(i);
                // 创建列
                for (int k = 0; k < paraName.length; k++) {
                    Field field = data.get(i - rowNum).getClass().getDeclaredField(paraName[k]);
                    // 判断对象是否可以访问
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    cell = row.createCell(k);
                    if (field.get(data.get(i - rowNum)) != null) {
                        if (field.getType().toString().equals("class java.util.Date")) {
                            // 如果是日期格式，需要转换
                            Date date = (Date) field.get(data.get(i - rowNum));
                            Calendar c = Calendar.getInstance();
                            c.setTime(date);
                            if (c.get(Calendar.HOUR) == 0 && c.get(Calendar.MINUTE) == 0 && c.get(Calendar.SECOND) == 0) {
                                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(date));
                            } else {
                                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                            }
                        } else if (field.getType().toString().equals("int")) {
                            cell.setCellValue(Integer.valueOf(field.get(data.get(i - rowNum)).toString()));
                        } else {
                            cell.setCellValue(field.get(data.get(i - rowNum)).toString());
                        }
                    } else {
                        cell.setCellValue("");
                    }
                    // 设置每列宽度
                    if (k == 0) {
                        sheet.setColumnWidth(k, 10000);
                    } else {
                        sheet.setColumnWidth(k, 3000);
                    }
                }
            }
            return wb;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 导出地址匹配结果（非通用）
     */
    public static byte[] exportWb(Excel excel) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        SXSSFWorkbook wb = getWorkBook(excel);
        try {
            if (wb != null) {
                wb.write(out);
                if (out != null) {
                    out.flush();
                    out.close();
                }
                return out.toByteArray();
            } else {
                throw new NullPointerException("获取导出信息流为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 导出地址匹配结果
     */
    public static SXSSFWorkbook getWorkBook(Excel excel) {
        String[] postName = excel.getPostName();
        String[] paraName = excel.getParaName();
        List<?> data = excel.getData();
        // 每当行数达到设置的值（1000）就刷新数据到硬盘,以清理内存
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        Sheet sheet = wb.createSheet();
        CellStyle cs = null;
        Font font = null;
        Row row = null;
        Cell cell = null;
        int rowNum = 0;
        try {
            // 设置标题
            if (!StringUtils.isBlank(excel.getTitle())) {
                // 合并行（首行，尾行，首列，尾列）
                CellRangeAddress cra = new CellRangeAddress(0, 0, 0, excel.getPostName().length * 2 - 2);
                sheet.addMergedRegion(cra);
                row = sheet.createRow(rowNum);
                cell = row.createCell(rowNum);
                cell.setCellValue(excel.getTitle());
                // 设置样式
                cs = wb.createCellStyle();
                font = wb.createFont();
                cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
                cs.setAlignment(CellStyle.ALIGN_LEFT);
                font.setFontHeightInPoints((short) 15);
                cs.setFont(font);
                cell.setCellStyle(cs);
                rowNum++;
            }
            // 设置表头
            if (excel.getPostName().length > 0) {
                CellRangeAddress cra = null;
                for (int j = 0; j < postName.length; j++) {
                    if (j == 0) {
                        // 第一列合并两行
                        cra = new CellRangeAddress(1, 2, 0, 0);
                    } else {
                        // 大于第一列的合并两列
                        cra = new CellRangeAddress(1, 1, j * 2 - 1, j * 2);
                    }
                    sheet.addMergedRegion(cra);
                }
                row = sheet.createRow(rowNum);
                for (int j = 0; j < postName.length; j++) {
                    if (j == 0) {
                        cell = row.createCell(j);
                    } else {
                        cell = row.createCell(2 * j - 1);
                    }
                    cell.setCellValue(postName[j].toString());
                    // 设置样式
                    cs = wb.createCellStyle();
                    cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
                    cs.setAlignment(CellStyle.ALIGN_CENTER);
                    font = wb.createFont();
                    font.setFontHeightInPoints((short) 13);
                    cs.setFont(font);
                    cell.setCellStyle(cs);

                }
                rowNum++;
                row = sheet.createRow(rowNum);
                for (int j = 1; j <= postName.length * 2 - 2; j++) {
                    cell = row.createCell(j);
                    cell.setCellValue(j % 2 == 0 ? "修改" : "新增");
                    // 设置样式
                    cs = wb.createCellStyle();
                    cs.setAlignment(CellStyle.ALIGN_CENTER);
                    font = wb.createFont();
                    font.setFontHeightInPoints((short) 13);
                    cs.setFont(font);
                    cell.setCellStyle(cs);
                }
                rowNum++;
            } else {
                throw new NullPointerException("未设置表头");
            }
            for (int i = rowNum; i < data.size() + rowNum; i++) {
                // 创建行
                row = sheet.createRow(i);
                // 创建列
                for (int k = 0; k < paraName.length; k++) {
                    Field field = data.get(i - rowNum).getClass().getDeclaredField(paraName[k]);
                    // 判断对象是否可以访问
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    cell = row.createCell(k);
                    if (field.get(data.get(i - rowNum)) != null) {
                        if (field.getType().toString().equals("class java.util.Date")) {
                            // 如果是日期格式，需要转换
                            Date date = (Date) field.get(data.get(i - rowNum));
                            Calendar c = Calendar.getInstance();
                            c.setTime(date);
                            if (c.get(Calendar.HOUR) == 0 && c.get(Calendar.MINUTE) == 0 && c.get(Calendar.SECOND) == 0) {
                                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(date));
                            } else {
                                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                            }
                        } else if (field.getType().toString().equals("int")) {
                            cell.setCellValue(Integer.valueOf(field.get(data.get(i - rowNum)).toString()));
                        } else {
                            cell.setCellValue(field.get(data.get(i - rowNum)).toString());
                        }
                    } else {
                        cell.setCellValue("");
                    }
                    // 设置每列宽度
                    if (k == 0) {
                        sheet.setColumnWidth(k, 10000);
                    } else {
                        sheet.setColumnWidth(k, 3000);
                    }
                }
            }
            return wb;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
