package com.rassavi.mastercardtest.comparators;

import java.util.Comparator;
import java.util.Map;

public class DistanceComparator implements Comparator<Map> {

    private Map<String, Object> object;

    public DistanceComparator(Map object) {
        this.object = object;
    }

    @Override
    public int compare(Map o1, Map o2) {
        double dist1 = calculateDistance(object, o1);
        double dist2 = calculateDistance(object, o2);
        return Double.compare(dist1,dist2);
    }

    public static double calculateDistance(Map o1, Map o2) {
        double diffX= Math.pow( new Double( o1.get("x").toString()) -  new Double(o2.get("x").toString()),2);
        double diffY= Math.pow(  new Double( o1.get("y").toString()) -  new Double(o2.get("y").toString()),2);
        return Math.sqrt(diffX + diffY);
    }


}
