package com.geocoo.web.services;

import com.geocoo.common.utils.FileUtil;
import com.geocoo.common.utils.ResponseResult;
import com.geocoo.common.utils.ZipUtil;
import com.geocoo.web.model.AppProperties;
import com.geocoo.web.model.ConvertParams;
import com.geocoo.vectortools.model.ShapefileTranslateOptionsEnum;
import com.geocoo.vectortools.model.SupportFormatsEnum;
import com.geocoo.vectortools.model.TranslateParams;
import com.geocoo.vectortools.exceptions.VectorToolException;
import com.geocoo.vectortools.services.ShpMergeTool;
import com.geocoo.vectortools.services.VectorTranslateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * desc: vector tools wrapper
 *
 * @author taopy
 * @create 2018-10-28 7:51 PM
 */
@Service
public class VectorToolServiceImpl implements VectorToolService {

    @Autowired
    AppProperties appProperties;

    @Autowired
    VectorTranslateTool vectorTranslateTool;

    @Autowired
    ShpMergeTool shpMergeTool;

    @Override
    public ResponseResult vectorTranslate(MultipartFile file, ConvertParams convertParams) {

        String convertId = UUID.randomUUID().toString();

        try {
            String sourceFilepath = saveSourceFile(convertId, file);
            String originalFilename = file.getOriginalFilename();
            String targetFilepath = getTargetFilepath(convertId, originalFilename.substring(0, originalFilename.lastIndexOf(".")), convertParams);

            if (sourceFilepath.endsWith(SupportFormatsEnum.SHAPEFILE.getExtName())) {
                return new ResponseResult(false, convertId, "shp数据请使用.zip压缩包上传!");
            }
            List<Vector> vectors = new ArrayList<>();

            if (convertParams.getT_format().equalsIgnoreCase(SupportFormatsEnum.SHAPEFILE.getDriverName())
                    && !originalFilename.endsWith(".zip")) {

                for (ShapefileTranslateOptionsEnum option : ShapefileTranslateOptionsEnum.values()
                        ) {
                    option.getOption().addAll(convertParams.toVector());
                    vectors.add(option.getOption());
                }

            } else {
                vectors.add(convertParams.toVector());
            }

            TranslateParams translateParams = new TranslateParams(sourceFilepath, targetFilepath, vectors);

            vectorTranslateTool.translate(translateParams);

            return new ResponseResult(true, convertId, "矢量数据转换成功");
        } catch (IOException e) {
            return new ResponseResult(false, convertId, e.getMessage());

        } catch (VectorToolException e) {
            return new ResponseResult(false, convertId, e.getMessage());
        }

    }

    @Override
    public ResponseResult shpMerge(MultipartFile file) {
        String convertId = UUID.randomUUID().toString();
        try {
            String sourceFilepath = saveSourceFile(convertId, file);
            String targetDir = FileUtil.mkdirs(this.appProperties.getTargetDir(convertId));
            shpMergeTool.merge(sourceFilepath, targetDir+"/merged.shp");
            return new ResponseResult(true, convertId, "shp 数据合并成功!");
        } catch (IOException e) {
            return new ResponseResult(false, convertId, e.getMessage());
        } catch (VectorToolException e) {
            return new ResponseResult(false, convertId, e.getMessage());
        }

    }

    private String saveSourceFile(String convertId, MultipartFile file) throws IOException {
        String sourceDir = FileUtil.mkdirs(this.appProperties.getTempDir(), convertId, "source");
        String originalFilename = file.getOriginalFilename();
        String sourceFilepath = Paths.get(sourceDir, originalFilename).toString();

        FileUtil.saveFile(file.getBytes(), sourceFilepath);

        if (sourceFilepath.endsWith(".zip")) {
            ZipUtil.unzip(sourceFilepath, sourceDir);
            sourceFilepath = sourceFilepath.substring(0, sourceFilepath.lastIndexOf("."));
        }
        return sourceFilepath;

    }

    private String getTargetFilepath(String convertId, String filename, ConvertParams convertParams) {
        String targetDir = FileUtil.mkdirs(this.appProperties.getTempDir(), convertId, "target");
        String targetFilepath = Paths.get(targetDir, filename + SupportFormatsEnum.getExtnameByDriverName(convertParams.getT_format())).toString();
        return targetFilepath;
    }
}