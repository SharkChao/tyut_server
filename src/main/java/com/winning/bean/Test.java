package com.winning.bean;

public class Test {
    private String alert_type;	//硬件编号
    private String Mac;	//数据类型
    private String  map_id;	//地图ID
    private String  org_id;	//区域ID
    private String poi_id;	//发生时间
    private String time_point;	//绑定对象编号
    private String device_id;	//绑定对象类型

    public String getAlert_type() {
        return alert_type;
    }

    public void setAlert_type(String alert_type) {
        this.alert_type = alert_type;
    }

    public String getMac() {
        return Mac;
    }

    public void setMac(String mac) {
        Mac = mac;
    }

    public String getMap_id() {
        return map_id;
    }

    public void setMap_id(String map_id) {
        this.map_id = map_id;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getPoi_id() {
        return poi_id;
    }

    public void setPoi_id(String poi_id) {
        this.poi_id = poi_id;
    }

    public String getTime_point() {
        return time_point;
    }

    public void setTime_point(String time_point) {
        this.time_point = time_point;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    @Override
    public String toString() {
        return "Test{" +
                "alert_type='" + alert_type + '\'' +
                ", Mac='" + Mac + '\'' +
                ", map_id='" + map_id + '\'' +
                ", org_id='" + org_id + '\'' +
                ", poi_id='" + poi_id + '\'' +
                ", time_point='" + time_point + '\'' +
                ", device_id='" + device_id + '\'' +
                '}';
    }
}
