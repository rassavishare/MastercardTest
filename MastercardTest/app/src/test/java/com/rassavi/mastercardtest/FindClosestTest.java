package com.rassavi.mastercardtest;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class FindClosestTest {

    @Test
    public void findClosestNoPointNull(){
        Map[] map= null;
        assertEquals(null, JavaTest.findClosest(map));
    }

    @Test
    public void findClosestNoPointZeroLengthArray(){
        Map[] map= new Map[0];
        assertEquals(null, JavaTest.findClosest(map));
    }

    @Test
    public void findClosestOnlyOnePoint(){
        final Map obj1 = new HashMap();
        obj1.put("name", "a"); obj1.put("x", 1); obj1.put("y", 1);
        final Map[] map = {obj1};

        assertEquals(null, JavaTest.findClosest(map));
    }

    @Test
    public void findClosestTwoPoints(){
        final Map obj1 = new HashMap();
        final Map obj2 = new HashMap();
        obj1.put("name", "a"); obj1.put("x", 1); obj1.put("y", 1);
        obj2.put("name", "b"); obj2.put("x", 1); obj2.put("y", 2);
        final Map[] map = {obj1,obj2};

        List expected = Arrays.asList(new String[]{"a","b"});

        assertEquals(expected, JavaTest.findClosest(map));
    }

    @Test
    public void findClosestThreePoints(){
        final Map obj1 = new HashMap();
        final Map obj2 = new HashMap();
        final Map obj3 = new HashMap();
        obj1.put("name", "a"); obj1.put("x", 1); obj1.put("y", 1);
        obj2.put("name", "b"); obj2.put("x", 9); obj2.put("y", 8);
        obj3.put("name", "c"); obj3.put("x", 10); obj3.put("y", 10);
        final Map[] map = {obj1,obj2,obj3};

        List expected = Arrays.asList(new String[]{"b","c"});

        assertEquals(expected, JavaTest.findClosest(map));
    }

    @Test
    public void findClosestPoints(){
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

        List expected = Arrays.asList(new String[]{"a","d"});

        assertEquals(expected, JavaTest.findClosest(map));
    }



}
