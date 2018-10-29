package com.geocoo.web.controller;


import com.geocoo.common.utils.FileUtil;
import com.geocoo.common.utils.ResponseResult;
import com.geocoo.model.AppProperties;
import com.geocoo.model.ConvertParams;
import com.geocoo.services.VectorToolService;
import com.geocoo.utils.JsonParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * desc: vector tools rest api
 *
 * @author taopy
 * @create 2018-10-26 2:15 PM
 */
@RestController
@RequestMapping("/vector")
public class VectorToolsController {
    @Autowired
    AppProperties appProperties;

    @Autowired
    VectorToolService vectorToolService;


    @RequestMapping(value = "/translate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResult translate(@RequestParam("file") MultipartFile file,
                                    @JsonParam(value = "data") ConvertParams params) {
        if (file.isEmpty()) {
            return new ResponseResult(false, "", "empty file");
        }

        return vectorToolService.vectorTranslate(file, params);

    }

    @RequestMapping(value = "/shpmerge", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResult shpMerge(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseResult(false, "", "empty file");
        }

        return vectorToolService.shpMerge(file);
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void download(@PathVariable(value = "id") String id, HttpServletResponse response) {

        File resultFile = FileUtil.getZipfile(this.appProperties.getTargetDir(id));
        if (resultFile == null) {
            return;
        }

        response.setHeader("Content-Type", "application/zip");
        response.setHeader("Content-Disposition", "attachment;filename=" + resultFile.getName());
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            byte[] bytes = new byte[1024];
            OutputStream ops = response.getOutputStream();
            fis = new FileInputStream(resultFile);
            bis = new BufferedInputStream(fis);
            int i = bis.read(bytes);
            while (i != -1) {
                ops.write(bytes, 0, i);
                ops.flush();
                i = bis.read(bytes);
            }
            resultFile.delete();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}