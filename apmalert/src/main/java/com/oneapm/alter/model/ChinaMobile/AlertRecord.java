package com.oneapm.alter.model.ChinaMobile;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by zou on 2020/9/10.
 */
@Data
public class AlertRecord {
    private String id;
    private String alarmType;
    private long sysUpTime;
    private String snmpTrapOid;
    private String objectId;
    private String location;
    private String deviceType;
    private int  notifyType;
    private int alarmLevel;
    private String alarmSystemName;
    private String alarmImpact;
    private String alarmTitle;
    private String alarmContent;
    private String alarmReason;
    private String alarmClearSteps;
    private String malfunctionType;
    private String malfunctionSubType;
    
}
