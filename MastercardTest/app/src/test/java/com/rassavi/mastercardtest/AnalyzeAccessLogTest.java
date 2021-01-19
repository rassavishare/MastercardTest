package com.rassavi.mastercardtest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AnalyzeAccessLogTest {

    @Test
    public void analyzeAccessLogEmptyLog(){
        String log= null;
        Map expected = null;

        assertEquals(expected, JavaTest.analyzeAccessLog(log));
    }

    @Test
    public void analyzeAccessLogOneLine(){
        String log= "192.168.2.20 - - [28/Jul/2006:10:27:10 -0300] \"GET /try/ HTTP/1.0\" 200 3395";
        Map expected = new HashMap();
        expected.put("192.168.2.20",0.0);

        assertEquals(expected, JavaTest.analyzeAccessLog(log));
    }

    @Test
    public void analyzeAccessLogOneLineFailure(){
        String log= "192.168.2.20 - - [28/Jul/2006:10:27:10 -0300] \"GET /try/ HTTP/1.0\" 404 3395";
        Map expected = new HashMap();
        expected.put("192.168.2.20",1.0);

        assertEquals(expected, JavaTest.analyzeAccessLog(log));
    }

    @Test
    public void analyzeAccessLogOneLineOtherResponseCode(){
        //Response Code 300
        String log= "192.168.2.20 - - [28/Jul/2006:10:27:10 -0300] \"GET /try/ HTTP/1.0\" 300 3395";
        Map expected = new HashMap();
        expected.put("192.168.2.20",0.0);

        assertEquals(expected, JavaTest.analyzeAccessLog(log));
    }

    @Test
    public void analyzeAccessLogThreeLines(){
        String log= "192.168.2.20 - - [28/Jul/2006:10:27:10 -0300] \"GET /try/ HTTP/1.0\" 200 3395\n" +
                "127.0.0.1 - - [28/Jul/2006:10:22:04 -0300] \"GET / HTTP/1.0\" 200 2216\n"+
                "127.0.0.1 - - [28/Jul/2006:10:27:32 -0300] \"GET /hidden/ HTTP/1.0\" 404 7218"
                ;
        Map expected = new HashMap();
        expected.put("192.168.2.20",0.0);
        expected.put("127.0.0.1",0.5);

        assertEquals(expected, JavaTest.analyzeAccessLog(log));
    }

    @Test
    public void analyzeAccessLogCoupleLines(){
        String log="192.168.2.20 - - [28/Jul/2006:10:27:10 -0300] \"GET /try/ HTTP/1.0\" 200 3395\n" +
                "192.168.2.20 - - [28/Jul/2006:10:27:10 -0300] \"GET /try/ HTTP/1.0\" 404 3395\n" +
                "127.0.0.1 - - [28/Jul/2006:10:22:04 -0300] \"GET / HTTP/1.0\" 200 2216\n" +
                "127.0.0.1 - - [28/Jul/2006:10:22:04 -0300] \"GET / HTTP/1.0\" 404 2216\n" +
                "127.0.0.1 - - [28/Jul/2006:10:22:04 -0300] \"GET / HTTP/1.0\" 200 2216\n" +
                "127.0.0.1 - - [28/Jul/2006:10:22:04 -0300] \"GET / HTTP/1.0\" 200 2216\n" +
                "127.0.0.1 - - [28/Jul/2006:10:27:32 -0300] \"GET /hidden/ HTTP/1.0\" 404 7218";
        Map expected = new HashMap();
        expected.put("192.168.2.20",0.5);
        expected.put("127.0.0.1",0.4);

        assertEquals(expected, JavaTest.analyzeAccessLog(log));
    }

}
