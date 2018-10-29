package com.geocoo.model;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * desc: format and spatial reference translate params of vector data
 *
 * @author taopy
 * @create 2018-10-28 7:53 PM
 */
public class ConvertParams {
    private String s_srs;
    private String t_srs;
    private String t_format;
    private Boolean skipfailures;
    private List<String> options;

    public String getS_srs() {
        return s_srs;
    }

    public void setS_srs(String s_srs) {
        this.s_srs = s_srs;
    }

    public String getT_srs() {
        return t_srs;
    }

    public void setT_srs(String t_srs) {
        this.t_srs = t_srs;
    }

    public String getT_format() {
        return t_format;
    }

    public void setT_format(String t_format) {
        this.t_format = t_format;
    }

    public Boolean getSkipfailures() {
        return skipfailures;
    }

    public void setSkipfailures(Boolean skipfailures) {
        this.skipfailures = skipfailures;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public ConvertParams() {
        this.options = Arrays.asList(
                "-lco",
                "ENCODING=UTF-8");
    }


    public Vector toVector() {
        Vector params = new Vector();
        params.add("-f");
        params.add(this.getT_format());
        if (!StringUtils.isEmpty(this.getS_srs())) {
            params.add("-s_srs");
            params.add(this.getS_srs());
        }
        if (!StringUtils.isEmpty(this.getT_srs())) {
            params.add("-t_srs");
            params.add(this.getT_srs());
        }
        if (this.getSkipfailures()) {
            params.add("-skipfailures");
        }
        if (!StringUtils.isEmpty(this.options)) {

            params.addAll(this.options);
        }
        return params;
    }
}