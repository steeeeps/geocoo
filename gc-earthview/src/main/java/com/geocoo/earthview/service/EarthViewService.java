package com.geocoo.earthview.service;


import com.geocoo.earthview.model.EarthViewWebModel;

import java.util.List;

/**
 * desc:
 *
 * @author taopy
 * @create 2018-11-01 10:26 PM
 */
public interface EarthViewService {

    List<EarthViewWebModel> listEarthView(int pageNum, int pageSize);

    List<EarthViewWebModel> randomEarthView(int size);

    String getEarthviewFilepath(int id);
}
