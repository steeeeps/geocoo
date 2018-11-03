package com.geocoo.gewcrawler.service;

import okhttp3.OkHttpClient;

import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * desc: build okhttpclient with proxy
 *
 * @author taopy
 * @create 2018-10-30 2:36 PM
 */
public class OKHttpClientProxyable {
    public static OkHttpClient build(String ip, int port, String username, String pwd) {
        Proxy proxy = new Proxy(Proxy.Type.SOCKS,
                new InetSocketAddress(ip, port));
        java.net.Authenticator.setDefault(new java.net.Authenticator() {
            private PasswordAuthentication authentication = new PasswordAuthentication(username, pwd.toCharArray());

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return authentication;
            }
        });
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(180, TimeUnit.SECONDS)
                .proxy(proxy).build();

        return client;
    }

}