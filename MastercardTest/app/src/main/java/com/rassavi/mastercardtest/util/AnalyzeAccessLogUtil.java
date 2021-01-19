package com.rassavi.mastercardtest.util;

import com.rassavi.mastercardtest.model.ResponseCode;
import com.rassavi.mastercardtest.model.AccessLog;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeAccessLogUtil {

    public static String extractIPAddress(String ipString){
        String IPADDRESS_PATTERN =
                "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(ipString);
        if (matcher.find()) {
            return matcher.group();
        } else{
            return "0.0.0.0";
        }
    }

    public static ResponseCode extractResponseCode(String log){
        String[] bits = log.trim().split("\\s+");
        String responseCode =  bits[bits.length - 2];
        return ResponseCode.getResponseCode(Integer.valueOf(responseCode));
    }

    public static Map<String,Double> calculateResponseCodePercentage(Map<String, AccessLog> map, ResponseCode responseCode){
        Map<String,Double> resultMap= new HashMap<>();
        for (Map.Entry<String, AccessLog> entry : map.entrySet()) {
            int totalResponses = entry.getValue().getTotalResponseCounts();
            if (totalResponses<=0){
                continue;
            }
            int responseCodeCount = entry.getValue().getMap().containsKey(responseCode)? entry.getValue().getMap().get(responseCode):0;

            resultMap.put(entry.getKey(), (double) (responseCodeCount)/ totalResponses);
        }
        return resultMap;
    }

}
