package com.geocoo.vectortools.model;

import java.util.Arrays;
import java.util.Vector;

/**
 * desc: shapfile geometry typies vector translate supported
 *
 * @author taopy
 * @create 2018-10-28 8:09 PM
 */
public enum ShapefileTranslateOptionsEnum {
    POINT(new Vector(Arrays.asList(
            "-where",
            "OGR_GEOMETRY='POINT'",
            "-nlt",
            "POINT",
            "-nln",
            "POINT"
    ))),

    POLYGON(new Vector(Arrays.asList(
            "-where",
            "OGR_GEOMETRY='POLYGON'",
            "-nlt",
            "POLYGON",
            "-nln",
            "POLYGON"
    ))),

    LINESTRING(new Vector(Arrays.asList(
            "-where",
            "OGR_GEOMETRY='LINESTRING'",
            "-nlt",
            "LINESTRING",
            "-nln",
            "LINESTRING"
    )));
    private Vector option;

    public Vector getOption() {
        return option;
    }

    ShapefileTranslateOptionsEnum(Vector option) {
        this.option = option;

    }

}
