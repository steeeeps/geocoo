package com.geocoo.vectortools.model;


/**
 * desc:
 * ogc formats vector translate supported
 *
 * @author taopy
 * @create 2018-10-28 10:41 AM
 */
public enum SupportFormatsEnum {
    DXF("DXF", ".dxf", true),
    CSV("CSV", ".csv", true),
    SHAPEFILE("ESRI Shapefile", ".shp", true),
    GeoJSON("GeoJSON", ".geojson", true);

    SupportFormatsEnum(String driverName, String extName, Boolean supported) {
        this.driverName = driverName;
        this.extName = extName;
        this.supported = supported;
    }

    private String driverName;
    private String extName;
    private Boolean supported;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public Boolean getSupported() {
        return supported;
    }

    public void setSupported(Boolean supported) {
        this.supported = supported;
    }

    public static String getExtnameByDriverName(String driverName) {
        for (SupportFormatsEnum f : SupportFormatsEnum.values()) {
            if (f.getDriverName().equalsIgnoreCase(driverName)) {
                return f.getExtName();
            }
        }
        return "";
    }
}
