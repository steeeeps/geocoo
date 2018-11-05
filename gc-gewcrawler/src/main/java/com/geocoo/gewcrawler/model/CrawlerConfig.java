package com.geocoo.gewcrawler.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * desc:
 *
 * @author taopy
 * @create 2018-10-30 3:31 PM
 */
@Component
public class CrawlerConfig {

    @Value("${crawler.startjson}")
    private String startJson;
    @Value("${crawler.earthViewHost}")
    private String earthViewHost;
    @Value("${proxy.host}")
    private String proxyHost;
    @Value("${proxy.port}")
    private Integer proxyPort;
    @Value("${proxy.username}")
    private String proxyUsername;
    @Value("${proxy.pwd}")
    private String proxyPwd;

    @Value("${crawler.imageBasedir}")
    private String imageBasedir;

    public String getStartJson() {
        return startJson;
    }

    public void setStartJson(String startJson) {
        this.startJson = startJson;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public String getEarthViewHost() {
        return earthViewHost;
    }

    public void setEarthViewHost(String earthViewHost) {
        this.earthViewHost = earthViewHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public String getProxyPwd() {
        return proxyPwd;
    }

    public void setProxyPwd(String proxyPwd) {
        this.proxyPwd = proxyPwd;
    }

    public String getImageBasedir() {
        return imageBasedir;
    }

    public void setImageBasedir(String imageBasedir) {
        this.imageBasedir = imageBasedir;
    }


}