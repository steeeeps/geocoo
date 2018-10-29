package com.geocoo.vectortools.services;

import com.geocoo.vectortools.exceptions.VectorToolException;

import java.io.IOException;

/**
 * desc: shapefile merge tool interface
 *
 * @author taopy
 * @create 2018-10-29 9:54 AM
 */
public interface ShpMergeTool {
    void merge(String sourceDir, String resultFilepath) throws IOException, VectorToolException;
}
