package com.oneapm.alter.model.base;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zou on 2020/3/23.
 */
@Data
public class BaseRepEnt implements Serializable{
    private String msg;
    private Boolean result = false;
}
