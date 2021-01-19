package com.rassavi.mastercardtest.model;

public class UserLog {

    private LogType logType;
    private String username;

    public UserLog() {

    }

    public UserLog(LogType logType, String username) {
        this.logType = logType;
        this.username = username;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
