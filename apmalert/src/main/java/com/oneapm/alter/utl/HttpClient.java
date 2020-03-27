package com.oneapm.alter.utl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * Created by zou on 2020/3/24.
 */
@Slf4j
public class HttpClient {
    public static String sendXmlByPut(String url, String xmlParam) throws Exception{
        log.info("url="+url);
        log.info("xmlParm="+xmlParam);
        int status = 0;
        HttpResponse<String> response = Unirest.put(url)
                .header("content-type", "application/xml")
                .header("cache-control", "no-cache")
                .body(xmlParam)
                .asString();
        status = response.getStatus();
        if(status == 200){
            return response.getBody();
        }else{
            log.error("请求异常");
            throw new Exception("请求异常");
        }
    }

    public static HashMap<String,Object> sendApplicationByPost(String url, String queryParam, String jsonStr)throws Exception{
        log.info("url="+url+",queryParam is"+queryParam);
        log.info("jsonStr= "+jsonStr);
        HttpResponse<String> response = Unirest.post(url+queryParam)
                .header("content-type", "application/json")
                .header("cache-control", "no-cache")
                .body(jsonStr)
                .asString();
        int status = response.getStatus();
        HashMap<String,Object> result = new HashMap<String,Object>();
        log.info("reponse's code is :"+status+" repsponse body is "+ response.getBody());
        if(status == 200){
            log.info("send request successfully the repsonse is"+response.getBody());
        }else{
            log.error("send request unsuccessful ，the response is "+response.getBody());
        }
        result.put("status",status);
        result.put("repContent",response.getBody());
        return result;
    }

}
