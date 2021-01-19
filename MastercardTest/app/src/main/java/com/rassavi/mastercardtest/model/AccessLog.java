package com.rassavi.mastercardtest.model;

import java.util.HashMap;
import java.util.Map;

public class AccessLog {

    private Map<ResponseCode, Integer> map=new HashMap<>();
    private int totalResponseCounts=0;

    public void increase(ResponseCode responseCode) {
        int count = map.containsKey(responseCode) ? map.get(responseCode): 0;
        map.put(responseCode, count+1);
        this.totalResponseCounts++;
    }

    public Map<ResponseCode, Integer> getMap() {
        return map;
    }

    public int getTotalResponseCounts() {
        return totalResponseCounts;
    }
}
