package com.rassavi.mastercardtest.util;

import com.rassavi.mastercardtest.model.LogType;
import com.rassavi.mastercardtest.model.UserLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseStatLogUtil {

    public static UserLog geLogInfo(String log){
        // Pattern
        // [Notice][Todd] has entered the room
        // [Message][Todd] wrote a 7 character message

        Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(log);

        boolean typeReceived=false;
        UserLog userLog = new UserLog();

        while(m.find()){
            if (!typeReceived){
                typeReceived=true;
                userLog.setLogType(LogType.getLogType(m.group(1)));
            } else {
                userLog.setUsername(m.group(1));
            }
        }
        return userLog;
    }

}
