package com.geocoo.web.controller;


import com.geocoo.earthview.model.EarthViewWebModel;
import com.geocoo.earthview.service.EarthViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * desc:
 *
 * @author taopy
 * @create 2018-11-01 11:15 PM
 */
@RestController
@RequestMapping("/earthview")
public class EarthViewController {

    @Autowired
    EarthViewService earthViewService;


    @RequestMapping(value = "/list/{page}/{size}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EarthViewWebModel> listEarthView(@PathVariable("page") int page,
                                                 @PathVariable("size") int size) {
        return earthViewService.listEarthView(page, size);

    }

    @RequestMapping(value = "/random/{size}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EarthViewWebModel> findRandom(@PathVariable("size") int size) {
        return earthViewService.randomEarthView(size);
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void download(@PathVariable("id") Integer id, HttpServletResponse response) {


        String filepath = earthViewService.getEarthviewFilepath(id);
        File file = new File(filepath);
        if (file == null) {
            return;
        }
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        ResponseUtil.downloadFile(response, file, false);

    }
}