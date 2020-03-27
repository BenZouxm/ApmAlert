package com.oneapm.alter.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zou on 2020/3/24.
 */

public class AlertClass{

    public static final String ALARM_ALETT = "alarm";
    public static final String MAJOR_ALERT = "major";
    public static final String MINOR_ALERT = "minor";
    public static final String WARN_ALERT = "warn";
    public static final String RECOVER_ALERT = "ok";
}