package com.geocoo.gewcrawler.service;

import com.alibaba.fastjson.JSON;
import com.geocoo.common.utils.FileUtil;
import com.geocoo.earthview.model.EarthView;
import com.geocoo.earthview.repositories.EarthViewRepository;
import com.geocoo.gewcrawler.model.CrawlerConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

/**
 * desc:
 * crawler service
 *
 * @author taopy
 * @create 2018-10-30 8:06 AM
 */
@Component
public class Crawler {

    @Autowired
    CrawlerConfig crawlerConfig;

    @Autowired
    EarthViewRepository repository;


    private OkHttpClient client;

    @PostConstruct
    private void initClient() {
        this.client = OKHttpClientProxyable.build(
                crawlerConfig.getProxyHost(),
                crawlerConfig.getProxyPort(),
                crawlerConfig.getProxyUsername(),
                crawlerConfig.getProxyPwd()
        );
    }

    public void start() {

        try {
            String startApi = crawlerConfig.getStartJson();
            EarthView latest = getLatestEarthview();
            if (latest != null) {
                startApi = latest.getNextApi();
            }
            fetchEarthview(crawlerConfig.getEarthViewHost() + startApi);

        } catch (IOException e) {
            System.err.println(e.getMessage());

        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("start again");
            this.start();
        }

    }

    private EarthView fetchEarthview(String url) throws IOException, InterruptedException {
        Request request = new Request.Builder().url(url).build();

        Response response = null;
        response = client.newCall(request).execute();
        while (!response.isSuccessful()) {
            System.out.println("try again...");
            Thread.sleep(1000 * 60 * 2);
            response = client.newCall(request).execute();
        }


        String body = response.body().string();
        EarthView earthView = JSON.parseObject(body, EarthView.class);

        saveEarthview(earthView);


        if (!StringUtils.isEmpty(earthView.getNextApi())) {
            Thread.sleep(1000 * 40 );
            fetchEarthview(crawlerConfig.getEarthViewHost() + earthView.getNextApi());
        } else {
            System.out.println("done");
        }
        return earthView;
    }

    @Transactional(rollbackOn = Exception.class)
    private void saveEarthview(EarthView earthView) throws IOException {
        if (!repository.findById(earthView.getId()).isPresent()) {
            earthView.setFetchtime(new Date());
            repository.save(earthView);
            downloadAllImg(earthView);
        }

    }

    private void downloadAllImg(EarthView earthView) throws IOException {
        downloadImg(earthView.getThumbUrl(), "thumb");

        downloadImg(earthView.getPhotoUrl(), "full");

    }

    private void downloadImg(String url, String type) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException(String.format("fail to download image , type : %s , url : %s", type, url));

        }
        String path = FileUtil.mkdirs(crawlerConfig.getImageBasedir(), type);
        String filename = url.substring(url.lastIndexOf("/"), url.length());
        FileOutputStream fos = new FileOutputStream(Paths.get(path, filename).toString());
        fos.write(response.body().bytes());
        fos.close();
    }

    private EarthView getLatestEarthview() {

        Page<EarthView> lists = repository.findLatest(PageRequest.of(0, 1));
        if (lists != null && lists.hasContent()) {
            return lists.getContent().get(0);
        }
        return null;
    }

}