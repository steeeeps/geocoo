package com.geocoo.vectortools.services;


import com.geocoo.vectortools.model.TranslateParams;
import com.geocoo.vectortools.exceptions.VectorToolException;

/**
 * desc: vector translate tool interface
 *
 * @author taopy
 * @create 2018-10-13 8:38 PM
 */
public interface VectorTranslateTool {
    void translate(TranslateParams params) throws VectorToolException;

}
