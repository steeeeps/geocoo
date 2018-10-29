package com.geocoo.vectortools.services;

import com.geocoo.vectortools.exceptions.VectorToolException;
import com.geocoo.vectortools.model.SupportFormatsEnum;
import org.gdal.ogr.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * desc: shapefile merge tool implements
 *
 * @author taopy
 * @create 2018-10-29 9:44 AM
 */
@Service
public class ShpMergeToolImpl implements ShpMergeTool {


    @Override
    public void merge(String sourceDir, String resultFilepath) throws IOException, VectorToolException {

        List<Path> filePaths = Files.walk(Paths.get(sourceDir))
                .filter((filename) -> filename.toString().endsWith(SupportFormatsEnum.SHAPEFILE.getExtName()))
                .collect(Collectors.toList());

        try {
            ogr.RegisterAll();

            Driver driver = ogr.GetDriverByName(SupportFormatsEnum.SHAPEFILE.getDriverName());
            DataSource targetDS = null;
            Layer resultLayer = null;
            int idx = 0;
            for (Path filepath : filePaths) {
                String path = filepath.toString();
                if (idx == 0) {
                    targetDS = driver.CopyDataSource(ogr.Open(path), resultFilepath);
                    resultLayer = targetDS.GetLayer(0);
                    idx++;
                } else {
                    DataSource ds = ogr.Open(path);
                    Layer layer = ds.GetLayer(0);
                    Feature f;
                    while ((f = layer.GetNextFeature()) != null) {
                        resultLayer.CreateFeature(f);
                    }
                }
            }
            targetDS.SyncToDisk();

        } catch (Exception e) {
            throw new VectorToolException("shp 数据合并失败," + e.getMessage());
        }
    }
}