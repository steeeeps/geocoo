package com.geocoo.gewcrawler.service;

import com.geocoo.earthview.model.EarthView;
import com.geocoo.earthview.model.EarthviewConfig;
import com.geocoo.earthview.repositories.EarthViewRepository;
import com.geocoo.gewcrawler.model.CrawlerConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * desc:
 * check image file is equal to db
 *
 * @author taopy
 * @create 2018-11-04 11:35 AM
 */
public class EarthviewInspector {

    @Autowired
    EarthViewRepository repository;
    @Autowired
    CrawlerConfig crawlerConfig;

    @Autowired
    Crawler crawler;

    public void inspect() {
        List<EarthView> lists = repository.findAll();
        lists.stream().forEach(earthView -> checkEarthview(earthView));


    }

    private void checkEarthview(EarthView earthview) {
        int id = earthview.getId();
        String thumbPath = Paths.get(crawlerConfig.getImageBasedir(), "thumb", id + ".jpg").toString();
        String fullPath = Paths.get(crawlerConfig.getImageBasedir(), "full", id + ".jpg").toString();


        //re downlod
        try {
            if (!isNormalImage(thumbPath))
                crawler.downloadImg("https://www.gstatic.com/prettyearth/assets/preview/" + id + ".jpg", "thumb");
            if (!isNormalImage(fullPath)) {
                crawler.downloadImg("https://www.gstatic.com/prettyearth/assets/full/" + id + ".jpg", "full");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private boolean isNormalImage(String filepath) {
        File file = new File(filepath);
        if (!file.exists() || file.length() != 0) {
            return false;
        }

        return true;
    }
}