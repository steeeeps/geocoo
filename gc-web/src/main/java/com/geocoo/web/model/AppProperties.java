package com.geocoo.web.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;


/**
 * desc: app config
 *
 * @author taopy
 * @create 2018-10-04 11:30 AM
 */
@Component
public class AppProperties {

    @Value("${vectortool.tempdir}")
    private String tempDir;

    public String getTempDir() {
        return tempDir;
    }

    public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }

    public String getSourceDir(String convertId) {
        return Paths.get(tempDir, convertId, "source").toString();
    }

    public String getTargetDir(String convertId) {
        return Paths.get(tempDir, convertId, "target").toString();
    }


    @Value("${app.image.server}")
    private String imageServer;

    public String getImageServer() {
        return imageServer;
    }

    public void setImageServer(String imageServer) {
        this.imageServer = imageServer;
    }
}