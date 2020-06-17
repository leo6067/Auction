package com.aten.compiler.widget.customerDialog.entity;

import com.aten.compiler.widget.wheel.entity.IWheelEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CityEntity implements IWheelEntity, Serializable {
    private String citycode;
    private String adcode;
    private String name;
    private String center;
    private String level;
    private List<CityEntity> districts;

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<CityEntity> getDistricts() {
        return districts == null ? new ArrayList<CityEntity>(1) : districts;
    }

    public void setDistricts(List<CityEntity> districts) {
        this.districts = districts;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "citycode='" + citycode + '\'' +
                ", adcode='" + adcode + '\'' +
                ", name='" + name + '\'' +
                ", center='" + center + '\'' +
                ", level='" + level + '\'' +
                ", districts=" + districts +
                '}';
    }

    /**
     * 重点：重写此方法，返回 WheelView 显示的文字
     * @return
     */
    @Override
    public String getWheelText() {
        return name == null ? "" : name;
    }
}
