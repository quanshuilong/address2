package com.tscloud.common.framework.domain;

import com.tscloud.common.utils.DateUtil;

import java.util.Date;
import java.util.List;

public class Excel {
    private String title;           // 表头
    private String fileName;        // 导出的文件名称
    private String[] postName;      // excel的列名
    private String[] paraName;      // 填充的对象-属性名称
    private List<?> data;           // 填充的数据对象

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String[] getPostName() {
        return postName;
    }

    public void setPostName(String[] postName) {
        this.postName = postName;
    }

    public String[] getParaName() {
        return paraName;
    }

    public void setParaName(String[] paraName) {
        this.paraName = paraName;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public void setExcelByContrastTask() {
        this.setTitle(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        this.setFileName("对比结果统计报表.xlsx");
        this.setPostName(new String[]{"分类名称", "新增数量", "地址修改数量", "其他修改数量", "删除数量"});
        this.setParaName(new String[]{"genreName", "addAmount", "addressUpdAmount", "otherUpdAmount", "delAmount"});
    }

    public void setExcelByAddressMatching() {
        this.setTitle(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        this.setFileName("地址匹配分析统计报表.xlsx");
        this.setPostName(new String[]{"分类名称", "1-省级", "2-地市级", "3-区县级", "4-乡镇、街道级", "5-村-（居）级", "6-开发区级", "7-商圈级", "8-道路线级", "9-道路点级",
                "10-支路级", "11-插值级", "12-交叉口级", "13-附近级", "14-支门牌级", "15-兴趣点级", "16-楼栋号级", "17-门牌级", "未匹配成功"});
        this.setParaName(new String[]{"genreName", "levOneAdds", "levOneUpds", "levTwoAdds", "levTwoUpds", "levThreeAdds", "levThreeUpds", "levFourAdds", "levFourUpds",
                "levFiveAdds", "levFiveUpds", "levSixAdds", "levSixUpds", "levSevenAdds", "levSevenUpds", "levEightAdds", "levEightUpds",
                "levNineAdds", "levNineUpds", "levTenAdds", "levTenUpds", "levElevenAdds", "levElevenUpds", "levTwelveAdds", "levTwelveUpds", "levThirteenAdds",
                "levThirteenUpds", "levFourteenAdds", "levFourteenUpds", "levFifteenAdds", "levFifteenUpds", "levSixteenAdds", "levSixteenUpds", "levSeventeenAdds",
                "levSeventeenUpds", "levEighteenAdds", "levEighteenUpds"});
    }

    public void setExcelByDataPublish(int status) {
        this.setTitle(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        String str = "";
        if (status == 0) {
            str = "待发布";
        } else if (status == 1) {
            str = "已发布";
        } else if (status == 2) {
            str = "待核实";
        }
        this.setFileName(str + "数据统计报表.xlsx");
        this.setPostName(new String[]{"分类名称", "发布数量"});
        this.setParaName(new String[]{"genreName", "amount"});
    }
}
