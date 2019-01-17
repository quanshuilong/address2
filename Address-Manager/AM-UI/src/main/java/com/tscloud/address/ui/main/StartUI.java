package com.tscloud.address.ui.main;

import com.tscloud.address.ui.utils.StartJetty;

/**
 * 启动ADDR-UI
 */
public class StartUI {

    public static void main(String[] args) throws Exception {
        StartJetty.getInstance().startJetty();
    }

}