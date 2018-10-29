package com.geocoo.vectortools.services;


import com.geocoo.vectortools.model.TranslateParams;
import com.geocoo.vectortools.exceptions.VectorToolException;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.VectorTranslateOptions;
import org.gdal.gdal.gdal;
import org.gdal.gdalconst.gdalconst;
import org.springframework.stereotype.Service;

import java.util.Vector;

/**
 * desc: vector translate tool interface implemented by gdal.VectorTranslate method
 *
 * @author taopy
 * @create 2018-09-19 上午8:17
 */

@Service
public class VectorTranslateToolImpl implements VectorTranslateTool {


    @Override
    public void translate(TranslateParams params) throws VectorToolException {
        try {
            gdal.AllRegister();
            Dataset sourceDs = gdal.OpenEx(params.getSource(), gdalconst.OF_VECTOR | gdalconst.OF_VERBOSE_ERROR);
            for (Vector option : params.getOptions()) {
                Dataset targetDs = gdal.VectorTranslate(params.getTarget(), sourceDs, new VectorTranslateOptions(option));
                targetDs.FlushCache();
            }

        } catch (Exception e) {
            throw new VectorToolException("矢量数据转换失败，" + e.getMessage());
        }
    }


}