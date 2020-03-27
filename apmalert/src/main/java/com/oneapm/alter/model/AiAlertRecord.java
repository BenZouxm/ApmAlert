package com.oneapm.alter.model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.Data;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


/**
 * Created by zou on 2020/3/24.
 */
@Data
public class AiAlertRecord implements Serializable {

    private String webhookName;
    private Boolean acceptanceStatus;
    private HashMap<String,Object> alertObject;
    private String alertPolicy;
    private String alertRule;
    private String appName;
    private long createTime;
    private String eventId;
    private String eventType;
    private String infoSource;
    private String nodeType;
    private ArrayList<String> realTimeIndex;
    private String alertObjectStatus;
    private ArrayList<String> ruleContent;


}
