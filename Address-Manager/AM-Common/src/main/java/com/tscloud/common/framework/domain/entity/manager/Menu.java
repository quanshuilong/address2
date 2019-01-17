package com.tscloud.common.framework.domain.entity.manager;

import com.tscloud.common.framework.domain.ParentGroup;

import java.io.Serializable;

/**
 * 菜单
 * Created by shm on 2015/9/9.
 */
public class Menu extends ParentGroup implements Serializable {

    private static final long serialVersionUID = 1107651403575709352L;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否选中
     */
    private String checked;

    private String chkDisabled;

    public String getChkDisabled() {

        return chkDisabled;
    }

    public void setChkDisabled(String chkDisabled) {

        this.chkDisabled = chkDisabled;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getChecked() {

        return checked;
    }

    public void setChecked(String checked) {

        this.checked = checked;
    }
}
