package com.geocoo.vectortools.model;

import java.util.List;
import java.util.Vector;

/**
 * desc:
 * gdal vector translate params
 *
 * @author taopy
 * @create 2018-10-13 4:46 PM
 */
public class TranslateParams {

    private String source;
    private String target;
    private List<Vector> options;

    public TranslateParams(){}
    public TranslateParams(String source, String target, List<Vector> options) {
        this.source = source;
        this.target = target;
        this.options = options;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<Vector> getOptions() {
        return options;
    }

    public void setOptions(List<Vector> options) {
        this.options = options;
    }
}