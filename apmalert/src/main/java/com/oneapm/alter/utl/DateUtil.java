package com.oneapm.alter.utl;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zou on 2020/3/26.
 */
public class DateUtil {

    public static Date calOverDate( String seconds){
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.SECOND, Integer.parseInt(seconds));// 5分钟之前的时间
        Date beforeD = beforeTime.getTime();
        return beforeD;
    }

    public static void main(String[] argv){
        System.out.print(calOverDate("-300").toString());
    }
}
