package com.geocoo.earthview.service;


import com.geocoo.earthview.model.EarthView;
import com.geocoo.earthview.model.EarthViewWebModel;
import com.geocoo.earthview.model.EarthviewConfig;
import com.geocoo.earthview.repositories.EarthViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * desc:
 *
 * @author taopy
 * @create 2018-11-01 10:35 PM
 */
@Service
public class EarthViewServiceImpl implements EarthViewService {
    @Autowired
    EarthviewConfig earthviewConfig;

    @Autowired
    EarthViewRepository repository;


    @Override
    public List<EarthViewWebModel> listEarthView(int pageNum, int pageSize) {
        Page<EarthView> result = repository.findAll(PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "id")));
        return result.getContent().stream().map(e -> createWebModel(e)).collect(Collectors.toList());
    }

    @Override
    public List<EarthViewWebModel> randomEarthView(int size) {
        return repository.findRadom(size).stream().map(e -> createWebModel(e)).collect(Collectors.toList());


    }

    @Override
    public String getEarthviewFilepath(int id) {

        String filepathh = Paths.get(earthviewConfig.getImageBasedir(), "full", id + ".jpg").toString();
        return filepathh;

    }

    private EarthViewWebModel createWebModel(EarthView earthView) {
        EarthViewWebModel model = new EarthViewWebModel(earthView);
        model.setThumbUrl(earthviewConfig.getImageServer() + "/thumb/" + model.getId() + ".jpg");
        model.setFullUrl(earthviewConfig.getImageServer() + "/full/" + model.getId() + ".jpg");
        return model;
    }

}