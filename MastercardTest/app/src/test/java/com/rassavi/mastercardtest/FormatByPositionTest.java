package com.rassavi.mastercardtest;

import org.junit.Test;
import static org.junit.Assert.*;

public class FormatByPositionTest {

    @Test
    public void formatByPositionNoArguments(){
        String str= "Hello";
        String expected= "Hello";
        assertEquals(expected, JavaTest.formatByPosition(str,null));
    }

    @Test
    public void formatByPositionOneStringArgument(){
        String str= "Hello {0}";
        String expected= "Hello John";
        assertEquals(expected, JavaTest.formatByPosition(str,"John"));
    }

    @Test
    public void formatByPositionOneStringOneIntegerArgument(){
        String str= "Hello {0} {1} times!";
        String expected= "Hello world 5 times!";
        assertEquals(expected, JavaTest.formatByPosition(str,"world",5));
    }

    @Test
    public void formatByPositionMixedArgument(){
        String str= "Hello {0} {1} times! double {2} long {3} float {4} boolean {5}";
        String expected= "Hello John 6 times! double 3.442 long 334 float 5.92 boolean false";

        int numInt = 6;
        double numDouble = 3.442;
        Long numLong = 334L;
        float numFloat= 5.92f;

        assertEquals(expected, JavaTest.formatByPosition(str,"John",numInt,numDouble, numLong,numFloat,false));
    }

}
