package com.geocoo.web.services;

import com.geocoo.common.utils.ResponseResult;
import com.geocoo.web.model.ConvertParams;
import org.springframework.web.multipart.MultipartFile;

/**
 * desc:
 *
 * @author taopy
 * @create 2018-10-28 7:51 PM
 */
public interface VectorToolService {

    ResponseResult vectorTranslate(MultipartFile file, ConvertParams convertParams);
    ResponseResult shpMerge(MultipartFile file);
}