package com.geocoo.earthview.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * desc:
 *
 * @author taopy
 * @create 2018-11-03 10:15 PM
 */
@Component
@Configuration
@PropertySource("classpath:earthview.properties")
public class EarthviewConfig {

    @Value("${app.image.server}")
    private String imageServer;

    @Value("${app.image.basedir}")
    private String imageBasedir;

    public String getImageServer() {
        return imageServer;
    }

    public void setImageServer(String imageServer) {
        this.imageServer = imageServer;
    }

    public String getImageBasedir() {
        return imageBasedir;
    }

    public void setImageBasedir(String imageBasedir) {
        this.imageBasedir = imageBasedir;
    }
}