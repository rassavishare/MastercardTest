package com.rassavi.mastercardtest.model;

public enum  ResponseCode {

    SUCCESS(200),
    FAILURE(404),
    UNKNOWN(-1);

    private int responseCode;

    ResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public static ResponseCode getResponseCode(int responseCode){
        for (ResponseCode code: ResponseCode.values()){
            if (code.responseCode == responseCode){
                return code;
            }
        }
        return UNKNOWN;
    }
}
