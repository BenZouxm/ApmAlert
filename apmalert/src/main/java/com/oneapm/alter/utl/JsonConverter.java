package com.oneapm.alter.utl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.oneapm.alter.model.AiAlertRecord;

import java.util.Date;

/**
 * Created by zou on 2020/3/24.
 */
public class JsonConverter {
    public static String beanToJson(Object bean) {
        return new Gson().toJson(bean);
    }

    public static Object jsonToObject(String json,Class beanClass) {
        Gson gson = new Gson();
        json = formatJsonStr(json);
        Object res = gson.fromJson(json, beanClass);
        return res;
    }

    public static String formatJsonStr(String content) {
        String content_1 = content.replace("\\", "");
        char c1 = content_1.charAt(0);
        char c2 = content_1.charAt(content_1.length() - 1);
        String content_2;
        if (String.valueOf(c1).equals("\"") && String.valueOf(c2).equals("\"")) {
            content_2 = content_1.substring(1, content_1.length() - 1);
        } else {
            content_2 = content_1;
        }
        /**
         * 去除telephone中的双引号！
         * "{\"telephone\":[18618470480,2451234,,3123423]}"
         *
         * {"telephone":[18618470480,2451234,,3123423]}
         */
        String replace = content_2.replace("\"{\"", "{\"");
        return replace.replace("]}\"", "]}");
    }


    public static void main(String[] args){
        String testJsonStr = "{\n" +
                "\t\"acceptanceStatus\": false,\n" +
                "\t\"alertObject\": {\n" +
                "\t\t\"agentName\": \"java:dc-quartz:8080(172.20.13.238)\",\n" +
                "\t\t\"metricName\": \"\",\n" +
                "\t\t\"systemId\": 2,\n" +
                "\t\t\"systemName\": \"O2O-Business\",\n" +
                "\t\t\"tierId\": 78,\n" +
                "\t\t\"tierName\": \"dc-quartz\"\n" +
                "\t},\n" +
                "\t\"alertObjectStatus\": \"正常\",\n" +
                "\t\"alertPolicy\": \"O2O-Business告警测试\",\n" +
                "\t\"alertRule\": \"O2O-BusinessJVMCPU使用率和堆内存使用率告警\",\n" +
                "\t\"appName\": \"O2O-Business\",\n" +
                "\t\"createTime\": 1585123720000,\n" +
                "\t\"eventId\": \"d1b461ec-e9f8-471d-b91f-54969aefce64\",\n" +
                "\t\"eventType\": \"警告报警关闭事件\",\n" +
                "\t\"infoSource\": \"ONEAPM\",\n" +
                "\t\"nodeType\": \"node\",\n" +
                "\t\"other\": \"{\\\"telephone\\\":[null]}\",\n" +
                "\t\"realTimeIndex\": [\"JVM CPU使用率:1.575%\", \"JVM堆内存平均使用率:59.852%\"],\n" +
                "\t\"ruleContent\": [\"严重 : 过去 5 分钟，JVM CPU使用率 的 平均值 大于等于 77.0%\", \"严重 : 过去 5 分钟，JVM堆内存使用率 的 平均值 大于等于 66.0%\", \"警告 : 过去 5 分钟，JVM CPU使用率 的 平均值 大于等于 70.0%\", \"警告 : 过去 5 分钟，JVM堆内存使用率 的 平均值 大于等于 60.0%\"],\n" +
                "\t\"webhookName\": \"test\"\n" +
                "}" ;

        AiAlertRecord aiAlertRecord = (AiAlertRecord) jsonToObject(testJsonStr,AiAlertRecord.class);
        System.out.print(aiAlertRecord.getAcceptanceStatus());
        Date currentDate = new Date();
        System.out.print(currentDate);
    }

}
