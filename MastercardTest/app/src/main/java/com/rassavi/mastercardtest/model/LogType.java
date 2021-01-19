package com.rassavi.mastercardtest.model;

public enum LogType {
    MESSAGE("Message"),
    NOTICE("Notice"),
    UNKNOWN("");

    private String str;

    LogType(String str) {
        this.str = str;
    }

    public static LogType getLogType(String str){
        for (LogType type: LogType.values()){
            if (type.str.equals(str)){
                return type;
            }
        }
        return UNKNOWN;
    }
}
