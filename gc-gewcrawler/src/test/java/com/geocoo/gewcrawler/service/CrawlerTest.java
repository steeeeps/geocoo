package com.geocoo.gewcrawler.service;

import com.geocoo.gewcrawler.CrawlerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = CrawlerApplication.class)
public class CrawlerTest {

    @Autowired
    Crawler crawler;

    @Test
    public void start() {
        crawler.start();
    }
}