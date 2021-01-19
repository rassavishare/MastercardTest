package com.rassavi.mastercardtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MYDEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testFormatByPosition();
        testFindClosestPoints();
        testAnalyzeAccessLog();
        testParseLogStat();
    }

    private void testFormatByPosition(){
        String test= "Hello {0} {1} times! double {2} float {3} long {4}";
        Long num = 334L;
        String format=JavaTest.formatByPosition(test,"John",6, 3.48787, 4.02f, num);
        Log.d(TAG,format);
    }

    private void testFindClosestPoints(){
        final Map obj1 = new HashMap();
        final Map obj2 = new HashMap();
        final Map obj3 = new HashMap();
        final Map obj4 = new HashMap();
        final Map obj5 = new HashMap();
        final Map obj6 = new HashMap();
        obj1.put("name", "a"); obj1.put("x", 6); obj1.put("y", 6);
        obj2.put("name", "b"); obj2.put("x", 16); obj2.put("y", 2);
        obj3.put("name", "c"); obj3.put("x", 10); obj3.put("y", 10);
        obj4.put("name", "d"); obj4.put("x", 6); obj4.put("y", 7);
        obj5.put("name", "e"); obj5.put("x", 1); obj5.put("y", 2);
        obj6.put("name", "f"); obj6.put("x", 10); obj6.put("y", 12);
        final Map[] map = {obj1, obj2, obj3, obj4, obj5, obj6};
        List<String> result = JavaTest.findClosest(map);

        Log.d(TAG, "########################### "+ Arrays.toString(result.toArray()));
    }

    private void testAnalyzeAccessLog(){
        String log="192.168.2.20 - - [28/Jul/2006:10:27:10 -0300] \"GET /try/ HTTP/1.0\" 200 3395\n" +
                "192.168.2.20 - - [28/Jul/2006:10:27:10 -0300] \"GET /try/ HTTP/1.0\" 404 3395\n" +
                "127.0.0.1 - - [28/Jul/2006:10:22:04 -0300] \"GET / HTTP/1.0\" 200 2216\n" +
                "127.0.0.1 - - [28/Jul/2006:10:22:04 -0300] \"GET / HTTP/1.0\" 404 2216\n" +
                "127.0.0.1 - - [28/Jul/2006:10:22:04 -0300] \"GET / HTTP/1.0\" 200 2216\n" +
                "127.0.0.1 - - [28/Jul/2006:10:22:04 -0300] \"GET / HTTP/1.0\" 200 2216\n" +
                "127.0.0.1 - - [28/Jul/2006:10:27:32 -0300] \"GET /hidden/ HTTP/1.0\" 404 7218";

        Map<String, Double> map = JavaTest.analyzeAccessLog(log);

        for (Map.Entry<String, Double> entry: map.entrySet()){
            Log.d(TAG, " ++++++ IP: "+entry.getKey() + "  Failure Percentage: "+entry.getValue()+"%");
        }

    }

    private void testParseLogStat(){
        final String message =
                "[Notice][Todd] has entered the room\n"+
                "[Notice][Todd] has exited the room\n"+
	            "[Notice][Todd] has entered the room\n"+
	            "[Message][Todd] wrote a 7 character message\n"+
	            "[Message][Ben] wrote a 17 character message\n"+
	            "[Message][Ben] wrote a 2 character message\n"+
	            "[Notice][Todd] has exited the room\n"+
	            "[Notice][Ben] has exited the room\n"+
	            "[Notice][Todd] has entered the room\n"+
	            "[Notice][Todd] has exited the room\n"+
	            "[Notice][Todd] has entered the room";

        Map<String,Object> map = JavaTest.parseLogStats2(message,"Todd");

        for (Map.Entry<String,Object> entry: map.entrySet()){
            Log.d(TAG, " ++++++  "+entry.getKey() + "  value: "+entry.getValue());
        }
    }
}