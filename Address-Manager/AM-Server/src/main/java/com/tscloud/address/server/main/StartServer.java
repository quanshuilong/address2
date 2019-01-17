package com.tscloud.address.server.main;

import com.tscloud.address.server.utils.StartJetty;

/**
 * 启动AM-Server
 */
public class StartServer {

    public static void main(String[] args) throws Exception {
        StartJetty.getInstance().startJetty();
    }

}