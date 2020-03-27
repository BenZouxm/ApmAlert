package com.oneapm.alter.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zou on 2020/3/24.
 */
@Data
public class TaiYueRecord {
    private String host;
    private String instance;
    private String parameter;
    //need change the classAlter name into class when get the name
    @SerializedName("class")
    private String classAlter;
    private String occurtime;
    //状态（必填）枚举值：
    private String Status;
    private String ressearchtype;

}
