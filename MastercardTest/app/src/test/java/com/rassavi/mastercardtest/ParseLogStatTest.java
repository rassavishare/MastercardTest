package com.rassavi.mastercardtest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ParseLogStatTest {

    @Test
    public void parseLogStatEmptyLog(){
        String log= null;
        Map expected = null;
        String username = "Bob";

        assertEquals(expected, JavaTest.parseLogStats2(log, username));
    }

    @Test
    public void parseLogStatEmptyUsername(){
        String log= "[Notice][Todd] has entered the room";
        Map expected = null;
        String username = null;

        assertEquals(expected, JavaTest.parseLogStats2(log, username));
    }

    @Test
    public void parseLogStatOneLine(){
        String log= "[Notice][Todd] has entered the room";
        String username = "Todd";

        Map expected = new HashMap();
        expected.put("Entries", 1);
        expected.put("Exits", 0);
        expected.put("StillHere", true);
        expected.put("TotalMessages", 0);


        assertEquals(expected, JavaTest.parseLogStats2(log, username));
    }

    @Test
    public void parseLogStatOneLineUsernameNoMatch(){
        String log= "[Notice][Todd] has entered the room";
        String username = "Bob";

        Map expected = new HashMap();
        expected.put("Entries", 0);
        expected.put("Exits", 0);
        expected.put("StillHere", false);
        expected.put("TotalMessages", 0);


        assertEquals(expected, JavaTest.parseLogStats2(log, username));
    }

    @Test
    public void parseLogStatMultipleLines(){
        final String log =
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
        String username = "Todd";

        Map expected = new HashMap();
        expected.put("Entries", 4);
        expected.put("Exits", 3);
        expected.put("StillHere", true);
        expected.put("TotalMessages", 1);

        assertEquals(expected, JavaTest.parseLogStats2(log, username));
    }

    @Test
    public void parseLogStatMultipleLines2(){
        final String log =
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
        String username = "Ben";

        Map expected = new HashMap();
        expected.put("Entries", 0);
        expected.put("Exits", 1);
        expected.put("StillHere", false);
        expected.put("TotalMessages", 2);

        assertEquals(expected, JavaTest.parseLogStats2(log, username));
    }

    @Test
    public void parseLogStatMultipleLinesUsernameNoMatch(){
        final String log =
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
        String username = "John";

        Map expected = new HashMap();
        expected.put("Entries", 0);
        expected.put("Exits", 0);
        expected.put("StillHere", false);
        expected.put("TotalMessages", 0);

        assertEquals(expected, JavaTest.parseLogStats2(log, username));
    }

}
