package com.oneapm.alter.entity;

import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by zou on 2020/3/24.
 */
@Data
public class AlertRecord implements Serializable{
    private long id;
    private String alertId;
    private String record;
    private Date recvTime;
    private Date sendTime;
    private boolean isSend;
    private String sendRep;
    private Integer alertSoureCode;
    private Integer reSendTimes;


}
